package com.example.consumer.mq;

import com.example.consumer.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Optional;

@Component
@Slf4j
public class HeartBeatConsumer {

    @Autowired
    private RedisTemplate redisTemplate;




    @KafkaListener(topics = {"${heart-beat.topid}"})
    public void listener(ConsumerRecord record) {
        String dayRedisKey = null;
        String mouthRedisKey = null;
        Long offset = null;

        try {
            Optional<?> msg = Optional.ofNullable(record.value());
            if (msg.isPresent()) {
                String value = (String) msg.get();
                String key = (String) record.key();
                log.info("---value：{}，key：{}----", value, key);
                dayRedisKey = DateUtil.formatDate("yyyy-MM-dd", value);
                mouthRedisKey = DateUtil.formatDate("yyyy-MM", value);
                offset = Long.parseLong(key);
                log.info("---redisKey：{}，offset：{}----", dayRedisKey, offset);
                //todo reids bitmap
                Boolean aBoolean = redisTemplate.opsForValue().setBit(dayRedisKey, offset, true);
                log.info("日活 dayRedisKey:{},offset:{} 心跳:{}", dayRedisKey, offset, aBoolean == true ? "成功" : "失败");
                Boolean bBoolean = redisTemplate.opsForValue().setBit(mouthRedisKey, offset, true);
                log.info("mouthRedisKey:{},offset:{} 心跳:{}", mouthRedisKey, offset, bBoolean == true ? "成功" : "失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("dayRedisKey:{},mouthRedisKey:{},offset:{} 心跳异常", dayRedisKey, mouthRedisKey, offset);
        }
    }
}
