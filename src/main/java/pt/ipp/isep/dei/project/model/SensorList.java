package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SensorList {
    private List<Sensor> mSensorList = new ArrayList<>();

    /**
     * Constructor method.
     */
    public SensorList() {
    }

    /**
     * Get method.
     *
     * @return mSensorList.
     */
    public List<Sensor> getmSensorList() {
        return mSensorList;
    }

    /**
     * Set method.
     *
     * @param mSensorList List of sensors to be used.
     */
    public void setmSensorList(List<Sensor> mSensorList) {
        this.mSensorList = mSensorList;
    }

    /**
     * Method that adds a sensor to the existing list.
     * @param sensor Chosen sensor.
     * @return True or false.
     */
    public boolean addSensorToTheListOfSensors(Sensor sensor) {
        if (!(mSensorList.contains(sensor))) {
            mSensorList.add(sensor);
            return true;
        }
        return false;
    }

    /**
     * Method that creates a new sensor.
     * @param name Name for the sensor.
     * @param sensorType Type of the sensor.
     * @param location Location of the sensor.
     * @return Sensor.
     */
    public Sensor createNewSensor(String name, SensorType sensorType, Location location) {
        return new Sensor(name, sensorType, location);
    }

    /**
     * Method that returns a list of the latest measurements by sensor type.
     * @param type Sensor type needed.
     * @return List with the lastest measeruments for the required type.
     */
    public List<Measurement> getListOfLatestMeasurementsBySensorType(SensorType type) {
        List<Measurement> listOfLatestMeasurements = new ArrayList<>();
        for (Sensor sensor : mSensorList) {
            if (sensor.measurementListIsEmpty()) {
                break;
            }
            if (sensor.sensorTypeEqualsSensorType(type) && (!(Double.isNaN(sensor.getLastMeasurement().getmValue())))) {
                listOfLatestMeasurements.add(sensor.getLastMeasurement());
            }
        }
        return listOfLatestMeasurements;
    }

    /**
     * method that receives a Sensortype, and gets the latest Measurement available by that Sensortype
     * @param type Sensortype
     * @return Measuremnt
     */
    public Measurement getLatestMeasurementBySensorType(SensorType type) {
        List<Measurement> listOfLatestMeasurements = getListOfLatestMeasurementsBySensorType(type);
        if (getListOfLatestMeasurementsBySensorType(type).isEmpty()) {
            return null;
        }
        Measurement latestMeasurement = listOfLatestMeasurements.get(0);
        for (Measurement measurement : listOfLatestMeasurements) {
            if (measurement.getmDateTime().isAfter(latestMeasurement.getmDateTime())) {
                latestMeasurement = measurement;
            }
        }
        return latestMeasurement;
    }

    /**
     * @param type type of sensor (temperature)
     * @param date any given day
     * @return maximum value of the temperature sensor in a given day.
     */
    public double getMaximumMeasureOfATypeOfSensorInAGivenDay(SensorType type, LocalDate date) {
        if (!mSensorList.isEmpty()) {
            double maxValue = mSensorList.get(0).getMaximumValueOfDay(date);
            for (Sensor sensor : mSensorList) {
                if (sensor.getmSensorType().equals(type) && (!(sensor.getDailyMeasurement(date).isEmpty()))) {
                    if (sensor.getMaximumValueOfDay(date) > maxValue) {
                        maxValue = sensor.getMaximumValueOfDay(date);

                    }

                }
            }
            return maxValue;
        }
        return Double.NaN;
    }
}