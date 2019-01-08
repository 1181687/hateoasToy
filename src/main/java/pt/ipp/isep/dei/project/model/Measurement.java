package pt.ipp.isep.dei.project.model;

import java.util.Date;

public class Measurement {
    private double mValue;
    private Date mDateTime;

    /**
     * constructor that receives a value and a date
     *
     * @param mValue    value
     * @param mDateTime date
     */
    public Measurement(double mValue, Date mDateTime) {
        this.mValue = mValue;
        this.mDateTime = mDateTime;
    }

    /**
     * Get method
     * @return mValue
     */
    public double getmValue() {
        return mValue;
    }

    /**
     * Get method
     * @return mDateTime
     */
    public Date getmDateTime() {
        return mDateTime;
    }

}
