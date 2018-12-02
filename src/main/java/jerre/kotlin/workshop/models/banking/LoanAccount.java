package jerre.kotlin.workshop.models.banking;

import java.math.BigDecimal;

public final class LoanAccount extends Account {

    public LoanAccount(Long id, BigDecimal initialLoanAmount) {
        super(id, initialLoanAmount.abs().multiply(BigDecimal.valueOf(-1)));
    }

    public boolean loanIsDownPaid() {
        return this.balance.compareTo(BigDecimal.ZERO) == 0;
    }

    public BigDecimal getRestAmount() {
        if (loanIsDownPaid()) {
            return BigDecimal.ZERO;
        }

        return balance.abs();
    }

    @Override
    public LoanAccount copy(BigDecimal newAmount) {
        return new LoanAccount(id, newAmount);
    }
}
