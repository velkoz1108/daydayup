package com.eden.springeventdemo.guava;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 使用guava event bus
 */
public class GuavaEventListener {

    @Subscribe
    public void processEvent(MyGuavaEvent event) {
        System.out.println("-----------guava event start---------------");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("event -> " + event);

        System.out.println("-----------guava event end---------------");

    }
}
