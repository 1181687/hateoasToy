package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;

public interface SensorRepository extends CrudRepository<GeoAreaSensor, String> {
}