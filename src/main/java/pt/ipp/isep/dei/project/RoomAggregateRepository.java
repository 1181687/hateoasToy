package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.List;
import java.util.Objects;

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

    public RoomSensor getSensorById(RoomSensorId roomSensorId) {
        return this.roomSensorRepository.findById(roomSensorId).orElse(null);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId) {
        return this.roomReadingRepository.existsById(roomReadingId);
    }

    public void saveReading(RoomReading roomReading){
        this.roomReadingRepository.save(roomReading);
    }

    public Room getRoomdById(RoomId roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public Iterable<RoomSensor> findAllRoomSensors() {
        return this.roomSensorRepository.findAll();
    }

    public Iterable<Device> findAllDevices(RoomId roomId) {
        return this.roomRepository.findByDeviceListIn(roomId);
    }

    public boolean addRoomSensor(RoomSensor sensor) {
        if (!this.roomSensorRepository.existsById(sensor.getId())) {
            this.roomSensorRepository.save(sensor);
            return true;
        }
        return false;
    }

    public boolean roomExists(RoomId id) {
        return roomRepository.existsById(id);
    }

    public boolean roomDeviceListIsEmpty(RoomId id) {
        Room room = this.roomRepository.findById(id).orElse(null);
        if (Objects.nonNull(room)) {
            return room.isDeviceListEmpty();
        }
        return false;
    }

    public Room findRoomById(RoomId id) {
        return this.roomRepository.findById(id).orElse(null);
    }

    public boolean detachRoomFromHouseGrid(RoomId roomId) {
        Room room = this.roomRepository.findById(roomId).orElse(null);
        if (Objects.nonNull(room)) {
            return room.detachRoomFromHouseGrid();
        }
        return false;
    }

    /*

     */

    /**
     * method that get the list of readings of a given room sensor by a given date
     *
     * @param
     * @param
     * @return List<RoomReading>
     *//*

    public List<RoomReading> findByRoomReadingIdAndRoomSensorIdAndDay(RoomSensorId roomSensorId, LocalDate localDate) {
        return roomReadingRepository.findByRoomReadingId_RoomSensorIdAndRoomReadingId_LocalDateTime_Date(roomSensorId, localDate);
    }
*/
    public void updateRoom(Room room) {
        this.roomRepository.save(room);
    }

    public boolean addRoom(RoomId roomId, String description, int housefloor, Dimension dimension) {
        if (roomExists(roomId)) {
            return false;
        }
        Room room = new Room(roomId, description, housefloor, dimension);
        this.roomRepository.save(room);
        return true;
    }

}