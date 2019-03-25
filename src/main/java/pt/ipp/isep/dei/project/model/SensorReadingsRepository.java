package pt.ipp.isep.dei.project.model;

import org.springframework.data.repository.CrudRepository;

public interface SensorReadingsRepository extends CrudRepository<Reading, Long> {
}
