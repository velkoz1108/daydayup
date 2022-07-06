package com.example.consumer.mq;

import com.example.consumer.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TerminalSyncConsumer {


    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${server.port}")
    private String port;


    @KafkaListener(topics = {"${terminal-sync.topid}"})
    public void listener(ConsumerRecord record) {
        Optional<?> msg = Optional.ofNullable(record.value());
        if (msg.isPresent()) {
            String value = (String) msg.get();
            String key = (String) record.key();
            //
            log.info("port:{} ----->  key:{},value:{}",port,key,value);
        }
    }
}
