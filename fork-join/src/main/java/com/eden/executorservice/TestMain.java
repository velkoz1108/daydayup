package com.eden.executorservice;

import com.eden.Calculate;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class TestMain {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculate calculator = new ExecutorServiceCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result); // 打印结果500500
    }

}
