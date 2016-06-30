package com.chaos.fibonacci;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecursiveFibonacciGeneratorTest {
    private static final long LIMIT = Long.MAX_VALUE;

    private FibonacciGenerator generator;

    @Before
    public void setUp() {
        this.generator = new RecursiveFibonacciGenerator(LIMIT);
    }

    @Test
    public void assertFirst() {
        final long result = this.generator.calculate(0);
        assertEquals(0, result);
    }

    @Test
    public void assertSecond() {
        final long result = this.generator.calculate(1);
        assertEquals(1, result);
    }

    @Test
    public void assertThird() {
        final long result = this.generator.calculate(2);
        assertEquals(1, result);
    }

    @Test
    public void assertFourth() {
        final long result = this.generator.calculate(3);
        assertEquals(2, result);
    }

    @Test
    public void assertFifth() {
        final long result = this.generator.calculate(4);
        assertEquals(3, result);
    }

    @Test
    public void assertSixth() {
        final long result = this.generator.calculate(5);
        assertEquals(5, result);
    }

    @Test
    public void assertSeventh() {
        final long result = this.generator.calculate(6);
        assertEquals(8, result);
    }

    @Test
    public void assertEighth() {
        final long result = this.generator.calculate(7);
        assertEquals(13, result);
    }

    @Test
    public void assertNinth() {
        final long result = this.generator.calculate(8);
        assertEquals(21, result);
    }
}
