package com.eden.springeventdemo;

import com.eden.springeventdemo.event.MyEventPublisher;
import com.eden.springeventdemo.guava.GuavaEventListener;
import com.eden.springeventdemo.guava.MyGuavaEvent;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SpringBootApplication
@EnableAsync
public class SpringEventDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventDemoApplication.class, args);


    }

    @Autowired
    private MyEventPublisher myEventPublisher;

    @GetMapping("/publishEvent")
    public String publishEvent(String msg) {
        myEventPublisher.publish(msg);
        return "success";
    }


    @GetMapping("/publishEvent2")
    public String publishEvent2(String msg) {
        myEventPublisher.publish2(msg);
        return "success";
    }

    @GetMapping("/guavaEvent")
    public String guavaEvent(String msg) {
        EventBus eventBus = new EventBus();

        eventBus.register(new GuavaEventListener());

        eventBus.post(new MyGuavaEvent(UUID.randomUUID().toString(), msg));

        return "success";
    }

}
