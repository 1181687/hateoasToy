package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.RoomReading;

public interface RoomSensorReadingsRepository extends CrudRepository<RoomReading, GeoAreaReadingId> {
}
