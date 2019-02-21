package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

public class GetListOfSensorsAndDevicesRoomController {

    private House mHouse;

    public GetListOfSensorsAndDevicesRoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * method that return the method getListOfRooms of the class House
     * @return the room list.
     */
    public RoomList getListOfRooms () {
        return this.mHouse.getRoomList();
    }

    /**
     * method that return the method getRoomNameByPosition of the class House
     * @param option
     * @return the name of the chosen room in a specific position of the room list.
     */
    public String getRoomNameByPosition(int option) {
        return this.mHouse.getRoomNameByPosition(option);
    }

    /**
     * method that return the method getRoomListContent of the class House
     * @return the room list content.
     */
    public String getRoomListContent () {
        return this.mHouse.getRoomListContent();
    }

    /**
     * method that return the method getSensorListToString of the class House
     * @param position
     * @return the sensor list content of a room by a position
     */
    public String getSensorsListContent(int position) {
        return this.mHouse.getSensorListContentOfARoom(position);
    }

    /**
     * method that return the method isSensorListEmpty of the class House
     * @param position
     */
    public boolean isSensorListEmpty(int position) {
        return this.mHouse.isSensorListEmpty(position);
    }

    /**
     * method that returns the method getDeviceListContentByPosition of the class House
     *
     * @param position
     * @return the device list content of a room by position
     */
    public String getDeviceListContent(int position) {
        return this.mHouse.getDeviceListContentRoom(position);
    }

    /**
     * method that returns the method deviceListEmpty of the Class House
     * @param position
     */
    public boolean isDeviceListEmpty(int position) {
        return this.mHouse.isDeviceListEmpty(position);
    }

    /**
     * method thar returns the method getRoomListSize from the class House
     * @return
     */
    public int roomListSize () {
        return mHouse.getRoomListSize();
    }

}
