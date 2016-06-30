package com.chaos.bank.standard;

import com.chaos.bank.Balance;
import com.chaos.bank.Ledger;
import com.chaos.bank.Transaction;
import com.chaos.bank.transaction.DepositTransaction;
import com.chaos.bank.transaction.BaseTransaction;
import com.chaos.bank.transaction.WithdrawTransaction;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StandardLedger implements Ledger {
    private static final BigInteger STANDARD_ALLOWANCE = BigInteger.TEN;

    private final List<Transaction> transactions;

    public StandardLedger() {
        this(new ArrayList<>());
    }

    public StandardLedger(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void withdraw(BigInteger amount, LocalDateTime time) {
        transactions.add(new WithdrawTransaction(amount, time));
    }

    @Override
    public void deposit(BigInteger amount, LocalDateTime time) {
        transactions.add(new DepositTransaction(amount, time));
    }

    @Override
    public BigInteger getBalance() {
        return Balance.from(transactions);
    }

    @Override
    public BigInteger getAllowance() {
        return STANDARD_ALLOWANCE;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
