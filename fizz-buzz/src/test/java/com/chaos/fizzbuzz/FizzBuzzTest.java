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
    public void setUp() throws Exception {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void assertClassCreation() throws Exception {
        assertNotNull(fizzBuzz);
    }

    @Test
    public void assertRunnable() throws Exception {
        assertTrue(Runnable.class.isAssignableFrom(FizzBuzz.class));
    }

    @Test
    public void assertInputOneEqualsOne() throws Exception {
        assertEquals("1", fizzBuzz.run(1));
    }

    @Test
    public void assertInputTwoEqualsTwo() throws Exception {
        assertEquals("2", fizzBuzz.run(2));
    }

    @Test
    public void assertInputThreeEqualsFizz() throws Exception {
        assertEquals(FIZZ, fizzBuzz.run(3));
    }

    @Test
    public void assertInputFourEqualsFour() throws Exception {
        assertEquals("4", fizzBuzz.run(4));
    }

    @Test
    public void assertInputFiveEqualsBuzz() throws Exception {
        assertEquals(BUZZ, fizzBuzz.run(5));
    }

    @Test
    public void assertInputFifteenIsFizzBuzz() throws Exception {
        assertEquals(FIZZ + BUZZ, fizzBuzz.run(15));
    }

    @Test
    public void assertInputThirtyIsFizzBuzz() throws Exception {
        assertEquals(FIZZ + BUZZ, fizzBuzz.run(30));
    }
}
