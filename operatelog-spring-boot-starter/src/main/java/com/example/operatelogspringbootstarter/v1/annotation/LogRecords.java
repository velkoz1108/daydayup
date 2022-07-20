package com.example.operatelogspringbootstarter.v1.annotation;


import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecords {
    LogRecord[] value();
}
