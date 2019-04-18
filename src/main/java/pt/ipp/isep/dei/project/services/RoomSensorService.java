package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomSensorRepository;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;
import java.util.Objects;


@Service
public class RoomSensorService {

    @Autowired
    private RoomSensorRepository roomSensorRepository;

    @Autowired
    private RoomReadingService roomReadingService;

    /**
     * method that return true if a given room have a Sensor of a given type
     * or return false if it don't have
     *
     * @param roomId       given room
     * @param sensorTypeId type of sensor
     * @return
     */
    public boolean isRoomWithoutSensorByType(RoomId roomId, SensorTypeId sensorTypeId) {
        RoomSensor roomSensor = this.getRoomSensorByRoomByType(roomId, sensorTypeId);
        return Objects.isNull(roomSensor);
    }

    /**
     * method that get RoomSensor of a given type for a given room
     *
     * @param roomId       room name
     * @param sensorTypeId sensor type
     * @return roomSensor
     */
    public RoomSensor getRoomSensorByRoomByType(RoomId roomId, SensorTypeId sensorTypeId) {
        return this.roomSensorRepository.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    /**
     * method that get the list of readings of a given room sensor
     *
     * @param roomSensorId id of the room sensor
     * @return List<RoomReading>
     */
    public List<RoomReading> getListOfRoomReadingByRoomSensorId(RoomSensorId roomSensorId) {
        return roomReadingService.getListOfRoomReadingByRoomSensorId(roomSensorId);
    }

    public boolean addRoomSensor(RoomSensor sensor) {
        if (!this.roomSensorRepository.existsById(sensor.getId())) {
            this.roomSensorRepository.save(sensor);
            return true;
        }
        return false;
    }

    public RoomSensor getSensorById(RoomSensorId roomSensorId) {
        return roomSensorRepository.findById(roomSensorId).orElse(null);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId) {
        return roomReadingService.isReadingDuplicated(roomReadingId);
    }

    public boolean addReading(RoomReading roomReading) {
        return roomReadingService.addReading(roomReading);
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
    }

 */
}
