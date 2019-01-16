package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class EditConfigurationDeviceController {

    private House mHouse;
    private Device mDevice;
    private Room mRoom;
    private Room newRoom;

    public EditConfigurationDeviceController(House mHouse) {
        this.mHouse = mHouse;
    }

    public String getRoomListContent () {
        return this.mHouse.getRoomListContent();
    }

    public RoomList getListOfRooms () {
        return this.mHouse.getRoomList();
    }

    public void getRoomByPosition (int option) {
        this.mRoom = this.mHouse.getRoomOfTheRoomList(option);
    }

    public String getDevicesInTheRoom () {
        return this.mRoom.getDeviceListContent();
    }

    public void getDeviceByPosition (int position) {
        this.mDevice = this.mRoom.getmDeviceList().getDeviceByPosition(position);
    }

    public String getDeviceAttributesToString () {
        return this.mDevice.getAttributesToString();
    }

    public boolean setDeviceName (String newName) {
        return this.mDevice.setmName(newName);
    }

    public boolean setDeviceSpecs (int attribute, double value) {
        return this.mDevice.setAttributesDevType(attribute, value);
    }

    public String getSpecsAttributesToString () {
        return this.mDevice.getSpecsAttributesToString();
    }

    public boolean setLocation () {
        return this.mDevice.setmLocation(newRoom);
    }

    public boolean checkIfRoomListIsEmpty () {
        return this.mHouse.getRoomList().checkIfRoomListIsEmpty();
    }

    public int roomListSize () {
        return mHouse.getRoomListSize();
    }

    public int getDeviceListLength () {
        return this.mHouse.getRoomList().getAllDevicesList().getLength();
    }

    public boolean checkIfDeviceListIsEmpty () {
        return this.mRoom.checkIfDeviceListIsEmpty();
    }

    public void getNewRoom (int position) {
        this.newRoom = mHouse.getRoomOfTheRoomList(position);
    }

    public int getNumberOfAttributesInDeviceSpecs(){
        return mDevice.getNumberOfSpecsAttributes();
    }
}
