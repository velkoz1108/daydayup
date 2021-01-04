package com.eden.springbootendpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootEndpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEndpointApplication.class, args);
    }

}
