package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeoAreaSensorList {

    private List<GeoAreaSensor> listOfSensors;

    /**
     * Constructor method.
     */
    public GeoAreaSensorList() {
        this.listOfSensors = new ArrayList<>();
    }

    /**
     * Get method of the sensor list.
     *
     * @return listOfSensors.
     */
    public List<GeoAreaSensor> getListOfSensors() {
        return listOfSensors;
    }

    /**
     * Set method.
     *
     * @param listOfSensors List of sensors to be used.
     */
    public void setListOfSensors(List<GeoAreaSensor> listOfSensors) {
        this.listOfSensors = listOfSensors;
    }

    /**
     * Method that adds a sensor to the existing list.
     *
     * @param sensor Chosen sensor.
     * @return True or false.
     */
    public boolean addSensor(GeoAreaSensor sensor) {
        if (listOfSensors.contains(sensor) || Objects.isNull(sensor)) {
            return false;
        }
        listOfSensors.add(sensor);
        return true;
    }

    /**
     * Method that creates a new sensor.
     *
     * @param name       Name for the sensor.
     * @param sensorTypeId Type of the sensor.
     * @param location   Location of the sensor.
     * @return sensor.
     */
    public GeoAreaSensor newSensor(SensorId id, String name, SensorTypeId sensorTypeId, Location location, String units) {
        return new GeoAreaSensor(id, name, sensorTypeId, location, units);
    }

    /**
     * Method that returns a list of the latest measurements by sensor type.
     *
     * @param typeId sensor type needed.
     * @return List with the lastest measeruments for the required type.
     */
    public List<Reading> getListOfLatestMeasurementsBySensorType(SensorTypeId typeId) {
        List<Reading> listOfLatestReadings = new ArrayList<>();
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.isMeasurementListEmpty()) {
                break;
            }
            if (sensor.sensorTypeEqualsSensorType(typeId) && (!(Double.isNaN(sensor.getLastMeasurement().getValue())))) {
                listOfLatestReadings.add(sensor.getLastMeasurement());
            }
        }
        return listOfLatestReadings;
    }

    /**
     * method that receives a Sensortype, and gets the latest Reading available by that Sensortype
     *
     * @param typeId Sensortype
     * @return Measuremnt
     */
    public Reading getLatestMeasurementBySensorType(SensorTypeId typeId) {
        List<Reading> listOfLatestReadings = getListOfLatestMeasurementsBySensorType(typeId);
        if (getListOfLatestMeasurementsBySensorType(typeId).isEmpty()) {
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
            for (GeoAreaSensor sensor : listOfSensors) {
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
        for (GeoAreaSensor sensor : listOfSensors) {
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
    public GeoAreaSensorList getNearestSensorsToLocation(Location location) {
        GeoAreaSensorList nearestSensors = new GeoAreaSensorList();
        double shortestDistance = Double.NaN;
        for (GeoAreaSensor sensor : listOfSensors) {
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
     * method that creates the same hashCode to the same GeoAreaSensorList
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
        if (!(obj instanceof GeoAreaSensorList)) {
            return false;
        }
        GeoAreaSensorList listOne = (GeoAreaSensorList) obj;
        return this.listOfSensors.equals(listOne.listOfSensors);
    }

    /**
     * Method that receives a list of sensors and returns the sensor with the most recent reading.
     *
     * @param geoAreaSensorList
     * @return
     */
    public GeoAreaSensor getSensorWithMostRecentReading(GeoAreaSensorList geoAreaSensorList) {
        GeoAreaSensor sensorWithMostRecentReading = geoAreaSensorList.getListOfSensors().get(0);
        for (GeoAreaSensor sensor : geoAreaSensorList.getListOfSensors()) {
            if (!(sensor.isMeasurementListEmpty()) &&
                    sensor.getLastMeasurement().getDateTime().isAfter(sensorWithMostRecentReading.getLastMeasurement().getDateTime())) {
                sensorWithMostRecentReading = sensor;
            }
        }
        return sensorWithMostRecentReading;
    }

    public boolean checkMeasurementExistenceBetweenDates(Location location, LocalDate startDate, LocalDate endDate) {
        return getSensorWithMostRecentReading(getNearestSensorsToLocation(location)).checkMeasurementExistenceBetweenDates(startDate, endDate);

    }

    /**
     * Method that checks if a sensor exists in the list by its id.
     *
     * @param sensorId Id of the sensor.
     * @return True or false.
     */
    public boolean checkIfSensorExistsById(String sensorId) {
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.getId().equals(sensorId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns a sensor by searching for it by its id.
     *
     * @param sensorId Id of the sensor.
     * @return GeoAreaSensor corresponding to the id.
     */
    public GeoAreaSensor getSensorById(String sensorId) {
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.getId().equals(sensorId)) {
                return sensor;
            }
        }
        return null;
    }

    /**
     * Returns the GeoAreaSensor with the most recent reading, of a list of sensors.
     *
     * @return GeoAreaSensor
     */
    public GeoAreaSensor getSensorWithMostRecentReading() {
        GeoAreaSensor sensorWithMostRecentReading = this.getListOfSensors().get(0);
        for (GeoAreaSensor sensor : this.getListOfSensors()) {
            if (!(sensor.isMeasurementListEmpty()) &&
                    sensor.getLastMeasurement().getDateTime().isAfter(sensorWithMostRecentReading.getLastMeasurement().getDateTime())) {
                sensorWithMostRecentReading = sensor;
            }
        }
        return sensorWithMostRecentReading;
    }

    /**
     * Method that removes a sensor by its id.
     *
     * @param sensorId Id of the sensor.
     * @return True or False.
     */
    public boolean removeSensorById(String sensorId) {
        GeoAreaSensor sensor = getSensorById(sensorId);
        if (Objects.nonNull(sensor)) {
            listOfSensors.remove(sensor);
            return true;
        }
        return false;
    }

    /*public boolean geoAreaSensorExists(String id) {
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.getId() == id) {
                return true;
            }

        }
        return false;
    }*/
}