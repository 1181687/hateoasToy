package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorReadingsRepository;
import pt.ipp.isep.dei.project.GeoAreaSensorRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDateTime;

@Service
public class GeoAreaSensorService {

    @Autowired
    GeoAreaSensorRepository geoAreaSensorRepository;

    @Autowired
    GeoAreaSensorReadingsRepository geoAreaSensorReadingsRepository;


    public boolean addSensor(String id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        if (!geoAreaSensorRepository.existsById(new GeoAreaSensorId(id))) {
            GeoAreaSensor geoAreaSensor = new GeoAreaSensor(id, sensorName, startingDate, sensorTypeId, location, units, geoAreaId);
            geoAreaSensorRepository.save(geoAreaSensor);
            return true;
        }
        return false;
    }

    public boolean isNameExistant(String id) {
        return geoAreaSensorRepository.existsById(new GeoAreaSensorId(id));
    }




/*


   /**
     * Constructor method.
     */
/*
    public GeoAreaSensorService() {
        this.listOfSensors = new ArrayList<>();
    }
/*
    /**
     * Get method of the sensor list.
     *
     * @return listOfSensors.
     */
    /*
    public List<GeoAreaSensor> getListOfSensors() {
        return listOfSensors;
    }
/*
    /**
     * Set method.
     *
     * @param listOfSensors List of sensors to be used.
     */
    /*
    public void setListOfSensors(List<GeoAreaSensor> listOfSensors) {
        this.listOfSensors = listOfSensors;
    }
/*
    /**
     * Method that adds a sensor to the existing list.
     *
     * @param sensor Chosen sensor.
     * @return True or false.
     */
    /*
    public boolean addSensor(GeoAreaSensor sensor) {
        if (listOfSensors.contains(sensor) || Objects.isNull(sensor)) {
            return false;
        }
        listOfSensors.add(sensor);
        return true;
    }
    /*

    /**
     * Method that creates a new sensor.
     *
     * @param name       Name for the sensor.
     * @param sensorType Type of the sensor.
     * @param location   Location of the sensor.
     * @return sensor.
     */
    /*
    public GeoAreaSensor newSensor(String id, String name, SensorType sensorType, Location location, String units) {
        return new GeoAreaSensor(id, name, sensorType, location, units);
    }
    /*

    /**
     * Method that returns a list of the latest measurements by sensor type.
     *
     * @param type sensor type needed.
     * @return List with the lastest measeruments for the required type.
     */
    /*
    public List<GeoAreaReading> getListOfLatestMeasurementsBySensorType(SensorType type) {
        List<GeoAreaReading> listOfLatestGeoAreaReadings = new ArrayList<>();
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.isMeasurementListEmpty()) {
                break;
            }
            if (sensor.sensorTypeEqualsSensorType(type) && (!(Double.isNaN(sensor.getLastMeasurement().getValue())))) {
                listOfLatestGeoAreaReadings.add(sensor.getLastMeasurement());
            }
        }
        return listOfLatestGeoAreaReadings;
    }
    /*

    /**
     * method that receives a Sensortype, and gets the latest GeoAreaReading available by that Sensortype
     *
     * @param type Sensortype
     * @return Measuremnt
     */
    /*
    public GeoAreaReading getLatestMeasurementBySensorType(SensorType type) {
        List<GeoAreaReading> listOfLatestGeoAreaReadings = getListOfLatestMeasurementsBySensorType(type);
        if (getListOfLatestMeasurementsBySensorType(type).isEmpty()) {
            return null;
        }
        GeoAreaReading latestGeoAreaReading = listOfLatestGeoAreaReadings.get(0);
        for (GeoAreaReading geoAreaReading : listOfLatestGeoAreaReadings) {
            if (geoAreaReading.getDateTime().isAfter(latestGeoAreaReading.getDateTime())) {
                latestGeoAreaReading = geoAreaReading;
            }
        }
        return latestGeoAreaReading;
    }
    /*

    /**
     * @param type type of sensor (temperature)
     * @param date any given day
     * @return maximum value of the temperature sensor in a given day.
     */
    /*
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
    /*

    /**
     * Method that returns de daily average of the measurements of a list of sensors
     *
     * @param date
     * @return
     */
    /*
    public double getDailyAverage(LocalDate date) {
        double dailyAverage = Double.NaN;
        for (GeoAreaSensor sensor : listOfSensors) {
            if (!(sensor.getDailyMeasurement(date).isEmpty())) {
                dailyAverage = sensor.getDailyAverage(date);
            }
        }
        return dailyAverage;
    }
    /*

    /**
     * Method that returns the list with the nearest sensor to a location.
     *
     * @param location Location used.
     * @return A list with the nearest sensor (or more, if there are more than one with the same distance).
     */
    /*
    public GeoAreaSensorService getNearestSensorsToLocation(Location location) {
        GeoAreaSensorService nearestSensors = new GeoAreaSensorService();
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
    /*


    /**
     * method that get the length of the sensors list.
     */
    /*
    public int getLength() {
        return getListOfSensors().size();
    }

    /*
    /**
     * method that displays the sensor list content
     *
     * @return content of sensor list
     */
    /*
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
    /*

    /**
     * method that check if sensor list is empty
     */
    /*
    public boolean isEmpty() {
        return listOfSensors.isEmpty();
    }
/*

    /**
     * method that creates the same hashCode to the same GeoAreaSensorService
     *
     * @return the hashcode created
     */
    /*
    @Override
    public int hashCode() {
        return Objects.hash(listOfSensors);
    }
/*
    /**
     * Equals method to determine if two DeviceList are equal.
     *
     * @param obj receives an object
     * @return boolean
     */
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoAreaSensorService)) {
            return false;
        }
        GeoAreaSensorService listOne = (GeoAreaSensorService) obj;
        return this.listOfSensors.equals(listOne.listOfSensors);
    }
    /*

    /**
     * Method that receives a list of sensors and returns the sensor with the most recent reading.
     *
     * @param geoAreaSensorService
     * @return
     */
    /*
    public GeoAreaSensor getSensorWithMostRecentReading(GeoAreaSensorService geoAreaSensorService) {
        GeoAreaSensor sensorWithMostRecentReading = geoAreaSensorService.getListOfSensors().get(0);
        for (GeoAreaSensor sensor : geoAreaSensorService.getListOfSensors()) {
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
    /*

    /**
     * Method that checks if a sensor exists in the list by its id.
     *
     * @param sensorId Id of the sensor.
     * @return True or false.
     */
    /*
    public boolean checkIfSensorExistsById(String sensorId) {
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.getId().equals(sensorId)) {
                return true;
            }
        }
        return false;
    }
    /*


    /**
     * Method that returns a sensor by searching for it by its id.
     *
     * @param sensorId Id of the sensor.
     * @return GeoAreaSensor corresponding to the id.
     */
    /*
    public GeoAreaSensor getSensorById(String sensorId) {
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.getId().equals(sensorId)) {
                return sensor;
            }
        }
        return null;
    }
    /*


    /**
     * Returns the GeoAreaSensor with the most recent reading, of a list of sensors.
     * @return GeoAreaSensor
     */
    /*
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

    /*

    /**
     * Method that removes a sensor by its id.
     *
     * @param sensorId Id of the sensor.
     * @return True or False.
     */

    /*

    public boolean removeSensorById(String sensorId) {
        GeoAreaSensor sensor = getSensorById(sensorId);
        if (Objects.nonNull(sensor)) {
            listOfSensors.remove(sensor);
            return true;
        }
        return false;
    }

    public boolean geoAreaSensorExists(String id) {
        for (GeoAreaSensor sensor : listOfSensors) {
            if (sensor.getId() == id) {
                return true;
            }

        }
        return false;
    }


    */
}