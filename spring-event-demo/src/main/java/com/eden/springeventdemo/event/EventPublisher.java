package com.eden.springeventdemo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventPublisher {

    ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publish(String msg) {
        System.out.println("publish start --> " + System.currentTimeMillis());
        eventPublisher.publishEvent(new MyEventObject(this, UUID.randomUUID().toString(), msg));
        System.out.println("publish end --> " + System.currentTimeMillis());
    }
}
