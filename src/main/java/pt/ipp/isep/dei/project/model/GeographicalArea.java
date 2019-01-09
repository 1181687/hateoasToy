package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeographicalArea {
    private String mNomeAreaGeo;
    private GeoAreaType mGeoAreaType;
    private GeographicalArea mInsertedIn;
    private Location mLocation;
    private AreaShape mAreaShape;
    private SensorList mSensorList = new SensorList();

    public GeographicalArea(String mNomeAreaGeo, GeoAreaType mGeoAreaType, Location mLocation, AreaShape mAreaShape) {
        this.mNomeAreaGeo = mNomeAreaGeo;
        this.mGeoAreaType = mGeoAreaType;
        this.mLocation = mLocation;
        this.mAreaShape = mAreaShape;
    }

    public SensorList getmSensorListInTheGeographicArea() {
        return mSensorList;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeographicalArea)) {
            return false;
        }
        GeographicalArea ag = (GeographicalArea) obj;
        return this.mNomeAreaGeo.equals(ag.mNomeAreaGeo) && this.mGeoAreaType.equals(ag.mGeoAreaType) && this.mLocation.equals(ag.mLocation) && this.mAreaShape.equals(ag.mAreaShape);

    }


    public String getNameOfGeoArea() {
        return mNomeAreaGeo;
    }

    public GeoAreaType getGeoAreaType() {
        return mGeoAreaType;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public GeographicalArea getInsertedIn() {
        return mInsertedIn;
    }

    public void setInsertedIn(GeographicalArea mInsertedIn) {
        this.mInsertedIn = mInsertedIn;
    }

    public double linearDistanceBetweenTwoGeoAreas(GeographicalArea newGeoArea) {
        return this.mLocation.distanceBetweenTwoLocations(newGeoArea.getLocation());
    }

    public boolean checkIfSensorInInsideOfGeoArea(Sensor sensor) {

        return mAreaShape.verificaSeLocalizacaoEstaContidaNumaArea(sensor.getmLocation());

    }

    public List<Sensor> sortSensorsInAGeoAreaByType(SensorType sensorType, List<Sensor> listOfSensors) {
        List<Sensor> listOfInsertedSensors = new ArrayList<>();
        for (Sensor sensor : listOfSensors) {
            if (checkIfSensorInInsideOfGeoArea(sensor) && sensor.getmSensorType().equals(sensorType)) {
                listOfInsertedSensors.add(sensor);
            }
        }
        return listOfInsertedSensors;
    }

    public SensorList listSensorsOfACertainTypeInTheGeoAreaInAGivenPeriod(SensorType type, List<Sensor> sensorList, LocalDate startDate, LocalDate endDate) {

        List<Sensor> listOfSensorsInGeoAreaByType = sortSensorsInAGeoAreaByType(type, sensorList);
        SensorList listOfSensorsOfATypeDuringAPeriod = new SensorList();

        for (Sensor sensor : listOfSensorsInGeoAreaByType) {
            if (sensor.checkMeasurementExistenceBetweenDates(startDate, endDate)) {
                listOfSensorsOfATypeDuringAPeriod.addSensorToTheListOfSensors(sensor);
            }
        }
        return listOfSensorsOfATypeDuringAPeriod;
    }

    public List<Sensor> sortSensorTypesOfAGeoAreaInADay(SensorType type, List<Sensor> sensorList, LocalDate day) {
        List<Sensor> sensorListByTypeInAGeoArea = sortSensorsInAGeoAreaByType(type, sensorList);
        List<Sensor> sensorListByTypeInADay = new ArrayList<>();

        for (Sensor sensor : sensorListByTypeInAGeoArea) {
            if (sensor.checkMeasurementExistenceBetweenDates(day, day)) {
                sensorListByTypeInADay.add(sensor);
            }
        }
        return sensorListByTypeInADay;
    }

    public Sensor newSensor(String name, SensorType newSensorType, Location newLocation) {
        return new Sensor(name, newSensorType, newLocation);
    }

    public Location newLocation(double mLatitude, double mLongitude, double mAltitude) {
        return new Location(mLatitude, mLongitude, mAltitude);
    }

    public Sensor getNearestSensorOfALocation (SensorList sensorList, Location location){
        Sensor nearestSensor = sensorList.getmSensorList().get(0);
        double shortestDistance = nearestSensor.distanceBetweenSensorAndLocation(location);
        for (Sensor sensor : sensorList.getmSensorList()) {
            if (shortestDistance > sensor.distanceBetweenSensorAndLocation(location)) {
                shortestDistance = sensor.distanceBetweenSensorAndLocation(location);
                nearestSensor = sensor;
            }
        }
        return nearestSensor;
    }

    /**
     * @param type
     * @return
     */
    public SensorList getTheSensorListInTheFirstAreaWithSensorOfAGivenType(SensorType type) {
        GeographicalArea areaToBeUsed = new GeographicalArea(mNomeAreaGeo, mGeoAreaType, mLocation, mAreaShape);
        areaToBeUsed.setInsertedIn(mInsertedIn);
        areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(mSensorList.getmSensorList());

        SensorList sensorList = new SensorList();
        sensorList.setmSensorList(sortSensorsInAGeoAreaByType(type, areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList()));
        while (sensorList.getmSensorList().isEmpty()) {
            if (areaToBeUsed.getInsertedIn() != null) {
                areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(areaToBeUsed.getInsertedIn().getmSensorListInTheGeographicArea().getmSensorList());
                areaToBeUsed.setInsertedIn(areaToBeUsed.getInsertedIn().getInsertedIn());
                sensorList.setmSensorList(areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList());
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
    public double getTheLastMeasurementInTheArea(Location location, SensorType type) {
        SensorList sensorList = getTheSensorListInTheFirstAreaWithSensorOfAGivenType(type);
        if (!sensorList.getmSensorList().isEmpty()) {
            if (getNearestSensorOfALocation(sensorList, location).getLastMeasurement() == null) {
                return Double.NaN;
            }
            return getNearestSensorOfALocation(sensorList, location).getLastMeasurement().getmValue();
        }
        return Double.NaN;
    }


    /**
     * Method that returns an ArrayList with the daily averages between two dates.
     *
     * @param sensorType
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Double> getDailyAverageMeasurementInTheArea(SensorType sensorType, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = new ArrayList<>();
        SensorList sensorListWithRightTypeDuringPeriod = listSensorsOfACertainTypeInTheGeoAreaInAGivenPeriod(sensorType, this.mSensorList.getmSensorList(), startDate, endDate);

        for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate); dateIterator = dateIterator.plusDays(1)) {
            double dailyAverage = sensorListWithRightTypeDuringPeriod.getDailyAverageOfTheListOfSensors(dateIterator);
            if (!Double.isNaN(dailyAverage)) {
                listOfDailyAverages.add(sensorListWithRightTypeDuringPeriod.getDailyAverageOfTheListOfSensors(dateIterator));
            }
        }
        return listOfDailyAverages;
    }


    /**
     * Method that returns the Total Daily Measurement of a Sensor Type in The Geographic Area. This method considers
     * the maximum value of the Sensor on that Area. In case there's no sensors in that Area, it returns Double NaN.
     *
     * @param sensorType
     * @param day
     * @return
     */
    public double getTotalDailyMeasurementInTheArea(SensorType sensorType, LocalDate day) {
        double totalDailyMeasurement = 0;
        List<Sensor> sensorListWithSameTypeDuringADay = sortSensorTypesOfAGeoAreaInADay(sensorType, this.mSensorList.getmSensorList(), day);
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