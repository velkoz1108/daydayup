package com.example.dynamicthreadpool.v1;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.dynamicthreadpool.v1.config.DtpProperties;
import com.example.dynamicthreadpool.v1.config.ThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/*
* 1、验证获取nacos里的数据
* 2、验证更新程序不做任何处理，更新nacos配置里的值，程序里的值能否刷新  使用@RefreshScope注解刷新
*
* */
@RestController
//@RefreshScope
@Slf4j
public class TestController {

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;


    @Autowired
    private DtpProperties dtpProperties;

    @Autowired
    private DynamicThreadPoolManager dynamicThreadPoolManager;

    @GetMapping("/test")
    public String test() {
        log.info("test-----{}",dtpProperties);
        List<ThreadPoolProperties> lists = dtpProperties.getExecutors();
        lists.forEach(threadPoolProperties -> {
            log.info("threadPoolName:{}",threadPoolProperties.getThreadPoolName());
            log.info("corePoolSize:{}",threadPoolProperties.getCorePoolSize());
            log.info("maximumPoolSize:{}",threadPoolProperties.getMaximumPoolSize());
            log.info("keepAliveTime:{}",threadPoolProperties.getKeepAliveTime());
            log.info("queueCapacity:{}",threadPoolProperties.getQueueCapacity());
        });
        return "username：" + this.username + "</br>" + "password：" + this.password;
    }

    @GetMapping("/testThreadPoolExecutor")
    public String testThreadPoolExecutor(@RequestParam String threadPoolName) {
        log.info("testThreadPoolExecutor-----");
        ThreadPoolExecutor threadPoolExecutor = dynamicThreadPoolManager.getThreadPoolExecutor(threadPoolName);
        String res = "corePoolSize："+ threadPoolExecutor.getCorePoolSize() +"</br>"
                + "maximumPoolSize：" +threadPoolExecutor.getMaximumPoolSize() +"</br>"
                + "keepAliveTime：" +threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS) +"</br>"
                + "workQueue：" +threadPoolExecutor.getQueue() +"</br>"
                + "handler："+threadPoolExecutor.getRejectedExecutionHandler() ;
        return res;
    }

}
