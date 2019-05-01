package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

public interface RoomSensorRepository extends CrudRepository<RoomSensor, SensorId> {

    boolean existsRoomSensorsByRoomIdAndSensorType (RoomId roomId, SensorType sensorType);

    RoomSensor findByRoomId (RoomId roomId);
}
