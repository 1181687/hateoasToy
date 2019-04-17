package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;

public interface GeoAreaSensorRepository extends CrudRepository<GeoAreaSensor, GeoAreaSensorId> {

    List<GeoAreaSensor> findBySensorTypeId(SensorTypeId typeId);


    List<GeoAreaSensor> findByGeoAreaId(GeoAreaId geoAreaId);
}
