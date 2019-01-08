package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;

public class Measurement {
    private double mValue;
    private LocalDateTime mDateTime;

    /**
     * constructor that receives a value and a date
     *
     * @param mValue    value
     * @param mDateTime date
     */
    public Measurement(double mValue, LocalDateTime mDateTime) {
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
    public LocalDateTime getmDateTime() {
        return mDateTime;
    }

}
