package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.Sensor;


public interface SensorRepository extends CrudRepository<Sensor,String> {
}
