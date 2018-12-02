package jerre.kotlin.workshop.models.services;

import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.ExpenditureAccount;
import jerre.kotlin.workshop.models.banking.ForeignSavingsAccount;
import jerre.kotlin.workshop.models.banking.LoanAccount;
import jerre.kotlin.workshop.models.banking.SavingsAccount;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankingService {

    private Map<Long, Set<Account>> accountOwners;
    private int maxForeignAccountsPerPerson;
    BigDecimal maxLoanAccountAmount, maxSingleDepositAmount;

    public BankingService() {
        this(1, BigDecimal.valueOf(2_000_000), BigDecimal.valueOf(10_000), new HashMap<>());
    }

    public BankingService(int maxForeignAccountsPerPerson,
                          BigDecimal maxLoanAccountAmount,
                          BigDecimal maxSingleDepositAmount,
                          Map<Long, Set<Account>> accountOwners) {
        this.maxForeignAccountsPerPerson = maxForeignAccountsPerPerson;
        this.maxLoanAccountAmount = maxLoanAccountAmount.abs();
        this.maxSingleDepositAmount = maxSingleDepositAmount;
        this.accountOwners = accountOwners;
    }

    public Collection<Account> getAccountsForPerson(Long id, Predicate<Account> filter) {
        if (filter == null) {
            return accountOwners.get(id);
        }
        return accountOwners.get(id).stream().filter(filter).collect(Collectors.toSet());
    }

    public boolean addAccountForPerson(Long personId, Account account) {
        if (!accountOwners.containsKey(personId)) {
            accountOwners.put(personId, new HashSet<>());
        }

        Set<Account> accounts = accountOwners.get(personId);

        if (account instanceof ForeignSavingsAccount) {
            if (accounts.stream().filter(acc -> acc instanceof ForeignSavingsAccount).count() + 1 > maxForeignAccountsPerPerson) {
                return false;
            }
            ((ForeignSavingsAccount) account).setMaxSingleDeposit(maxSingleDepositAmount);
        } else if (account instanceof LoanAccount) {
            if (maxLoanAccountAmount.abs().compareTo(account.getBalance().abs()) < 0) {
                return false;
            }
        } else if(account instanceof ExpenditureAccount) {
            ((ExpenditureAccount) account).setMaxSingleDeposit(maxSingleDepositAmount);
        } else if(account instanceof SavingsAccount) {
            ((SavingsAccount) account).setMaxSingleDeposit(maxSingleDepositAmount);
        }

        return accountOwners.get(personId).add(account);
    }

}
