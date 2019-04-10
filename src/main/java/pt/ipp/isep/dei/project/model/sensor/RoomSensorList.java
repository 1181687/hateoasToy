package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Reading;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomSensorList {

    private List<RoomSensor> listOfSensors;

    public RoomSensorList() {
        this.listOfSensors = new ArrayList<>();
    }

    public boolean addSensor(RoomSensor sensor) {
        if (listOfSensors.contains(sensor)) {
            return false;
        }
        listOfSensors.add(sensor);

        return true;
    }

    public double getMaximumMeasureOfTypeOfSensorInGivenDay(SensorType type, LocalDate date) {
        if (!listOfSensors.isEmpty()) {
            double maxValue = listOfSensors.get(0).getMaximumValueOfDay(date);
            for (RoomSensor sensor : listOfSensors) {
                if (sensor.getSensorType().equals(type) && (!(sensor.getDailyMeasurement(date).isEmpty())) && (Double.compare(sensor.getMaximumValueOfDay(date), maxValue) == 1)) {
                    maxValue = sensor.getMaximumValueOfDay(date);
                }
            }
            return maxValue;
        }
        return Double.NaN;
    }

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

    public List<Reading> getListOfLatestMeasurementsBySensorType(SensorType type) {
        List<Reading> listOfLatestReadings = new ArrayList<>();
        for (RoomSensor sensor : listOfSensors) {
            if (sensor.isMeasurementListEmpty()) {
                break;
            }
            if (sensor.sensorTypeEqualsSensorType(type) && (!(Double.isNaN(sensor.getLastMeasurement().getValue())))) {
                listOfLatestReadings.add(sensor.getLastMeasurement());
            }
        }
        return listOfLatestReadings;
    }


}
