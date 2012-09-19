package ch.skyr.costcontrol.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
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
    @NonVisual
    private Long id;
    @Column(nullable = false, length = 250)
    @Validate("required")
    private String name;
    @Column(nullable = false)
    @Validate("required")
    private AccountActive active = AccountActive.ACTIVE; // default value

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
}
