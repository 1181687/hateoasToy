package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.time.LocalDateTime;

@Embeddable()
public class GeoAreaReading {

    @EmbeddedId
    private GeoAreaReadingId geoAreaReadingId;
    private double value;

    /**
     * constructor that receives a value and a date
     *
     * @param value    value
     * @param dateTime date
     */
    public GeoAreaReading(GeoAreaSensorId geoAreaSensorId, LocalDateTime dateTime, double value) {
        this.geoAreaReadingId = new GeoAreaReadingId(geoAreaSensorId,dateTime);
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
        return geoAreaReadingId.getLocalDateTime();
    }

    /**
     * Get method of the GeoAreaSensorId of the reading.
     * @return
     */
    public GeoAreaSensorId getSensorId() {
        return geoAreaReadingId.getGeoAreaSensorId();
    }

    private GeoAreaReadingId getGeoAreaReadingId(){
        return geoAreaReadingId;
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
        GeoAreaReadingId geoAreaReadingId = this.geoAreaReadingId;
        return geoAreaReadingId.equals(geoAreaReading.getGeoAreaReadingId());
    }
}
