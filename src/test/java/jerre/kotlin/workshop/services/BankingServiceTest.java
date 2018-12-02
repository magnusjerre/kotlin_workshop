package jerre.kotlin.workshop.services;

import jerre.kotlin.workshop.models.Address;
import jerre.kotlin.workshop.models.BankingUtils;
import jerre.kotlin.workshop.models.Person;
import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.AccountType;
import jerre.kotlin.workshop.models.services.BankingService;
import jerre.kotlin.workshop.testingmodels.Roughly;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankingServiceTest {

    private BankingService bankingService;
    private Person person = new Person(1L, LocalDate.of(2000, 1, 1), "Yo Lo", new Address("Gata 1", "1234", "Stedet"));

    @Before
    public void setup() {
        bankingService = new BankingService(BigDecimal.TEN, new HashMap<>());
        bankingService.addPerson(person);
    }


    @Test
    public void getAccounts_should_only_return_accounts_that_match_the_filter() {
        addAccounts();

        Collection<Account> accountsForPerson = bankingService.getAccountsForPerson(person.getId(), BankingUtils.expenditureAccountFilter);
        assertEquals(1, accountsForPerson.size());
    }

    @Test
    public void prettySummary() {
        addAccounts();
        String expected = "---- Customer 1, start ----\n" +
                "Name: Yo Lo\n" +
                "Birthdate: 2000-01-01, 18 years old\n" +
                "Address: Gata 1, 1234 Stedet\n" +
                "Accounts:\n" +
                "\tSavingsAccount, balance is 0 kr\n" +
                "\tSavingsAccount, balance is 0 kr\n" +
                "\tExpenditureAccount, balance is 0 kr\n" +
                "---- Customer 1, end ----";

        assertEquals(expected, bankingService.summary(person));
    }

    @Test
    public void deposit_5000_twice_should_give_10000() {
        addAccounts();
        Optional<Account> first = bankingService.getAccountsForPerson(person.getId(), BankingUtils.expenditureAccountFilter).stream().findFirst();
        assertTrue(first.isPresent());
        Long accountId = first.get().getId();
        bankingService.deposit(person.getId(), accountId, BigDecimal.valueOf(5_000L)).onResult(account ->
                        Roughly.assertThat(account.getBalance()).isRoughly(5_000d).plusOrMinus(0d)
                , null);
        bankingService.deposit(person.getId(), accountId, BigDecimal.valueOf(5_000L)).onResult(account ->
                        Roughly.assertThat(account.getBalance()).isRoughly(10_000d).plusOrMinus(0d)
                , null);
    }

    @Test
    public void withdraw_legal_amount() {
        Account account = bankingService.createAccountForPerson(person.getId(), AccountType.SAVINGS, BigDecimal.TEN);
        bankingService
                .withdraw(person.getId(), account.getId(), BigDecimal.ONE)
                .onResult(acc ->
                        Roughly.assertThat(acc.getBalance()).isRoughly(9d).plusOrMinus(0d), null);
    }

    @Test
    public void withdraw_from_nonexisting_account() {
        assertTrue(bankingService
                .withdraw(person.getId(), -1L, BigDecimal.ONE)
                .isError());
    }

    @Test
    public void withdraw_from_LoanAccount_should_give_error() {
        Account account = bankingService.createAccountForPerson(person.getId(), AccountType.LOAN, BigDecimal.TEN);
        assertTrue(bankingService
                .withdraw(person.getId(), account.getId(), BigDecimal.ONE)
                .isError());
    }


    private void addAccounts() {
        bankingService.createAccountForPerson(person.getId(), AccountType.SAVINGS, BigDecimal.ZERO);
        bankingService.createAccountForPerson(person.getId(), AccountType.SAVINGS, BigDecimal.ZERO);
        bankingService.createAccountForPerson(person.getId(), AccountType.EXPENSE, BigDecimal.ZERO);
    }

}
