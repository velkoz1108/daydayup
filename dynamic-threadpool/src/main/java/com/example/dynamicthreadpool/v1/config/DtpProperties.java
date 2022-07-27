package com.example.dynamicthreadpool.v1.config;

import com.example.dynamicthreadpool.v1.constant.DynamicTpConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Data
//@Component
@ConfigurationProperties(prefix = DynamicTpConst.MAIN_PROPERTIES_PREFIX)
public class DtpProperties {

    /**
     * Nacos DataId, 监听配置修改用
     */
    private String nacosDataId;

    /**
     * Nacos Group, 监听配置修改用
     */
    private String nacosGroup;

    /**
     * ThreadPoolExecutor configs.
     */
    private List<ThreadPoolProperties> executors;


}
