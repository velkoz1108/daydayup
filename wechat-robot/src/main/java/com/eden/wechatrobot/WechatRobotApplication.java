package com.eden.wechatrobot;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableFeignClients(basePackages = "com.eden.wechatrobot")
@SpringBootApplication
public class WechatRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatRobotApplication.class, args);
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject test(HttpServletRequest request, String echo) {
        System.out.println(request.getRequestURL());
        JSONObject obj = new JSONObject();
        obj.put("code", "7777");
        obj.put("msg", "from 8097 " + echo);
        return obj;
    }
}
