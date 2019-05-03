package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.time.LocalDate;
import java.util.List;

public class GetCurrentAndMaxTempRoomController {

    private SensorTypeId temperature;
    private RoomSensorService roomSensorService;
    private RoomService roomService;


    /**
     * constructor that receives a House and a SensorType
     * @param typeId  SensorType received
     */
    public GetCurrentAndMaxTempRoomController(SensorTypeId typeId, RoomSensorService sensorService, RoomService rService) {
        this.temperature = typeId;
        this.roomSensorService = sensorService;
        this.roomService = rService;
    }

    public SensorTypeId getType() {
        return this.temperature;
    }

    /*/**
     * receives a Room name, and gets the latest Reading according to the sensorType
     * passed on the constructor
     *
 * @param name String name of the room
 * @return latest measurement
     */

    /*public Reading getLatestMeasurementByRoomName(String name) {
        return this.house.getLatestMeasurementBySensorType(name, temperature);
    }*/

    public ReadingDTO getLatestMeasurementOfRoomSensor(RoomId roomId, SensorTypeId temperature) {
        return this.roomSensorService.getLastMeasurement(roomId, temperature);
    }

    public String getRoomDTOListToString() {
        List<RoomDTO> roomDtoList = roomService.getAllRoomsDTO();
        StringBuilder roomListContent = new StringBuilder();
        int numberOfRoom = 1;
        for (RoomDTO roomDto : roomDtoList) {
            roomListContent.append(numberOfRoom + " - Id: " + roomDto.getRoomId());
            numberOfRoom++;
        }
        return roomListContent.toString();
    }

    /**
     * gets the lenght of roomList
     *
     * @return lenght of roomList
     */
    public int getRoomListSize() {
        return this.roomService.getAllRooms().size();
    }

    public double getMaximumTemperatureOfRoomInGivenDay(RoomId roomId, SensorTypeId temperature, LocalDate date) {
        return this.roomSensorService.getMaxMeasurementValueOfADay(roomId, temperature, date);
    }

    /**
     * gets the room name, by a specific position
     *
     * @param position integer position number of the room
     * @return a String with the name of room
     */
    /*public String getRoomNameByPos(int position) {
        return this.house.getRoomNameByPosition(position);
    }*/

    /**
     * @param name receives the string name of the room
     * @param type receives the type of sensor (temperature)
     * @param date receives a given day
     * @return the maximum temperature of that room in the choosen day
     */
 /*public double getMaximumTemperatureOfRoomInGivenDay(String name, SensorType type, LocalDate date) {
        return this.house.getMaximumTemperatureOfRoomInSpecificDay(name, type, date);
    }*/
}
