package com.chaos.bank.transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class WithdrawTransaction extends BaseTransaction {
    public WithdrawTransaction(BigInteger amount, LocalDateTime time) {
        super(amount, time);
    }

    @Override
    public BigInteger affect(BigInteger amount) {
        return amount.subtract(getAmount());
    }
}
