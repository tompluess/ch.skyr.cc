package ch.skyr.costcontrol.services;

import ch.skyr.costcontrol.entities.Account;
import ch.skyr.costcontrol.entities.Money;
import ch.skyr.costcontrol.entities.PositionType;
import ch.skyr.costcontrol.entities.Saldo;
public class AccountService {
    public Saldo getCurrentSaldo(final Account account) {
        final Money amount = new Money(0, account.getCurrency());
        final Saldo saldo = new Saldo();
        saldo.setAmount(amount);
        saldo.setPositionType(PositionType.CALCULATED);
        return saldo;
    }
}
