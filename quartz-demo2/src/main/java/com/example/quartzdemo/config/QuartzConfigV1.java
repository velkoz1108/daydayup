package com.example.quartzdemo.config;

import com.example.quartzdemo.Job.MyJop;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 单机版
* */

//@Configuration
public class QuartzConfigV1 {

    private static final String ID = "SUMMERDAY";

    @Bean
    public JobDetail jobDetail1() {
        return JobBuilder.newJob(MyJop.class)
                .withIdentity(ID + " 01")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger1() {
        CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1())
                .withIdentity(ID + " 01Trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
