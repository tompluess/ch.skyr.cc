package ch.skyr.costcontrol.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.skyr.costcontrol.entities.Account;
import ch.skyr.costcontrol.entities.Money;
import ch.skyr.costcontrol.entities.PositionType;
import ch.skyr.costcontrol.entities.Saldo;

import com.google.inject.Inject;
public class AccountService {
    @Inject
    private EntityManager em;

    //    @Transactional 
    public Saldo getCurrentSaldo(final Account account) {
        final TypedQuery<Saldo> query = em.createQuery("from Saldo s where s.monetaryAccount = :account", Saldo.class);
        query.setParameter("account", account);
        final List<Saldo> saldoList = query.getResultList();
        final Saldo resultSaldo;
        if (saldoList.isEmpty()) {
            final Money amount = new Money(0, account.getCurrency());
            resultSaldo = new Saldo();
            resultSaldo.setAmount(amount);
            resultSaldo.setPositionType(PositionType.CALCULATED);
        } else {
            resultSaldo = saldoList.get(0);
        }
        return resultSaldo;
    }
}
