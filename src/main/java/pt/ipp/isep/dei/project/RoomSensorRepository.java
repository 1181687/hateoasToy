package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

public interface RoomSensorRepository extends CrudRepository<RoomSensor, GeoAreaSensorId> {
}
