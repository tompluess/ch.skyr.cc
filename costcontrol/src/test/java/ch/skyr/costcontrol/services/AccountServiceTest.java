package ch.skyr.costcontrol.services;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.skyr.costcontrol.core.DataProvider;
import ch.skyr.costcontrol.entities.Account;
import ch.skyr.costcontrol.entities.Currency;
import ch.skyr.costcontrol.entities.MonetaryAccount;
import ch.skyr.costcontrol.entities.Money;
import ch.skyr.costcontrol.entities.PositionType;
import ch.skyr.costcontrol.entities.Saldo;
public class AccountServiceTest {
    private static EntityManager entityManager;
    private final AccountService testee = new AccountService();
    private Account testAccount;

    /**
     * initialize the entity manager.
     */
    @BeforeClass
    public static void setupJPA() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DataProvider.PERSISTENCE_UNIT_NAME);
        entityManager = emf.createEntityManager();
    }

    @Before
    public void setupAccount() {
        testAccount = new MonetaryAccount();
        testAccount.setCurrency(Currency.EUR);
    }

    @Test
    public void getCurrentSaldo_emptyAccount_saldoZero() {
        //act
        final Saldo result = testee.getCurrentSaldo(testAccount);
        //assert
        assertEquals("saldo amount", new Money(0, Currency.EUR), result.getAmount());
        assertEquals("saldo position type", PositionType.CALCULATED, result.getPositionType());
    }
}
