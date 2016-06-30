package com.chaos.fizzbuzz;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FizzBuzzTest {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    private FizzBuzz fizzBuzz;

    @Before
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void assertClassCreation() {
        assertNotNull(fizzBuzz);
    }

    @Test
    public void assertRunnable() {
        assertTrue(com.chaos.fizzbuzz.Runnable.class.isAssignableFrom(FizzBuzz.class));
    }
    @Test
    public void assertInputOneEqualsOne() {
        assertEquals("1", fizzBuzz.run(1));
    }

    @Test
    public void assertInputThreeEqualsFizz() {
        assertEquals(FIZZ, fizzBuzz.run(3));
    }

    @Test
    public void assertInputFiveEqualsBuzz(){
        assertEquals(BUZZ, fizzBuzz.run(5));
    }

    @Test
    public void assertInputFifteenIsFizzBuzz() {
        assertEquals(FIZZ + BUZZ, fizzBuzz.run(15));
    }
}
