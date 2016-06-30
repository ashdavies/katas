package com.chaos.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

class FizzBuzz implements Runnable {
    private final List<Rule> rules = new ArrayList<>();

    public FizzBuzz() {
        rules.add(0, new ModulosRule(3, "Fizz"));
        rules.add(1, new ModulosRule(5, "Buzz"));
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
