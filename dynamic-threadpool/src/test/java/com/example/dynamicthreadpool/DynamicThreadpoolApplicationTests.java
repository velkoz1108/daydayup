package com.example.dynamicthreadpool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicThreadpoolApplicationTests {



    @Test
    void contextLoads() {
        System.out.println(""+Runtime.getRuntime().availableProcessors());

    }


}

