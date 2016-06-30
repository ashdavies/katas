package com.chaos.fizzbuzz;

public class ModulusRule implements Rule {
    private final int expectation;
    private final String output;

    public ModulusRule(int expectation, String output) {
        this.expectation = expectation;
        this.output = output;
    }

    @Override
    public String run(int value) {
        if (value % expectation == 0) {
            return output;
        }

        return null;
    }
}
