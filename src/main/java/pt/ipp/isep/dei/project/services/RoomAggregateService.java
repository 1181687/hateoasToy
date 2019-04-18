package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomAggregateRepository;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoomAggregateService {

    @Autowired
    private RoomAggregateRepository roomAggregateRepo;


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
        return this.roomAggregateRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    /**
     * method that get the list of readings of a given room sensor
     *
     * @param roomSensorId id of the room sensor
     * @return List<RoomReading>
     */
    public List<RoomReading> getListOfRoomReadingByRoomSensorId(RoomSensorId roomSensorId) {
        return this.roomAggregateRepo.findByRoomReadingIdAndRoomSensorId(roomSensorId);
    }

    /**
     * method that gets the latest temperature reading of a given roomSensor
     *
     * @param roomSensorId id of the sensor
     * @return latest roomReading
     */
    public RoomReading getLatestTemperatureReading(RoomSensorId roomSensorId) {

        List<RoomReading> roomReadings = this.getListOfRoomReadingByRoomSensorId(roomSensorId);
        if (roomReadings.isEmpty()) {
            return null;
        }
        RoomReading latestReading = roomReadings.get(0);
        for (RoomReading reading : roomReadings) {
            if (reading.getRoomReadingId().getLocalDateTime().isAfter(latestReading.getRoomReadingId().getLocalDateTime())) {
                latestReading = reading;
            }
        }
        return latestReading;
    }

    /**
     * Method that returns all the rooms in the repo.
     *
     * @return List of Room.
     */
    public List<Room> getAllRooms() {
        Iterable<Room> roomIterable = this.roomAggregateRepo.findAllRooms();
        List<Room> rooms = new ArrayList<>();
        roomIterable.forEach(rooms::add);
        return rooms;
    }
}
