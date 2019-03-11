package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;

public class DeleteAndDeactivateDeviceFromRoomController {

    private House house;
    private Room room;

    /**
     * construtor of controller
     *
     * @param house
     */
    public DeleteAndDeactivateDeviceFromRoomController(House house) {
        this.house = house;
    }

    /**
     * method that return the method getRoomListContent of the class House
     *
     * @return the room list content.
     */
    public String getRoomListContent() {
        return this.house.getRoomListContent();
    }

    /**
     * method thar returns the method getRoomListSize from the class House
     *
     * @return
     */
    public int roomListSize() {
        return this.house.getRoomListSize();
    }

    /**
     * method that get the device name by position
     * @param position
     * @return the position
     */
    public String deviceNameByPosition(int position) {
        return this.room.getDeviceNameByPosition(position);
    }

    /**
     * method that get the size of a device list.
     *
     * @return the size of the device list.
     */
    public int deviceListSize() {
        return this.room.getDeviceList().size();
    }

    /**
     * method that check if the room list is empty.
     *
     * @return boolean.
     */
    public boolean roomListEmpty() {
        return this.house.getRoomList().isEmpty();
    }

    /**
     * method that get a room from the room list by a position.
     * @param option
     */
    public void getRoomPosition(int option) {
        this.room = this.house.getRoomOfTheRoomList(option);
    }

    /**
     * method that check if the device list is empty.
     *
     * @return a boolean.
     */
    public boolean deviceListEmpty() {
        return this.room.isDeviceListEmpty();
    }

    /**
     * method that get devices in the room.
     *
     * @return a device list to string.
     */
    public String getDeviceListToString() {
        return this.room.getDeviceListToString();
    }

    /**
     * method that delete a device.
     *
     * @param device
     * @return a boolean.
     */
    public boolean deleteDevice(String device) {
        return this.room.deleteDevice(device);
    }

    /**
     * method that deactivate a device
     *
     * @param device
     * @return a boolean.
     */
    public boolean deactivateDevice(String device) {
        return this.room.deactivateDevice(device);
    }

    /**
     * method that get and active device from the list of devices to string.
     * @return an ative device.
     */
    public String getActiveDeviceListToString() {
        return this.room.getActiveDeactiveDeviceListToString();
    }

    /**
     * method that get a device by a position.
     * @param position
     * @return a postiion.
     */
    public Device getDevice(int position) {
        return this.room.getDeviceList().get(position);
    }

    public String getDateDeactivateDeviceToString() {
        return this.room.getDeviceList().get(0).getDateDeactivateDeviceToString();
    }
}
