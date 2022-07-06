package com.example.producer.controller;

import com.example.producer.mq.HeartBeatProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HeartBeatController {

    @Autowired
    private HeartBeatProducer heartBeatProducer;

    @GetMapping("/heartBeat")
    public String heartBeat(@RequestParam("sn") String sn){
        heartBeatProducer.send(sn);
        return "success";
    }
}
