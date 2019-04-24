package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeoAreaAggregateRepository {

    @Autowired
    private GeoAreaRepository geoAreaRepo;

    @Autowired
    private GeoAreaSensorRepository geoAreaSensorRepo;

    @Autowired
    private GeoAreaSensorReadingsRepository geoAreaReadingRepo;

    /**
     * Constructor.
     */
    public GeoAreaAggregateRepository() {
        // empty
    }

    public List<GeographicalArea> findByIdGeographicalAreaTypeId(GeoAreaTypeId geoAreaTypeId) {
        return geoAreaRepo.findById_GeographicalAreaTypeId(geoAreaTypeId);
    }

    public List<GeoAreaSensor> findBySensorTypeId(SensorTypeId typeId) {
        return this.geoAreaSensorRepo.findBySensorTypeId(typeId);
    }


    public List<GeoAreaSensor> findByGeoAreaId(GeoAreaId geoAreaId) {
        return this.geoAreaSensorRepo.findByGeoAreaId(geoAreaId);
    }

    public List<GeoAreaReading> findByGeoAreaReadingIdGeoAreaSensorId(GeoAreaSensorId id) {
        return this.geoAreaReadingRepo.findByGeoAreaReadingId_GeoAreaSensorId(id);
    }

    /*public boolean existsByDateTime_DateBetweenAndGeoAreaReadingId_GeoAreaSensorId(LocalDate startDate, LocalDate endDate, GeoAreaSensorId id) {
        return this.geoAreaReadingRepo.existsByDateTime_DateBetweenAndGeoAreaReadingId_GeoAreaSensorId(startDate, endDate, id);
    }*/

    public Iterable<GeographicalArea> findAllGeoAreas() {
        return geoAreaRepo.findAll();
    }

    public Long numberOfGeoAreasInRepo() {
        return this.geoAreaRepo.count();
    }

    public boolean isReadingDuplicated(GeoAreaReadingId geoAreaReadingId){
        return geoAreaReadingRepo.existsById(geoAreaReadingId);
    }

    public void saveReading(GeoAreaReading geoAreaReading) {
        this.geoAreaReadingRepo.save(geoAreaReading);
    }

    public GeographicalArea getGeoAreaById(GeoAreaId geoAreaId){
        return geoAreaRepo.findById(geoAreaId).orElse(null);
    }

    public boolean doesSensorExist(GeoAreaSensorId geoAreaSensorId) {
        return geoAreaSensorRepo.existsById(geoAreaSensorId);
    }

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId){
        return geoAreaSensorRepo.findById(geoAreaSensorId).orElse(null);
    }

    public void saveGeoAreaSensor(GeoAreaSensor geoAreaSensor) {
        this.geoAreaSensorRepo.save(geoAreaSensor);
    }

  /*  public List<GeoAreaReading> findByDateTime_DateBetweenAndGeoAreaReadingId_GeoAreaSensorId(LocalDate startDate, LocalDate endDate, GeoAreaSensorId geoAreaSensorId) {
        return this.geoAreaReadingRepo.findByDateTime_DateBetweenAndGeoAreaReadingId_GeoAreaSensorId(startDate, endDate, geoAreaSensorId);
    }*/

    public boolean existGeoAreaById(GeoAreaId id) {
        return this.geoAreaRepo.existsById(id);
    }

    public void saveGeoArea(GeographicalArea geoArea) {
        this.geoAreaRepo.save(geoArea);
    }

    public void removeSensor(GeoAreaSensor geoAreaSensor){
        this.geoAreaSensorRepo.delete(geoAreaSensor);
    }

    public List<GeoAreaSensor> getActiveSensors(){
        Iterable<GeoAreaSensor> sensors= this.geoAreaSensorRepo.findAll();
        List<GeoAreaSensor> activeSensors = new ArrayList<>();

        for (GeoAreaSensor sensor : sensors) {
            if(sensor.isActive()){
                activeSensors.add(sensor);
            }
        }
        return activeSensors;
    }

    public boolean deactivateSensorById(GeoAreaSensorId id){
        if(this.geoAreaSensorRepo.findById(id).isPresent()){
            GeoAreaSensor sensor = this.geoAreaSensorRepo.findById(id).get();
            sensor.deactivateSensor();
            this.geoAreaSensorRepo.save(sensor);
            return true;
        }
        return false;
    }

    public List<GeoAreaSensor> findByGeoAreaIdAndSensorTypeId(GeoAreaId geoAreaId, SensorTypeId sensorTypeId) {
        return geoAreaSensorRepo.findByGeoAreaIdAndSensorTypeId(geoAreaId, sensorTypeId);
    }


    public boolean existsReadingBySensorIdAndInterval(GeoAreaSensorId sensorId, LocalDateTime startDate, LocalDateTime endDate) {
        return geoAreaReadingRepo.existsByGeoAreaReadingId_GeoAreaSensorIdAndGeoAreaReadingId_LocalDateTimeBetween(sensorId, startDate, endDate);
    }
}
