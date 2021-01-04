package com.eden.springbootendpoint;


import org.springframework.boot.actuate.endpoint.Endpoint;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MemEndpoint implements Endpoint {

    private List<MemStatus> status;

    public MemEndpoint(List<MemStatus> status) {
        this.status = status;
    }

    @Override
    public String getId() {
        return "memEndpoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public Object invoke() {
        if (status == null || status.isEmpty()) {
            return "No thing";
        }
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        for (MemStatus memStatus : status) {
            for (Map.Entry<String, Object> entry : memStatus.getStatus().entrySet()) {


                List<Map<String, Object>> collectList = result.get(entry.getKey());
                if (collectList == null) {
                    collectList = new LinkedList<Map<String, Object>>();
                    result.put(entry.getKey(), collectList);
                }

                Map<String, Object> collect = new HashMap<>();
                collect.put("date", memStatus.getDate());
                collect.put(entry.getKey(), entry.getValue());

                collectList.add(collect);
            }
        }
        return result;
    }
}
