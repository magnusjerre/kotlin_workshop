package jerre.kotlin.workshop.models;

import jerre.kotlin.workshop.Utils;
import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.ExpenditureAccount;
import jerre.kotlin.workshop.models.banking.Interest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankingUtils {
    public static Predicate<Account> expenditureAccountFilter = account -> account instanceof ExpenditureAccount;

    public static BigDecimal sum(Collection<BigDecimal> values) {
        return values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public static BigDecimal sumBalance(Collection<Account> accounts) {
        List<BigDecimal> balances = accounts.stream().map(account -> account.getBalance()).collect(Collectors.toList());
        return sum(balances).setScale(2);
    }

    /**
     * formula: P = [r * (PV)] / [1 - (1 + r)^(-n)]
     * P = Payment
     * PV = present value
     * r = rate per period
     * n = number of periods
     *
     * http://financeformulas.net/Annuity_Payment_Formula.html
     * @param nPeriods
     * @return
     */
    public static BigDecimal calcAnnuityPeriodPayment(BigDecimal balance, int nPeriods, Interest interest) {
//        BigDecimal numerator = interest.getRate().multiply(balance);
//
//        BigDecimal inner = BigDecimal.ONE.add(interest.getRate());
//        BigDecimal innerPowed = inner.pow(-nPeriods, MathContext.DECIMAL128);
//        BigDecimal denominator = BigDecimal.ONE.subtract(innerPowed);

//        return Utils.currencyScale(numerator.divide(denominator, RoundingMode.HALF_UP));

        return Utils.currencyScale(
                interest.getRate().multiply(balance).divide(BigDecimal.ONE.subtract(BigDecimal.ONE.add(interest.getRate()).pow(-nPeriods, MathContext.DECIMAL128)), RoundingMode.HALF_UP)
        );
    }
}
