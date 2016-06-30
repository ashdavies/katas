package com.chaos.bank.transaction;

import com.chaos.bank.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DepositTransactionTest {
    private static final LocalDateTime now = LocalDateTime.now();

    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new DepositTransaction(BigInteger.ONE, now);
    }

    @Test
    public void assertTransactionAffectsAmount() throws Exception {
        assertEquals(BigInteger.valueOf(6), transaction.affect(BigInteger.valueOf(5)));
    }

    @Test
    public void assertTransactionGetsAmount() throws Exception {
        assertEquals(BigInteger.ONE, transaction.getAmount());
    }

    @Test
    public void assertTransactionGetsTime() throws Exception {
        assertEquals(now, transaction.getTime());
    }
}
