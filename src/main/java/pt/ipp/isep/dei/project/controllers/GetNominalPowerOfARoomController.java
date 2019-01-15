package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class GetNominalPowerOfARoomController {
    private Room mChosenRoom;
    private House mHouse;

    public GetNominalPowerOfARoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    public String getListOfRooms() {
        return mHouse.getRoomListContent();
    }

    public int getRoomListLength() {
        return mHouse.houseRoomListLength();
    }

    public boolean checkIfDeviceListIsEmpty(int option) {
        return mHouse.checkIfDeviceListIsEmpty(option);
    }

    public void getRoom(int option) {
        mChosenRoom = mHouse.getRoomOfTheRoomList(option);
    }

    public double getNominalPower() {
        return mChosenRoom.getNominalPower();
    }
}
