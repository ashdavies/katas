package com.chaos.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class CachedIteratorFibonacciGenerator extends IteratorFibonacciGenerator {
    private final Map<Integer, Long> cache = new HashMap<>();

    public CachedIteratorFibonacciGenerator(long limit) {
        super(limit);
    }

    @Override
    public Long calculate(int number) {
        Long result = cache.get(number);

        if (result == null) {
            result = super.calculate(number);
            cache.put(number, result);
        }

        return result;
    }
}
