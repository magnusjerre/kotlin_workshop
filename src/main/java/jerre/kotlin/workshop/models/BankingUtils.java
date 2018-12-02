package jerre.kotlin.workshop.models;

import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.ExpenditureAccount;
import jerre.kotlin.workshop.models.banking.SavingsAccount;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankingUtils {
    public static Predicate<Account> savingsAccountFilter = account -> account instanceof SavingsAccount;
    public static Predicate<Account> expenditureAccountFilter = account -> account instanceof ExpenditureAccount;


    public static BigDecimal sum(Collection<BigDecimal> values) {
        return values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public static BigDecimal sumBalance(Collection<Account> accounts) {
        List<BigDecimal> balances = accounts.stream().map(account -> account.getBalance()).collect(Collectors.toList());
        return sum(balances).setScale(2);
    }
}
