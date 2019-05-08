package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.util.List;

public interface GeoAreaSensorRepository extends CrudRepository<GeoAreaSensor, SensorId> {


    List<GeoAreaSensor> findByGeoAreaIdAndSensorTypeId(GeoAreaId geoAreaId, SensorTypeId sensorTypeId);

    GeoAreaSensor findGeoAreaSensorsById(SensorId id);

    GeoAreaSensor findGeoAreaSensorsByGeoAreaIdAndSensorTypeId(GeoAreaId geoAreaId, SensorTypeId sensorTypeId);

    List<GeoAreaSensor> findAllByGeoAreaId(GeoAreaId geoAreaId);

    List<GeoAreaSensor> findByLocationAndSensorTypeIdAndListOfReadingsBetween(Location location, SensorTypeId sensorTypeId,
                                                                              LocalDate startDate, LocalDate endDate);

}
