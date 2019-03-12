package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class GeographicalArea {
    private String id;
    private String description;
    private GeographicalAreaType geographicalAreaType;
    private GeographicalArea insertedIn;
    private Location location;
    private AreaShape areaShape;
    private SensorList sensorList = new SensorList();

    /**
     * constructor of geographical area that receives a name, type, insertedIn, location, areaShape and a sensor list.
     *
     * @param id
     * @param geographicalAreaType
     * @param location
     * @param areaShape
     */
    public GeographicalArea(String id, String description, GeographicalAreaType geographicalAreaType, Location location, AreaShape areaShape) {
        this.id = id;
        this.description = description;
        this.geographicalAreaType = geographicalAreaType;
        this.location = location;
        this.areaShape = areaShape;
    }

    /**
     * get a sensor list in the geographical area.
     *
     * @return a sensor list.
     */
    public SensorList getSensorListInTheGeographicArea() {
        return sensorList;
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
        return this.id.equals(ag.id) && this.geographicalAreaType.equals(ag.geographicalAreaType) && this.location.equals(ag.location);
    }

    public String getId() {
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
    public GeographicalAreaType getGeoAreaType() {
        return geographicalAreaType;
    }

    public void setGeographicalAreaType(GeographicalAreaType geographicalAreaType) {
        this.geographicalAreaType = geographicalAreaType;
    }

    /**
     * get the location of a geographical area.
     *
     * @return a location
     */
    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
        return this.location.distanceBetweenTwoLocations(newGeoArea.getLocation());
    }

    /**
     * method that check if a sensor is inside the geographical area.
     *
     * @param sensor
     * @return boolean.
     */
    public boolean checkIfSensorInInsideOfGeoArea(Sensor sensor) {

        return areaShape.checkIfLocationIsInsertedInAnArea(sensor.getLocation());

    }

    /**
     * this method get sensors by type inserted in a geo area.
     *
     * @param sensorType
     * @return the Sensorlist of sensors by type.
     */
    public SensorList getSensorsInGeographicalAreaByType(SensorType sensorType) {
        SensorList listOfInsertedSensors = new SensorList();
        for (Sensor sensor : this.sensorList.getListOfSensors()) {
            if (checkIfSensorInInsideOfGeoArea(sensor) && sensor.getSensorType().equals(sensorType)) {
                listOfInsertedSensors.addSensor(sensor);
            }
        }
        return listOfInsertedSensors;
    }

    /**
     * method that list the type of sensors of a certain area in a given period
     *
     * @param type
     * @param startDate
     * @param endDate
     * @return the list of type of sensors of a certain area in a given period
     */
    public SensorList getSensorListByTypeInAPeriod(SensorType type, LocalDate startDate, LocalDate endDate) {

        SensorList listOfSensorsInGeoAreaByType = getSensorsInGeographicalAreaByType(type);
        SensorList listOfSensorsOfATypeDuringAPeriod = new SensorList();

        for (Sensor sensor : listOfSensorsInGeoAreaByType.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(startDate, endDate)) {
                listOfSensorsOfATypeDuringAPeriod.addSensor(sensor);
            }
        }
        return listOfSensorsOfATypeDuringAPeriod;
    }

    /**
     * method that gets a SensorList of a given type of a geo area in a day
     *
     * @param type of sensor
     * @param day
     * @return the list of sensors by type in a day.
     */
    public SensorList getSensorListByTypeInADay(SensorType type, LocalDate day) {
        SensorList sensorListByTypeInAGeoArea = getSensorsInGeographicalAreaByType(type);
        SensorList sensorListByTypeInADay = new SensorList();

        for (Sensor sensor : sensorListByTypeInAGeoArea.getListOfSensors()) {
            if (sensor.checkMeasurementExistenceBetweenDates(day, day)) {
                sensorListByTypeInADay.addSensor(sensor);
            }
        }
        return sensorListByTypeInADay;
    }

    /**
     * that method create a new sensor with a name, a type and a location.
     *
     * @param name
     * @param newSensorType
     * @param newLocation
     * @return a new sensor.
     */
    public Sensor newSensor(String id, String name, SensorType newSensorType, Location newLocation, String units) {
        return new Sensor(id, name, newSensorType, newLocation, units);
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
     * @param type
     * @return sensor list.
     */
    public SensorList getTheSensorListOfAGivenType(SensorType type) {
        GeographicalArea areaToBeUsed = new GeographicalArea(id, description, geographicalAreaType, location, areaShape);
        areaToBeUsed.setInsertedIn(insertedIn);
        SensorList listOfSensors = new SensorList();
        listOfSensors.setListOfSensors(getSensorsInGeographicalAreaByType(type).getListOfSensors());
        while (listOfSensors.getListOfSensors().isEmpty()) {
            if (areaToBeUsed.getInsertedIn() != null) {
                areaToBeUsed.getSensorListInTheGeographicArea().setListOfSensors(areaToBeUsed.getInsertedIn().getSensorListInTheGeographicArea().getListOfSensors());
                areaToBeUsed.setInsertedIn(areaToBeUsed.getInsertedIn().getInsertedIn());
                listOfSensors.setListOfSensors(areaToBeUsed.getSensorListInTheGeographicArea().getListOfSensors());
            } else {
                return listOfSensors;
            }
        }
        return listOfSensors;
    }

    /**
     * Method that returns the last measurement of a given type in the area.
     *
     * @param location Location to be used.
     * @param type     sensor type.
     * @return Last measurement.
     */
    public double getLastMeasurementByLocationType(Location location, SensorType type) {
        SensorList sensorListWithTheRequiredType = getTheSensorListOfAGivenType(type);
        double latestReadingValue = Double.NaN;
        if (!sensorListWithTheRequiredType.getListOfSensors().isEmpty()) {
            SensorList nearestSensors = sensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            Reading latestReading = null;
            for (Sensor sensor : nearestSensors.getListOfSensors()) {
                if ((!Objects.isNull(sensor.getLastMeasurement())) && (Objects.isNull(latestReading) ||
                        sensor.getLastMeasurement().getDateTime().isAfter(latestReading.getDateTime()))) {
                    latestReading = sensor.getLastMeasurement();
                    latestReadingValue = latestReading.getValue();
                }
            }
        }
        return latestReadingValue;
    }

    public LocalDateTime getDateLastMeasurementByLocationType(Location location, SensorType type) {
        SensorList sensorListWithTheRequiredType = getTheSensorListOfAGivenType(type);
        LocalDateTime latestReadingDate = null;
        if (!sensorListWithTheRequiredType.getListOfSensors().isEmpty()) {
            SensorList nearestSensors = sensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            Reading latestReading = null;
            for (Sensor sensor : nearestSensors.getListOfSensors()) {
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
    public double getDailyAverageOfASensor(Sensor sensor, LocalDate date) {
        double dailyAverage = Double.NaN;
        if (!(sensor.getDailyMeasurement(date).isEmpty())) {
            dailyAverage = sensor.getDailyAverage(date);
        }
        return dailyAverage;
    }

    /**
     * Method that returns an ArrayList with the daily averages between two dates.
     *
     * @param sensorType
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Double> getDailyAverageMeasurement(SensorType sensorType, Location location, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = new ArrayList<>();
        SensorList nearestSensorsWithRightTypeDuringPeriod = getSensorListByTypeInAPeriod(sensorType, startDate, endDate).getNearestSensorsToLocation(location);
        if (nearestSensorsWithRightTypeDuringPeriod.isEmpty()) {
            return listOfDailyAverages;
        }
        Sensor nearestSensor = nearestSensorsWithRightTypeDuringPeriod.getSensorWithMostRecentReading(nearestSensorsWithRightTypeDuringPeriod);

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
     * @param sensorType type of sensor
     * @param location   location of the area wanted to get the daily amplitude
     * @param startDate  initial Localdate of the interval
     * @param endDate    final Localdate of the interval
     * @return Map<LocalDate                               ,                                                               Double> map Of Daily Amplitude
     */
    public Map<LocalDate, Double> getDailyAmplitudeInInterval(SensorType sensorType, Location location, LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Double> mapOfDailyAmplitude = new HashMap<>();
        SensorList nearestSensorsWithRightTypeDuringPeriod = getSensorListByTypeInAPeriod(sensorType, startDate, endDate).getNearestSensorsToLocation(location);
        if (nearestSensorsWithRightTypeDuringPeriod.isEmpty()) {
            return mapOfDailyAmplitude;
        }
        Sensor nearestSensor = nearestSensorsWithRightTypeDuringPeriod.getSensorWithMostRecentReading(nearestSensorsWithRightTypeDuringPeriod);

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
     * @return Map<LocalDate                               ,                                                               Double> map Of Highest Daily Amplitude
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
     * @param sensorType
     * @param day
     * @return
     */

    public double getTotalDailyMeasurement(SensorType sensorType, LocalDate day, Location location) {
        double totalDailyMeasurement = Double.NaN;
        SensorList sensorListWithSameTypeDuringADay = getSensorListByTypeInADay(sensorType, day);
        SensorList nearestSensors = sensorListWithSameTypeDuringADay.getNearestSensorsToLocation(location);
        Reading latestReading;
        if (!(nearestSensors.isEmpty()) && !(nearestSensors.getListOfSensors().get(0).isMeasurementListEmpty())) {
            latestReading = nearestSensors.getListOfSensors().get(0).getLastMeasurement();

            for (Sensor sensor : nearestSensors.getListOfSensors()) {
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

    public Reading getHighestReadingOfASensor(Location location, LocalDate startDate, LocalDate endDate) {
        return sensorList.getHighestReadingOfSensor(location, startDate, endDate);
    }

    public Reading getFirstHighestReading(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        Sensor chosenSensor = getNearestSensorWithMostRecentReading(type, location);
        Reading highestReading = chosenSensor.getHighestReading(startDate, endDate);
        if (highestReading != null) {
            for (Reading reading : chosenSensor.getReadingsBetweenDates(startDate, endDate)) {
                Number readingValue = reading.getValue();
                Number highestReadingValue = highestReading.getValue();
                if (readingValue == highestReadingValue) {
                    highestReading = reading;
                    break;
                }
            }
        }
        return highestReading;
    }

    public List<Reading> getReadingListWithoutNulls(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        Sensor chosenSensor = getNearestSensorWithMostRecentReading(type, location);
        List<Reading> readingList = chosenSensor.getReadingsBetweenDates(startDate, endDate);
        for (Reading reading : readingList) {
            if (reading == null) {
                readingList.remove(reading);
            }
        }
        return readingList;
    }


    public double getHighestReadingInInterval(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        Sensor chosenSensor = getNearestSensorWithMostRecentReading(type, location);
        Reading highestReading = chosenSensor.getHighestReading(startDate, endDate);
        return highestReading.getValue();
    }

    public boolean checkMeasurementExistenceBetweenDates(Location location, LocalDate startDate, LocalDate endDate) {
        return sensorList.checkMeasurementExistenceBetweenDates(location, startDate, endDate);
    }

    public Reading getLastLowestMaximumReading(Location location, SensorType sensorType, LocalDate startDate, LocalDate endDate) {
        Sensor sensor = getNearestSensorWithMostRecentReading(sensorType, location);
        if (Objects.isNull(sensor)) {
            return null;
        }
        List<Reading> readings = sensor.getDailyMaxReadingsInAnInterval(startDate, endDate);
        return sensor.getLastLowestReading(readings);
    }

    public Sensor getNearestSensorWithMostRecentReading(SensorType type, Location location) {
        SensorList sensorListWithTheRequiredType = getTheSensorListOfAGivenType(type);

        if (sensorListWithTheRequiredType.isEmpty()) {
            return null;
        }
        return sensorListWithTheRequiredType
                .getNearestSensorsToLocation(location)
                .getSensorWithMostRecentReading();
    }

    public boolean addSensor(Sensor sensor) {
        return this.sensorList.addSensor(sensor);
    }

    public boolean isReadingsListOfSensorEmpty(SensorType type, Location location, LocalDate startDate, LocalDate endDate) {
        return getNearestSensorWithMostRecentReading(type, location) == null;
    }
}