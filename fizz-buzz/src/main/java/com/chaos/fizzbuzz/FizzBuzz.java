package com.chaos.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz implements Runnable {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    private final List<Rule> rules = new ArrayList<>();

    public FizzBuzz() {
        rules.add(new ModulusRule(15, FIZZ + BUZZ));
        rules.add(new ModulusRule(5, BUZZ));
        rules.add(new ModulusRule(3, FIZZ));
    }

    @Override
    public String run(int value) {
        for (Rule rule : rules) {
            String result = rule.run(value);

            if (result != null) {
                return result;
            }
        }

        return String.valueOf(value);
    }
}
