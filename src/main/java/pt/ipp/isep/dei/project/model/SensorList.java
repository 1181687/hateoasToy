package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SensorList {
    private List<Sensor> listOfSensors;

    /**
     * Constructor method.
     */
    public SensorList() {
        this.listOfSensors = new ArrayList<>();
    }

    /**
     * Get method of the sensor list.
     *
     * @return listOfSensors.
     */
    public List<Sensor> getListOfSensors() {
        return listOfSensors;
    }

    /**
     * Set method.
     *
     * @param listOfSensors List of sensors to be used.
     */
    public void setListOfSensors(List<Sensor> listOfSensors) {
        this.listOfSensors = listOfSensors;
    }

    /**
     * Method that adds a sensor to the existing list.
     *
     * @param sensor Chosen sensor.
     * @return True or false.
     */
    public boolean addSensor(Sensor sensor) {
        if (!(listOfSensors.contains(sensor))) {
            listOfSensors.add(sensor);
            return true;
        }
        return false;
    }

    /**
     * Method that creates a new sensor.
     *
     * @param name       Name for the sensor.
     * @param sensorType Type of the sensor.
     * @param location   Location of the sensor.
     * @return Sensor.
     */
    public Sensor newSensor(String name, SensorType sensorType, Location location) {
        return new Sensor(name, sensorType, location);
    }

    /**
     * Method that returns a list of the latest measurements by sensor type.
     *
     * @param type Sensor type needed.
     * @return List with the lastest measeruments for the required type.
     */
    public List<Reading> getListOfLatestMeasurementsBySensorType(SensorType type) {
        List<Reading> listOfLatestReadings = new ArrayList<>();
        for (Sensor sensor : listOfSensors) {
            if (sensor.isMeasurementListEmpty()) {
                break;
            }
            if (sensor.sensorTypeEqualsSensorType(type) && (!(Double.isNaN(sensor.getLastMeasurement().getValue())))) {
                listOfLatestReadings.add(sensor.getLastMeasurement());
            }
        }
        return listOfLatestReadings;
    }

    /**
     * method that receives a Sensortype, and gets the latest Reading available by that Sensortype
     *
     * @param type Sensortype
     * @return Measuremnt
     */
    public Reading getLatestMeasurementBySensorType(SensorType type) {
        List<Reading> listOfLatestReadings = getListOfLatestMeasurementsBySensorType(type);
        if (getListOfLatestMeasurementsBySensorType(type).isEmpty()) {
            return null;
        }
        Reading latestReading = listOfLatestReadings.get(0);
        for (Reading reading : listOfLatestReadings) {
            if (reading.getDateTime().isAfter(latestReading.getDateTime())) {
                latestReading = reading;
            }
        }
        return latestReading;
    }

    /**
     * @param type type of sensor (temperature)
     * @param date any given day
     * @return maximum value of the temperature sensor in a given day.
     */
    public double getMaximumMeasureOfTypeOfSensorInGivenDay(SensorType type, LocalDate date) {
        if (!listOfSensors.isEmpty()) {
            double maxValue = listOfSensors.get(0).getMaximumValueOfDay(date);
            for (Sensor sensor : listOfSensors) {
                if (sensor.getSensorType().equals(type) && (!(sensor.getDailyMeasurement(date).isEmpty())) && (Double.compare(sensor.getMaximumValueOfDay(date), maxValue) == 1)) {
                        maxValue = sensor.getMaximumValueOfDay(date);
                }
            }
            return maxValue;
        }
        return Double.NaN;
    }

    /**
     * Method that returns de daily average of the measurements of a list of sensors
     *
     * @param date
     * @return
     */
    public double getDailyAverage(LocalDate date) {
        double dailyAverage = Double.NaN;
        for (Sensor sensor : listOfSensors) {
            if (!(sensor.getDailyMeasurement(date).isEmpty())) {
                dailyAverage = sensor.getDailyAverage(date);
            }
        }
        return dailyAverage;
    }

    /**
     * Method that returns the list with the nearest sensor to a location.
     *
     * @param location Location used.
     * @return A list with the nearest sensor (or more, if there are more than one with the same distance).
     */
    public SensorList getNearestSensorsToLocation(Location location) {
        SensorList nearestSensors = new SensorList();
        double shortestDistance = Double.NaN;
        for (Sensor sensor : listOfSensors) {
            if (Double.isNaN(shortestDistance) || shortestDistance > sensor.distanceBetweenSensorAndLocation(location)) {
                shortestDistance = sensor.distanceBetweenSensorAndLocation(location);
                nearestSensors.getListOfSensors().clear();
                nearestSensors.addSensor(sensor);
            } else {
                Double comparableShortestDistance = shortestDistance;
                if (comparableShortestDistance.equals(sensor.distanceBetweenSensorAndLocation(location))
                        && !sensor.equals(nearestSensors.getListOfSensors().get(0))) {
                    nearestSensors.addSensor(sensor);
                }
            }
        }
        return nearestSensors;
    }


    /**
     * method that get the length of the sensors list.
     */
    public int getLength() {
        return getListOfSensors().size();
    }

    /**
     * method that displays the sensor list content
     *
     * @return content of sensor list
     */
    public String getSensorListToString() {
        StringBuilder content = new StringBuilder();
        int sensorListLength = getLength();
        int numberInTheList = 1;
        for (int i = 1; i <= sensorListLength; i++) {
            content.append(numberInTheList + " - Name of the sensor: " + getListOfSensors().get(i - 1).getSensorName());
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * method that check if sensor list is empty
     */
    public boolean isEmpty() {
        return listOfSensors.isEmpty();
    }


    /**
     * method that creates the same hashCode to the same SensorList
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(listOfSensors);
    }

    /**
     * Equals method to determine if two DeviceList are equal.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorList)) {
            return false;
        }
        SensorList listOne = (SensorList) obj;
        return this.listOfSensors.equals(listOne.listOfSensors);
    }

    /**
     * Method that receives a list of sensors and returns the sensor with the most recent reading.
     *
     * @param sensorList
     * @return
     */
    public Sensor getSensorWithMostRecentReading(SensorList sensorList) {
        Sensor sensorWithMostRecentReading = sensorList.getListOfSensors().get(0);
        for (Sensor sensor : sensorList.getListOfSensors()) {
            if (!(sensor.isMeasurementListEmpty()) &&
                    sensor.getLastMeasurement().getDateTime().isAfter(sensorWithMostRecentReading.getLastMeasurement().getDateTime())) {
                sensorWithMostRecentReading = sensor;
            }
        }
        return sensorWithMostRecentReading;
    }
}