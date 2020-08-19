package com.example.hao;

import org.apache.dubbo.config.annotation.Reference;
import org.example.hao.AnnotationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerWebApplicationTests {

    @Reference
    private AnnotationService annotationService;


    @Test
    void contextLoads() {
        System.out.println(annotationService.sayHello("TOK"));

    }

}
