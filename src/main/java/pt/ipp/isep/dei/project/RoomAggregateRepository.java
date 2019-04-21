package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;

@Service
public class RoomAggregateRepository {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomSensorRepository roomSensorRepository;

    @Autowired
    private RoomSensorReadingsRepository roomReadingRepository;

    /**
     * Constructor.
     */
    public RoomAggregateRepository() {
        // empty
    }

    public List<Room> findAllByHouseGridIdEquals(HouseGridId houseGridId) {
        return roomRepository.findAllByHouseGridIdEquals(houseGridId);
    }

    /**
     * Get method.
     *
     * @return RoomSensorReadingsRepository.
     */
    public RoomSensorReadingsRepository getRoomReadingRepository() {
        return roomReadingRepository;
    }

    /**
     * method that get RoomSensor of a given type for a given room
     *
     * @param roomId       room name
     * @param sensorTypeId sensor type
     * @return roomSensor
     */
    public RoomSensor findByRoomIdAndSensorTypeId(RoomId roomId, SensorTypeId sensorTypeId) {
        return roomSensorRepository.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    /**
     * method that get the list of readings of a given room sensor
     *
     * @param roomSensorId id of the room sensor
     * @return List<RoomReading>
     */
    public List<RoomReading> findByRoomReadingIdAndRoomSensorId(RoomSensorId roomSensorId) {
        return roomReadingRepository.findByRoomReadingId_RoomSensorId(roomSensorId);
    }

    /**
     * Method that returns all the rooms in the repo.
     *
     * @return List of Room.
     */
    public Iterable<Room> findAllRooms() {
        return this.roomRepository.findAll();
    }

    public RoomSensor getRoomSensorById(RoomSensorId roomSensorId){
        return this.roomSensorRepository.findById(roomSensorId).orElse(null);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId) {
        return this.roomReadingRepository.existsById(roomReadingId);
    }

    public void saveReading(RoomReading roomReading){
        this.roomReadingRepository.save(roomReading);
    }

    public boolean addRoomSensor(RoomSensor sensor) {
        if (!this.roomSensorRepository.existsById(sensor.getId())) {
            this.roomSensorRepository.save(sensor);
            return true;
        }
        return false;
    }

    public boolean roomExists (RoomId id){
        return roomRepository.existsById(id);
    }

    public Room getRoomById(RoomId roomId){
        if (roomExists(roomId)){
            roomRepository.findById(roomId);
        }
        return null;
    }
}
