package ch.skyr.costcontrol.entities;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
/** Geldwert */
@Entity
public class MonetaryAccount extends Account {
    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "monetaryAccount")
    @Basic(fetch = FetchType.LAZY)
    private List<Saldo> saldi;

    public List<Saldo> getSaldi() {
        return saldi;
    }
}
