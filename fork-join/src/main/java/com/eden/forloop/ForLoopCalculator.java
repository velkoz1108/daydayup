package com.eden.forloop;

import com.eden.Calculate;

public class ForLoopCalculator implements Calculate {
    public long sumUp(long[] numbers) {
        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }
}
