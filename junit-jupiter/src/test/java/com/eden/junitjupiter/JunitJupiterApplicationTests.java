package com.eden.junitjupiter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JunitJupiterApplicationTests {

    private final Calculator calculator = new Calculator();



    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }

    @Test
    @Tag("myTag")
    void addition2() {
        assertEquals(2, calculator.add(1, 1));
    }

    @Test
    @Fast
    void addition3() {
        assertEquals(2, calculator.add(1, 1));
    }

    @FastTest
    void addition4() {
        assertEquals(2, calculator.add(1, 1));
    }
}
