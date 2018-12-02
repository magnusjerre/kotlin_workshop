package jerre.kotlin.workshop.services;

import jerre.kotlin.workshop.models.BankingUtils;
import jerre.kotlin.workshop.models.Person;
import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.ExpenditureAccount;
import jerre.kotlin.workshop.models.banking.ForeignSavingsAccount;
import jerre.kotlin.workshop.models.services.BankingService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankingServiceTest {

    private BankingService bankingService;
    private Person person = new Person(1L, LocalDate.of(2000, 1, 1), "Yo", "Lo", new HashSet<>());

    @Before
    public void setup() {
        bankingService = new BankingService();
    }

    @Test
    public void add_foreignSavingsAccount_should_return_true() {
        ForeignSavingsAccount foreignSavingsAccount = new ForeignSavingsAccount(0L, BigDecimal.valueOf(100_000));
        boolean result = bankingService.addAccountForPerson(person.getId(), foreignSavingsAccount);
        assertTrue(result);
    }

    @Test
    public void add_ForeignSavingsAccount_should_return_false_since_there_can_only_be_one_ForeignSavingsAccount() {
        ForeignSavingsAccount foreignSavingsAccount1 = new ForeignSavingsAccount(0L, BigDecimal.TEN);
        bankingService.addAccountForPerson(person.getId(), foreignSavingsAccount1);
        ForeignSavingsAccount foreignSavingsAccount2 = new ForeignSavingsAccount(1L, BigDecimal.TEN);
        boolean result = bankingService.addAccountForPerson(person.getId(), foreignSavingsAccount2);
        assertFalse(result);
    }

    @Test
    public void getAccounts_should_only_return_accounts_that_match_the_filter() {
        addAccounts();

        Collection<Account> accountsForPerson = bankingService.getAccountsForPerson(person.getId(), BankingUtils.expenditureAccountFilter);
        assertEquals(2, accountsForPerson.size());
    }


    private void addAccounts() {
        ForeignSavingsAccount foreignSavingsAccount1 = new ForeignSavingsAccount(0L, BigDecimal.TEN);
        ExpenditureAccount expenditureAccount1 = new ExpenditureAccount(1L, BigDecimal.ONE);
        ExpenditureAccount expenditureAccount2 = new ExpenditureAccount(2L, BigDecimal.TEN);

        bankingService.addAccountForPerson(person.getId(), foreignSavingsAccount1);
        bankingService.addAccountForPerson(person.getId(), expenditureAccount1);
        bankingService.addAccountForPerson(person.getId(), expenditureAccount2);
    }

}
