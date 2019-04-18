package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class GeographicalArea {

    @EmbeddedId
    private GeoAreaId id;
    private String description;

    @Transient
    private GeoAreaId parentGeoArea;

    @Embedded
    private AreaShape areaShape;


    protected GeographicalArea(){
        //intentionally empty
    }


    public GeographicalArea(GeoAreaId geoAreaId, String description, AreaShape areaShape) {
        this.id = geoAreaId;
        this.description = description;
        this.areaShape = areaShape;
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
    public String getGeoAreaTypeId() {
        return id.getId();
    }

    /**
     * get the location of a geographical area.
     *
     * @return a location
     */
    public Location getLocation() {
        return this.id.getLocation();
    }


    /**
     * get the inserted area where the geographical area is.
     *
     * @return the area inserted
     */
    public GeoAreaId getParentGeoArea() {
        return parentGeoArea;
    }


    public void setParentGeoArea(GeoAreaId insertedIn) {
        this.parentGeoArea = insertedIn;
    }

    public AreaShape getAreaShape() {
        return areaShape;
    }

    public void setAreaShape(AreaShape areaShape) {
        this.areaShape = areaShape;
    }
/*
    *//**
     * method that calculate the linear distance between two geao areas.
     *
     * @param newGeoArea
     * @return location between two geo areas.
     *//*
    public double linearDistanceBetweenTwoGeoAreas(GeographicalArea newGeoArea) {
        return this.id.getLocation().distanceBetweenTwoLocations(newGeoArea.getLocation());
    }

    *//**
     * method that check if a sensor is inside the geographical area.
     *
     * @param sensor
     * @return boolean.
     *//*
    public boolean checkIfSensorInInsideOfGeoArea(GeoAreaSensor sensor) {

        return areaShape.checkIfLocationIsInsertedInAnArea(sensor.getLocation());

    }

    *//**
     * this method get sensors by type inserted in a geo area.
     *
     * @param sensorType
     * @return the Sensorlist of sensors by type.
     *//*
    public GeoAreaSensorService getSensorsByType(SensorType sensorType) {
        GeoAreaSensorService listOfInsertedSensors = new GeoAreaSensorService();
        for (GeoAreaSensor sensor : this.geoAreaSensorService.getListOfSensors()) {
            if (checkIfSensorInInsideOfGeoArea(sensor) && sensor.getSensorType().equals(sensorType)) {
                listOfInsertedSensors.addGeoAreaSensor(sensor);
            }
        }
        return listOfInsertedSensors;
    }

    *//**
     * method that list the type of sensors of a certain area in a given period
     *
     * @param type
     * @param startDate
     * @param endDate
     * @return the list of type of sensors of a certain area in a given period
     *//*
    public GeoAreaSensorService getSensorListByTypeInAPeriod(SensorType type, LocalDate startDate, LocalDate endDate) {

        GeoAreaSensorService listOfSensorsInGeoAreaByType = getSensorsByType(type);
        GeoAreaSensorService listOfSensorsOfATypeDuringAPeriod = new GeoAreaSensorService();

        for (GeoAreaSensor sensor : listOfSensorsInGeoAreaByType.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(startDate, endDate)) {
                listOfSensorsOfATypeDuringAPeriod.addGeoAreaSensor(sensor);
            }
        }
        return listOfSensorsOfATypeDuringAPeriod;
    }

    *//**
     * method that gets a GeoAreaSensorService of a given type of a geo area in a day
     *
     * @param type of sensor
     * @param day
     * @return the list of sensors by type in a day.
     *//*
    public GeoAreaSensorService getSensorListByTypeInADay(SensorType type, LocalDate day) {
        GeoAreaSensorService geoAreaSensorListByTypeInAGeoArea = getSensorsByType(type);
        GeoAreaSensorService geoAreaSensorListByTypeInADay = new GeoAreaSensorService();

        for (GeoAreaSensor sensor : geoAreaSensorListByTypeInAGeoArea.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(day, day)) {
                geoAreaSensorListByTypeInADay.addGeoAreaSensor(sensor);
            }
        }
        return geoAreaSensorListByTypeInADay;
    }

    *//**
     * that method create a new sensor with a name, a type and a location.
     *
     * @param name
     * @param newSensorType
     * @param newLocation
     * @return a new sensor.
     *//*
    public GeoAreaSensor newSensor(String id, String name, SensorType newSensorType, Location newLocation, String units) {
        return new GeoAreaSensor(id, name, newSensorType, newLocation, units);
    }

    *//**
     * that method create a new location with a latitude, longitude and altitude
     *
     * @param latitude
     * @param longitude
     * @param altitude
     * @return a new location.
     *//*
    public Location newLocation(double latitude, double longitude, double altitude) {
        return new Location(latitude, longitude, altitude);
    }

    *//**
     * Method that get the list of sensors that exists in an area, with a certain type of sensor.
     *
     * @param type
     * @return sensor list.
     *//*
    public GeoAreaSensorService getFirstSensorsOfATypeInHierarchy(SensorType type) {
        GeographicalArea areaToBeUsed = this;
        GeoAreaSensorService listOfSensors = getSensorsByType(type);
        while (listOfSensors.getListOfSensors().isEmpty()) {
            if (Objects.nonNull(areaToBeUsed.getInsertedIn())) {
                areaToBeUsed = areaToBeUsed.getInsertedIn();
                listOfSensors.setListOfSensors(areaToBeUsed.getSensorsByType(type).getListOfSensors());
            } else {
                return listOfSensors;
            }
        }
        return listOfSensors;
    }

    public boolean isSensorListOfAGivenTypeEmpty(SensorType type) {
        return this.getFirstSensorsOfATypeInHierarchy(type).isEmpty();
    }

    *//**
     * Method that returns the last measurement of a given type in the area.
     *
     * @param location Location to be used.
     * @param type     sensor type.
     * @return Last measurement.
     *//*
    public double getLastMeasurementByLocationType(Location location, SensorType type) {
        GeoAreaSensorService geoAreaSensorListWithTheRequiredType = getFirstSensorsOfATypeInHierarchy(type);
        double latestReadingValue = Double.NaN;
        if (!geoAreaSensorListWithTheRequiredType.getListOfSensors().isEmpty()) {
            GeoAreaSensorService nearestSensors = geoAreaSensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            GeoAreaReading latestGeoAreaReading = null;
            for (GeoAreaSensor sensor : nearestSensors.getListOfSensors()) {
                if ((!Objects.isNull(sensor.getLastMeasurement())) && (Objects.isNull(latestGeoAreaReading) ||
                        sensor.getLastMeasurement().getDateTime().isAfter(latestGeoAreaReading.getDateTime()))) {
                    latestGeoAreaReading = sensor.getLastMeasurement();
                    latestReadingValue = latestGeoAreaReading.getValue();
                }
            }
        }
        return latestReadingValue;
    }

    public LocalDateTime getDateLastMeasurementByLocationType(Location location, SensorType type) {
        GeoAreaSensorService geoAreaSensorListWithTheRequiredType = getFirstSensorsOfATypeInHierarchy(type);
        LocalDateTime latestReadingDate = null;
        if (!geoAreaSensorListWithTheRequiredType.getListOfSensors().isEmpty()) {
            GeoAreaSensorService nearestSensors = geoAreaSensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            GeoAreaReading latestGeoAreaReading = null;
            for (GeoAreaSensor sensor : nearestSensors.getListOfSensors()) {
                if ((!Objects.isNull(sensor.getLastMeasurement())) && (Objects.isNull(latestGeoAreaReading) ||
                        sensor.getLastMeasurement().getDateTime().isAfter(latestGeoAreaReading.getDateTime()))) {
                    latestGeoAreaReading = sensor.getLastMeasurement();
                    latestReadingDate = latestGeoAreaReading.getDateTime();
                }

            }
        }
        return latestReadingDate;
    }


    *//**
     * Method that returns de daily average of the measurements of a sensor.
     *
     * @param date
     * @return
     *//*
    public double getDailyAverageOfASensor(GeoAreaSensor sensor, LocalDate date) {
        double dailyAverage = Double.NaN;
        if (!(sensor.getDailyMeasurement(date).isEmpty())) {
            dailyAverage = sensor.getDailyAverage(date);
        }
        return dailyAverage;
    }

    *//**
     * Method that returns an ArrayList with the daily averages between two dates.
     *
     * @param sensorType
     * @param startDate
     * @param endDate
     * @return
     *//*
    public List<Double> getDailyAverageMeasurement(SensorType sensorType, Location location, LocalDate startDate, LocalDate endDate) {
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
        return listOfDailyAverages;
    }

    *//**
     * get Daily Amplitude Map <localdate, Double> in a given interval of Localdate by given sensortype and location
     *
     * @param sensorType type of sensor
     * @param location   location of the area wanted to get the daily amplitude
     * @param startDate  initial Localdate of the interval
     * @param endDate    final Localdate of the interval
     * @return Map<LocalDate                               ,                                                               Double> map Of Daily Amplitude
     *//*
    public Map<LocalDate, Double> getDailyAmplitudeInInterval(SensorType sensorType, Location location, LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Double> mapOfDailyAmplitude = new HashMap<>();
        GeoAreaSensorService nearestSensorsWithRightTypeDuringPeriod = getSensorListByTypeInAPeriod(sensorType, startDate, endDate).getNearestSensorsToLocation(location);
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

    *//**
     * receives a map Of Daily Amplitude and gets the Highest Daily Amplitude (localdate-Double)
     * if there are two equal amplitudes, it gets both.
     *
     * @param mapOfDailyAmplitude given daily Amplitude Map<LocalDate, Double>
     * @return Map<LocalDate, Double> map Of Highest Daily Amplitude
     *//*
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

    *//**
     * Method that returns the Total Daily GeoAreaReading of a sensor Type in The Geographic Area. This method considers
     * the maximum value of the sensor on that Area. In case there's no sensors in that Area, it returns Double NaN.
     *
     * @param sensorType
     * @param day
     * @return
     *//*

    public double getTotalDailyMeasurement(SensorType sensorType, LocalDate day, Location location) {
        double totalDailyMeasurement = Double.NaN;
        GeoAreaSensorService geoAreaSensorListWithSameTypeDuringADay = getSensorListByTypeInADay(sensorType, day);
        GeoAreaSensorService nearestSensors = geoAreaSensorListWithSameTypeDuringADay.getNearestSensorsToLocation(location);
        GeoAreaReading latestGeoAreaReading;
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
        return totalDailyMeasurement;
    }

    *//**
     * method to get the first highest reading of a sensor of a specific type (nearest one with most recent readings)
     * in a given interval
     *
     * @param startDate initial date of the period the user wants to consider
     * @param endDate   final date of the period the user wants to consider
     * @return a GeoAreaReading
     *//*
    public GeoAreaReading getFirstHighestReading(SensorType type, LocalDate startDate, LocalDate endDate) {
        GeoAreaSensor chosenSensor = getNearestSensorWithMostRecentReading(type, this.id.getLocation());
        return chosenSensor.getFirstHighestReading(startDate, endDate);
    }


    *//**
     * Method that returns the last lowest maximum GeoAreaReading in a given period. It takes in consideration the readings
     * of the nearest sensor of a given type that has the most recent reading. If there are no sensors available
     * in the geographical area, the method return a null.
     * @param location location of the house area
     * @param sensorType the type of the sensor
     * @param startDate LocalDate of the beginning of the period
     * @param endDate LocalDate of the ending of the period
     * @return
     *//*
    public GeoAreaReading getLastLowestMaximumReading(Location location, SensorType sensorType, LocalDate startDate, LocalDate endDate) {
        GeoAreaSensor sensor = getNearestSensorWithMostRecentReading(sensorType, location);
        if (Objects.isNull(sensor)) {
            return null;
        }
        List<GeoAreaReading> geoAreaReadings = sensor.getDailyMaxReadingsInAnInterval(startDate, endDate);
        return sensor.getLastLowestReading(geoAreaReadings);
    }

    *//**
     * Method that returns the sensor, of a given type, that is closest to the given location and has the most recent
     * reading
     * @param type sensor type
     * @param location location of the house area
     * @return
     *//*
    public GeoAreaSensor getNearestSensorWithMostRecentReading(SensorType type, Location location) {
        GeoAreaSensorService geoAreaSensorListWithTheRequiredType = getFirstSensorsOfATypeInHierarchy(type);
        if (geoAreaSensorListWithTheRequiredType.isEmpty()) {
            return null;
        }
        return geoAreaSensorListWithTheRequiredType
                .getNearestSensorsToLocation(location)
                .getSensorWithMostRecentReading();
    }


    public boolean addGeoAreaSensor(GeoAreaSensor sensor) {
        return this.geoAreaSensorService.addGeoAreaSensor(sensor);
    }

    *//**
     * Method that removes a sensor by its id.
     *
     * @param sensorId Id of the sensor.
     * @return True or False.
     *//*
    public boolean removeSensorById(String sensorId) {
        return geoAreaSensorService.removeSensorById(sensorId);
    }*/
}