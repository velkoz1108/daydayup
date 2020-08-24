package com.eden.springeventdemo.event;

import org.springframework.context.ApplicationEvent;

public class MyEventObject extends ApplicationEvent {
    private String eventId;
    private String eventMsg;

    public MyEventObject(Object source, String eventId, String eventMsg) {
        super(source);
        this.eventId = eventId;
        this.eventMsg = eventMsg;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    @Override
    public String toString() {
        return "MyEventObject{" +
                "eventId='" + eventId + '\'' +
                ", eventMsg='" + eventMsg + '\'' +
                '}';
    }
}
