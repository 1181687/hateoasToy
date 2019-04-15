package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.GeoAreaReading;
import pt.ipp.isep.dei.project.model.ReadingId;

public interface GeoAreaSensorReadingsRepository extends CrudRepository<GeoAreaReading, ReadingId> {
}
