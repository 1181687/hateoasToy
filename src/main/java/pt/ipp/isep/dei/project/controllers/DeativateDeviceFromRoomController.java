package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class DeativateDeviceFromRoomController {

    private House mHouse;
    private Room mRoom;
    private Device mDevice;

    public DeativateDeviceFromRoomController(House mHouse) {
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

    public String getDeviceNameByPosition(int position) {
        return this.mRoom.getDeviceNameByPosition(position);
    }

    public int getDeviceListLength() {
        return this.mRoom.getDevicesListLength();
    }

    public boolean checkIfRoomListIsEmpty() {
        return this.mHouse.getRoomList().isEmpty();
    }

    public void getRoomByPosition(int option) {
        this.mRoom = this.mHouse.getRoomOfTheRoomList(option);
    }

    public boolean checkIfDeviceListIsEmpty() {
        return this.mRoom.isDeviceListEmpty();
    }

    public boolean deativateDevice(String device) {
        return this.mRoom.deativateDevice(device);
    }
    public String getActiveDeviceListToString() {
        return this.mRoom.getActiveDeviceListToString();
    }

    public boolean getIsActive() {
        return this.mDevice.getIsActive();
    }

    public Device getDevice(int position) {
        return this.mRoom.getDeviceList().getDeviceByPosition(position);
    }

}
