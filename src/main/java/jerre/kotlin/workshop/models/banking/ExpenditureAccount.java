package jerre.kotlin.workshop.models.banking;

import java.math.BigDecimal;

public final class ExpenditureAccount extends Account {

    public ExpenditureAccount(Long id, BigDecimal initialAmount) {
        super(id, initialAmount);
    }

    @Override
    public ExpenditureAccount copy(BigDecimal newAmount) {
        return new ExpenditureAccount(id, newAmount);
    }
}
