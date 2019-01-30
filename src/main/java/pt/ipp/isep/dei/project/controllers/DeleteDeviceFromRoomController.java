package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class DeleteDeviceFromRoomController {

    private House mHouse;
    private Room mRoom;
    private Device mDevice;

    /**
     * construtor of controller
     *
     * @param mHouse
     */
    public DeleteDeviceFromRoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * method that return the method getRoomListContent of the class House
     *
     * @return the room list content.
     */
    public String getRoomListContent() {
        return this.mHouse.getRoomListContent();
    }

    /**
     * method that returns the method deviceListIsEmpty of the Class House
     *
     * @param position
     */
    public boolean isDeviceListEmpty(int position) {
        return this.mHouse.isDeviceListEmpty(position);
    }

    /**
     * method thar returns the method getRoomListSize from the class House
     *
     * @return
     */
    public int roomListSize() {
        return this.mHouse.getRoomListSize();
    }

    /**
     * method that delete a device.
     *
     * @param device
     * @return a boolean.
     */
    public boolean deleteDevice(String device) {
        return this.mRoom.deleteDevice(device);
    }

    /**
     * method that get the device name by position
     * @param position
     * @return the position
     */
    public String getDeviceNameByPosition(int position) {
        return this.mRoom.getDeviceNameByPosition(position);
    }

    /**
     * method that get the size of a device list.
     *
     * @return the size of the device list.
     */
    public int getDeviceListSize() {
        return this.mRoom.getDevicesListSize();
    }

    /**
     * method that check if the room list is empty.
     *
     * @return boolean.
     */
    public boolean roomListIsEmpty() {
        return this.mHouse.getRoomList().isEmpty();
    }

    /**
     * method that get a room from the room list by a position.
     * @param option
     */
    public void getRoomByPosition(int option) {
        this.mRoom = this.mHouse.getRoomOfTheRoomList(option);
    }

    /**
     * method that check if the device list is empty.
     *
     * @return a boolean.
     */
    public boolean deviceListIsEmpty() {
        return this.mRoom.isDeviceListEmpty();
    }

    /**
     * method that get devices in the room.
     * @return a device list to string.
     */
    public String getDevicesInTheRoom() {
        return this.mRoom.getDeviceListToString();
    }

}
