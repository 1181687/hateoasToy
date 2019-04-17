package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

import java.util.List;

public interface GeoAreaSensorReadingsRepository extends CrudRepository<GeoAreaReading, GeoAreaReadingId> {

    List<GeoAreaReading> findByGeoAreaReadingId_GeoAreaSensorId(GeoAreaSensorId id);
}
