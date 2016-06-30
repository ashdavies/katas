package com.chaos.fizzbuzz;

public class ModulosRule implements Rule {
    private final int expectation;
    private final String output;

    public ModulosRule(int expectation, String output) {
        this.expectation = expectation;
        this.output = output;
    }

    @Override
    public String run(int value) {
        return null;
    }
}
