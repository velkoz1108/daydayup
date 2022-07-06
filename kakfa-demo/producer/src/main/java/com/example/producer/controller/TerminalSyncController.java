package com.example.producer.controller;

import com.example.producer.entity.Terminal;
import com.example.producer.mq.TerminalSyncProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TerminalSyncController {

    @Autowired
    private TerminalSyncProducer terminalSyncProducer;


    @GetMapping("/terminalSync")
    public String heartBeat(@RequestBody Terminal terminal){
        // 获取sn xin
        log.info("TerminalSyncController terminal:{}", terminal);
        terminalSyncProducer.send(terminal);
        return "success";
    }


}
