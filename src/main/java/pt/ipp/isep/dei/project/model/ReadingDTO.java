package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;

public class ReadingDTO {
    private double value;
    private LocalDateTime dateTime;

    /**
     * Constructor.
     */
    public ReadingDTO() {
    }

    /** Method that returns the value of a reading.
     * @return Double corresponding to the value.
     */
    public double getValue() {
        return value;
    }

    /** Methot that sets the value.
     * @param value Value to be used.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /** Method that returns the date time.
     * @return LocalDateTime corresponding to the date time of a reading.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /** Method that sets the date time.
     * @param dateTime Date time to be used.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
