package com.chaos.fibonacci;

public class IteratorFibonacciGenerator extends FibonacciGenerator {
    public IteratorFibonacciGenerator(final long limit) {
        super(limit);
    }

    @Override
    public Long calculate(final int number) {
        if (number <= 0) {
            return 0L;
        }

        else if (number < 3) {
            return 1L;
        }

        else {
            Long n1 = 1L, n2 = 1L, result = 1L;
            for (int i = 3; i <= number; i++) {
                result = n1 + n2;
                n1 = n2;
                n2 = result;
            }

            return result;
        }
    }
}
