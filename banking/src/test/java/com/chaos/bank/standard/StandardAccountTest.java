package com.chaos.bank.standard;

import com.chaos.bank.Account;
import com.chaos.bank.Ledger;
import com.chaos.bank.Printer;
import com.chaos.bank.exception.NoFundsException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(MockitoJUnitRunner.class)
public class StandardAccountTest {
    private Account account;

    @Mock
    Ledger ledger;

    @Mock
    Printer printer;

    @Before
    public void setUp() throws Exception {
        account = new StandardAccount(ledger);
    }

    @Test
    @Ignore
    public void acceptanceTest() throws Exception {
        account.deposit(BigInteger.valueOf(1000), LocalDateTime.of(LocalDate.of(2012, 1, 10), LocalTime.now()));
        account.deposit(BigInteger.valueOf(2000), LocalDateTime.of(LocalDate.of(2012, 1, 13), LocalTime.now()));
        account.withdraw(BigInteger.valueOf(1000), LocalDateTime.of(LocalDate.of(2012, 1, 14), LocalTime.now()));

        Mockito.verify(printer).print("date || credit || debit || balance");
        Mockito.verify(printer).print("14/01/2012 || || 500.00 || 2500.00");
        Mockito.verify(printer).print("13/01/2012 || 2000.00 || || 3000.00");
        Mockito.verify(printer).print("10/01/2012 || 1000.00 || || 1000.00");
    }

    @Test
    public void assertWithdrawal() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        Mockito.when(ledger.getBalance()).thenReturn(BigInteger.ONE);
        Mockito.when(ledger.getAllowance()).thenReturn(BigInteger.ZERO);

        account.withdraw(BigInteger.ONE, now);
        Mockito.verify(ledger).withdraw(BigInteger.ONE, now);
    }

    @Test
    public void assertDeposit() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        account.deposit(BigInteger.ONE, now);
        Mockito.verify(ledger).deposit(BigInteger.ONE, now);
    }

    @Test(expected = NoFundsException.class)
    public void assertWithdrawalBelowBalanceException() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        Mockito.when(ledger.getBalance()).thenReturn(BigInteger.ZERO);
        Mockito.when(ledger.getAllowance()).thenReturn(BigInteger.ZERO);

        account.withdraw(BigInteger.ONE, now);
    }
}
