package com.eden.wechatrobot;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wehcat-robot-api", url = "https://qyapi.weixin.qq.com")
public interface WechatRobotApi {

    @PostMapping(path = "/cgi-bin/webhook/send")
    String sendMessage(@RequestParam("key") String key,
                       @RequestBody JSONObject message);
}
