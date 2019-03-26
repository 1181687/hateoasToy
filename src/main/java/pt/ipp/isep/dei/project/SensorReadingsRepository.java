package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.Reading;

public interface SensorReadingsRepository extends CrudRepository<Reading, Long> {
}
