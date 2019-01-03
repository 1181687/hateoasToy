package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.SensorType;

import java.util.Date;

public class US605Controller {

    private House mHouse;
    private SensorType mType;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house House received
     * @param type  SensorType received
     */
    public US605Controller(House house, SensorType type) {
        this.mHouse = house;
        this.mType = type;
    }

    /**
     * receives a Room name, and gets the latest Measurement according to the sensorType
     * passed on the constructor
     *
     * @param name String name of the room
     * @return latest measurement
     */
    public Measurement getLatestMeasurementByRoomName(String name) {
        return this.mHouse.getListOfRoom().getRoomByName(name).getSensorList().getLatestMeasurementBySensorType(mType);
    }

    /**
     * gets a formatted string with information from the rooms that are available
     *
     * @return a String with room's name, house floor, height, length and width:
     */
    public String getDisplayRoomList() {
        return this.mHouse.getListOfRoom().getDisplayRoomList();
    }

    /**
     * gets the lenght of roomList
     *
     * @return lenght of roomList
     */
    public int lengthOfRoomList() {
        return this.mHouse.getListOfRoom().listSize();
    }

    /**
     * gets the room name, by a specific position
     *
     * @param position integer position number of the room
     * @return a String with the name of room
     */
    public String getNameOfTheChosenRoomInSpecificPos(int position) {
        return this.mHouse.getListOfRoom().getNameOfTheChosenRoomInSpecificPos(position);
    }


    /**
     * @param name receives the string name of the room
     * @param type receives the type of sensor (temperature)
     * @param date receives a given day
     * @return the maximum temperature of that room in the choosen day
     */
    public double getMaximumTemperatureOfARoomInAGivenDay(String name, SensorType type, Date date) {
        return this.mHouse.getMaximumTemperatureOfARoomInASpecificDay(name, type, date);
    }
}
