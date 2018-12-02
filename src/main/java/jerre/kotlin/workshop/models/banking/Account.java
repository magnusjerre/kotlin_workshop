package jerre.kotlin.workshop.models.banking;

import jerre.kotlin.workshop.models.Id;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account implements Id {

    protected BigDecimal balance;
    protected Long id;

    protected Account(Long id, BigDecimal initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(balance, account.balance) &&
                Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, id);
    }

    public Account deposit(BigDecimal amount) {
        return copy(balance.add(amount));
    }

    public abstract Account copy(BigDecimal newAmount);
}
