package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repositories.GeoAreaSensorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GeoAreaSensorService {
    @Autowired
    private GeoAreaSensorRepository geoAreaSensorRepo;

    /**
     * Method that searches for a sensor by its id.
     *
     * @param id Id of the sensor.
     * @return Sensor required.
     */
    public GeoAreaSensor getSensorById(SensorId id) {
        return geoAreaSensorRepo.findById(id).orElse(null);
    }

    /**
     * Method that saves all the
     */
    public void saveSensor(GeoAreaSensor sensor) {
        geoAreaSensorRepo.save(sensor);
    }

    /**
     * Method that saves all the
     */
    public boolean saveSensors(List<GeoAreaSensor> sensors) {
        boolean saved = false;
        List<GeoAreaSensor> geoAreaSensors = new ArrayList<>();
        for (GeoAreaSensor sensor : sensors) {
            if (!geoAreaSensorRepo.existsById(new SensorId(sensor.getId()))) {
                geoAreaSensors.add(sensor);
                saved = true;
            }
        }
        geoAreaSensorRepo.saveAll(geoAreaSensors);
        return saved;
    }


    public List<GeoAreaSensor> getSensorsWithReadingsInInterval(GeoAreaId geoAreaId, SensorType sensorType, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> sensorListWithReadings = new ArrayList<>();
        //LocalDateTime startDate1 = startDate.atStartOfDay();
        //LocalDateTime endDate1 = endDate.atTime(23, 59, 59);
        List<GeoAreaSensor> sensors = geoAreaSensorRepo.findByGeoAreaIdAndSensorType(geoAreaId, sensorType);
        for (GeoAreaSensor sensor : sensors) {
            if (sensor.existReadingsBetweenDates(startDate, endDate)) {
                sensorListWithReadings.add(sensor);
            }
        }
        return sensorListWithReadings;
    }

    public List<GeoAreaSensor> getNearestSensors(Location location, List<GeoAreaSensor> sensors) {
        List<GeoAreaSensor> nearestSensors = new ArrayList<>();
        Double shortestDistance = Double.NaN;
        if (!sensors.isEmpty()) {
            for (GeoAreaSensor sensor : sensors) {
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
        }
        return nearestSensors;
    }

    public Reading getLatestGeoAreaReadingInInterval(List<GeoAreaSensor> sensors, LocalDate startDate, LocalDate endDate) {
        Reading latestGeoAreaReading = null;
        LocalDateTime initialDate1 = startDate.atStartOfDay();
        LocalDateTime finalDate1 = endDate.atTime(23, 59, 59);
        if (!sensors.isEmpty()) {
            for (GeoAreaSensor sensor : sensors) {
                List<Reading> readings = sensor.getReadingsBetweenDates(startDate, endDate);
                Reading mostRecentValidReading = sensor.getMostRecentValidReading(readings);
                if ((Objects.isNull(latestGeoAreaReading))
                        || Objects.nonNull(mostRecentValidReading)
                        && mostRecentValidReading.getDateTime().isAfter(latestGeoAreaReading.getDateTime())) {
                    latestGeoAreaReading = mostRecentValidReading;
                }
            }
        }
        return latestGeoAreaReading;
    }


    public GeoAreaSensor getNearestSensorWithMostRecentReading(Location location, List<GeoAreaSensor> sensors, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> nearestSensors = this.getNearestSensors(location, sensors);
        GeoAreaSensor nearestSensorWithMostRecentReading = null;
        if (!nearestSensors.isEmpty()) {
            for (GeoAreaSensor nearestOne : nearestSensors) {
                List<Reading> readings = nearestOne.getReadingsBetweenDates(startDate, endDate);
                nearestOne.getMostRecentValidReading(readings);
                if (Objects.isNull(nearestSensorWithMostRecentReading)) {
                    nearestSensorWithMostRecentReading = nearestOne;
                }
            }
        }
        return nearestSensorWithMostRecentReading;
    }


    public Double getDailyAverageOfASensor(SensorId sensorId, LocalDate day) {
        GeoAreaSensor sensor = geoAreaSensorRepo.findGeoAreaSensorsById(sensorId);
        return sensor.getDailyAverage(day);
    }






}
