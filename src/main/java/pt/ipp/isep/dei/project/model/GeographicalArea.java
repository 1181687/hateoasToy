package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeographicalArea {
    private String mGeoAreaName;
    private GeographicalAreaType mGeographicalAreaType;
    private GeographicalArea mInsertedIn;
    private Location mLocation;
    private AreaShape mAreaShape;
    private SensorList mSensorList = new SensorList();

    /**
     * constructor of geographical area that receives a name, type, insertedIn, location, areaShape and a sensor list.
     * @param geoAreaName
     * @param geographicalAreaType
     * @param location
     * @param areaShape
     */
    public GeographicalArea(String geoAreaName, GeographicalAreaType geographicalAreaType, Location location, AreaShape areaShape) {
        this.mGeoAreaName = geoAreaName;
        this.mGeographicalAreaType = geographicalAreaType;
        this.mLocation = location;
        this.mAreaShape = areaShape;
    }

    /**
     * get a sensor list in the geographical area.
     * @return a sensor list.
     */
    public SensorList getSensorListInTheGeographicArea() {
        return mSensorList;
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
        return this.mGeoAreaName.equals(ag.mGeoAreaName) && this.mGeographicalAreaType.equals(ag.mGeographicalAreaType) && this.mLocation.equals(ag.mLocation) && this.mAreaShape.equals(ag.mAreaShape);

    }

    /**
     * get the name of a geographical area.
     * @return the name of geographical area.
     */
    public String getNameOfGeoArea() {
        return mGeoAreaName;
    }

    /**
     * get a geographical area type.
     * @return a type of geographical area.
     */
    public GeographicalAreaType getGeoAreaType() {
        return mGeographicalAreaType;
    }

    /**
     * get the location of a geographical area.
     * @return a location
     */
    public Location getLocation() {
        return this.mLocation;
    }

    /**
     * get the inserted area where the geographical area is.
     * @return the area inserted
     */
    public GeographicalArea getInsertedIn() {
        return mInsertedIn;
    }

    /**
     * set the inserted area where the geo area is.
     * @param mainGeoArea Geographical area where THIS Geo Area will be inserted in.
     */
    public void setInsertedIn(GeographicalArea mainGeoArea) {
            this.mInsertedIn = mainGeoArea;
    }

    /**
     * method that calculate the linear distance between two geao areas.
     * @param newGeoArea
     * @return location between two geo areas.
     */
    public double linearDistanceBetweenTwoGeoAreas(GeographicalArea newGeoArea) {
        return this.mLocation.distanceBetweenTwoLocations(newGeoArea.getLocation());
    }

    /**
     * method that check if a sensor is inside the geographical area.
     * @param sensor
     * @return boolean.
     */
    public boolean checkIfSensorInInsideOfGeoArea(Sensor sensor) {

        return mAreaShape.checkIfLocationIsInsertedInAnArea(sensor.getLocation());

    }

    /**
     * this method sort sensors by type in a geo area.
     * @param sensorType
     * @param listOfSensors
     * @return the list of sensors sorted by type.
     */
    public List<Sensor> sortSensorsByType(SensorType sensorType, List<Sensor> listOfSensors) {
        List<Sensor> listOfInsertedSensors = new ArrayList<>();
        for (Sensor sensor : listOfSensors) {
            if (checkIfSensorInInsideOfGeoArea(sensor) && sensor.getSensorType().equals(sensorType)) {
                listOfInsertedSensors.add(sensor);
            }
        }
        return listOfInsertedSensors;
    }

    /**
     * method that list the type of sensors of a certain area in a given period
     * @param type
     * @param sensorList
     * @param startDate
     * @param endDate
     * @return the list of type of sensors of a certain area in a given period
     */
    public List<Sensor> listSensorsOfACertainTypeInAGivenPeriod(SensorType type, List<Sensor> sensorList, LocalDate startDate, LocalDate endDate) {

        List<Sensor> listOfSensorsInGeoAreaByType = sortSensorsByType(type, sensorList);
        List<Sensor> listOfSensorsOfATypeDuringAPeriod = new ArrayList<>();

        for (Sensor sensor : listOfSensorsInGeoAreaByType) {
            if (sensor.checkMeasurementExistenceBetweenDates(startDate, endDate)) {
                listOfSensorsOfATypeDuringAPeriod.add(sensor);
            }
        }
        return listOfSensorsOfATypeDuringAPeriod;
    }

    /**
     * method that sort the types of sensors of a geo area in a day
     * @param type
     * @param sensorList
     * @param day
     * @return the list of sensors by type in a day.
     */
    public List<Sensor> sortSensorTypesInADay(SensorType type, List<Sensor> sensorList, LocalDate day) {
        List<Sensor> sensorListByTypeInAGeoArea = sortSensorsByType(type, sensorList);
        List<Sensor> sensorListByTypeInADay = new ArrayList<>();

        for (Sensor sensor : sensorListByTypeInAGeoArea) {
            if (sensor.checkMeasurementExistenceBetweenDates(day, day)) {
                sensorListByTypeInADay.add(sensor);
            }
        }
        return sensorListByTypeInADay;
    }

    /**
     * that method create a new sensor with a name, a type and a location.
     * @param name
     * @param newSensorType
     * @param newLocation
     * @return a new sensor.
     */
    public Sensor newSensor(String name, SensorType newSensorType, Location newLocation) {
        return new Sensor(name, newSensorType, newLocation);
    }

    /**
     * that method create a new location with a latitude, longitude and altitude
     * @param latitude
     * @param longitude
     * @param altitude
     * @return a new location.
     */
    public Location newLocation(double latitude, double longitude, double altitude) {
        return new Location(latitude, longitude, altitude);
    }

    /** Method that get the list of sensors that exists in an area, with a certain type of sensor.
     * @param type
     * @return sensor list.
     */
    public SensorList getTheSensorListOfAGivenType(SensorType type) {
        GeographicalArea areaToBeUsed = new GeographicalArea(mGeoAreaName, mGeographicalAreaType, mLocation, mAreaShape);
        areaToBeUsed.setInsertedIn(mInsertedIn);
        areaToBeUsed.getSensorListInTheGeographicArea().setSensorList(mSensorList.getSensorList());

        SensorList sensorList = new SensorList();
        sensorList.setSensorList(sortSensorsByType(type, areaToBeUsed.getSensorListInTheGeographicArea().getSensorList()));
        while (sensorList.getSensorList().isEmpty()) {
            if (areaToBeUsed.getInsertedIn() != null) {
                areaToBeUsed.getSensorListInTheGeographicArea().setSensorList(areaToBeUsed.getInsertedIn().getSensorListInTheGeographicArea().getSensorList());
                areaToBeUsed.setInsertedIn(areaToBeUsed.getInsertedIn().getInsertedIn());
                sensorList.setSensorList(areaToBeUsed.getSensorListInTheGeographicArea().getSensorList());
            } else {
                return sensorList;
            }
        }
        return sensorList;
    }

    /**
     * Method that returns the last measurement of a given type in the area.
     *
     * @param location Location to be used.
     * @param type     Sensor type.
     * @return Last measurement.
     */
    public double getTheLastMeasurement(Location location, SensorType type) {
        SensorList sensorListWithTheRequiredType = getTheSensorListOfAGivenType(type);
        double latestMeasurementValue = Double.NaN;
        if (!sensorListWithTheRequiredType.getSensorList().isEmpty()) {
            SensorList nearestSensors = sensorListWithTheRequiredType.getNearestSensorsToLocation(location);
            Readings latestReadings = null;
            if (nearestSensors.getSensorList().size() > 1) {
                for (Sensor sensor : nearestSensors.getSensorList()) {
                    if (latestReadings == null || sensor.getLastMeasurement().getDateTime().isAfter(latestReadings.getDateTime())) {
                        latestReadings = sensor.getLastMeasurement();
                        latestMeasurementValue = sensor.getLastMeasurement().getValue();
                    }
                }
            } else {
                Sensor nearestSensor = nearestSensors.getSensorList().get(0);
                if (nearestSensor.getLastMeasurement() != null) {
                    latestMeasurementValue = nearestSensor.getLastMeasurement().getValue();
                }
            }
        }
        return latestMeasurementValue;
    }

    /**
     * Method that returns de daily average of the measurements of a list of sensors
     *
     * @param sensorList
     * @param date
     * @return
     */
    public double getDailyAverageOfAListOfSensors(List<Sensor> sensorList, LocalDate date) {
        double dailyAverage = Double.NaN;
        for (Sensor sensor : sensorList) {
            if (!(sensor.getDailyMeasurement(date).isEmpty())) {
                dailyAverage = sensor.getDailyAverage(date);
            }
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
    public List<Double> getDailyAverageMeasurement(SensorType sensorType, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = new ArrayList<>();
        List<Sensor> sensorListWithRightTypeDuringPeriod = listSensorsOfACertainTypeInAGivenPeriod(sensorType, this.mSensorList.getSensorList(), startDate, endDate);

        for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate); dateIterator = dateIterator.plusDays(1)) {
            double dailyAverage = getDailyAverageOfAListOfSensors(sensorListWithRightTypeDuringPeriod, dateIterator);
            if (!Double.isNaN(dailyAverage)) {
                listOfDailyAverages.add(getDailyAverageOfAListOfSensors(sensorListWithRightTypeDuringPeriod, dateIterator));
            }
        }
        return listOfDailyAverages;
    }

    /**
     * Method that returns the Total Daily Readings of a Sensor Type in The Geographic Area. This method considers
     * the maximum value of the Sensor on that Area. In case there's no sensors in that Area, it returns Double NaN.
     *
     * @param sensorType
     * @param day
     * @return
     */
    public double getTotalDailyMeasurement(SensorType sensorType, LocalDate day) {
        double totalDailyMeasurement = 0;
        List<Sensor> sensorListWithSameTypeDuringADay = sortSensorTypesInADay(sensorType, this.mSensorList.getSensorList(), day);
        if(!sensorListWithSameTypeDuringADay.isEmpty()) {
            for (Sensor sensor : sensorListWithSameTypeDuringADay) {
                if (totalDailyMeasurement < sensor.getTotalDailyMeasurements(day)) {
                    totalDailyMeasurement = sensor.getTotalDailyMeasurements(day);
                }
            }
        } else {totalDailyMeasurement = Double.NaN;}
        return totalDailyMeasurement;
    }
}