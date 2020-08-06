package com.eden.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyExecutionAspect {

    @Pointcut("execution(* com.eden.aopdemo.obj.*.testExecution(..))")
    private void myAspect2() {
        // Do nothing
    }

    @Before(value = "myAspect2()")
    public void methodBefore(JoinPoint joinPoint) {
        System.out.println();
        System.out.println("--- execution start ---");

        System.out.println(joinPoint.toString());

        System.out.println("--- execution end ---");
        System.out.println();
    }

}
