package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;

public interface GeoAreaSensorRepository extends CrudRepository<GeoAreaSensor, SensorId> {
}
