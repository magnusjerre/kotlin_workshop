package jerre.kotlin.workshop.models.banking;

import java.math.BigDecimal;

public final class LoanAccount extends Account {

    public LoanAccount(Long id, BigDecimal initialLoanAmount) {
        super(id, initialLoanAmount.abs().multiply(BigDecimal.valueOf(-1)));
    }

    public BigDecimal deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Must deposit a positive balance, you tired to deposit " + amount + " kr");

        BigDecimal newAmount = this.balance.add(amount);
        if (newAmount.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("Can't deposit more money than what the loan asks for");
        }

        return this.balance = newAmount;
    }

    public boolean loanIsDownPaid() {
        return this.balance.compareTo(BigDecimal.ZERO) == 0;
    }

    public BigDecimal getRestAmount() {
        if (loanIsDownPaid()) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.ZERO.min(balance);
    }
}
