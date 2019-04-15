package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

import java.time.LocalDateTime;

public class ReadingId {

    private GeoAreaSensorId geoAreaSensorId;
    private LocalDateTime localDateTime;

    public ReadingId(GeoAreaSensorId geoAreaSensorId, LocalDateTime localDateTime) {
        this.geoAreaSensorId = geoAreaSensorId;
        this.localDateTime = localDateTime;
    }

    public GeoAreaSensorId getGeoAreaSensorId() {
        return geoAreaSensorId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
