package com.eden.springeventdemo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class EventListener implements ApplicationListener<MyEventObject> {

    @Async
    @Override
    public void onApplicationEvent(MyEventObject event) {
        System.out.println("listener start -> " + System.currentTimeMillis());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = event.toString();
        System.out.println("s = " + s);
        System.out.println("listener start -> " + System.currentTimeMillis());
        throw new RuntimeException("system error");
    }
}
