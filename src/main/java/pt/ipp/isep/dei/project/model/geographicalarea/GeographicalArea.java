package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.roles.Root;
import pt.ipp.isep.dei.project.utils.Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class GeographicalArea implements Root {
    @EmbeddedId
    private GeoAreaId id;
    private String description;
    @OneToOne
    private GeographicalArea insertedIn;
    @Embedded
    private AreaShape areaShape;
    @Transient
    private GeoAreaSensorList geoAreaSensorList = new GeoAreaSensorList();

    /**
     * Empty constructor.
     */
    protected GeographicalArea() {
        //intentionally empty
    }

    /**
     * constructor of geographical area that receives a name, type, insertedIn, location, areaShape and a sensor list.
     *
     * @param id
     * @param geographicalAreaType
     * @param location
     * @param areaShape
     */
    public GeographicalArea(String id, String description, GeographicalAreaType geographicalAreaType, Location location, AreaShape areaShape) {
        this.id = new GeoAreaId(location, id, geographicalAreaType);
        this.description = description;
        this.areaShape = areaShape;
    }

    /**
     * get a sensor list in the geographical area.
     *
     * @return a sensor list.
     */
    public GeoAreaSensorList getSensorListInTheGeographicArea() {
        return geoAreaSensorList;
    }

    /**
     * method that creates the hashcode to geographical area.
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two geographical area are equal.
     * They are equals if all atributtes (name, type, insertedIn, location, areaShape and a sensor list) are equal.
     *
     * @param obj
     * @return boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeographicalArea)) {
            return false;
        }
        GeographicalArea ag = (GeographicalArea) obj;
        return this.id.equals(ag.id) && this.id.getGeographicalAreaType().equals(ag.id.getGeographicalAreaType()) && this.id.getLocation().equals(ag.id.getLocation());
    }

    public GeoAreaId getId() {
        return id;
    }

    /**
     * get the name of a geographical area.
     *
     * @return the name of geographical area.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get a geographical area type.
     *
     * @return a type of geographical area.
     */
    public GeoAreaTypeId getGeoAreaType() {
        return id.getGeographicalAreaType();
    }

    public void setGeographicalAreaType(GeographicalAreaType geographicalAreaType) {
        this.id.setGeographicalAreaType(geographicalAreaType);
    }

    /**
     * get the location of a geographical area.
     *
     * @return a location
     */
    public Location getLocation() {
        return this.id.getLocation();
    }

    public void setLocation(Location location) {
        this.id.setLocation(location);
    }

    /**
     * get the inserted area where the geographical area is.
     *
     * @return the area inserted
     */
    public GeographicalArea getInsertedIn() {
        return insertedIn;
    }

    /**
     * set the inserted area where the geo area is.
     *
     * @param mainGeoArea Geographical area where THIS Geo Area will be inserted in.
     */
    public void setInsertedIn(GeographicalArea mainGeoArea) {
        this.insertedIn = mainGeoArea;
    }

    public AreaShape getAreaShape() {
        return areaShape;
    }

    public void setAreaShape(AreaShape areaShape) {
        this.areaShape = areaShape;
    }

    /**
     * method that calculate the linear distance between two geao areas.
     *
     * @param newGeoArea
     * @return location between two geo areas.
     */
    public double linearDistanceBetweenTwoGeoAreas(GeographicalArea newGeoArea) {
        return this.id.getLocation().distanceBetweenTwoLocations(newGeoArea.getLocation());
    }

    /**
     * method that check if a sensor is inside the geographical area.
     *
     * @param sensor
     * @return boolean.
     */
    public boolean checkIfSensorInInsideOfGeoArea(GeoAreaSensor sensor) {

        Location sensorLocation = sensor.getLocation();

        double upperLeftCornerLatitude = this.id.getLocation().getLatitude() + (this.areaShape.getWidth() / 2);
        double upperLeftCornerLongitude = this.id.getLocation().getLongitude() - (this.areaShape.getLength() / 2);

        double bottomRightCornerLatitude = this.id.getLocation().getLatitude() - (this.areaShape.getWidth() / 2);
        double bottomRightCornerLongitude = this.id.getLocation().getLongitude() + (this.areaShape.getLength() / 2);

        return (sensorLocation.getLatitude() >= bottomRightCornerLatitude
                && sensorLocation.getLatitude() <= upperLeftCornerLatitude
                && sensorLocation.getLongitude() <= bottomRightCornerLongitude
                && sensorLocation.getLongitude() >= upperLeftCornerLongitude);


    }

    /**
     * this method get sensors by type inserted in a geo area.
     *
     * @param sensorTypeId
     * @return the Sensorlist of sensors by type.
     */
    public GeoAreaSensorList getSensorsByType(SensorTypeId sensorTypeId) {
        GeoAreaSensorList listOfInsertedSensors = new GeoAreaSensorList();
        for (GeoAreaSensor sensor : this.geoAreaSensorList.getListOfSensors()) {
            if (checkIfSensorInInsideOfGeoArea(sensor) && sensor.getSensorType().equals(sensorTypeId)) {
                listOfInsertedSensors.addSensor(sensor);
            }
        }
        return listOfInsertedSensors;
    }

    /**
     * method that list the type of sensors of a certain area in a given period
     *
     * @param typeId
     * @param startDate
     * @param endDate
     * @return the list of type of sensors of a certain area in a given period
     */
    public GeoAreaSensorList getSensorListByTypeInAPeriod(SensorTypeId typeId, LocalDate startDate, LocalDate endDate) {

        GeoAreaSensorList listOfSensorsInGeoAreaByType = getSensorsByType(typeId);
        GeoAreaSensorList listOfSensorsOfATypeDuringAPeriod = new GeoAreaSensorList();

        for (GeoAreaSensor sensor : listOfSensorsInGeoAreaByType.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(startDate, endDate)) {
                listOfSensorsOfATypeDuringAPeriod.addSensor(sensor);
            }
        }
        return listOfSensorsOfATypeDuringAPeriod;
    }

    /**
     * method that gets a GeoAreaSensorList of a given type of a geo area in a day
     *
     * @param typeId of sensor
     * @param day
     * @return the list of sensors by type in a day.
     */
    public GeoAreaSensorList getSensorListByTypeInADay(SensorTypeId typeId, LocalDate day) {
        GeoAreaSensorList geoAreaSensorListByTypeInAGeoArea = getSensorsByType(typeId);
        GeoAreaSensorList geoAreaSensorListByTypeInADay = new GeoAreaSensorList();

        for (GeoAreaSensor sensor : geoAreaSensorListByTypeInAGeoArea.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(day, day)) {
                geoAreaSensorListByTypeInADay.addSensor(sensor);
            }
        }
        return geoAreaSensorListByTypeInADay;
    }

    /**
     * that method create a new sensor with a name, a type and a location.
     *
     * @param name
     * @param newSensorTypeId
     * @param newLocation
     * @return a new sensor.
     */
    public GeoAreaSensor newSensor(SensorId id, String name, SensorTypeId newSensorTypeId, Location newLocation, String units) {
        return new GeoAreaSensor(id, name, newSensorTypeId, newLocation, units);
    }

    /**
     * that method create a new location with a latitude, longitude and altitude
     *
     * @param latitude
     * @param longitude
     * @param altitude
     * @return a new location.
     */
    public Location newLocation(double latitude, double longitude, double altitude) {
        return new Location(latitude, longitude, altitude);
    }

    /**
     * Method that get the list of sensors that exists in an area, with a certain type of sensor.
     *
     * @param typeId
     * @return sensor list.
     */
    public GeoAreaSensorList getFirstSensorsOfATypeInHierarchy(SensorTypeId typeId) {
        GeographicalArea areaToBeUsed = this;
        GeoAreaSensorList listOfSensors = getSensorsByType(typeId);
        while (listOfSensors.getListOfSensors().isEmpty()) {
            if (Objects.nonNull(areaToBeUsed.getInsertedIn())) {
                areaToBeUsed = areaToBeUsed.getInsertedIn();
                listOfSensors.setListOfSensors(areaToBeUsed.getSensorsByType(typeId).getListOfSensors());
            } else {
                return listOfSensors;
            }
        }
        return listOfSensors;
    }

    public boolean isSensorListOfAGivenTypeEmpty(SensorTypeId typeId) {
        return this.getFirstSensorsOfATypeInHierarchy(typeId).isEmpty();
    }

    /**
     * Method that returns the last measurement of a given type in the area.
     *
     * @param location Location to be used.
     * @param typeId   sensor type.
     * @return Last measurement.
     */
    public double getLastMeasurementByLocationType(Location location, SensorTypeId typeId) {
        GeoAreaSensorList geoAreaSensorListWithTheRequiredType = getFirstSensorsOfATypeInHierarchy(typeId);
        double latestReadingValue = Double.NaN;
        if (!geoAreaSensorListWithTheRequiredType.getListOfSensors().isEmpty()) {
            GeoAreaSensorList nearestSensors = geoAreaSensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            Reading latestReading = null;
            for (GeoAreaSensor sensor : nearestSensors.getListOfSensors()) {
                if ((!Objects.isNull(sensor.getLastMeasurement())) && (Objects.isNull(latestReading) ||
                        sensor.getLastMeasurement().getDateTime().isAfter(latestReading.getDateTime()))) {
                    latestReading = sensor.getLastMeasurement();
                    latestReadingValue = latestReading.getValue();
                }
            }
        }
        return latestReadingValue;
    }

    public LocalDateTime getDateLastMeasurementByLocationType(Location location, SensorTypeId typeId) {
        GeoAreaSensorList geoAreaSensorListWithTheRequiredType = getFirstSensorsOfATypeInHierarchy(typeId);
        LocalDateTime latestReadingDate = null;
        if (!geoAreaSensorListWithTheRequiredType.getListOfSensors().isEmpty()) {
            GeoAreaSensorList nearestSensors = geoAreaSensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            Reading latestReading = null;
            for (GeoAreaSensor sensor : nearestSensors.getListOfSensors()) {
                if ((!Objects.isNull(sensor.getLastMeasurement())) && (Objects.isNull(latestReading) ||
                        sensor.getLastMeasurement().getDateTime().isAfter(latestReading.getDateTime()))) {
                    latestReading = sensor.getLastMeasurement();
                    latestReadingDate = latestReading.getDateTime();
                }

            }
        }
        return latestReadingDate;
    }


    /**
     * Method that returns de daily average of the measurements of a sensor.
     *
     * @param date
     * @return
     */
    public double getDailyAverageOfASensor(GeoAreaSensor sensor, LocalDate date) {
        double dailyAverage = Double.NaN;
        if (!(sensor.getDailyMeasurement(date).isEmpty())) {
            dailyAverage = sensor.getDailyAverage(date);
        }
        return dailyAverage;
    }

    /**
     * Method that returns an ArrayList with the daily averages between two dates.
     *
     * @param sensorTypeId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Double> getDailyAverageMeasurement(SensorTypeId sensorTypeId, Location location, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = new ArrayList<>();
        GeoAreaSensorList nearestSensorsWithRightTypeDuringPeriod = getSensorListByTypeInAPeriod(sensorTypeId, startDate, endDate).getNearestSensorsToLocation(location);
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
        return listOfDailyAverages;
    }

    /**
     * get Daily Amplitude Map <localdate, Double> in a given interval of Localdate by given sensortype and location
     *
     * @param sensorTypeId type of sensor
     * @param location     location of the area wanted to get the daily amplitude
     * @param startDate    initial Localdate of the interval
     * @param endDate      final Localdate of the interval
     * @return Map<LocalDate, Double> map Of Daily Amplitude
     */
    public Map<LocalDate, Double> getDailyAmplitudeInInterval(SensorTypeId sensorTypeId, Location location, LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Double> mapOfDailyAmplitude = new HashMap<>();
        GeoAreaSensorList nearestSensorsWithRightTypeDuringPeriod = getSensorListByTypeInAPeriod(sensorTypeId, startDate, endDate).getNearestSensorsToLocation(location);
        if (nearestSensorsWithRightTypeDuringPeriod.isEmpty()) {
            return mapOfDailyAmplitude;
        }
        GeoAreaSensor nearestSensor = nearestSensorsWithRightTypeDuringPeriod.getSensorWithMostRecentReading(nearestSensorsWithRightTypeDuringPeriod);

        for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate.plusDays(1)); dateIterator = dateIterator.plusDays(1)) {

            Double dailyAmplitude = Math.abs(nearestSensor.getMaximumValueOfDay(dateIterator) - nearestSensor.getLowestMeasurementOfDay(dateIterator));
            mapOfDailyAmplitude.put(dateIterator, dailyAmplitude);
        }
        return mapOfDailyAmplitude;
    }

    /**
     * receives a map Of Daily Amplitude and gets the Highest Daily Amplitude (localdate-Double)
     * if there are two equal amplitudes, it gets both.
     *
     * @param mapOfDailyAmplitude given daily Amplitude Map<LocalDate, Double>
     * @return Map<LocalDate, Double> map Of Highest Daily Amplitude
     */
    public Map<LocalDate, Double> getHighestDailyAmplitude(Map<LocalDate, Double> mapOfDailyAmplitude) {

        Map<LocalDate, Double> mapOfHighestDailyAmplitude = new HashMap<>();
        Map<LocalDate, Double> cleanList = Utils.removeDoubleNanHashMap(mapOfDailyAmplitude);

        Set<Map.Entry<LocalDate, Double>> set = cleanList.entrySet();

        if (!set.isEmpty()) {

            Double maximumValueOfInterval = (Collections.max(cleanList.values()));
            for (Map.Entry<LocalDate, Double> dailyAmplitude : set) {

                if (dailyAmplitude.getValue().compareTo(maximumValueOfInterval) == 0) {
                    mapOfHighestDailyAmplitude.put(dailyAmplitude.getKey(), dailyAmplitude.getValue());
                }
            }
        }

        return mapOfHighestDailyAmplitude;
    }

    /**
     * Method that returns the Total Daily Reading of a sensor Type in The Geographic Area. This method considers
     * the maximum value of the sensor on that Area. In case there's no sensors in that Area, it returns Double NaN.
     *
     * @param sensorTypeId
     * @param day
     * @return
     */

    public double getTotalDailyMeasurement(SensorTypeId sensorTypeId, LocalDate day, Location location) {
        double totalDailyMeasurement = Double.NaN;
        GeoAreaSensorList geoAreaSensorListWithSameTypeDuringADay = getSensorListByTypeInADay(sensorTypeId, day);
        GeoAreaSensorList nearestSensors = geoAreaSensorListWithSameTypeDuringADay.getNearestSensorsToLocation(location);
        Reading latestReading;
        if (!(nearestSensors.isEmpty()) && !(nearestSensors.getListOfSensors().get(0).isMeasurementListEmpty())) {
            latestReading = nearestSensors.getListOfSensors().get(0).getLastMeasurement();

            for (GeoAreaSensor sensor : nearestSensors.getListOfSensors()) {
                List<Reading> readingList = sensor.getDailyMeasurement(day);
                int lastReadingPosition = readingList.size() - 1;
                if (!(readingList.isEmpty()) && readingList.get(lastReadingPosition).getDateTime().isAfter(latestReading.getDateTime())) {
                    latestReading = sensor.getLastMeasurement();
                }
                totalDailyMeasurement = latestReading.getValue();
            }
        }
        return totalDailyMeasurement;
    }

    /**
     * method to get the first highest reading of a sensor of a specific type (nearest one with most recent readings)
     * in a given interval
     *
     * @param typeId
     * @param startDate initial date of the period the user wants to consider
     * @param endDate   final date of the period the user wants to consider
     * @return a Reading
     */
    public Reading getFirstHighestReading(SensorTypeId typeId, LocalDate startDate, LocalDate endDate) {
        GeoAreaSensor chosenSensor = getNearestSensorWithMostRecentReading(typeId, this.id.getLocation());
        return chosenSensor.getFirstHighestReading(startDate, endDate);
    }


    /**
     * Method that returns the last lowest maximum Reading in a given period. It takes in consideration the readings
     * of the nearest sensor of a given type that has the most recent reading. If there are no sensors available
     * in the geographical area, the method return a null.
     *
     * @param location     location of the house area
     * @param sensorTypeId the type of the sensor
     * @param startDate    LocalDate of the beginning of the period
     * @param endDate      LocalDate of the ending of the period
     * @return
     */
    public Reading getLastLowestMaximumReading(Location location, SensorTypeId sensorTypeId, LocalDate startDate, LocalDate endDate) {
        GeoAreaSensor sensor = getNearestSensorWithMostRecentReading(sensorTypeId, location);
        if (Objects.isNull(sensor)) {
            return null;
        }
        List<Reading> readings = sensor.getDailyMaxReadingsInAnInterval(startDate, endDate);
        return sensor.getLastLowestReading(readings);
    }

    /**
     * Method that returns the sensor, of a given type, that is closest to the given location and has the most recent
     * reading
     *
     * @param typeId   sensor type
     * @param location location of the house area
     * @return
     */
    public GeoAreaSensor getNearestSensorWithMostRecentReading(SensorTypeId typeId, Location location) {
        GeoAreaSensorList geoAreaSensorListWithTheRequiredType = getFirstSensorsOfATypeInHierarchy(typeId);
        if (geoAreaSensorListWithTheRequiredType.isEmpty()) {
            return null;
        }
        return geoAreaSensorListWithTheRequiredType
                .getNearestSensorsToLocation(location)
                .getSensorWithMostRecentReading();
    }


    public boolean addSensor(GeoAreaSensor sensor) {
        return this.geoAreaSensorList.addSensor(sensor);
    }

    /**
     * Method that removes a sensor by its id.
     *
     * @param sensorId Id of the sensor.
     * @return True or False.
     */
    public boolean removeSensorById(SensorId sensorId) {
        return geoAreaSensorList.removeSensorById(sensorId);
    }
}