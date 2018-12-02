package jerre.kotlin.workshop.models.services;

import jerre.kotlin.workshop.models.Address;
import jerre.kotlin.workshop.models.Person;
import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.AccountModificationResult;
import jerre.kotlin.workshop.models.banking.AccountType;
import jerre.kotlin.workshop.models.banking.ExpenditureAccount;
import jerre.kotlin.workshop.models.banking.LoanAccount;
import jerre.kotlin.workshop.models.banking.SavingsAccount;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BankingService {

    private Map<Long, Set<Account>> accountOwners;
    BigDecimal maxLoanAccountAmount;

    Supplier<Long> idSupplier = new Supplier<Long>() {
        private long id;
        @Override
        public Long get() {
            return id++;
        }
    };

    public BankingService(BigDecimal maxLoanAccountAmount,
                          Map<Long, Set<Account>> accountOwners) {
        this.maxLoanAccountAmount = maxLoanAccountAmount.abs();
        this.accountOwners = accountOwners;
    }

    public Collection<Account> getAccountsForPerson(Long id, Predicate<Account> filter) {
        if (filter == null) {
            return accountOwners.get(id);
        }
        return accountOwners.get(id).stream().filter(filter).collect(Collectors.toSet());
    }

    public void addPerson(Person person) {
        accountOwners.put(person.getId(), new HashSet<>());
    }

    public Account createAccountForPerson(Long personId, AccountType accountType, BigDecimal initialBalance) {
        if (!accountOwners.containsKey(personId)) {
            accountOwners.put(personId, new HashSet<>());
        }

        Account newAccount = createAccount(accountType, initialBalance);
        accountOwners.get(personId).add(newAccount);
        return newAccount;
    }

    private Account createAccount(AccountType type, BigDecimal initialBalance) {
        switch (type) {
            case EXPENSE: return new ExpenditureAccount(idSupplier.get(), initialBalance);
            case LOAN: return new LoanAccount(idSupplier.get(), initialBalance);
            case SAVINGS: return new SavingsAccount(idSupplier.get(), initialBalance);
            default: return null;
        }
    }

    public AccountModificationResult deposit(Long personId, Long accountId, BigDecimal amount) {
        Optional<Account> optionalAccount = getAccount(personId, accountId);
        if (!optionalAccount.isPresent()) {
            return AccountModificationResult.error("Couldn't find account! (╯°□°）╯︵ ┻━┻");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return AccountModificationResult.error("Can't deposit a negative amount.");
        }

        Account account = optionalAccount.get();
        Account newAccount = account.deposit(amount);
        Set<Account> accounts = accountOwners.get(personId);
        replaceAccount(newAccount, accounts);

        return AccountModificationResult.success(newAccount);
    }

    private boolean replaceAccount(Account account, Set<Account> accounts) {
        accounts.remove(account);
        return accounts.add(account);
    }

    private Optional<Account> getAccount(Long personId, Long accountId) {
        return accountOwners.get(personId).stream().filter(acc -> acc.getId().equals(accountId)).findFirst();
    }

    public AccountModificationResult withdraw(Long personId, Long accountId, BigDecimal amount) {
        Optional<Account> optionalAccount = getAccount(personId, accountId);
        if (!optionalAccount.isPresent()) {
            return AccountModificationResult.error("Couldn't find account! (╯°□°）╯︵ ┻━┻");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return AccountModificationResult.error("Can't withdraw a negative amount.");
        }

        Account account = optionalAccount.get();
        if (account instanceof LoanAccount) {
            return AccountModificationResult.error("Why u no understand this account can't be withdrawn from?");
        } else {
            if (account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) > 0) {
                Account copy = account.copy(account.getBalance().subtract(amount));
                replaceAccount(copy, accountOwners.get(personId));
                return AccountModificationResult.success(copy);
            } else {
                return AccountModificationResult.error("No no no... Not enough money in your account.");
            }
        }
    }

    public String summary(Person person) {
        Collection<Account> accounts = getAccountsForPerson(person.getId(), null);

        return String.format("---- Customer %d, start ----\n"
                + "Name: %s\n"
                + "Birthdate: %s, %d years old\n"
                + "Address: %s\n"
                + "Accounts:\n"
                + "%s\n"
                + "---- Customer %d, end ----",
                person.getId(),
                person.getName(),
                person.getBirthDate(), person.getAge(),
                person.getAddress().map(Address::pretty).orElse("Unknown"),
                String.join("\n", accounts.stream().map(account -> String.format("\t%s, balance is %s kr", account.getClass().getSimpleName(), account.getBalance().toPlainString())).collect(Collectors.toList())),
                person.getId()
        );
    }

}
