package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.RoomReading;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class RoomSensorList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private List<RoomSensor> listOfSensors;

    public RoomSensorList() {
        this.listOfSensors = new ArrayList<>();
    }

    public boolean addSensor(RoomSensor sensor) {
        if (listOfSensors.contains(sensor) || Objects.isNull(sensor)) {
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

    public RoomReading getLatestMeasurementBySensorType(SensorTypeId typeId) {
        List<RoomReading> listOfLatestReadings = getListOfLatestMeasurementsBySensorType(typeId);
        if (getListOfLatestMeasurementsBySensorType(typeId).isEmpty()) {
            return null;
        }
        RoomReading latestReading = listOfLatestReadings.get(0);
        for (RoomReading reading : listOfLatestReadings) {
            if (reading.getDateTime().isAfter(latestReading.getDateTime())) {
                latestReading = reading;
            }
        }
        return latestReading;
    }

    public List<RoomReading> getListOfLatestMeasurementsBySensorType(SensorTypeId typeId) {
        List<RoomReading> listOfLatestReadings = new ArrayList<>();
        for (RoomSensor sensor : listOfSensors) {
            if (sensor.isMeasurementListEmpty()) {
                break;
            }
            if (sensor.sensorTypeEqualsSensorType(typeId) && (!(Double.isNaN(sensor.getLastMeasurement().getValue())))) {
                listOfLatestReadings.add(sensor.getLastMeasurement());
            }
        }
        return listOfLatestReadings;
    }

    public RoomSensor getSensorById(SensorId sensorId) {
        for (RoomSensor sensor : listOfSensors) {
            if (sensor.getId().equals(sensorId)) {
                return sensor;
            }
        }
        return null;
    }

    public List<RoomSensor> getListOfSensors() {
        return listOfSensors;
    }

    public boolean roomSensorExists(SensorId id) {
        for (RoomSensor sensor : listOfSensors) {
            if (sensor.getId() == id) {
                return true;
            }

        }
        return false;
    }


}
