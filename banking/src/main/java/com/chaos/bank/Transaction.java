package com.chaos.bank;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface Transaction {
    BigInteger getAmount();

    LocalDateTime getTime();

    BigInteger affect(BigInteger amount);
}
