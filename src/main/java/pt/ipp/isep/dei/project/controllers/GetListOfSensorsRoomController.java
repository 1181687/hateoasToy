package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

public class GetListOfSensorsRoomController {

    private House mHouse;

    public GetListOfSensorsRoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    public RoomList getListOfRooms () {
        return this.mHouse.getRoomList();
    }

    public String getRoomOfTheRoomList (int option) {
        return this.mHouse.getNameOfTheChosenRoomInSpecificPos(option);
    }

    public String getRoomListContent () {
        return this.mHouse.getRoomListContent();
    }

    public String getSensorsListContent(int position) {
        return this.mHouse.getSensorListContentOfARoom(position);
    }

    public boolean checkIfListIsEmpty (int position) {
        return this.mHouse.checkIfSensorListIsEmpty(position);
    }

}
