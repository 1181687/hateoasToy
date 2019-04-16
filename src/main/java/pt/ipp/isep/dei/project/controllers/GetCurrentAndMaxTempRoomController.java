package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.readings.RoomReading;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.util.ArrayList;
import java.util.List;

public class GetCurrentAndMaxTempRoomController {

    private RoomService roomService;
    private SensorTypeId sensorTypeId = new SensorTypeId("temperature");
    private SensorType sensorType = new SensorType(sensorTypeId);
    private RoomId choosenRoomId;



    public GetCurrentAndMaxTempRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Method that gets all rooms, turns each one into a dto object and adds it to a list,
     * in order to be sent to the UI.
     *
     * @return List of roomDTO.
     */
    public List <RoomDTO> getListRoomDTo(){
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: roomService.getAllRooms()){
             roomDTOS.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOS;
    }

    /**
     * checks if there aren't rooms
     * @return true if there aren't rooms, false if are.
     */
    public boolean isListofRoomEmpty(){
        return (this.getListRoomDTo().isEmpty());
    }

    /**
     * receives a string roomId and creates a RoomId object
     * that is saved in the controller
     * @param roomId
     */
    public void newChoosenRoomId (String roomId){
     this.choosenRoomId = new RoomId((roomId));
    }

    /**
     * checks if there aren't temperature sensors by saved roomId
     * @return true if there aren't temperature sensors, false if are.
     */
    public boolean isRoomWithoutTemperatureSensor(){
        return roomService.isRoomWithoutSensorByType(this.choosenRoomId, this.sensorTypeId);
    }

    public List<RoomSensor> getListOfRoomSensorByRoomByType() {
     return roomService.getListOfRoomSensorByRoomByType(this.choosenRoomId, this.sensorTypeId);
    }

    public RoomSensorId getRoomSensorId(){
        return this.getListOfRoomSensorByRoomByType().get(0).getId();
    }

    public List<RoomReading> getListOfRoomReadingByRoomSensorId () {
        return roomService.getListOfRoomReadingByRoomSensorId(this.getRoomSensorId());
    }


    /*

    public RoomReading getLatestMeasurementByRoom(){
        Iterable<RoomReading> roomReadingsIterable = this.roomService..findAll();
        List<Room> rooms = new ArrayList<>();
        roomIterable.forEach(rooms::add);
        return rooms;

    }





/*
    public SensorType getType() {
        return sensorType;
    }

    /**
     * receives a Room name, and gets the latest Reading according to the sensorType
     * passed on the constructor
     *
     * @return latest measurement
     */
/*
    public RoomReading getLatestMeasurementByRoomName(RoomId roomId) {
        return this.roomSensorService.getLatestMeasurementBySensorType()
                .getLatestMeasurementBySensorType(roomId, sensorType);
    }


    /**
     * gets a formatted string with information from the rooms that are available
     *
     * @return a String with room's name, housegrid floor, height, length and width:
     */
/*
    public String getRoomListContent() {
        return this.house.getRoomListContent();
    }

    /**
     * gets the lenght of roomList
     *
     * @return lenght of roomList
     */

/*
    public int getRoomListSize() {
        return this.house.getRoomListSize();
    }

    /**
     * gets the room name, by a specific position
     *
     * @param position integer position number of the room
     * @return a String with the name of room
     */

/*
    public String getRoomNameByPos(int position) {
        return this.house.getRoomNameByPosition(position);
    }

    /**
     * @param name receives the string name of the room
     * @param type receives the type of sensor (temperature)
     * @param date receives a given day
     * @return the maximum temperature of that room in the choosen day
     */

/*
    public double getMaximumTemperatureOfRoomInGivenDay(String name, SensorType type, LocalDate date) {
        return this.house.getMaximumTemperatureOfRoomInSpecificDay(name, type, date);
    }
*/
}
