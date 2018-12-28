package pt.ipp.isep.dei.project.model;

import java.util.Date;

public class Measurement {
    private double mValue;
    private Date mDateTime;

    public Measurement(double mValue, Date mDateTime) {
        this.mValue = mValue;
        this.mDateTime = mDateTime;
    }

    public double getmValue() {
        return mValue;
    }

    public Date getmDateTime() {
        return mDateTime;
    }

}
