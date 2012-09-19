package ch.skyr.costcontrol.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
/**
 * Entity implementation class for Entity: Position
 */
@Entity
@TableGenerator(name = Position.SEQUENCE_NAME, initialValue = 0, allocationSize = 20)
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    static final String SEQUENCE_NAME = "position_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQUENCE_NAME)
    @NonVisual
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    @Validate("required")
    private String name;
    @Validate("required")
    @Basic(optional = false)
    @ManyToOne
    private Account originAccount;
    @Basic(optional = false)
    @ManyToOne
    @Validate("required")
    private Account targetAccount;
    @Basic(optional = false)
    @Validate("required")
    private Money amount;
    @Basic(optional = false)
    @Validate("required")
    private PositionType positionType;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date valutaDate;
    private Integer validDays;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate = new Date(); // default value: now.

    public Position() {
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

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(final Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(final Account targetAccount) {
        this.targetAccount = targetAccount;
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

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(final Integer validDays) {
        this.validDays = validDays;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
}
