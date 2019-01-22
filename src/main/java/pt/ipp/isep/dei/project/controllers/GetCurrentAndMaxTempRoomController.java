package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDate;

public class GetCurrentAndMaxTempRoomController {

    private House mHouse;
    private SensorType mType;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house House received
     * @param type  SensorType received
     */
    public GetCurrentAndMaxTempRoomController(House house, SensorType type) {
        this.mHouse = house;
        this.mType = type;
    }

    public SensorType getmType() {
        return mType;
    }

    /**
     * receives a Room name, and gets the latest Measurement according to the sensorType
     * passed on the constructor
     *
     * @param name String name of the room
     * @return latest measurement
     */
    public Measurement getLatestMeasurementByRoomName(String name) {
        return this.mHouse.getLatestMeasurementBySensorType(name, mType);
    }

    /**
     * gets a formatted string with information from the rooms that are available
     *
     * @return a String with room's name, house floor, height, length and width:
     */
    public String getRoomListContent() {
        return this.mHouse.getRoomListContent();
    }

    /**
     * gets the lenght of roomList
     *
     * @return lenght of roomList
     */
    public int getLengthOfRoomList() {
        return this.mHouse.getRoomListSize();
    }

    /**
     * gets the room name, by a specific position
     *
     * @param position integer position number of the room
     * @return a String with the name of room
     */
    public String getRoomNameByPos(int position) {
        return this.mHouse.getRoomNameByPosition(position);
    }

    /**
     * @param name receives the string name of the room
     * @param type receives the type of sensor (temperature)
     * @param date receives a given day
     * @return the maximum temperature of that room in the choosen day
     */
    public double getMaximumTemperatureOfARoomInAGivenDay(String name, SensorType type, LocalDate date) {
        return this.mHouse.getMaximumTemperatureOfRoomInSpecificDay(name, type, date);
    }
}
