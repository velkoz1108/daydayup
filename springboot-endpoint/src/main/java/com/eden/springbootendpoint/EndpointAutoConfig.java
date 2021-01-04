package com.eden.springbootendpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EndpointAutoConfig {
    private List status = new ArrayList();

    @Bean
    public MemEndpoint memEndpoint() {
        return new MemEndpoint(status);
    }

    @Bean
    public MemCollector memCollector() {
        return new MemCollector(status);
    }
}
