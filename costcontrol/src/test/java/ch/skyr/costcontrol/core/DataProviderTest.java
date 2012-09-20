package ch.skyr.costcontrol.core;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.skyr.costcontrol.entities.Account;
import ch.skyr.costcontrol.entities.Currency;
import ch.skyr.costcontrol.entities.MonetaryAccount;
import ch.skyr.costcontrol.entities.Money;
import ch.skyr.costcontrol.entities.Position;
import ch.skyr.costcontrol.entities.PositionType;
public class DataProviderTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setUpClass() throws Exception {
        emf = Persistence.createEntityManagerFactory(DataProvider.PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    private DataProvider testee;

    @Before
    public void setupTestee() {
        testee = new DataProvider();
        //
        em.getTransaction().begin();
        final Account firstAccount = new MonetaryAccount();
        firstAccount.setName("first account");
        em.persist(firstAccount);
        final Position firstPosition = new Position();
        firstPosition.setOriginAccount(firstAccount);
        firstPosition.setName("first position");
        firstPosition.setAmount(new Money(5.5, Currency.CHF));
        firstPosition.setPositionType(PositionType.CONFIRMED);
        firstPosition.setValutaDate(new GregorianCalendar(2012, 2, 12).getTime());
        em.persist(firstPosition);
        em.getTransaction().commit();
    }

    @Test
    public void testLoadAccounts() {
        final List<Account> loadAccounts = testee.loadAccounts();
        assertTrue(loadAccounts.size() > 0);
    }

    @Test
    public void testLoadPositions() {
        final List<Position> loadPositions = loadAllPositions();
        assertTrue(loadPositions.size() > 0);
    }

    private List<Position> loadAllPositions() {
        final List<Position> loadPositions = testee.loadPositions();
        return loadPositions;
    }

    @Test
    public void testLoadPosition() {
        final List<Position> loadPositions = loadAllPositions();
        final Position testPosition = loadPositions.get(0);
        // act
        final Position loadPosition = testee.loadPosition(testPosition.getId());
        assertEquals(testPosition.getName(), loadPosition.getName());
    }
}
