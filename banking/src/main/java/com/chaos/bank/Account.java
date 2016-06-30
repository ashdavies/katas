package com.chaos.bank;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Account {
    void withdraw(BigInteger amount, LocalDateTime time);

    void deposit(BigInteger amount, LocalDateTime time);

    void print(Printer printer);
}
