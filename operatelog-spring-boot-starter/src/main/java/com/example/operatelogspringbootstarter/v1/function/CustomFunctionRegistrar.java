package com.example.operatelogspringbootstarter.v1.function;

import com.example.operatelogspringbootstarter.v1.annotation.LogRecordFunc;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@Component
public class CustomFunctionRegistrar implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    private static Map<String, Method> functionMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        //处理LogRecordFunc 描述的类和放到
        Map<String, Object> beanWithAnnotation =  applicationContext.getBeansWithAnnotation(LogRecordFunc.class);
        log.info("setApplicationContext beanWithAnnotation:{}",beanWithAnnotation);

        beanWithAnnotation.values()
                .forEach(
                        component -> {
                            log.info("setApplicationContext component:{}",component);
                            Method[] methods = component.getClass().getMethods();
                            LogRecordFunc classLogRecordFunc = component.getClass().getAnnotation(LogRecordFunc.class);
                            String prefixName = classLogRecordFunc.value();
                            if(StringUtils.isNotBlank(prefixName)){
                                prefixName = prefixName + "_";
                            }
                            if(methods.length > 0){
                                for(Method method : methods){
                                    if(method.isAnnotationPresent(LogRecordFunc.class)&& isStaticMethod(method)){
                                        LogRecordFunc methodLogRecordFunc = method.getAnnotation(LogRecordFunc.class);
                                        String name = StringUtils.isNotBlank(methodLogRecordFunc.value())? methodLogRecordFunc.value():method.getName();
                                        functionMap.put(prefixName+name, method);
                                        log.info("setApplicationContext name:{},method:{}",prefixName+name,method);
                                    }
                                }
                            }

                        }
                );
    }

    public static void register(StandardEvaluationContext standardEvaluationContext){
//        functionMap.forEach((key,value) -> {
//            log.info("key:{},method:{}",key,value.getName());
//            standardEvaluationContext.registerFunction(key,value);
//        });
        functionMap.forEach(standardEvaluationContext::registerFunction);

    }

    private static Boolean isStaticMethod(Method method){
        if(method==null){
            return false;
        }
        int modifiers = method.getModifiers();
        log.info("isStaticMethod modifiers:{}",modifiers);
        return Modifier.isStatic(modifiers);
    }
}
