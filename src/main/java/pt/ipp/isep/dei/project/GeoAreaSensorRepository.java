package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

public interface GeoAreaSensorRepository extends CrudRepository<GeoAreaSensor, GeoAreaSensorId> {
}
