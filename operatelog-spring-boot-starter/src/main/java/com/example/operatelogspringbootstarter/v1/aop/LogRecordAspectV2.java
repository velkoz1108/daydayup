package com.example.operatelogspringbootstarter.v1.aop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.operatelogspringbootstarter.v1.annotation.LogRecord;
import com.example.operatelogspringbootstarter.v1.context.LogRecordContext;
import com.example.operatelogspringbootstarter.v1.entity.LogRecordEntity;
import com.example.operatelogspringbootstarter.v1.function.CustomFunctionRegistrar;
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
import org.apache.commons.lang3.StringUtils;



@Component
@Aspect
@Slf4j
public class LogRecordAspectV2 {

    @Value("${spring.application.name}")
    private String applicationName; //从配置文件获得服务名


    private final ExpressionParser expressionParser = new SpelExpressionParser();

    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();


    @AfterReturning("@annotation(com.example.operatelogspringbootstarter.v1.annotation.LogRecord)|| @annotation(com.example.operatelogspringbootstarter.v1.annotation.LogRecords)")
    public void invoked(JoinPoint joinPoint){
        //获取方法
        Method method = this.getMethod(joinPoint);
//        LogRecord logRecordAnnotation = method.getAnnotation(LogRecord.class);
//        resolveExpress(method,logRecordAnnotation,joinPoint);

        LogRecord[] logRecordAnnotations = method.getAnnotationsByType(LogRecord.class);
        for(LogRecord logRecord : logRecordAnnotations){
            resolveExpress(method,logRecord,joinPoint);
        }

        LogRecordContext.clearContext();




    }

//    private void resolveExpress(Method method , LogRecord[] logRecord, JoinPoint joinPoint){
//        String businessIdSpel = logRecord.businessId();
//        String bizTypeSpel = logRecord.bizType();
//        String messageSpel = logRecord.message();
//        String businessId = businessIdSpel;
//        Method bizTypeMetod = null;
//        String bizType = "";
//
//        String message = messageSpel;
//
//        LogRecordEntity logRecordEntity = LogRecordEntity.builder().build();
//        // 从上下文获取当前操作的用户信息
//        logRecordEntity.setOperatorId("111");
//        logRecordEntity.setOperatorName("xxx");
//
//
//        Object[] arguments = joinPoint.getArgs();
//        log.info("resolveExpress arguments:{}",arguments);
//        String[] params = nameDiscoverer.getParameterNames(method);
//        log.info("resolveExpress params:{}",params);
//
////        EvaluationContext evaluationContext = new StandardEvaluationContext();
//        StandardEvaluationContext standardEvaluationContext = LogRecordContext.getContext();
//        CustomFunctionRegistrar.register(standardEvaluationContext);
//        if (params != null && params.length > 0){
//            for (int i = 0; i < arguments.length; i++) {
//                // 替换spel里的变量值为实际值， 比如 #user -->  user对象
//                standardEvaluationContext.setVariable(params[i],arguments[i]);
//            }
//        }
//
//        if(StringUtils.isNotBlank(businessIdSpel)){
//            businessId = expressionParser.parseExpression(businessIdSpel).getValue(standardEvaluationContext,String.class);
//        }
//
//        if(StringUtils.isNotBlank(bizTypeSpel)){
//            bizType = expressionParser.parseExpression(bizTypeSpel).getValue(standardEvaluationContext,String.class);
//            log.info("bizTypeSpel:{},bizType:{}",bizTypeSpel,bizType);
//
//        }
//
//        if(StringUtils.isNotBlank(message)){
//            message = expressionParser.parseExpression(messageSpel).getValue(standardEvaluationContext,String.class);
//        }
//
//        logRecordEntity.setBusinessId(businessId);
//        logRecordEntity.setBizType(bizType);
//        logRecordEntity.setMessage(message);
//
//        log.info("logRecordEntity:{}",logRecordEntity);
//
//
//
//
//    }


    private void resolveExpress(Method method , LogRecord logRecord, JoinPoint joinPoint){
        String businessIdSpel = logRecord.businessId();
        String bizTypeSpel = logRecord.bizType();
        String messageSpel = logRecord.message();
        String businessId = businessIdSpel;
        Method bizTypeMetod = null;
        String bizType = "";

        String message = messageSpel;

        LogRecordEntity logRecordEntity = LogRecordEntity.builder().build();
        // 从上下文获取当前操作的用户信息
        logRecordEntity.setOperatorId("111");
        logRecordEntity.setOperatorName("xxx");


        Object[] arguments = joinPoint.getArgs();
        log.info("resolveExpress arguments:{}",arguments);
        String[] params = nameDiscoverer.getParameterNames(method);
        log.info("resolveExpress params:{}",params);

//        EvaluationContext evaluationContext = new StandardEvaluationContext();
        StandardEvaluationContext standardEvaluationContext = LogRecordContext.getContext();
        CustomFunctionRegistrar.register(standardEvaluationContext);
        if (params != null && params.length > 0){
            for (int i = 0; i < arguments.length; i++) {
                // 替换spel里的变量值为实际值， 比如 #user -->  user对象
                standardEvaluationContext.setVariable(params[i],arguments[i]);
            }
        }

        if(StringUtils.isNotBlank(businessIdSpel)){
            businessId = expressionParser.parseExpression(businessIdSpel).getValue(standardEvaluationContext,String.class);
        }

        if(StringUtils.isNotBlank(bizTypeSpel)){
            bizType = expressionParser.parseExpression(bizTypeSpel).getValue(standardEvaluationContext,String.class);
            log.info("bizTypeSpel:{},bizType:{}",bizTypeSpel,bizType);

        }

        if(StringUtils.isNotBlank(message)){
            message = expressionParser.parseExpression(messageSpel).getValue(standardEvaluationContext,String.class);
        }

        logRecordEntity.setBusinessId(businessId);
        logRecordEntity.setBizType(bizType);
        logRecordEntity.setMessage(message);

        log.info("logRecordEntity:{}",logRecordEntity);




    }

    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method;
    }


}
