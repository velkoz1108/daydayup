package com.eden.springbootendpoint;

import java.util.Date;
import java.util.Map;

public class MemStatus {

    private Date date;

    private Map<String, Object> status;

    public MemStatus(Date date, Map<String, Object> status) {
        this.date = date;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }
}
