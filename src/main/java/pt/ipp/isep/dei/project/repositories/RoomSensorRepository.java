package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RoomSensorRepository extends CrudRepository<RoomSensor, SensorId> {

    boolean existsRoomSensorsByRoomIdAndSensorTypeId(RoomId roomId, SensorTypeId sensorTypeId);

    RoomSensor findByRoomId(RoomId roomId);

    RoomSensor findByRoomIdAndSensorTypeId(RoomId roomId, SensorTypeId sensorTypeId);

    RoomSensor findByRoomIdAndSensorTypeIdAndReadingsBetween(RoomId roomId, SensorTypeId sensorTypeId, LocalDateTime localDate1, LocalDateTime localDate2);
}
