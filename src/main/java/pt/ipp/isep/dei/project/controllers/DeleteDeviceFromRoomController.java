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

    public boolean deleteDevice(String device) {
        return this.mRoom.deleteDevice(device);
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

    public String getDevicesInTheRoom() {
        return this.mRoom.getDeviceListToString();
    }

    public boolean deativateDevice(String device) {
        return this.mRoom.deativateDevice(device);
    }

}
