package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.util.List;

public interface GeoAreaSensorRepository extends CrudRepository<GeoAreaSensor, SensorId> {


    List<GeoAreaSensor> findByGeoAreaIdAndSensorType(GeoAreaId geoAreaId, SensorType sensorType);




}
