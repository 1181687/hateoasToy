package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

public class GetListOfSensorsRoomController {

    private House mHouse;

    /**
     * construtor of the class GetListOfSensorsRoomController that receives a house.
     * @param mHouse
     */
    public GetListOfSensorsRoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * method that return the method getRoomList of the class House
     * @return the room list.
     */
    public RoomList getListOfRooms () {
        return this.mHouse.getRoomList();
    }

    /**
     * method that return the method getRoomOfTheRoomList of the class House
     * @param option
     * @return the name of the chosen room in a specific position of the room list.
     */
    public String getRoomOfTheRoomList (int option) {
        return this.mHouse.getNameOfTheChosenRoomInSpecificPos(option);
    }

    /**
     * method that return the method getRoomListContent of the class House
     * @return the room list content.
     */
    public String getRoomListContent () {
        return this.mHouse.getRoomListContent();
    }

    /**
     * method that return the method getSensorsListContent of the class House
     * @param position
     * @return the sensor list content of a room by a position
     */
    public String getSensorsListContent(int position) {
        return this.mHouse.getSensorListContentOfARoom(position);
    }

    /**
     * method that return the method checkIfListIsEmpty of the class House
     * @param position
     */
    public boolean checkIfListIsEmpty (int position) {
        return this.mHouse.checkIfSensorListIsEmpty(position);
    }

}
