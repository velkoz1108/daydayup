package com.example.producer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class HeartBeatProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${heart-beat.topid}")
    private String topic;

    public void send(String sn) {
        log.info("编号：{} 发送心跳",sn);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;        // 实例化模板对象
        String newDay = sdf.format(date);
        kafkaTemplate.send(topic, sn, newDay);
        log.info("编号：{} 发送心跳完成",sn);
    }
}
