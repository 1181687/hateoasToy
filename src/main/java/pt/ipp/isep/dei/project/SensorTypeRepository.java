package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

public interface SensorTypeRepository extends CrudRepository<SensorType, String> {
}
