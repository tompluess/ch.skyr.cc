package ch.skyr.costcontrol.services;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import ch.skyr.costcontrol.entities.Currency;
import ch.skyr.costcontrol.entities.MonetaryAccount;
import ch.skyr.costcontrol.entities.Money;
import ch.skyr.costcontrol.entities.PositionType;
import ch.skyr.costcontrol.entities.Saldo;
public class AccountServiceTest extends AbstractGuiceTest {
    private EntityManager em;
    private AccountService testee;
    private MonetaryAccount testAccount;

    @Before
    public void setupTestee() {
        em = getInjector().getInstance(EntityManager.class);
        testee = getInjector().getInstance(AccountService.class);
        //
        testAccount = new MonetaryAccount();
        testAccount.setName("test account");
        testAccount.setCurrency(Currency.EUR);
        em.persist(testAccount);
    }

    @Test
    public void getCurrentSaldo_emptyAccount_saldoZero() {
        //act
        final Saldo result = testee.getCurrentSaldo(testAccount);
        //assert
        assertEquals("saldo amount", new Money(0, Currency.EUR), result.getAmount());
        assertEquals("saldo position type", PositionType.CALCULATED, result.getPositionType());
    }

    @Test
    public void getCurrentSaldo_oneConfirmedSaldo_returnSaldo() {
        //arrange
        em.getTransaction().begin();
        final Saldo firstSaldo = new Saldo();
        final Currency saldoCurrency = Currency.CHF;
        final double saldoAmount = 100.0;
        firstSaldo.setMonetaryAccount(testAccount);
        firstSaldo.setAmount(new Money(saldoAmount, saldoCurrency));
        firstSaldo.setValutaDate(new GregorianCalendar(2012, 1, 1).getTime());
        firstSaldo.setPositionType(PositionType.CONFIRMED);
        em.persist(firstSaldo);
        em.getTransaction().commit();
        //act
        final Saldo result = testee.getCurrentSaldo(testAccount);
        //assert
        assertEquals("saldo", firstSaldo, result);
        assertEquals("saldo amount", new Money(saldoAmount, saldoCurrency), result.getAmount());
        assertEquals("saldo position type", PositionType.CONFIRMED, result.getPositionType());
    }
}
