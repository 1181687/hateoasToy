package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import java.util.List;

public interface RoomSensorReadingsRepository extends CrudRepository<RoomReading, RoomReadingId> {

    List<RoomReading> findByRoomReadingId_RoomSensorId(RoomSensorId roomSensorId);
}

