package com.chaos.fibonacci;

public class RecursiveFibonacciGenerator extends FibonacciGenerator {
    public RecursiveFibonacciGenerator(final long limit) {
        super(limit);
    }

    @Override
    public Long calculate(final int number) {
        if (number <= 0) {
            return 0l;
        }

        else if (number < 3) {
            return 1l;
        }

        else {
            return this.calculate(number - 1) + this.calculate(number - 2);
        }
    }
}
