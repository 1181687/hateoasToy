package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;

public interface GeoAreaSensorReadingsRepository extends CrudRepository<GeoAreaReading, GeoAreaReadingId> {
}
