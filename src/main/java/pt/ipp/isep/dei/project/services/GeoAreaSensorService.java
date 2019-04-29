package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.repositories.GeoAreaSensorRepository;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
     * Method that saves a list of sensors that aren't already in the repo (if wanted).
     *
     * @param sensors List of sensors to be analyzed.
     * @param analyze Option to decide if the list is analyzed or not (in terms of repetitions in the repo).
     */
    public boolean saveSensors(List<GeoAreaSensor> sensors, boolean analyze) {
        boolean saved = false;
        List<GeoAreaSensor> geoAreaSensors = new ArrayList<>();
        if (analyze) {
            for (GeoAreaSensor sensor : sensors) {
                if (!geoAreaSensorRepo.existsById(new SensorId(sensor.getId().getSensorId()))) {
                    geoAreaSensors.add(sensor);
                    saved = true;
                }
            }
            geoAreaSensorRepo.saveAll(geoAreaSensors);
            return saved;
        }
        geoAreaSensors = sensors;
        geoAreaSensorRepo.saveAll(geoAreaSensors);
        return true;
    }

    public List<GeoAreaSensor> getSensorsWithReadingsInInterval(GeoAreaId geoAreaId, SensorTypeId sensorTypeId, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> sensorListWithReadings = new ArrayList<>();
        List<GeoAreaSensor> sensors = geoAreaSensorRepo.findByGeoAreaIdAndSensorTypeId(geoAreaId, sensorTypeId);
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


    public Double getDailyAverageBySensorId(SensorId sensorId, LocalDate day) {
        GeoAreaSensor sensor = geoAreaSensorRepo.findGeoAreaSensorsById(sensorId);
        return sensor.getDailyAverage(day);
    }

    /**
     * gets a Map<LocalDate, Double>> with the daily average per LocalDate, of the daily measurements of a sensorType, in a location,
     * in a interval of time. It uses the sensor that is nearest and has the most recent readings.
     *
     * @param location
     * @param geoAreaId
     * @param typeId    type of sensor
     * @param startDate
     * @param endDate
     * @return Map<LocalDate, Double>>
     */
    public Map<LocalDate, Double> getMapAverageOfDailyMeasurements(Location location, GeoAreaId geoAreaId, SensorTypeId typeId, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> sensors = this.getSensorsWithReadingsInInterval(geoAreaId, typeId, startDate, endDate);
        Map<LocalDate, Double> mapOfDailyAverages = new HashMap<>();

        if (!sensors.isEmpty()) {
            GeoAreaSensor sensor = this.getNearestSensorWithMostRecentReading(location, sensors, startDate, endDate);
            for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate); dateIterator = dateIterator.plusDays(1)) {
                double dailyAverage = this.getDailyAverageBySensorId(sensor.getId(), dateIterator);
                if (!Double.isNaN(dailyAverage)) {
                    mapOfDailyAverages.put(dateIterator, dailyAverage);
                }
            }
        }
        return mapOfDailyAverages;
    }

    /**
     * receives a map of Daily Averages in a interval, and gets Map<LocalDate, List<Double>> with list of the Comfort Temperature Min
     * and Max for category One, organized By LocalDate
     *
     * @param mapOfDailyAverages map of Daily Averages in a interval organized by day
     * @return Map<LocalDate, List < Double>> map with List of Comfort Temperature Min and Max,  By Day
     */
    public Map<LocalDate, List<Double>> getMapComfortTemperatureMinMaxByDayIntervalCategoryOne(Map<LocalDate, Double> mapOfDailyAverages) {
        Map<LocalDate, List<Double>> mapComfortTemperatureMinMaxByDay = new HashMap<>();
        Map<LocalDate, Double> cleanList = Utils.removeDoubleNanHashMap(mapOfDailyAverages);

        Set<Map.Entry<LocalDate, Double>> setCleanList = cleanList.entrySet();

        if (!setCleanList.isEmpty()) {

            for (Map.Entry<LocalDate, Double> dailyAverage : setCleanList) {

                double tempComfortMin = 0.33 * dailyAverage.getValue() + 18.8 - 2;
                double tempComfortMax = 0.33 * dailyAverage.getValue() + 18.8 + 2;
                LocalDate localDate = dailyAverage.getKey();

                mapComfortTemperatureMinMaxByDay.put(localDate, new ArrayList<>());
                if (!Double.isNaN(tempComfortMax) || !Double.isNaN(tempComfortMin)) {
                    mapComfortTemperatureMinMaxByDay.get(localDate).add(tempComfortMin);
                    mapComfortTemperatureMinMaxByDay.get(localDate).add(tempComfortMax);
                }
            }
        }
        return mapComfortTemperatureMinMaxByDay;
    }

    public List<LocalDate> getDaysWithoutComfortTemp(Map<LocalDate, List<Double>> mapComfortDailyTemperature) {
        List<LocalDate> listOfDaysWithoutComfortTemp = new ArrayList<>();

        Set<Map.Entry<LocalDate, List<Double>>> set = mapComfortDailyTemperature.entrySet();

        if (!set.isEmpty()) {
            for (Map.Entry<LocalDate, List<Double>> dailyComfortTemp : set) {
                if (Objects.isNull(dailyComfortTemp.getValue())) {
                    listOfDaysWithoutComfortTemp.add(dailyComfortTemp.getKey());
                }
            }
        }
        return listOfDaysWithoutComfortTemp;
    }

}
