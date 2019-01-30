package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;

public class Readings {
    private double mValue;
    private LocalDateTime mDateTime;

    /**
     * constructor that receives a value and a date
     *
     * @param value    value
     * @param dateTime date
     */
    public Readings(double value, LocalDateTime dateTime) {
        this.mValue = value;
        this.mDateTime = dateTime;
    }

    /**
     * Get method
     * @return mValue
     */
    public double getValue() {
        return mValue;
    }

    /**
     * Get method
     * @return mDateTime
     */
    public LocalDateTime getDateTime() {
        return mDateTime;
    }

}
