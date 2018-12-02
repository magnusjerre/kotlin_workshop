package jerre.kotlin.workshop;

import jerre.kotlin.workshop.models.BankingUtils;
import jerre.kotlin.workshop.models.banking.Account;
import jerre.kotlin.workshop.models.banking.ExpenditureAccount;
import jerre.kotlin.workshop.models.banking.ForeignSavingsAccount;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BankingUtilsTest {

    ForeignSavingsAccount foreignSavingsAccount1 = new ForeignSavingsAccount(0L, BigDecimal.TEN);
    ExpenditureAccount expenditureAccount1 = new ExpenditureAccount(1L ,BigDecimal.ONE);
    ExpenditureAccount expenditureAccount2 = new ExpenditureAccount(2L, BigDecimal.TEN);

    @Test
    public void sumBalance_should_give_21() {
        List<Account> accounts = Arrays.asList(foreignSavingsAccount1, expenditureAccount1, expenditureAccount2);
        BigDecimal bigDecimal = BankingUtils.sumBalance(accounts);
        assertEquals(BigDecimal.valueOf(21d).setScale(2), bigDecimal);
    }

}
