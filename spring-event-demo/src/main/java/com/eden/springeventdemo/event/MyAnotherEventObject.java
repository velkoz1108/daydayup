package com.eden.springeventdemo.event;

import org.springframework.context.ApplicationEvent;

public class MyAnotherEventObject extends ApplicationEvent {
    private String eventId;
    private String eventMsg;
    private String eventName;

    public MyAnotherEventObject(Object source, String eventId, String eventMsg, String eventName) {
        super(source);
        this.eventId = eventId;
        this.eventMsg = eventMsg;
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return "MyAnotherEventObject{" +
                "eventId='" + eventId + '\'' +
                ", eventMsg='" + eventMsg + '\'' +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}
