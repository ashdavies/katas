package com.chaos.fibonacci;

import android.util.SparseArray;

public class CachedIteratorFibonacciGenerator extends IteratorFibonacciGenerator {
    private final SparseArray<Long> cache = new SparseArray<>();

    public CachedIteratorFibonacciGenerator(final long limit) {
        super(limit);
    }

    @Override
    public Long calculate(final int number) {
        Long result = this.cache.get(number);

        if (result == null) {
            result = super.calculate(number);
            this.cache.put(number, result);
        }

        return result;
    }
}
