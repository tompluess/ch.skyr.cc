package ch.skyr.costcontrol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.skyr.costcontrol.core.DataProvider;
import ch.skyr.costcontrol.entities.Account;
import ch.skyr.costcontrol.entities.Account.AccountActive;
import ch.skyr.costcontrol.entities.MonetaryAccount;
import ch.skyr.costcontrol.entities.Money;
import ch.skyr.costcontrol.entities.Position;
import ch.skyr.costcontrol.entities.PositionType;
public class IntegrationTest {
    public static final String TEST_ACCOUNT_NAME_INACTIVE = "test account";
    public static final String TEST_ACCOUNT_NAME_CASH = "Cash";
    private static EntityManager entityManager;
    private final transient DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("de", "CH"));
    private Long lastAccountPk;

    /**
     * initialize the entity manager.
     */
    @BeforeClass
    public static void setupJPA() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DataProvider.PERSISTENCE_UNIT_NAME);
        entityManager = emf.createEntityManager();
    }

    @Test
    public void testMain() {
        App.main(null);
    }

    @Test
    public void storeAnAccountTest() {
        storeTwoAccounts();
    }

    private void storeTwoAccounts() {
        entityManager.getTransaction().begin();
        storeAnAccount(TEST_ACCOUNT_NAME_INACTIVE, AccountActive.INACTIVE);
        final Account account = storeAnAccount(TEST_ACCOUNT_NAME_CASH, AccountActive.ACTIVE);
        lastAccountPk = account.getId();
        entityManager.getTransaction().commit();
    }

    private static Account storeAnAccount(final String accountName, final AccountActive accountActive) {
        final Account account = new MonetaryAccount();
        account.setName(accountName);
        account.setActive(accountActive);
        entityManager.persist(account);
        return account;
    }

    @Test
    public void storeAPositionTest() {
        storeTwoAccounts();
        final Account account1 = entityManager.find(Account.class, lastAccountPk);
        final Account account2 = account1;
        entityManager.getTransaction().begin();
        storeAPosition(account1, account2);
        entityManager.getTransaction().commit();
    }

    private static void storeAPosition(final Account account1, final Account account2) {
        final Position pos = new Position();
        pos.setName("Test Position");
        pos.setTargetAccount(account1);
        pos.setOriginAccount(account2);
        pos.setAmount(new Money(5.5, Money.Currency.CHF));
        pos.setPositionType(PositionType.CONFIRMED);
        pos.setValutaDate(new GregorianCalendar(2012, 2, 12).getTime());
        entityManager.persist(pos);
    }

    public static void setupTestData() {
        entityManager.getTransaction().begin();
        final Account account1 = storeAnAccount(TEST_ACCOUNT_NAME_INACTIVE, AccountActive.INACTIVE);
        final Account account2 = storeAnAccount(TEST_ACCOUNT_NAME_CASH, AccountActive.ACTIVE);
        storeAPosition(account1, account2);
        entityManager.getTransaction().commit();
    }
}
