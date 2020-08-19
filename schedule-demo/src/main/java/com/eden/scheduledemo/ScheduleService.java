package com.eden.scheduledemo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ScheduleService {

    //    @PostConstruct
//    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    @Scheduled(cron = "*/5 * * * * *")
    public void init() {
        System.out.println("Time --> " + System.currentTimeMillis());
    }
}
