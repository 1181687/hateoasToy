package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReadingDTO {
    private String id;
    private double value;
    private LocalDateTime dateTime;
    private String units;

    /**
     * Constructor.
     */
    public ReadingDTO() {
    }

    /**
     * Method that returns the value of a reading.
     *
     * @return Double corresponding to the value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Methot that sets the value.
     *
     * @param value Value to be used.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Method that returns the date time.
     *
     * @return LocalDateTime corresponding to the date time of a reading.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Method that sets the date time.
     *
     * @param dateTime Date time to be used.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadingDTO)) {
            return false;
        }
        ReadingDTO readingDTO = (ReadingDTO) obj;
        return Utils.isSameDouble(this.value,readingDTO.value) && this.dateTime.equals(readingDTO.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value,this.dateTime);
    }
}
