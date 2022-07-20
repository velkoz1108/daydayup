package com.example.operatelogspringbootstarter.v1.context;

import org.springframework.expression.spel.support.StandardEvaluationContext;

public class LogRecordContext {

    private static final ThreadLocal<StandardEvaluationContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static StandardEvaluationContext getContext() {
        return CONTEXT_THREAD_LOCAL.get() == null ? new StandardEvaluationContext() : CONTEXT_THREAD_LOCAL.get();
    }

    public static void putVariables(String key, String value){
        StandardEvaluationContext standardEvaluationContext = getContext();
        standardEvaluationContext.setVariable(key,value);
        CONTEXT_THREAD_LOCAL.set(standardEvaluationContext);
    }

    public static void clearContext() {
        CONTEXT_THREAD_LOCAL.remove();
    }


}
