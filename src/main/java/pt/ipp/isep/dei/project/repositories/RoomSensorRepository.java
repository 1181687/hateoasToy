package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;

public interface RoomSensorRepository extends CrudRepository<RoomSensor, SensorId> {
}
