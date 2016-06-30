package com.chaos.bank.transaction;

import com.chaos.bank.Transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;

public abstract class BaseTransaction implements Transaction {
    private final BigInteger amount;
    private final LocalDateTime time;

    public BaseTransaction(BigInteger amount, LocalDateTime time) {
        this.amount = amount;
        this.time = time;
    }

    @Override
    public BigInteger getAmount() {
        return amount;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }
}
