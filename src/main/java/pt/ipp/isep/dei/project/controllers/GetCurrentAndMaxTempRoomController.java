package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.HouseService;

public class GetCurrentAndMaxTempRoomController {

    private House house;
    private SensorTypeId sensorTypeId;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house House received
     * @param typeId  SensorType received
     */
    public GetCurrentAndMaxTempRoomController(HouseService houseService, SensorTypeId typeId) {
        this.house = houseService.getHouse();
        this.sensorTypeId = typeId;
    }

    public SensorTypeId getType() {
        return this.sensorTypeId;
    }

    /*
     */
/**
     * receives a Room name, and gets the latest Reading according to the sensorType
     * passed on the constructor
     *
 * @param name String name of the room
 * @return latest measurement
 *//*

    public Reading getLatestMeasurementByRoomName(String name) {
        return this.house.getLatestMeasurementBySensorType(name, sensorType);
    }
*/

    /**
     * gets a formatted string with information from the rooms that are available
     *
     * @return a String with room's name, housegrid floor, height, length and width:
     */
    public String getRoomListContent() {
        return this.house.getRoomListContent();
    }

    /**
     * gets the lenght of roomList
     *
     * @return lenght of roomList
     */
    public int getRoomListSize() {
        return this.house.getRoomListSize();
    }

    /**
     * gets the room name, by a specific position
     *
     * @param position integer position number of the room
     * @return a String with the name of room
     */
    public String getRoomNameByPos(int position) {
        return this.house.getRoomNameByPosition(position);
    }

    /**
     * @param name receives the string name of the room
     * @param type receives the type of sensor (temperature)
     * @param date receives a given day
     * @return the maximum temperature of that room in the choosen day
     */
 /*   public double getMaximumTemperatureOfRoomInGivenDay(String name, SensorType type, LocalDate date) {
        return this.house.getMaximumTemperatureOfRoomInSpecificDay(name, type, date);
    }
    */
}
