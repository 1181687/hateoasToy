package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class DeactivateDeviceFromRoomController {

    private House mHouse;
    private Room mRoom;
    private Device mDevice;

    public DeactivateDeviceFromRoomController(House mHouse) {
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
     * method thar returns the method getRoomListSize from the class House
     *
     * @return the size of the list of rooms.
     */
    public int roomListSize() {
        return this.mHouse.getRoomListSize();
    }

    /**
     * method that get the device name by position
     *
     * @param position
     * @return the position
     */
    public String deviceNameByPosition(int position) {
        return this.mRoom.getDeviceNameByPosition(position);
    }

    /**
     * method that get the device list size.
     *
     * @return the size of the device list.
     */
    public int deviceListSize() {
        return this.mRoom.getDevicesListSize();
    }

    /**
     * method that check if the room list is empty.
     *
     * @return a boolean.
     */
    public boolean roomListEmpty() {
        return this.mHouse.getRoomList().isEmpty();
    }

    /**
     * method that get a room by position from the list.
     * @param option
     */
    public void getRoomPosition(int option) {
        this.mRoom = this.mHouse.getRoomOfTheRoomList(option);
    }

    /**
     * method that check if the device list is empty.
     *
     * @return a boolean.
     */
    public boolean deviceListEmpty() {
        return this.mRoom.isDeviceListEmpty();
    }

    /**
     * method that deactivate a device
     *
     * @param device
     * @return a boolean.
     */
    public boolean deactivateDevice(String device) {
        return this.mRoom.deactivateDevice(device);
    }

    /**
     * method that get and active device from the list of devices to string.
     * @return an ative device.
     */
    public String getActiveDeviceListToString() {
        return this.mRoom.getActiveDeviceListToString();
    }

    /**
     * method that get a device by a pposition.
     * @param position
     * @return a postiion.
     */
    public Device getDevice(int position) {
        return this.mRoom.getDeviceList().getDeviceByPosition(position);
    }

}
