package com.example.producer.mq;

import com.alibaba.fastjson.JSONObject;
import com.example.producer.entity.Terminal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TerminalSyncProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${terminal-sync.topid}")
    private String topic;

    public void send(Terminal terminal) {
        log.info("终端：{} 同步",terminal.toString());
        kafkaTemplate.send(topic, terminal.getSn(), JSONObject.toJSONString(terminal));
        log.info("终端：{} 同步完成",terminal.getSn());
    }

}
