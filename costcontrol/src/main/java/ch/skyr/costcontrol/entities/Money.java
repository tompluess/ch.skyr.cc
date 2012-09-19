package ch.skyr.costcontrol.entities;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Embeddable
public class Money {
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal amount;

    public Money() {
        // for JPA
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money(final BigDecimal amount, final Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money(final double amount, final Currency currency) {
        this(new BigDecimal(amount), currency);
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public enum Currency {
        CHF, EUR
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Money other = (Money) obj;
        if (amount == null) {
            if (other.amount != null) {
                return false;
            }
        } else if (amount.doubleValue() != other.amount.doubleValue()) {
            return false;
        }
        if (currency != other.currency) {
            return false;
        }
        return true;
    }
}
