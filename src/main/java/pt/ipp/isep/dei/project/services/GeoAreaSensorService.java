package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
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
public class GeoAreaSensorService {

    // Repository
    @Autowired
    GeoAreaSensorRepository geoAreaSensorRepository;

    // Services
    @Autowired
    GeoAreaSensorReadingsService geoAreaSensorReadingsService;
    @Autowired
    SensorTypeService sensorTypeService;


    public List<SensorType> getSensorTypeList() {
        return sensorTypeService.getSensorTypeList();
    }
    

    public boolean addSensor(String id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        if (!geoAreaSensorRepository.existsById(new GeoAreaSensorId(id))) {
            GeoAreaSensor geoAreaSensor = new GeoAreaSensor(id, sensorName, startingDate, sensorTypeId, location, units, geoAreaId);
            geoAreaSensorRepository.save(geoAreaSensor);
            return true;
        }
        return false;
    }

    public boolean isNameExistant(String id) {
        return geoAreaSensorRepository.existsById(new GeoAreaSensorId(id));
    }


    /**
     * Method that returns the list with the nearest sensors to a given location.
     *
     * @param location Location used.
     * @return A list with the nearest sensor (or more, if there are more than one with the same distance).
     */
    public List<GeoAreaSensor> getNearestSensorsToLocation(Location location, List<GeoAreaSensor> geoAreaSensors) {
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
        HashMap<LocalDateTime, Double> map = null;
        GeoAreaReading latestGeoAreaReading = null;
        List<GeoAreaSensor> geoAreaSensorsOfType = geoAreaSensorRepository.findBySensorTypeId(typeId);
        if (!geoAreaSensorsOfType.isEmpty()) {
            List<GeoAreaSensor> nearestSensors = getNearestSensorsToLocation(location, geoAreaSensorsOfType);
            for (GeoAreaSensor sensor : nearestSensors) {
                GeoAreaReading sensorsMostRecentReading = geoAreaSensorReadingsService.getMostRecentValidReading(sensor.getId());
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
}