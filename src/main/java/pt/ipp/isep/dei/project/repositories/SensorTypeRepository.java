package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

public interface SensorTypeRepository extends CrudRepository<SensorType, SensorTypeId> {
}
