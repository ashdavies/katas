package com.chaos.bank.transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class DepositTransaction extends BaseTransaction {
    public DepositTransaction(BigInteger amount, LocalDateTime time) {
        super(amount, time);
    }

    @Override
    public BigInteger affect(BigInteger amount) {
        return amount.add(getAmount());
    }
}
