package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;

public class Reading {
    private double value;
    private LocalDateTime dateTime;

    /**
     * constructor that receives a value and a date
     *
     * @param value    value
     * @param dateTime date
     */
    public Reading(double value, LocalDateTime dateTime) {
        this.value = value;
        this.dateTime = dateTime;
    }

    /**
     * Get method
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Get method
     * @return dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
