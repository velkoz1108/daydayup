package com.eden.springeventdemo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MyEventPublisher {

    ApplicationEventPublisher eventPublisher;

    public MyEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publish(String msg) {
        System.out.println("publish start --> " + System.currentTimeMillis());
        eventPublisher.publishEvent(new MyEventObject(this, UUID.randomUUID().toString(), msg));
        System.out.println("publish end --> " + System.currentTimeMillis());
    }

    public void publish2(String msg) {
        System.out.println("publish2 start --> " + System.currentTimeMillis());
        eventPublisher.publishEvent(new MyAnotherEventObject(this, UUID.randomUUID().toString(), msg, msg));
        System.out.println("publish2 end --> " + System.currentTimeMillis());
    }
}
