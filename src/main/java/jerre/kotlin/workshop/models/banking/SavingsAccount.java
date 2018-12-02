package jerre.kotlin.workshop.models.banking;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    public SavingsAccount(Long id, BigDecimal initialAmount) {
        super(id, initialAmount);
    }

    @Override
    public SavingsAccount copy(BigDecimal newAmount) {
        return new SavingsAccount(id, newAmount);
    }

}
