package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class GetNominalPowerOfARoomController {
    private Room chosenRoom;
    private House house;

    public GetNominalPowerOfARoomController(House house) {
        this.house = house;
    }

    /**
     * Method that returns the String builder with the list of rooms.
     *
     * @return A string with the list of rooms.
     */
    public String getListOfRooms() {
        return house.getRoomListContent();
    }

    /**
     * Method that returns the length of the roomList.
     * @return integer
     */
    public int getRoomListSize() {
        return house.houseRoomListSize();
    }

    /**
     * Method that checks if the device list is empty, given a room from the list of rooms.
     * @param option Chosen room from the list as an integer.
     * @return
     */
    public boolean isDeviceListEmpty(int option) {
        return house.isDeviceListEmpty(option);
    }

    /**
     * Method that selects the room from the list and saves it in the controller.
     * @param option Chosen room from the list as an integer.
     */
    public void getRoom(int option) {
        chosenRoom = house.getRoomOfTheRoomList(option);
    }

    /**
     * Method that returns the nominal power of the room previously chosen.
     * @return
     */
    public double getNominalPower() {
        return chosenRoom.getNominalPower();
    }
}
