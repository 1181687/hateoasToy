package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class GeoAreaReadingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private GeoAreaSensorId geoAreaSensorId;

    private LocalDateTime localDateTime;

    public GeoAreaReadingId(GeoAreaSensorId geoAreaSensorId, LocalDateTime localDateTime) {
        this.geoAreaSensorId = geoAreaSensorId;
        this.localDateTime = localDateTime;
    }

    public GeoAreaReadingId() {
        // empty
    }

    public GeoAreaSensorId getGeoAreaSensorId() {
        return geoAreaSensorId;
    }

    public void setGeoAreaSensorId(GeoAreaSensorId geoAreaSensorId) {
        this.geoAreaSensorId = geoAreaSensorId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
