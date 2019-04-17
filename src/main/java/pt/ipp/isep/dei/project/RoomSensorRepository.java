package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;

public interface RoomSensorRepository extends CrudRepository<RoomSensor, RoomSensorId> {
    List<RoomSensor> findByRoomId(RoomId roomId);

    RoomSensor findByRoomIdAndSensorTypeId(RoomId roomId, SensorTypeId sensorTypeId);

}
