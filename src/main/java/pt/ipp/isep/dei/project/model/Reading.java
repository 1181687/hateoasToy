package pt.ipp.isep.dei.project.model;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable()
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

    protected Reading() {
        // empty
    }

    /**
     * Get method
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get method
     *
     * @return dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Method that creates a hashcode.
     *
     * @return Created hashcode.
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Method that determines if two readings are equal.
     *
     * @param obj Object used.
     * @return True or false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Reading)) {
            return false;
        }
        Reading reading = (Reading) obj;
        Double valueOfTheReading = this.value;
        return valueOfTheReading.equals(reading.getValue()) && this.dateTime.equals(reading.getDateTime());
    }
}
