package com.chaos.bank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BalanceTest {
    private Balance balance;

    @Before
    public void setUp() {
        balance = new Balance();
    }

    @Test
    public void assertZeroStartBalance() {
        assertEquals(0, balance.getAmount());
    }

    @Test
    public void assertStartBalance() {
        Balance balance = new Balance(BigInteger.ONE);
        assertEquals(BigInteger.ONE, balance.getAmount());
    }
}
