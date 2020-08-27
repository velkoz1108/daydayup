package com.eden.springeventdemo.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SpringListener {

    /**
     * 使用spring的注解实现事件监听 监听的事件对象ApplicationContextEvent及其子类
     * 这里也是同步的
     *
     * @param event
     */
    @EventListener
    public void process(ApplicationEvent event) {
//        System.out.println("------------ ApplicationEvent start ------------");
//
//        System.out.println("event --> " + event);
//
//        System.out.println("------------ ApplicationEvent end ------------");
    }


    @EventListener
    public void process(MyEventObject event) {
        System.out.println("------------ MyEventObject start ------------");

        System.out.println("event --> " + event);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("------------ MyEventObject end ------------");
    }

    @Async
    @EventListener
    public void process(MyAnotherEventObject event) {
        System.out.println("------------ MyAnotherEventObject start ------------");

        System.out.println("event --> " + event);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------ MyAnotherEventObject end ------------");
    }

}
