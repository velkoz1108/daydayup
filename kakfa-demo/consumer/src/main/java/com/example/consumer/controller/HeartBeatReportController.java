package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HeartBeatReportController {

    @Autowired
    RedisTemplate redisTemplate;


    /*日或月*/
    @GetMapping("/heartBeatReport")
    public String heartBeatReport(String key){
        Long i = (Long) redisTemplate.execute((RedisCallback) con -> con.bitCount(key.getBytes()));
        return String.valueOf(i);
    }


    @GetMapping("/weekHeartBeatReport")
    public String weekHeartBeatReport(String startDate , String endDate){


        return null;
    }

    @GetMapping("/monthHeartBeatReport")
    public String monthHeartBeatReport(){

        return null;
    }
}
