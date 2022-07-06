package com.example.kakfademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KakfaDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(KakfaDemoApplication.class, args);
        KafkaProducer kafkaProducer = context.getBean(KafkaProducer.class);
        for (int i = 0; i < 3; i++) {
            kafkaProducer.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
