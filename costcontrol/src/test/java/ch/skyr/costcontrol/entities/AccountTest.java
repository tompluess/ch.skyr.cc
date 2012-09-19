package ch.skyr.costcontrol.entities;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.skyr.costcontrol.core.DataProvider;
import ch.skyr.costcontrol.util.H2Util;
public class AccountTest {
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
    public void persistAndLoad_validAccount_success() {
        // Arrange
        final Account account = new MonetaryAccount();
        account.setName("first account");
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        System.out.println(account);
        em.clear();
        // Act
        final Account accountResult = em.find(Account.class, account.getId());
        System.out.println(accountResult);
        // Assert
        assertNotSame(account, accountResult);
        assertEquals(account.getId(), accountResult.getId());
        assertEquals(account.getName(), accountResult.getName());
    }
}
