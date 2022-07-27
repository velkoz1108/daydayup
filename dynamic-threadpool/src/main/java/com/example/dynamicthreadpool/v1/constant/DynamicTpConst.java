package com.example.dynamicthreadpool.v1.constant;

public class DynamicTpConst {

    private DynamicTpConst() {}

    // 动态线程池
    public static final String MAIN_PROPERTIES_PREFIX = "spring.dynamic.tp";

    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

}
