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

    /**
     * @return
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
