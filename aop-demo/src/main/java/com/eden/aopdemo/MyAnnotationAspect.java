package com.eden.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAnnotationAspect {
    @Pointcut("@annotation(com.eden.aopdemo.EdenAnnotation)")
    private void myAspect() {
        // Do nothing
    }

    @Before(value = "myAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        System.out.println();
        System.out.println("--- @annotation start ---");

        System.out.println(joinPoint.toString());

        System.out.println("--- @annotation end ---");
        System.out.println();
    }

}
