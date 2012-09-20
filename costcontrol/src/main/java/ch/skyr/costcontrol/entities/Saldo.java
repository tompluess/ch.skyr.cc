package ch.skyr.costcontrol.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Objects;
@Entity
@TableGenerator(name = Saldo.SEQUENCE_NAME, initialValue = 0, allocationSize = 20)
public class Saldo {
    private static final long serialVersionUID = 1L;
    static final String SEQUENCE_NAME = "saldo_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQUENCE_NAME)
    private Long id;
    @Column(length = 250)
    private String description;
    @ManyToOne
    @Basic(optional = false)
    private MonetaryAccount monetaryAccount;
    @Basic(optional = false)
    private Money amount;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private PositionType positionType;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date valutaDate;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date(); // default value: now.

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public MonetaryAccount getMonetaryAccount() {
        return monetaryAccount;
    }

    public void setMonetaryAccount(final MonetaryAccount monetaryAccount) {
        this.monetaryAccount = monetaryAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(final Money amount) {
        this.amount = amount;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(final PositionType positionType) {
        this.positionType = positionType;
    }

    public Date getValutaDate() {
        return valutaDate;
    }

    public void setValutaDate(final Date valutaDate) {
        this.valutaDate = valutaDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)//
            .add("ID", getId())//
            .add("Account", getMonetaryAccount())//
            .add("Amount", getAmount())//
            .add("Type", getPositionType())//
            .toString();
    }
}
