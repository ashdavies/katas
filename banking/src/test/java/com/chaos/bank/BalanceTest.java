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
    public void setUp() throws Exception {
        balance = new Balance();
    }

    @Test
    public void assertZeroStartBalance() throws Exception {
        assertEquals(BigInteger.ZERO, balance.getAmount());
    }

    @Test
    public void assertStartBalance() throws Exception {
        Balance balance = new Balance(BigInteger.ONE);
        assertEquals(BigInteger.ONE, balance.getAmount());
    }
}
