package com.example.operatelogspringbootstarter.v1.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
@Documented
@Repeatable(LogRecords.class)
public @interface LogRecord {

    /*
    * 日志内容
    * */
    String message() default "";

    /*
     * 业务ID
     * */
    String businessId() default "";

    /*
     * 操作人
     * */
    String bizType() default "";
}
