package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import java.util.List;

public interface RoomSensorReadingsRepository extends CrudRepository<RoomReading, GeoAreaReadingId> {

    List<RoomReading> findByRoomReadingId_RoomSensorId (RoomSensorId roomSensorId);
}
