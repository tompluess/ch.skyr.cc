package ch.skyr.costcontrol.entities;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.skyr.costcontrol.core.DataProvider;
import ch.skyr.costcontrol.util.H2Util;
public class SaldoTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setUpClass() throws Exception {
        emf = Persistence.createEntityManagerFactory(DataProvider.PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        H2Util.mayStartWebConsole(emf, false);
        em.close();
        emf.close(); // close at application end
    }

    @Test
    public void persistAndLoad_validSaldo_canBeFoundViaAccount() {
        // Arrange
        final MonetaryAccount account = new MonetaryAccount();
        account.setName("monney account");
        em.getTransaction().begin();
        em.persist(account);
        final Saldo saldo = new Saldo();
        saldo.setMonetaryAccount(account);
        saldo.setAmount(new Money(99.50, Currency.CHF));
        saldo.setPositionType(PositionType.GUESS);
        saldo.setValutaDate(new GregorianCalendar(2013, 12, 31).getTime());
        em.persist(saldo);
        em.getTransaction().commit();
        System.out.println(account);
        em.clear();
        // Act
        final MonetaryAccount accountResult = em.find(MonetaryAccount.class, account.getId());
        final List<Saldo> saldi = accountResult.getSaldi();
        System.out.println(accountResult);
        // Assert
        assertEquals("saldi size", 1, saldi.size());
        final Saldo result = saldi.get(0);
        assertEquals("saldo description", saldo.getDescription(), result.getDescription());
        assertEquals("saldo account", saldo.getMonetaryAccount(), result.getMonetaryAccount());
        assertEquals("saldo amount", saldo.getAmount(), result.getAmount());
        assertEquals("saldo valuta", saldo.getValutaDate(), result.getValutaDate());
        assertEquals("saldo positiontype", saldo.getPositionType(), result.getPositionType());
    }
}
