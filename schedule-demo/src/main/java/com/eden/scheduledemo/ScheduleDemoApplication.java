package com.eden.scheduledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling
public class ScheduleDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(ScheduleDemoApplication.class, args);
    }


}
