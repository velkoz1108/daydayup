package com.eden.wechatrobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.eden.wechatrobot")
@SpringBootApplication
public class WechatRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatRobotApplication.class, args);
    }

}
