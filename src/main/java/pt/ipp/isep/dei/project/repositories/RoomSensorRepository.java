package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;

public interface RoomSensorRepository extends CrudRepository<RoomSensor, SensorId> {

    boolean existsRoomSensorsByRoomIdAndSensorTypeId(RoomId roomId, SensorTypeId sensorTypeId);

    RoomSensor findByRoomId (RoomId roomId);

    RoomSensor findByRoomIdAndSensorTypeId (RoomId roomId, SensorTypeId sensorTypeId);

    List<RoomSensor> findAllByRoomId(RoomId roomId);
}
