package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class EditConfigurationDeviceController {

    private House mHouse;
    private Device mDevice;
    private Room mRoom;
    private Room newRoom;

    /**
     * constructor of the controller that receive a house.
     *
     * @param mHouse
     */
    public EditConfigurationDeviceController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * this method get the content of the room list
     * @return the content of the room list.
     */
    public String getRoomListContent () {
        return this.mHouse.getRoomListContent();
    }

    /**
     * this method get the name of the room in a specific position.
     * @param position
     * @return the name of the room by position.
     */
    public String getRoomName (int position) {
        return this.mHouse.getNameOfTheChosenRoomInSpecificPos(position);
    }

    /**
     * method get the position of the room, on the list.
     * @param option
     */
    public void getRoomByPosition (int option) {
        this.mRoom = this.mHouse.getRoomOfTheRoomList(option);
    }

    /**
     * method that get the devices in the room
     * @return the content of the device list.
     */
    public String getDevicesInTheRoom () {
        return this.mRoom.getDeviceListContent();
    }

    /**
     * method that get the device, in a list of devices, by position
     * @param position
     */
    public void getDeviceByPosition (int position) {
        this.mDevice = this.mRoom.getmDeviceList().getDeviceByPosition(position);
    }

    /**
     * method that get the attributes of a device.
     * @return tha attributes.
     */
    public String getDeviceAttributesToString () {
        return this.mDevice.getAttributesToString();
    }

    /**
     * method that set the device with a new name.
     * @param newName
     * @return a boolean.
     */
    public boolean setDeviceName (String newName) {
        return this.mDevice.setName(newName);
    }

    /**
     * method that set the specifications of a device with the position of the attribute of the list of attributes, and the value of it.
     * @param attribute
     * @param value
     * @return th set of the device type.
     */
    public boolean setDeviceSpecs (int attribute, double value) {
        return this.mDevice.setAttributesDevType(attribute, value);
    }

    /**
     * method that get the specifications of the attribute to a string
     * @return the specifications of the attribute.
     */
    public String getSpecsAttributesToString () {
        return this.mDevice.getSpecsAttributesToString();
    }

    /**
     * method that set the location of the new room.
     * @return the set location of the new room.
     */
    public boolean setLocation () {
        return this.mDevice.setmLocation(newRoom);
    }

    /**
     * method that check if the list of rooms is empty.
     * @return a boolean.
     */
    public boolean checkIfRoomListIsEmpty () {
        return this.mHouse.getRoomList().checkIfRoomListIsEmpty();
    }

    /**
     * method that get the size of the room list.
     * @return room list size.
     */
    public int roomListSize () {
        return mHouse.getRoomListSize();
    }

    /**
     * method that get the length of the device list.
     * @return the length of all devices of the list.
     */
    public int getDeviceListLength () {
        return this.mHouse.getRoomList().getAllDevicesList().getLength();
    }

    /**
     * method that check if the list of devices is empty.
     * @return a boolean.
     */
    public boolean checkIfDeviceListIsEmpty () {
        return this.mRoom.checkIfDeviceListIsEmpty();
    }

    /**
     * method that get the position of a new room on the room list.
     * @param position
     */
    public void getNewRoom (int position) {
        this.newRoom = mHouse.getRoomOfTheRoomList(position);
    }

    /**
     * method that get a number of attributes in the specifications of a device.
     * @return number of attributes.
     */
    public int getNumberOfAttributesInDeviceSpecs(){
        return mDevice.getNumberOfSpecsAttributes();
    }
}
