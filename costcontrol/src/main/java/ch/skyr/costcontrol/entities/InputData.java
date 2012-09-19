/*
 * Copyright 2011, Thomas Pluess, www.skyr.ch
 */
package ch.skyr.costcontrol.entities;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.tapestry5.beaneditor.Validate;

/**
 * The class for the form parameters.
 * 
 * @author ThomasPluess (last modified: $Author: thomaspluess $)
 * @version $Revision: 338 $ ($Date: 2011-08-11 15:04:35 +0200 (Thu, 11 Aug 2011) $)
 * @since 18.09.2011
 */
public class InputData {

    private Date fromDate;

    private Date toDate;

    private String equiNr;

    /**
     * Constructor sets default values.
     */
    public InputData() {
        // set the default values.
        final GregorianCalendar dateCalculator = new GregorianCalendar();
        toDate = dateCalculator.getTime();
        dateCalculator.add(GregorianCalendar.MONTH, -1);
        fromDate = dateCalculator.getTime();
    }

    @Validate("required")
    public Date getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(final Date fromDate) {
        this.fromDate = fromDate;
    }

    @Validate("required")
    public Date getToDate() {
        return this.toDate;
    }

    public void setToDate(final Date toDate) {
        this.toDate = toDate;
    }

    @Validate("required")
    public String getEquiNr() {
        return this.equiNr;
    }

    public void setEquiNr(final String equiNr) {
        this.equiNr = equiNr;
    }

}
