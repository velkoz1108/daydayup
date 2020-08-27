package com.eden.springeventdemo.guava;

import java.io.Serializable;

public class MyGuavaEvent implements Serializable {
    private String eventId;
    private String eventMsg;

    @Override
    public String toString() {
        return "MyGuavaEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventMsg='" + eventMsg + '\'' +
                '}';
    }

    public MyGuavaEvent(String eventId, String eventMsg) {
        this.eventId = eventId;
        this.eventMsg = eventMsg;
    }
}
