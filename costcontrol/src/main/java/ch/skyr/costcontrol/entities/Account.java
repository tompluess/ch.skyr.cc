package ch.skyr.costcontrol.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

import com.google.common.base.Objects;
/**
 * Entity implementation class for Entity: Account
 * 
 */
@Entity
@TableGenerator(name = "account_seq", initialValue = 1001, allocationSize = 20)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "account_seq")
    private Long id;
    @Column(nullable = false, length = 250)
    private String name;
    @Column(nullable = false)
    private AccountActive active = AccountActive.ACTIVE; // default value
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.CHF; // default value

    public Account() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AccountActive getActive() {
        return this.active;
    }

    public void setActive(final AccountActive active) {
        this.active = active;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public enum AccountActive {
        INACTIVE, ACTIVE;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        final Account other = (Account) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)//
            .add("ID", getId())//
            .add("Name", getName())//
            .toString();
    }
}
