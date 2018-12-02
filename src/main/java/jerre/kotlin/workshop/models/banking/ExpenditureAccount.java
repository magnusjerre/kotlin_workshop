package jerre.kotlin.workshop.models.banking;

import java.math.BigDecimal;

public final class ExpenditureAccount extends Account {

    private BigDecimal maxSingleDeposit;

    public ExpenditureAccount(Long id, BigDecimal initialAmount) {
        super(id, initialAmount);
    }

    public void setMaxSingleDeposit(BigDecimal amount) {
        this.maxSingleDeposit = amount;
    }

    public BigDecimal deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Must deposit a positive amount, you tired to deposit " + amount + " kr");

        return this.balance = this.balance.add(amount);
    }

    public BigDecimal withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Must deposit a positive amount, you tired to deposit " + amount + " kr");
        return balance = this.balance.subtract(amount);
    }
}
