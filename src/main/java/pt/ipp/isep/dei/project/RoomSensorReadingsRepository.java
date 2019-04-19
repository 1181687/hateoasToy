package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import java.time.LocalDate;
import java.util.List;

public interface RoomSensorReadingsRepository extends CrudRepository<RoomReading, RoomReadingId> {

    List<RoomReading> findByRoomReadingId_RoomSensorId(RoomSensorId roomSensorId);

    List<RoomReading> findByRoomReadingId_RoomSensorId_AndRoomReadingId_LocalDateTime_Date(RoomSensorId roomSensorId, LocalDate localDate);

}

