package com.example.dynamicthreadpool.v1.listener;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import com.example.dynamicthreadpool.v1.DynamicThreadPoolManager;
import com.example.dynamicthreadpool.v1.config.DtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class NacosCloudConfigUpdateListener {

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    @Autowired
    private DtpProperties dtpProperties;

    @Autowired
    private DynamicThreadPoolManager dynamicThreadPoolManager;


    @PostConstruct
    public void init() {
        initConfigUpdateListener();
    }

    public void initConfigUpdateListener() {
        ConfigService configService = nacosConfigProperties.configServiceInstance();
        Assert.hasText(dtpProperties.getNacosDataId(), "请配置kitty.threadpools.nacosDataId");
        Assert.hasText(dtpProperties.getNacosGroup(), "请配置kitty.threadpools.nacosGroup");

        try {
            configService.addListener(dtpProperties.getNacosDataId(), dtpProperties.getNacosGroup(), new AbstractListener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    new Thread(() -> dynamicThreadPoolManager.refreshThreadPoolExecutor(true)).start();
                    log.info("线程池配置有变化，刷新完成");
                }
            });
        } catch (NacosException e) {
            log.error("Nacos配置监听异常", e);
        }
    }


}
