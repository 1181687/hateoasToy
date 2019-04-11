package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Reading;

public interface Sensor {


    boolean addReading(Reading reading);

    boolean readingExistsBySensorIdLocalDateTime(Reading reading);
}
