package com.chaos.bank.standard;

import com.chaos.bank.Ledger;
import com.chaos.bank.transaction.DepositTransaction;
import com.chaos.bank.transaction.WithdrawTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StandardLedgerTest {
    private Ledger ledger;

    @Before
    public void setUp() throws Exception {
        ledger = new StandardLedger();
    }

    @Test
    public void assertWithdrawTransaction() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        ledger.withdraw(BigInteger.TEN, now);

        assertEquals(WithdrawTransaction.class, ledger.getTransactions().get(0).getClass());
        assertEquals(BigInteger.TEN, ledger.getTransactions().get(0).getAmount());
        assertEquals(now, ledger.getTransactions().get(0).getTime());
    }

    @Test
    public void assertDepositTransaction() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        ledger.deposit(BigInteger.ONE, now);

        assertEquals(DepositTransaction.class, ledger.getTransactions().get(0).getClass());
        assertEquals(BigInteger.ONE, ledger.getTransactions().get(0).getAmount());
        assertEquals(now, ledger.getTransactions().get(0).getTime());
    }

    @Test
    public void assertCalculateBalance() throws Exception {
        ledger.deposit(BigInteger.valueOf(14), LocalDateTime.now());
        ledger.withdraw(BigInteger.valueOf(3), LocalDateTime.now());
        ledger.withdraw(BigInteger.valueOf(2), LocalDateTime.now());

        assertEquals(BigInteger.valueOf(9), ledger.getBalance());
    }

    @Test
    public void assertStandardAllowance() throws Exception {
        assertEquals(BigInteger.TEN, ledger.getAllowance());
    }

    @Test
    public void assertGetTransactionList() throws Exception {
        ledger.deposit(BigInteger.ONE, LocalDateTime.now());
        ledger.deposit(BigInteger.ONE, LocalDateTime.now());

        assertEquals(2, ledger.getTransactions().size());
    }
}
