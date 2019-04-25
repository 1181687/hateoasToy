package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaAggregateRepository;
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
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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


    public List<GeographicalAreaType> listOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }

    /**
     * Method that returns the list of readings from a sensor by its id.
     *
     * @param id Id of the sensor.
     * @return List of GeoAreaReading.
     */
    public List<GeoAreaReading> getGeoAreaReadingsBySensorId(GeoAreaSensorId id) {
        return geoAreaAggregateRepo.findByGeoAreaReadingIdGeoAreaSensorId(id);
    }

    public List<GeoAreaReading> getGeoAreaReadingsBySensorIdAndInInterval(GeoAreaSensorId id, LocalDateTime initialDate, LocalDateTime finalDate) {
        return geoAreaAggregateRepo.findByGeoAreaReadingIdGeoAreaSensorIdInInterval(id, initialDate, finalDate);
    }


    /*    *//**
     * Method that returns the list of sensors of a given type that have readings in a specific date.
     *
     * @param typeId Id of the sensor type.
     * @param startDate Given start date.
     * @param endDate Given end date.
     * @return List of sensors with readings in the specific date.
     *//*
    public List<GeoAreaSensor> getSensorsWithReadingsInAPeriodByType(SensorTypeId typeId, LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> result = new ArrayList<>();
        for (GeoAreaSensor sensor : getGeoAreaSensorByType(typeId)) {
            if (geoAreaAggregateRepo.
                    existsByDateTime_DateBetweenAndGeoAreaReadingId_GeoAreaSensorId(startDate, endDate, sensor.getId())) {
                result.add(sensor);
            }
        }
        return result;
    }

    *//**
     * Method that returns the latest reading of a sensor in a specific period (for example, a day, a week, a month).
     * @param geoAreaSensorId Id of the sensor.
     * @param startDate Given start date.
     * @param endDate Given end date.
     * @return Latest reading in the period.
     *//*
    public GeoAreaReading getLatestMeasurementInAPeriod(GeoAreaSensorId geoAreaSensorId, LocalDate startDate, LocalDate endDate) {
        GeoAreaReading latestReading = null;
        List<GeoAreaReading> readings = geoAreaAggregateRepo.
                findByDateTime_DateBetweenAndGeoAreaReadingId_GeoAreaSensorId(startDate, endDate, geoAreaSensorId);
        for (GeoAreaReading reading : readings) {
            if (Objects.isNull(latestReading) || reading.getDateTime().isAfter(latestReading.getDateTime())) {
                latestReading = reading;
            }
        }
        return latestReading;
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


    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        Location geoLocation = new Location(latitude, longitude, elevation);
        GeoAreaTypeId geographicalAreaTypeId = new GeoAreaTypeId(geoAreaTypeId);
        return geoAreaAggregateRepo.existGeoAreaById(new GeoAreaId(geoAreaId, geoLocation, geographicalAreaTypeId));

    }

    public List<GeoAreaId> getAllGeoAreasId() {
        Iterable<GeographicalArea> geoAreas = this.geoAreaAggregateRepo.findAllGeoAreas();
        List<GeoAreaId> geoAreaIdList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreas) {
            geoAreaIdList.add(geoArea.getId());
        }
        return geoAreaIdList;
    }


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


    public List<GeoAreaReading> getReadingListInInterval(GeoAreaSensor sensor, LocalDate initialDate, LocalDate finalDate) {
        List<GeoAreaReading> sensorReadings = getGeoAreaReadingsBySensorId(sensor.getId());
        List<GeoAreaReading> sensorReadingsInInterval = new ArrayList<>();
        for (GeoAreaReading geoAreaReading : sensorReadings) {
            if (!geoAreaReading.getDateTime().toLocalDate().isBefore(initialDate) || !geoAreaReading.getDateTime().toLocalDate().isAfter(finalDate)) {
                sensorReadingsInInterval.add(geoAreaReading);
            }
        }
        return sensorReadingsInInterval;
    }


    /*
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
    /*
    public GeoAreaSensorService getSensorListByTypeInADay(SensorType type, LocalDate day) {
        GeoAreaSensorService geoAreaSensorListByTypeInAGeoArea = getSensorsByType(type);
        GeoAreaSensorService geoAreaSensorListByTypeInADay = new GeoAreaSensorService();
        for (GeoAreaSensor sensor : geoAreaSensorListByTypeInAGeoArea.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(day, day)) {
                geoAreaSensorListByTypeInADay.addSensor(sensor);
            }
        }
        return geoAreaSensorListByTypeInADay;
    }
    */

    public boolean deactivateSensor(GeoAreaSensorId id) {
        return this.geoAreaAggregateRepo.deactivateSensorById(id);
    }

    /*
    public Double getDailyAverageMeasurement(SensorType sensorType, Location location, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = new ArrayList<>();
        GeoAreaSensorService nearestSensorsWithRightTypeDuringPeriod = getSensorListByTypeInAPeriod(sensorType, startDate, endDate).getNearestSensorsToLocation(location);
        if (nearestSensorsWithRightTypeDuringPeriod.isEmpty()) {
            return listOfDailyAverages;
        }
        GeoAreaSensor nearestSensor = nearestSensorsWithRightTypeDuringPeriod.getSensorWithMostRecentReading(nearestSensorsWithRightTypeDuringPeriod);
        for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate); dateIterator = dateIterator.plusDays(1)) {
            double dailyAverage = getDailyAverageOfASensor(nearestSensor, dateIterator);
            if (!Double.isNaN(dailyAverage)) {
                listOfDailyAverages.add(getDailyAverageOfASensor(nearestSensor, dateIterator));
            }
        }
        double sum = 0;
        if (listOfDailyAverages.isEmpty()) {
            return 0.0;
        }
        for (int i = 0; i < listOfDailyAverages.size(); i++) {
            sum += listOfDailyAverages.get(i);
        }
        return sum / listOfDailyAverages.size();
    }
    */

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
                List<GeoAreaReading> readings = getGeoAreaReadingsBySensorIdAndInInterval(sensor.getId(), initialDate1, finalDate1);
                GeoAreaReading sensorsMostRecentReading = getMostRecentValidReading(readings);
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
                List<GeoAreaReading> readings = getGeoAreaReadingsBySensorId(sensor.getId());
                GeoAreaReading sensorsMostRecentReading = getMostRecentValidReading(readings);
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

    public GeoAreaSensor getNearestSensorWithMostRecentReading(Location location, List<GeoAreaSensor> geoAreaSensors, LocalDate startDate, LocalDate finalDate) {
        List<GeoAreaSensor> nearestSensors = this.getNearestSensors(location, geoAreaSensors);
        if (nearestSensors.size() > 1) {
            GeoAreaReading mostRecentReading = getLatestGeoAreaReadingInInterval(nearestSensors, startDate, finalDate);
            return geoAreaAggregateRepo.getSensorById(mostRecentReading.getSensorId());
        }
        return nearestSensors.get(0);
    }

    public List<GeoAreaSensor> getSensorsByGeoAreaIdAndSensorTypeId(GeoAreaId geoAreaId, SensorTypeId sensorTypeId) {
        return geoAreaAggregateRepo.findByGeoAreaIdAndSensorTypeId(geoAreaId, sensorTypeId);
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
     * Method that returns all the sensors that are in a geo area and are of the required type.
     *
     * @param geoAreaId    Id of the geo area where the sensors are.
     * @param sensorTypeId Type of sensors to search for.
     * @return List of the sensors that correspond to the criteria used.
     */
    public List<GeoAreaSensor> getSensorsWithReadingsInInterval(GeoAreaId geoAreaId, SensorTypeId sensorTypeId,
                                                                LocalDate startDate, LocalDate endDate) {
        List<GeoAreaSensor> sensorListWithReadings = new ArrayList<>();
        LocalDateTime startDate1 = startDate.atStartOfDay();
        LocalDateTime endDate1 = endDate.atTime(23, 59, 59);
        List<GeoAreaSensor> sensors = geoAreaAggregateRepo.findByGeoAreaIdAndSensorTypeId(geoAreaId, sensorTypeId);
        for (GeoAreaSensor sensor : sensors) {
            if (geoAreaAggregateRepo.existsReadingBySensorIdAndInterval(sensor.getId(), startDate1, endDate1)) {
                sensorListWithReadings.add(sensor);
            }
        }
        return sensorListWithReadings;
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
        List<GeoAreaSensor> sensors = this.getSensorsByGeoAreaIdAndSensorTypeId(geoAreaId, typeId);
        while (sensors.isEmpty()) {
            GeographicalArea geographicalArea = geoAreaAggregateRepo.getGeoAreaById(geoAreaId);
            if (Objects.nonNull(geographicalArea.getParentGeoArea())) {
                sensors = this.getSensorsByGeoAreaIdAndSensorTypeId(geographicalArea.getParentGeoArea().getId(), typeId);
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
        List<GeoAreaSensor> sensors = getSensorsWithReadingsInInterval(geoAreaId, typeId, day, day);
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


    // getfirstsensorofatypeinhierarchy
    //getnearestsensorswithmostrecentreading

    //getMapAverageofdailyMeasurement
    //getMapComfortTemperatureMinMaxByDayInterval
    //getIntervalRoomReadinsByRoom
    //getMapInstantsAboveComfortTemperatureInInterval
    //get listofinstantsAboveComfortTemperatureInInterval

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
                double dailyAverage = this.getDailyAverageOfASensor(sensor.getId(), dateIterator);
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

        Set<Map.Entry<LocalDate, Double>> setCleanList = cleanList.entrySet();//temos de limpar porque se tiver doubleNans vai dizer que esse numero é maior qd comparar

        // de algum local date tiver um value null, vamos ter de eliminar esse dia das contas e do mapper e avisar o user
        if (!setCleanList.isEmpty()) {

            for (Map.Entry<LocalDate, Double> dailyAverage : setCleanList) {

                double tempComfortMin = 0.33 * dailyAverage.getValue() + 18.8 - 2;
                double tempComfortMax = 0.33 * dailyAverage.getValue() + 18.8 + 2;
                LocalDate localDate = dailyAverage.getKey();

                mapComfortTemperatureMinMaxByDay.put(localDate, new ArrayList<>());
                mapComfortTemperatureMinMaxByDay.get(localDate).add(tempComfortMin);
                mapComfortTemperatureMinMaxByDay.get(localDate).add(tempComfortMax);
            }
        }

        return mapComfortTemperatureMinMaxByDay;
    }
}
