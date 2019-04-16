package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;

public interface RoomSensorReadingsRepository extends CrudRepository<RoomReading, GeoAreaReadingId> {
}
