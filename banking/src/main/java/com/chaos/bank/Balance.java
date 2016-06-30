package com.chaos.bank;

import java.math.BigInteger;
import java.util.List;

public class Balance {
    private BigInteger amount;

    public Balance() {
        this(BigInteger.ZERO);
    }

    public Balance(BigInteger amount) {
        this.amount = amount;
    }

    public void affect(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            amount = transaction.affect(amount);
        }
    }

    public BigInteger getAmount() {
        return amount;
    }

    public static BigInteger from(List<Transaction> transactions) {
        Balance balance = new Balance();
        balance.affect(transactions);
        return balance.getAmount();
    }
}
