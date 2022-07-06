package com.example.kakfademo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class KakfaDemoApplicationTests {




    static class Producer extends Thread {
        private final KafkaProducer<Integer, String> producer;
        private final String topic;

        public Producer(String topic) {
            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "81.68.164.7:9092");
            properties.put(ProducerConfig.CLIENT_ID_CONFIG, "practice-producer");
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            producer = new KafkaProducer<Integer, String>(properties);
            this.topic = topic;
        }

        @Override
        public void run() {
            int num = 0;
            while (num < 50) {
                String msg = "pratice test message:" + num;
                try {
                    Object res = producer.send(new ProducerRecord<Integer, String>(topic, num, msg)).get();
                    System.out.println("-------------------------------"+res);
                    TimeUnit.SECONDS.sleep(2);
                    num++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            new Producer("test").start();
        }
    }


    public static class Consumer extends Thread {
        private final KafkaConsumer<Integer, String> consumer;
        private final String topic;

        public Consumer(String topic) {
            Properties properties = new Properties();
            properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "81.68.164.7:9092");
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, "practice-consumer");
            properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            //设置 offset自动提交
            properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
            // 自动提交间隔时间
            properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
            properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
            properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            //对于 当前groupid来说，消息的offset从最早的消息开始消费
            consumer = new KafkaConsumer<>(properties);
            this.topic = topic;
        }

        @Override
        public void run() {
            while (true) {
                consumer.subscribe(Collections.singleton(this.topic));
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
                records.forEach(record -> {
                    System.out.println("-------------------"+record.key() + " " + record.value() + " -> offset:" + record.offset());
                });
            }
        }

        public static void main(String[] args) {
            new Consumer("test").start();
        }

    }

}
