package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomAggregateRepository;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomAggregateService {

    @Autowired
    private RoomAggregateRepository roomAggregateRepository;

    @Autowired
    private HouseGridAggregateService houseGridAggregateService;


    /**
     * method that get the String content Name and Location of all devices in the list,
     * grouped by device type.
     *
     * @return String with Device Name and Location grouped by Type.
     */
    public String getContentNameLocationOrderedByType(List<Device> deviceList) {
        StringBuilder content = new StringBuilder();
        Map<String, List<Device>> byDeviceType = deviceList.stream()
                .collect(Collectors.groupingBy(Device::getType));


        for (Map.Entry<String, List<Device>> entry : byDeviceType.entrySet()) {
            content.append(entry.getKey());
            content.append("\n");
            for (Device dev : entry.getValue()) {

                content.append("- Device Name: ");
                content.append(dev.getName());
                content.append(", Location: ");
                content.append(dev.getLocation().getRoomId());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that get the String content Name and Location of all devices in the list, of a given housegrid,
     * and grouped by device type.
     *
     * @return String with devices Names and Location grouped by Type.
     */
    public String getDeviceListContentNameTypeLocationByGrid(String houseGridId) {
        List<Device> deviceList = this.getDeviceListByHouseGridById(houseGridId);
        return getContentNameLocationOrderedByType(deviceList);
    }


    public List<Room> getRoomListByHouseGrid(String id) {
        HouseGridId houseGridId = new HouseGridId(id);
        return roomAggregateRepository.findAllByHouseGridIdEquals(houseGridId);
    }

    /**
     * method that gets a List of all devices in a room of the HouseGrid
     *
     * @param
     * @return List <Device>
     */
    public List<Device> getDeviceListByRoomOfGridById(String houseGridId) {

        List<Device> deviceListOfRoomOfHGrid = new ArrayList<>();
        for (Room room : this.getRoomListByHouseGrid(houseGridId)) {
            deviceListOfRoomOfHGrid = room.getDeviceList();
        }
        return deviceListOfRoomOfHGrid;
    }

    public List<Device> getDeviceListByHouseGridById(String houseGridId) {
        List<Device> deviceList = new ArrayList<>();
        for (Device device : this.getDeviceListByRoomOfGridById(houseGridId)) {
            deviceList.add(device);
        }
        return deviceList;
    }

    public List<HouseGrid> getAllGrids() {
        return houseGridAggregateService.getAllGrids();
    }

    /**
     * Method that searches for a grid by its Id. If it exists in the repo, the grid is returned, if not, null is returned.
     *
     * @param id Id to be used.
     * @return HouseGrid or null.
     */
    public HouseGrid getGridById(HouseGridId id) {
        return houseGridAggregateService.getGridById(id);
    }

    public boolean isHouseGridListEmpty() {
        return this.houseGridAggregateService.numberOfHouseGridsInRepository();
    }
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

    public RoomSensor getSensorById(RoomSensorId roomSensorId){
        return this.roomAggregateRepo.getRoomSensorById(roomSensorId);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId){
        return this.roomAggregateRepo.isReadingDuplicated(roomReadingId);
    }

    public boolean addReading(RoomReading roomReading){
        if (!isReadingDuplicated(roomReading.getRoomReadingId())) {
            this.roomAggregateRepo.saveReading(roomReading);
            return true;
        }
        return false;
    }

    public Room getRoomdById(RoomId roomId) {
        return roomAggregateRepository.getRoomdById(roomId);
    }

    public List<RoomSensor> getAllRoomSensors() {
        Iterable<RoomSensor> roomIterable = this.roomAggregateRepo.findAllRoomSensors();
        List<RoomSensor> roomSensorList = new ArrayList<>();
        roomIterable.forEach(roomSensorList::add);
        return roomSensorList;
    }
}
