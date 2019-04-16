package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;

public interface RoomSensorReadingsRepository extends CrudRepository<RoomReading, RoomReadingId> {
}
