package jerre.kotlin.workshop.models.banking;

import java.math.BigDecimal;

public final class ForeignSavingsAccount extends SavingsAccount {
    public ForeignSavingsAccount(Long id, BigDecimal initialAmount) {
        super(id, initialAmount);
    }
}
