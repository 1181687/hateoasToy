package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomSensorRepository;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import java.util.List;

import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoomSensorService {

    @Autowired
    private RoomSensorRepository roomSensorRepository;

    @Autowired
    private RoomReadingService roomReadingService;


    public boolean addRoomSensor(RoomSensor sensor) {
        if (this.roomSensorRepository.existsById(sensor.getId())) {
            this.roomSensorRepository.save(sensor);
            return true;
        }
        return false;
    }

    public RoomSensor getSensorById(RoomSensorId roomSensorId) {
        return roomSensorRepository.findById(roomSensorId).orElse(null);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId){
        return roomReadingService.isReadingDuplicated(roomReadingId);
    }

    public boolean addReading(RoomReading roomReading){
        return roomReadingService.addReading(roomReading);
    }
}

    /*public boolean addSensor(RoomSensor sensor) {
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
*/
/*
    public RoomReading getLatestMeasurementBySensorType(SensorType type, RoomId roomId) {
        List<RoomReading> listOfLatestReadings = getListOfLatestMeasurementsBySensorType(type);
        if (getListOfLatestMeasurementsBySensorType(type).isEmpty()) {
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

    public List<RoomReading> getListOfLatestMeasurementsBySensorType(SensorType type) {
        List<RoomReading> listOfLatestReadings = new ArrayList<>();
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

    public RoomSensor getSensorById(String sensorId) {
        for (RoomSensor sensor : listOfSensors) {
            if (sensor.getId().equalsIgnoreCase(sensorId)) {
                return sensor;
            }
        }
        return null;
    }

    public boolean roomSensorExists(String id) {
        for (RoomSensor sensor : listOfSensors) {
            if (sensor.getId() == id) {
                return true;
            }

        }
        return false;
    }*/
