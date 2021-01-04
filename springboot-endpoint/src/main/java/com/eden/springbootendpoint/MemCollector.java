package com.eden.springbootendpoint;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemCollector {
    private int maxSize = 5;

    private List<MemStatus> status;

    public MemCollector(List<MemStatus> status) {
        this.status = status;
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void collect() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();

        Map<String, Object> memoryMap = new HashMap<>(2, 1);
        Date date = Calendar.getInstance().getTime();

        memoryMap.put("maxMemory", maxMemory);
        memoryMap.put("totalMemory", totalMemory);

        if (status.size() > maxSize) {
            status.remove(0);
        }
        status.add(new MemStatus(date, memoryMap));
    }
}
