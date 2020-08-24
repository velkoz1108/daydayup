package com.eden.springeventdemo;

import com.eden.springeventdemo.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAsync
public class SpringEventDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventDemoApplication.class, args);
    }

    @Autowired
    private EventPublisher eventPublisher;

    @GetMapping("/publishEvent")
    public String publishEvent(String msg) {
        eventPublisher.publish(msg);
        return "success";
    }
}
