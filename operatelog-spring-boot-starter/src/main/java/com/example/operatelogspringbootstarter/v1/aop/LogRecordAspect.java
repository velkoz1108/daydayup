package com.example.operatelogspringbootstarter.v1.aop;


import com.example.operatelogspringbootstarter.v1.annotation.LogRecord;
import com.example.operatelogspringbootstarter.v1.entity.LogRecordEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Aspect
@Slf4j
public class LogRecordAspect {

    @Value("${spring.application.name}")
    private String applicationName; //从配置文件获得服务名


    private final ExpressionParser expressionParser = new SpelExpressionParser();

    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();


    @AfterReturning("@annotation(logRecord)")
    public void invoked(JoinPoint joinPoint, LogRecord logRecord){
        String content = logRecord.content();
        String businessId = logRecord.businessId();
        String bizType = logRecord.bizType();
        log.info("invoked content:{},businessId:{},bizType:{}",content,businessId,bizType);

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();


        LogRecordEntity logRecordEntity = LogRecordEntity.builder().build();
        // 从上下文获取当前操作的用户信息
        logRecordEntity.setOperatorId("111");
        logRecordEntity.setOperatorName("xxx");

        //设置服务名和接口名
        logRecordEntity.setApplicationName(applicationName);
        logRecordEntity.setInterfaceName(methodSignature.getDeclaringTypeName()+"."+methodSignature.getName());

        Method method = methodSignature.getMethod();
        String[] paramNames = nameDiscoverer.getParameterNames(method);
        log.info("invoked paramNames:{}",paramNames);

        EvaluationContext evaluationContext = new StandardEvaluationContext();
        //解析context
        if (paramNames != null && paramNames.length > 0){

            //获取方法参数值
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                evaluationContext.setVariable(paramNames[i],args[i]); // 替换spel里的变量值为实际值， 比如 #user -->  user对象
            }

            if(content.contains("#")){
                // 解析出实际的日志信息
                content = expressionParser.parseExpression(content).getValue(evaluationContext).toString();
                log.info("invoked content:{}",content);
            }


            businessId = expressionParser.parseExpression(businessId).getValue(evaluationContext, String.class);
            log.info("invoked businessId:{}",businessId);

            bizType = expressionParser.parseExpression(bizType).getValue(evaluationContext, String.class);
            log.info("invoked bizType:{}",bizType);

            logRecordEntity.setContext(content);
            logRecordEntity.setBusinessId(businessId);
            logRecordEntity.setBizType(bizType);
        }



        log.info(""+joinPoint.getSignature().getName()+" 记录操作日志@AfterReturning。。。logRecordEntity:{}",logRecordEntity);

    }


}
