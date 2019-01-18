package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class GetNominalPowerOfARoomController {
    private Room mChosenRoom;
    private House mHouse;

    public GetNominalPowerOfARoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * Method that returns the String builder with the list of rooms.
     *
     * @return A string with the list of rooms.
     */
    public String getListOfRooms() {
        return mHouse.getRoomListContent();
    }

    /**
     * Method that returns the length of the roomList.
     * @return integer
     */
    public int getRoomListLength() {
        return mHouse.houseRoomListLength();
    }

    /**
     * Method that checks if the device list is empty, given a room from the list of rooms.
     * @param option Chosen room from the list as an integer.
     * @return
     */
    public boolean checkIfDeviceListIsEmpty(int option) {
        return mHouse.checkIfDeviceListIsEmpty(option);
    }

    /**
     * Method that selects the room from the list and saves it in the controller.
     * @param option Chosen room from the list as an integer.
     */
    public void getRoom(int option) {
        mChosenRoom = mHouse.getRoomOfTheRoomList(option);
    }

    /**
     * Method that returns the nominal power of the room previously chosen.
     * @return
     */
    public double getNominalPower() {
        return mChosenRoom.getNominalPower();
    }
}
