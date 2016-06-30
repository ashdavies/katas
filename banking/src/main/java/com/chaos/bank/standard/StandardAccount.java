package com.chaos.bank.standard;

import com.chaos.bank.*;
import com.chaos.bank.exception.NoFundsException;

import java.math.BigInteger;
import java.time.LocalDateTime;

public final class StandardAccount implements Account {
    private final Ledger ledger;

    public StandardAccount(Ledger ledger) {
        this.ledger = ledger;
    }

    @Override
    public void withdraw(BigInteger amount, LocalDateTime time) {
        if (ledger.getBalance().add(ledger.getAllowance()).compareTo(amount) < 0) {
            throw new NoFundsException();
        }

        ledger.withdraw(amount, time);
    }

    @Override
    public void deposit(BigInteger amount, LocalDateTime time) {
        ledger.deposit(amount, time);
    }

    @Override
    public void print(Printer printer) {
        throw new UnsupportedOperationException();
    }
}
