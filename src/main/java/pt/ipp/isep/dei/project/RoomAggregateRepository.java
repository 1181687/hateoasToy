package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;

@Service
public class RoomAggregateRepository {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private RoomSensorRepository roomSensorRepo;

    @Autowired
    private RoomSensorReadingsRepository roomReadingRepo;

    /**
     * Constructor.
     */
    public RoomAggregateRepository() {
        // empty
    }

    /**
     * Get method.
     *
     * @return RoomRepository.
     */
    public RoomRepository getRoomRepo() {
        return roomRepo;
    }

    /**
     * Get method.
     *
     * @return RoomSensorRepository.
     */
    public RoomSensorRepository getRoomSensorRepo() {
        return roomSensorRepo;
    }

    /**
     * Get method.
     *
     * @return RoomSensorReadingsRepository.
     */
    public RoomSensorReadingsRepository getRoomReadingRepo() {
        return roomReadingRepo;
    }

    /**
     * method that get RoomSensor of a given type for a given room
     *
     * @param roomId       room name
     * @param sensorTypeId sensor type
     * @return roomSensor
     */
    public RoomSensor findByRoomIdAndSensorTypeId(RoomId roomId, SensorTypeId sensorTypeId) {
        return roomSensorRepo.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    /**
     * method that get the list of readings of a given room sensor
     *
     * @param roomSensorId id of the room sensor
     * @return List<RoomReading>
     */
    public List<RoomReading> findByRoomReadingIdAndRoomSensorId(RoomSensorId roomSensorId) {
        return roomReadingRepo.findByRoomReadingId_RoomSensorId(roomSensorId);
    }

    /**
     * Method that returns all the rooms in the repo.
     *
     * @return List of Room.
     */
    public Iterable<Room> findAllRooms() {
        return this.roomRepo.findAll();
    }

    public RoomSensor getRoomById(RoomSensorId roomSensorId){
        return this.roomSensorRepo.findById(roomSensorId).orElse(null);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId) {
        return this.roomReadingRepo.existsById(roomReadingId);
    }

    public void saveReading(RoomReading roomReading){
        this.roomReadingRepo.save(roomReading);
    }
}
