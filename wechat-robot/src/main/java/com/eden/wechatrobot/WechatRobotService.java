package com.eden.wechatrobot;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WechatRobotService {

    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private WechatRobotApi wechatRobotApi;

    @GetMapping("/msg")
    public String sendMessage(String msg) {
        String key = wechatConfig.getKey();
        JSONObject message = new JSONObject();

        JSONObject content = new JSONObject();
        content.put("content", msg);

        message.put("text", content);
        message.put("msgtype", "text");
        return wechatRobotApi.sendMessage(key, message);
    }

}
