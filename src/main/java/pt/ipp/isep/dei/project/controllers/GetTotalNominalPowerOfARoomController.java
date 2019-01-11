package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class GetTotalNominalPowerOfARoomController {
    private Room mChosenRoom;
    private House mHouse;

    public GetTotalNominalPowerOfARoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    public String getListOfRooms() {
        return mHouse.getRoomList();
    }

    public void getRoom(int option) {
        mChosenRoom = mHouse.getRoomOfTheRoomList(option);
    }

    public double getNominalPower() {
        return mChosenRoom.getNominalPower();
    }
}
