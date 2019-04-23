package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomAggregateRepository;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.utils.ApplicationConfiguration;

import java.time.LocalDate;
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
    private SensorTypeService sensorTypeService;

    private String configFile = "Configuration.properties";

    private List<DeviceType> deviceTypeList = ApplicationConfiguration.createDeviceTypes(configFile);


    public List<SensorType> getSensorTypeList() {
        return sensorTypeService.getSensorTypeList();
    }


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

    public double getGridNominalPower(HouseGridId id) {
        List<Room> rooms = getRoomListByHouseGrid(id.getId());
        double nominalPower = 0;
        for (Room room : rooms) {
            nominalPower += room.getNominalPower();
        }
        return nominalPower;
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
        return this.roomAggregateRepository.findByRoomIdAndSensorTypeId(roomId, sensorTypeId);
    }

    /**
     * method that get the list of readings of a given room sensor
     *
     * @param roomSensorId id of the room sensor
     * @return List<RoomReading>
     */
    public List<RoomReading> getListOfRoomReadingByRoomSensorId(RoomSensorId roomSensorId) {
        return this.roomAggregateRepository.findByRoomReadingIdAndRoomSensorId(roomSensorId);
    }

    /*
     *//**
     * method that get the list of readings of a given room sensor (by id) by a given date
     * @param localDate given date
     * @param roomSensorId id of the room sensor
     * @return List<RoomReading> of a day of a sensor
     *//*
    public List<RoomReading> getListOfRoomReadingByRoomSensorIdByDay(RoomSensorId roomSensorId, LocalDate localDate) {
        return this.roomAggregateRepository.findByRoomReadingIdAndRoomSensorIdAndDay(roomSensorId, localDate);
    }
*/
    /**
     * method that gets the latest reading of a given roomSensor
     *
     * @param roomSensorId id of the sensor
     * @return latest roomReading
     */
    public RoomReading getLatestReadingByRoomSensorId(RoomSensorId roomSensorId) {

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
        Iterable<Room> roomIterable = this.roomAggregateRepository.findAllRooms();
        List<Room> rooms = new ArrayList<>();
        roomIterable.forEach(rooms::add);
        return rooms;
    }

    public RoomSensor getSensorById(RoomSensorId roomSensorId){
        return this.roomAggregateRepository.getSensorById(roomSensorId);
    }

    public boolean isReadingDuplicated(RoomReadingId roomReadingId){
        return this.roomAggregateRepository.isReadingDuplicated(roomReadingId);
    }

    public boolean addReading(RoomReading roomReading){
        if (!isReadingDuplicated(roomReading.getRoomReadingId())) {
            this.roomAggregateRepository.saveReading(roomReading);
            return true;
        }
        return false;
    }

    public boolean addRoomSensor(RoomSensor sensor) {
        return this.roomAggregateRepository.addRoomSensor(sensor);
    }

    public boolean roomExists(RoomId id) {
        return this.roomAggregateRepository.roomExists(id);
    }

    public boolean roomDeviceListIsEmpty(RoomId id) {
        return this.roomAggregateRepository.roomDeviceListIsEmpty(id);
    }

    public double getRoomNominalPower(RoomId id) {
        return this.roomAggregateRepository.findRoomById(id).getNominalPower();
    }

    /**
     * gets the Maximum Room Reading by sensor Id in a given day
     *
     * @param roomSensorId sensor id
     * @param localDate    given day
     * @return Maximum RoomReading. If there aren't readings return null
     *//*
    public RoomReading getMaximumReadingBySensorIdInADay(RoomSensorId roomSensorId, LocalDate localDate) {

        List<RoomReading> listReadingDay = this.getListOfRoomReadingByRoomSensorIdByDay(roomSensorId, localDate);
        if (!listReadingDay.isEmpty()) {
            RoomReading maxRoomReading = listReadingDay.get(0);
            for (RoomReading reading : listReadingDay) {
                if (Double.compare(reading.getValue(), maxRoomReading.getValue()) == 1) {
                    maxRoomReading = reading;
                }
            }
            return maxRoomReading;
        }
        return null;
    }*/
    public List<RoomReading> getListOfRoomReadingByRoomSensorIdByDay(RoomSensorId roomSensorId, LocalDate localDate) {

        List<RoomReading> listReadingSensor = this.getListOfRoomReadingByRoomSensorId(roomSensorId);
        if (Objects.nonNull(listReadingSensor)) {
            List<RoomReading> dailyRoomReading = new ArrayList<>();
            for (RoomReading reading : listReadingSensor) {
                if (reading.getRoomReadingId().getLocalDateTime().toLocalDate().isEqual(localDate)) {
                    dailyRoomReading.add(reading);
                }
            }
            return dailyRoomReading;
        }
        return null;
    }

    /**
     * gets the Maximum Room Reading by sensor Id in a given day
     *
     * @param roomSensorId sensor id
     * @param localDate    given day
     * @return Maximum RoomReading. If there aren't readings return null
     */
    public RoomReading getMaximumReadingBySensorIdInADay(RoomSensorId roomSensorId, LocalDate localDate) {

        List<RoomReading> dailyRoomReading = this.getListOfRoomReadingByRoomSensorIdByDay(roomSensorId, localDate);

        if (Objects.nonNull(dailyRoomReading) && (!dailyRoomReading.isEmpty())) {
            RoomReading maxRoomReading = dailyRoomReading.get(0);
            for (RoomReading reading : dailyRoomReading) {
                if (Double.compare(reading.getValue(), maxRoomReading.getValue()) == 1) {
                    maxRoomReading = reading;
                }
            }
            return maxRoomReading;
        }
        return null;
    }

    public Room getRoomById(RoomId roomId){
        return this.roomAggregateRepository.findRoomById(roomId);
    }

    /**
     * creates a Device and returns true if type name exists and deviceName not exists in the
     * rooms of the housegrid
     *
     * @param typeName   String type name of Device
     * @param deviceName String device name
     * @return true if creates and false if not
     */
    public Device createDevice(String typeName, String deviceName, Room room) {
        if (Objects.isNull(getDeviceType(typeName))) {
            return null;
        }
        for (Room allRoom : this.roomAggregateRepository.findAllRooms()) {
            if (allRoom.isDeviceNameExistant(deviceName)) {
                return null;
            }
        }
        Device device = getDeviceType(typeName).createDevice(deviceName);
        device.setLocation(room);
        return device;
    }

    public DeviceType getDeviceType(String typeName) {
        for (DeviceType deviceType : this.deviceTypeList) {
            if (deviceType.getTypeName().equals(typeName)) {
                return deviceType;
            }
        }
        return null;
    }

    public List<Device> getDeviceListContentRoom(RoomId roomId) {
        return getRoomById(roomId).getDeviceList();
    }

    public int numberOfDeviceTypes() {
        return this.deviceTypeList.size();
    }

    public List<DeviceType> getDeviceTypes() {
        return this.deviceTypeList;
    }

    public List<Room> getRoomsOfAHouseGrid(HouseGridId houseGridId) {
        return this.roomAggregateRepository.findAllByHouseGridIdEquals(houseGridId);
    }

    public boolean detachRoomFromHouseGrid(RoomId roomId) {
        return this.roomAggregateRepository.detachRoomFromHouseGrid(roomId);
    }

    public void updateRoom(Room room) {
        this.roomAggregateRepository.updateRoom(room);
    }

    public boolean createRoom(RoomId roomId, String description, int housefloor, double length, double width, double height) {
        Dimension dimension = new Dimension(height, length, width);
        return this.roomAggregateRepository.addRoom(roomId, description, housefloor, dimension);
    }


    public Room getRoomdById(RoomId roomId) {
        return roomAggregateRepository.getRoomdById(roomId);
    }

    public List<RoomSensor> getAllRoomSensors() {
        Iterable<RoomSensor> roomIterable = this.roomAggregateRepository.findAllRoomSensors();
        List<RoomSensor> roomSensorList = new ArrayList<>();
        roomIterable.forEach(roomSensorList::add);
        return roomSensorList;
    }
}
