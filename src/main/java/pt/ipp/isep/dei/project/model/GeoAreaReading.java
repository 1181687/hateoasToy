package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.sensor.SensorId;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.time.LocalDateTime;

@Embeddable()
public class GeoAreaReading {

    @EmbeddedId
    private ReadingId readingId;
    private double value;

    /**
     * constructor that receives a value and a date
     *
     * @param value    value
     * @param dateTime date
     */
    public GeoAreaReading(SensorId sensorId, LocalDateTime dateTime, double value) {
        this.readingId = new ReadingId(sensorId,dateTime);
        this.value = value;
    }

    protected GeoAreaReading() {
        // empty
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
        return readingId.getLocalDateTime();
    }

    /**
     * Get method of the SensorId of the reading.
     * @return
     */
    public SensorId getSensorId() {
        return readingId.getSensorId();
    }

    private ReadingId getReadingId(){
        return readingId;
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
        if (!(obj instanceof GeoAreaReading)) {
            return false;
        }
        GeoAreaReading geoAreaReading = (GeoAreaReading) obj;
        ReadingId readingId = this.readingId;
        return readingId.equals(geoAreaReading.getReadingId());
    }
}
