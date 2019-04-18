package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaAggregateRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class GeoAreaAggregateService {

    @Autowired
    private GeoAreaAggregateRepository geoAreaAggregateRepo;

    /**
     * Constructor.
     */
    public GeoAreaAggregateService() {
        // empty
    }

    /**
     * Method that returns the list of readings from a sensor by its id.
     *
     * @param id Id of the sensor.
     * @return List of GeoAreaReading.
     */
    public List<GeoAreaReading> getGeoAreaReadingsBySensorId(GeoAreaSensorId id) {
        return geoAreaAggregateRepo.getGeoAreaReadingRepo().findByGeoAreaReadingId_GeoAreaSensorId(id);
    }

    /**
     * Method that returns the list of sensors of a given type.
     *
     * @param id Id of the sensor type..
     * @return List of GeoAreaSensor.
     */
    private List<GeoAreaSensor> getGeoAreasByType(SensorTypeId id) {
        return geoAreaAggregateRepo.getGeoAreaSensorRepo().findBySensorTypeId(id);
    }

    /**
     * Method that returns the most recent valid reading from a set of readings of a sensor. If the most recent
     * doesn't have a valid value (for example, NaN), it is not accepted as a valid result and, therefore, doesn't get
     * stored as the most recent reading.
     *
     * @param id Id of the sensor.
     * @return Most recent (valid) GeoAreaReading.
     */
    private GeoAreaReading getMostRecentValidReading(GeoAreaSensorId id) {
        List<GeoAreaReading> readings = getGeoAreaReadingsBySensorId(id);
        GeoAreaReading mostRecentReading = null;
        for (GeoAreaReading reading : readings) {
            if (Objects.isNull(mostRecentReading)
                    || reading.getDateTime().isAfter(mostRecentReading.getDateTime()) && !Double.isNaN(reading.getValue())) {
                mostRecentReading = reading;
            }
        }
        return mostRecentReading;
    }

    /**
     * Method that returns the list with the nearest sensors to a given location.
     *
     * @param location Location used.
     * @return A list with the nearest sensor (or more, if there are more than one with the same distance).
     */
    private List<GeoAreaSensor> getNearestSensorsToLocation(Location location, List<GeoAreaSensor> geoAreaSensors) {
        List<GeoAreaSensor> nearestSensors = new ArrayList<>();
        Double shortestDistance = Double.NaN;
        for (GeoAreaSensor sensor : geoAreaSensors) {
            Double distance = sensor.getLocation().distanceBetweenTwoLocations(location);
            if (Double.isNaN(shortestDistance) || shortestDistance > distance) {
                shortestDistance = distance;
                nearestSensors.clear();
                nearestSensors.add(sensor);
            } else {
                if (shortestDistance.equals(distance)) {
                    nearestSensors.add(sensor);
                }
            }
        }
        return nearestSensors;
    }

    /**
     * Method that returns the most recent value of a type of sensor, having in consideration the distance between a sensor
     * and a given location. If there is more than one sensor at the same distance, the one with the most recent
     * [valid] reading is considered to be the most adequate.
     *
     * @param location Location to have in consideration.
     * @param typeId   Type of sensor to search for.
     * @return Map with the date time and the value of the latest [valid] reading.
     */
    public HashMap<LocalDateTime, Double> getLatestMeasurement(Location location, SensorTypeId typeId) {
        HashMap<LocalDateTime, Double> map = new HashMap<>();
        GeoAreaReading latestGeoAreaReading = null;
        List<GeoAreaSensor> geoAreaSensorsOfType = getGeoAreasByType(typeId);
        if (!geoAreaSensorsOfType.isEmpty()) {
            List<GeoAreaSensor> nearestSensors = getNearestSensorsToLocation(location, geoAreaSensorsOfType);
            for (GeoAreaSensor sensor : nearestSensors) {
                GeoAreaReading sensorsMostRecentReading = getMostRecentValidReading(sensor.getId());
                if ((Objects.isNull(latestGeoAreaReading))
                        || Objects.nonNull(sensorsMostRecentReading)
                        && sensorsMostRecentReading.getDateTime().isAfter(latestGeoAreaReading.getDateTime())) {
                    latestGeoAreaReading = sensorsMostRecentReading;
                }
            }
        }
        if (Objects.nonNull(latestGeoAreaReading)) {
            map.put(latestGeoAreaReading.getDateTime(), latestGeoAreaReading.getValue());
        }
        return map;
    }

    /**
     * Method that returns the list of sensors of a given type that have readings in a specific date.
     *
     * @param typeId Id of the sensor type.
     * @param date   Given date.
     * @return
     */
    private List<GeoAreaSensor> getSensorsWithReadingsInADayByType(SensorTypeId typeId, LocalDate date) {
        List<GeoAreaSensor> geoAreaSensorsOfType = getGeoAreasByType(typeId);
        List<GeoAreaReading> geoAreaReadings = new ArrayList<>();
        List<GeoAreaSensor> result = new ArrayList<>();
        for (GeoAreaSensor sensor : geoAreaSensorsOfType) {
            geoAreaReadings.addAll(geoAreaAggregateRepo.getGeoAreaReadingRepo().
                    findByDateTime_DateAAndSensorId(date, sensor.getId()));
            result.add(sensor);
        }
        return result;
    }

    /**
     * Method that returns the Total Daily GeoAreaReading of a sensor Type in The Geographic Area. This method considers
     * the maximum value of the sensor on that Area. In case there's no sensors in that Area, it returns Double NaN.
     *
     * @param day
     * @return
     */
    public Double getTotalDailyMeasurement(Location location, SensorTypeId typeId, LocalDate day) {
        GeoAreaReading latestGeoAreaReading = null;
        List<GeoAreaSensor> geoAreaSensors = getSensorsWithReadingsInADayByType(typeId, day);
        List<GeoAreaSensor> nearestSensors = getNearestSensorsToLocation(location, geoAreaSensors);
        /*
        if (!(nearestSensors.isEmpty()) && !(nearestSensors.getListOfSensors().get(0).isMeasurementListEmpty())) {
            latestGeoAreaReading = nearestSensors.getListOfSensors().get(0).getLastMeasurement();

            for (GeoAreaSensor sensor : nearestSensors.getListOfSensors()) {
                List<GeoAreaReading> geoAreaReadingList = sensor.getDailyMeasurement(day);
                int lastReadingPosition = geoAreaReadingList.size() - 1;
                if (!(geoAreaReadingList.isEmpty()) && geoAreaReadingList.get(lastReadingPosition).getDateTime().isAfter(latestGeoAreaReading.getDateTime())) {
                    latestGeoAreaReading = sensor.getLastMeasurement();
                }
                totalDailyMeasurement = latestGeoAreaReading.getValue();
            }
        }
        */
        return latestGeoAreaReading.getValue();
    }

    /**
     * **
     * Method that adds a geographical area to the geoAreaRepository.
     * If it doesn't exist in the repository, it adds the area and return true.
     * If it does, then it just returns true
     @param geoArea
      * @return
     */
    public boolean addGeographicalArea(GeographicalArea geoArea) {
        if (!geoAreaAggregateRepo.getGeoAreaRepo().existsById(geoArea.getId())) {
            geoAreaAggregateRepo.getGeoAreaRepo().save(geoArea);
            return true;
        }
        return false;
    }


    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        Location geoLocation = new Location(latitude, longitude, elevation);
        GeoAreaTypeId geographicalAreaTypeId = new GeoAreaTypeId(geoAreaTypeId);
        return geoAreaAggregateRepo.getGeoAreaRepo().existsById(new GeoAreaId(geoAreaId, geoLocation, geographicalAreaTypeId));

    }

    public List<GeoAreaId> getAllGeoAreasId(){
        Iterable<GeographicalArea> geoAreas = this.geoAreaAggregateRepo.getGeoAreaRepo().findAll();
        List<GeoAreaId> geoAreaIdList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreas) {
            geoAreaIdList.add(geoArea.getId());
        }
        return geoAreaIdList;
    }

    /*
     *//**
     * method that add a geographical area to the list of geographical areas.
     * @param geoArea
     * @return boolean
     *//*
    public boolean addGeoArea(GeographicalArea geoArea) {
        if (!(geoAreaRepository.existsById(geoArea.getId()))) {
            geoAreaList.add(geoArea);
            geoAreaRepository.save(geoArea);
            return true;
        }
        return false;
    }

    */

    /**
     * get a geographical area of a geographical areas list.
     *
     * @param
     * @return a geoArea if exists on the list. If not, return null.
     *//*
    public GeographicalArea getGeographicalArea(GeographicalArea geographicalArea) {
        for (GeographicalArea area : geoAreaList) {
            if (area.equals(geographicalArea)) {
                return area;
            }
        }
        return null;
    /**
     * method that get a list of all geographical areas
     *
     * @return a list of geo areas
     */
    public List<GeographicalArea> getAllGeoAreas() {
        Iterable<GeographicalArea> geoAreaIterables = this.geoAreaAggregateRepo.getGeoAreaRepo().findAll();
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();
        geoAreaIterables.forEach(geographicalAreaList::add);
        return geographicalAreaList;
    }

    /**
     * method that verify if the grid repository is empty, or not
     *
     * @return a boolean
     */
    public boolean isGeoAreaRepositoryEmpty() {
        return this.geoAreaAggregateRepo.getGeoAreaRepo().count() == 0;
    }

    /**
     * method that get a list of sensor types
     *
     * @return a list of sensor types.
     */
    public List<SensorType> getSensorTypeList() {
        return geoAreaAggregateRepo.getGeoAreaSensorRepo().getSensorTypeList();
    }

    /*public boolean addSensorDTO(String id, String sensorName, String sensorTypeId, LocationDTO location, String units) {
        return geoAreaSensorService.addSensorDTO(id, sensorName, sensorTypeId, location, units);
    }*/

    public boolean isNameExistant(String id) {
        return geoAreaSensorService.isNameExistant(id);
    }


    /**
     * method that gel a list of all geo area types
     *
     * @return a list of geo area types
     */
    public List<GeographicalAreaType> getListOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }

    /**
     * method that get a list of geo areas by type
     *
     * @param type
     * @return a list of geo areas by type
     */
    public List<GeographicalArea> getGeoAreasByType(String type) {
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(type);
        return geoAreaAggregateRepo.getGeoAreaRepo().findById_GeographicalAreaTypeId(geoAreaTypeId);
    }

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId){
        return geoAreaSensorService.getSensorById(geoAreaSensorId);
    }

    public boolean isReadingDuplicated(GeoAreaReadingId geoAreaReadingId){
        return geoAreaSensorReadingsService.isReadingDuplicated(geoAreaReadingId);
    }

    public boolean addReading(GeoAreaReading geoAreaReading){
        return geoAreaSensorReadingsService.addReading(geoAreaReading);
    }


    public List<GeographicalAreaType> listOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }

    public boolean addGeoAreaSensor(GeoAreaSensor geoAreaSensor){
        return geoAreaSensorService.addSensor(geoAreaSensor);
    }

    public GeographicalArea getGeoAreaById(GeoAreaId geoAreaId){
        return geoAreaAggregateRepo.getGeoAreaRepo().findById(geoAreaId).orElse(null);
    }

    public List<SensorType> getSensorTypeList() {
        return sensorTypeService.getSensorTypeList();
    }


    public boolean isNameExistant(String id) {
        return geoAreaSensorRepository.existsById(new GeoAreaSensorId(id));
    }

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId){
        return geoAreaSensorRepository.findById(geoAreaSensorId).orElse(null);
    }

    public boolean addSensor(GeoAreaSensor geoAreaSensor){
        if (!geoAreaSensorRepository.existsById(geoAreaSensor.getId())) {
            geoAreaSensorRepository.save(geoAreaSensor);
            return true;
        }
        return false;
    }


}
