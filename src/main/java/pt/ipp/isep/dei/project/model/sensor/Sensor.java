package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.GeoAreaReading;

public interface Sensor {

    boolean addReading(GeoAreaReading geoAreaReading);

    boolean readingExistsBySensorIdLocalDateTime(GeoAreaReading geoAreaReading);
}
