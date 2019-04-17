package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

import java.util.List;

public interface GeoAreaSensorRepository extends CrudRepository<GeoAreaSensor, GeoAreaSensorId> {

    List<GeoAreaSensor> findByGeoAreaId(GeoAreaId geoAreaId);
}
