package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaAggregateRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GeoAreaAggregateService {

    @Autowired
    private GeoAreaAggregateRepository geoAreaAggregateRepo;

    @Autowired
    private SensorTypeService sensorTypeService;

    @Autowired
    private GeoAreaTypeService geoAreaTypeService;

    /**
     * Constructor.
     */
    public GeoAreaAggregateService() {
        // empty
    }

    /**
     * method that get a list of sensor types
     *
     * @return a list of sensor types.
     */
    public List<SensorType> getSensorTypeList() {
        return sensorTypeService.getSensorTypeList();
    }

    /*public boolean addSensorDTO(String id, String sensorName, String sensorTypeId, LocationDTO location, String units) {
        return geoAreaSensorService.addSensorDTO(id, sensorName, sensorTypeId, location, units);
    }*/

    /**
     * **
     * Method that adds a geographical area to the geoAreaRepository.
     * If it doesn't exist in the repository, it adds the area and return true.
     * If it does, then it just returns true
     *
     * @param geoArea
     * @return
     */
    public boolean addGeographicalArea(GeographicalArea geoArea) {
        if (!geoAreaAggregateRepo.existGeoAreaById(geoArea.getId())) {
            geoAreaAggregateRepo.saveGeoArea(geoArea);
            return true;
        }
        return false;
    }

    /**
     * method that add a geographical area to the list of geographical areas.
     * @param geoArea
     * @return boolean
     */
    /*
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
    }*/

    /**
     * method that get a list of all geographical areas
     *
     * @return a list of geo areas
     */
    public List<GeographicalArea> getAllGeoAreas() {
        Iterable<GeographicalArea> geoAreaIterables = this.geoAreaAggregateRepo.findAllGeoAreas();
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
        return this.geoAreaAggregateRepo.numberOfGeoAreasInRepo() == 0;
    }


    public boolean isReadingDuplicated(GeoAreaReadingId geoAreaReadingId) {
        return geoAreaAggregateRepo.isReadingDuplicated(geoAreaReadingId);
    }

    public boolean addReading(GeoAreaReading geoAreaReading) {
        if (!isReadingDuplicated(geoAreaReading.getReadingId())) {
            geoAreaAggregateRepo.saveReading(geoAreaReading);
            return true;
        }
        return false;
    }

    public GeographicalArea getGeoAreaById(GeoAreaId geoAreaId) {
        return geoAreaAggregateRepo.getGeoAreaById(geoAreaId);
    }

    public boolean doesSensorExist(GeoAreaSensorId geoAreaSensorId) {
        return geoAreaAggregateRepo.doesSensorExist(geoAreaSensorId);
    }

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId) {
        return geoAreaAggregateRepo.getSensorById(geoAreaSensorId);
    }

    public boolean addGeoAreaSensor(GeoAreaSensor geoAreaSensor) {
        if (!geoAreaAggregateRepo.doesSensorExist(geoAreaSensor.getId())) {
            geoAreaAggregateRepo.saveGeoAreaSensor(geoAreaSensor);
            return true;
        }
        return false;
    }

    public List<GeoAreaSensor> getSensorsFromGeoArea(GeoAreaId geoAreaId) {
        return this.geoAreaAggregateRepo.findByGeoAreaId(geoAreaId);
    }

    public boolean removeSensor(GeoAreaSensor geoAreaSensor) {
        if (geoAreaAggregateRepo.doesSensorExist(geoAreaSensor.getId())) {
            geoAreaAggregateRepo.removeSensor(geoAreaSensor);
            return true;
        }
        return false;
    }

    public List<GeoAreaSensor> getActiveSensors() {
        return this.geoAreaAggregateRepo.getActiveSensors();
    }

    public boolean deactivateSensor(GeoAreaSensorId id) {
        return this.geoAreaAggregateRepo.deactivateSensorById(id);
    }


    /**
     * Method that returns all the sensors that are in a geo area and are of the required type.
     *
     * @param geoAreaId    Id of the geo area where the sensors are.
     * @param sensorTypeId Type of sensors to search for.
     * @return List of the sensors that correspond to the criteria used.
     */
    public List<GeoAreaSensor> getSensorsWithReadingsInInterval(GeoAreaId geoAreaId, SensorTypeId sensorTypeId, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> sensorListWithReadings = new ArrayList<>();
        LocalDateTime startDate1 = startDate.atStartOfDay();
        LocalDateTime endDate1 = endDate.atTime(23, 59, 59);
        List<GeoAreaSensor> sensors = geoAreaAggregateRepo.getSensorsByGeoAreaIdAndSensorTypeId(geoAreaId, sensorTypeId);
        for (GeoAreaSensor sensor : sensors) {
            if (geoAreaAggregateRepo.existsReadingBySensorIdAndInterval(sensor.getId(), startDate1, endDate1)) {
                sensorListWithReadings.add(sensor);
            }
        }
        return sensorListWithReadings;
    }

    /**
     * Method that returns the list with the nearest sensors to a given location.
     *
     * @param location Location used.
     * @param sensors  List of sensors to be analyzed.
     * @return A list with the nearest sensor (or more, if there are more than one with the same distance).
     */
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

    /**
     * Method that returns the list of readings in a given period.
     *
     * @param sensor      Sensor to be analyzed.
     * @param initialDate Start date.
     * @param finalDate   End date.
     * @return List of readings.
     */
    public List<GeoAreaReading> getReadingListInInterval(GeoAreaSensor sensor, LocalDate initialDate, LocalDate finalDate) {
        List<GeoAreaReading> sensorReadings = geoAreaAggregateRepo.getReadingsBySensorId(sensor.getId());
        List<GeoAreaReading> sensorReadingsInInterval = new ArrayList<>();
        for (GeoAreaReading geoAreaReading : sensorReadings) {
            if (!geoAreaReading.getDateTime().toLocalDate().isBefore(initialDate) || !geoAreaReading.getDateTime().toLocalDate().isAfter(finalDate)) {
                sensorReadingsInInterval.add(geoAreaReading);
            }
        }
        return sensorReadingsInInterval;
    }

    /**
     * Method that returns the most recent valid reading from a set of readings of a sensor. If the most recent
     * doesn't have a valid value (for example, NaN), it is not accepted as a valid result and, therefore, doesn't get
     * stored as the most recent reading.
     *
     * @param readings List of readings to be analyzed.
     * @return Most recent (valid) reading.
     */
    public GeoAreaReading getMostRecentValidReading(List<GeoAreaReading> readings) {
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
     * Method that returns the latest [valid] reading of a list of sensors, having in consideration a given interval.
     *
     * @param sensors   List of sensors to be analyzed.
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Latest reading.
     */
    public GeoAreaReading getLatestGeoAreaReadingInInterval(List<GeoAreaSensor> sensors, LocalDate startDate, LocalDate endDate) {
        GeoAreaReading latestGeoAreaReading = null;
        LocalDateTime initialDate1 = startDate.atStartOfDay();
        LocalDateTime finalDate1 = endDate.atTime(23, 59, 59);
        if (!sensors.isEmpty()) {
            for (GeoAreaSensor sensor : sensors) {
                List<GeoAreaReading> readings = geoAreaAggregateRepo.getReadingsBySensorIdAndInInterval(sensor.getId(), initialDate1, finalDate1);
                GeoAreaReading sensorsMostRecentReading = this.getMostRecentValidReading(readings);
                if ((Objects.isNull(latestGeoAreaReading))
                        || Objects.nonNull(sensorsMostRecentReading)
                        && sensorsMostRecentReading.getDateTime().isAfter(latestGeoAreaReading.getDateTime())) {
                    latestGeoAreaReading = sensorsMostRecentReading;
                }
            }
        }
        return latestGeoAreaReading;
    }

    /**
     * Method that returns the latest [valid] reading of a list of sensors.
     *
     * @param sensors List of sensors to be analyzed.
     * @return Latest reading.
     */
    public GeoAreaReading getLatestGeoAreaReading(List<GeoAreaSensor> sensors) {
        GeoAreaReading latestGeoAreaReading = null;
        if (!sensors.isEmpty()) {
            for (GeoAreaSensor sensor : sensors) {
                List<GeoAreaReading> readings = geoAreaAggregateRepo.getReadingsBySensorId(sensor.getId());
                GeoAreaReading sensorsMostRecentReading = this.getMostRecentValidReading(readings);
                if ((Objects.isNull(latestGeoAreaReading))
                        || Objects.nonNull(sensorsMostRecentReading)
                        && sensorsMostRecentReading.getDateTime().isAfter(latestGeoAreaReading.getDateTime())) {
                    latestGeoAreaReading = sensorsMostRecentReading;
                }
            }
        }
        return latestGeoAreaReading;
    }

    /**
     * Method that searches for the nearest sensor of a list of sensors that are at the same distance to a location,
     * having in consideration which is the one with the most recent reading in the given period.
     *
     * @param location  Location to have in consideration.
     * @param sensors   List of sensors to be analyzed.
     * @param startDate Start date.
     * @param endDate   End date.
     * @return
     */
    public GeoAreaSensor getNearestSensorWithMostRecentReading(Location location, List<GeoAreaSensor> sensors, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> nearestSensors = this.getNearestSensors(location, sensors);
        if (nearestSensors.size() > 1) {
            GeoAreaReading mostRecentReading = this.getLatestGeoAreaReadingInInterval(nearestSensors, startDate, endDate);
            return geoAreaAggregateRepo.getSensorById(mostRecentReading.getSensorId());
        }
        return nearestSensors.get(0);
    }

    /**
     * Method that returns the list of sensors of a certain type that exists in an area.
     * If the geographical area doesn't have sensors of the required type, it's parent geo area is then used to search
     * for the sensors (and so on, until no more parent areas exist).
     *
     * @param geoAreaId Id of the geo area where the sensors will be searched for.
     * @param typeId    Type of sensor to search for.
     * @return List of sensors that correspond to the type and are in the geographical area (or in a parent one).
     */
    public List<GeoAreaSensor> getFirstSensorsOfATypeInHierarchy(GeoAreaId geoAreaId, SensorTypeId typeId) {
        List<GeoAreaSensor> sensors = geoAreaAggregateRepo.getSensorsByGeoAreaIdAndSensorTypeId(geoAreaId, typeId);
        while (sensors.isEmpty()) {
            GeographicalArea geographicalArea = geoAreaAggregateRepo.getGeoAreaById(geoAreaId);
            if (Objects.nonNull(geographicalArea.getParentGeoArea())) {
                sensors = geoAreaAggregateRepo.getSensorsByGeoAreaIdAndSensorTypeId(geographicalArea.getParentGeoArea().getId(), typeId);
            } else {
                return sensors;
            }
        }
        return sensors;
    }

    /**
     * Method that returns the most recent measurement of a certain type, having in consideration the geographical area
     * that is required and the distance between a sensor and a given location.
     *
     * @param location  Location to have in consideration.
     * @param geoAreaId Id of the geo area where the sensors are.
     * @param typeId    Type of sensor to search for.
     * @return Latest [valid] reading.
     */
    public GeoAreaReading getLatestMeasurement(Location location, GeoAreaId geoAreaId, SensorTypeId typeId) {
        GeoAreaReading latestGeoAreaReading = null;
        List<GeoAreaSensor> sensors = this.getFirstSensorsOfATypeInHierarchy(geoAreaId, typeId);
        if (!sensors.isEmpty()) {
            if (sensors.size() > 1) {
                List<GeoAreaSensor> nearestSensors = this.getNearestSensors(location, sensors);
                latestGeoAreaReading = this.getLatestGeoAreaReading(nearestSensors);
            } else {
                latestGeoAreaReading = this.getLatestGeoAreaReading(sensors);
            }
        }
        return latestGeoAreaReading;
    }

    /**
     * Method that returns the total daily measurement of a certain type (probably used for rainfall), having in
     * consideration the geographical area that is required and the distance between a sensor and a given location.
     *
     * @param location  Location to have in consideration.
     * @param geoAreaId Id of the geo area where the sensors are.
     * @param typeId    Type of sensor to search for.
     * @param day       Given day.
     * @return Latest [valid] reading.
     */
    public GeoAreaReading getTotalDailyMeasurement(Location location, GeoAreaId geoAreaId, SensorTypeId typeId, LocalDate day) {
        GeoAreaReading latestGeoAreaReading = null;
        List<GeoAreaSensor> sensors = this.getSensorsWithReadingsInInterval(geoAreaId, typeId, day, day);
        if (!sensors.isEmpty()) {
            if (sensors.size() > 1) {
                List<GeoAreaSensor> nearestSensors = this.getNearestSensors(location, sensors);
                latestGeoAreaReading = this.getLatestGeoAreaReadingInInterval(nearestSensors, day, day);
            } else {
                latestGeoAreaReading = this.getLatestGeoAreaReadingInInterval(sensors, day, day);
            }
        }
        return latestGeoAreaReading;
    }

    /**
     * Method that returns the daily average of a sensor in a given day.
     *
     * @param sensorId Id of the sensor to be analyzed.
     * @param day      Given day.
     * @return Double corresponding to the daily average of a sensor.
     */
    public Double getDailyAverageOfASensor(GeoAreaSensorId sensorId, LocalDate day) {
        double dailyAverage = Double.NaN;
        LocalDateTime startDate = day.atStartOfDay();
        LocalDateTime endDate = day.atTime(23, 59, 59);
        double sum = 0;
        List<GeoAreaReading> readings = geoAreaAggregateRepo.getReadingsBySensorIdAndInInterval(sensorId, startDate, endDate);
        if (!(readings.isEmpty())) {
            for (GeoAreaReading reading : readings) {
                if (!Double.isNaN(reading.getValue())) {
                    sum += reading.getValue();
                }
            }
            dailyAverage = sum / readings.size();
        }
        return dailyAverage;
    }

    /**
     * Method that returns the average of the daily measurements of a certain type, having in consideration the
     * geographical area that is required and the distance between a sensor and a given location.
     *
     * @param location  Location to have in consideration.
     * @param geoAreaId Id of the geo area where the sensors are.
     * @param typeId    Type of sensor to search for.
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Double corresponding to the average of the daily measurements.
     */
    public Double getAverageOfDailyMeasurements(Location location, GeoAreaId geoAreaId, SensorTypeId typeId, LocalDate startDate, LocalDate endDate) {
        double result = Double.NaN;
        List<GeoAreaSensor> sensors = this.getSensorsWithReadingsInInterval(geoAreaId, typeId, startDate, endDate);
        if (!sensors.isEmpty()) {
            List<Double> listOfDailyAverages = new ArrayList<>();
            GeoAreaSensor sensor = this.getNearestSensorWithMostRecentReading(location, sensors, startDate, endDate);
            for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate); dateIterator = dateIterator.plusDays(1)) {
                double dailyAverage = this.getDailyAverageOfASensor(sensor.getId(), dateIterator);
                if (!Double.isNaN(dailyAverage)) {
                    listOfDailyAverages.add(dailyAverage);
                }
            }
            result = listOfDailyAverages.stream().mapToDouble(a -> a).average().orElse(Double.NaN);
        }
        return result;
    }

    /**
     * Method that returns the last lowest maximum GeoAreaReading in a given period. It takes in consideration the readings
     * of the nearest sensor of a given type that has the most recent reading. If there are no sensors available
     * in the geographical area, the method return a null.
     *
     * @param location   location of the house area
     * @param sensorTypeId the type of the sensor
     * @param startDate  LocalDate of the beginning of the period
     * @param endDate    LocalDate of the ending of the period
     * @return
     */
    //GABIX
    /*public GeoAreaReading getLastLowestMaximumReading(Location location, SensorTypeId sensorTypeId, LocalDate startDate, LocalDate endDate) {
        GeoAreaSensor sensor = getNearestSensorWithMostRecentReading(sensorTypeId, location);
        if (Objects.isNull(sensor)) {
            return null;
        }
        List<GeoAreaReading> geoAreaReadings = sensor.getDailyMaxReadingsInAnInterval(startDate, endDate);
        return sensor.getLastLowestReading(geoAreaReadings);
    }*/

    /**
     * Method that returns the reading with the highest temperature in a given period (...)
     *
     * @param location     Location to have in consideration.
     * @param geoAreaId    Id of the geo area where the sensors are.
     * @param sensorTypeId Type of sensor to search for.
     * @param initialDate  Start date.
     * @param finalDate    End date.
     * @return Reading with the highest temperature.
     */
    public GeoAreaReading getReadingWithTheHighestTemperature(Location location, GeoAreaId geoAreaId, SensorTypeId sensorTypeId, LocalDate initialDate, LocalDate finalDate) {
        List<GeoAreaSensor> sensorsWithReadings = this.getSensorsWithReadingsInInterval(geoAreaId, sensorTypeId, initialDate, finalDate);
        GeoAreaReading readingHighestTemp = null;
        if (!sensorsWithReadings.isEmpty()) {
            GeoAreaSensor chosenSensor;
            if (sensorsWithReadings.size() > 1) {
                chosenSensor = this.getNearestSensorWithMostRecentReading(location, sensorsWithReadings, initialDate, finalDate);
            } else {
                chosenSensor = sensorsWithReadings.get(0);
            }
            List<GeoAreaReading> sensorReadings = getReadingListInInterval(chosenSensor, initialDate, finalDate);
            GeoAreaReading readingWithHighestTemperature = sensorReadings.get(0);
            for (GeoAreaReading geoAreaReading : sensorReadings) {
                if (geoAreaReading.getValue() > readingWithHighestTemperature.getValue()) {
                    readingWithHighestTemperature = geoAreaReading;
                }
            }
        }
        return readingHighestTemp;
    }
}
