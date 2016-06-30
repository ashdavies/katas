package com.chaos.bank;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface Ledger {
    void withdraw(BigInteger amount, LocalDateTime date);

    void deposit(BigInteger amount, LocalDateTime date);

    BigInteger getBalance();

    BigInteger getAllowance();

    List<Transaction> getTransactions();
}
