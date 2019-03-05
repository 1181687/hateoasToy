package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.util.List;

public class EditConfigurationDeviceController {

    private House house;
    private Device device;
    private Room room;
    private Room newRoom;

    /**
     * constructor of the controller that receive a house.
     *
     * @param house
     */
    public EditConfigurationDeviceController(House house) {
        this.house = house;
    }

    /**
     * this method get the content of the room list
     *
     * @return the content of the room list.
     */
    public String getRoomListContent() {
        return this.house.getRoomListContent();
    }

    /**
     * this method get the name of the room in a specific position.
     *
     * @param position
     * @return the name of the room by position.
     */
    public String getRoomName(int position) {
        return this.house.getRoomNameByPosition(position);
    }

    /**
     * method get the position of the room, on the list.
     *
     * @param option
     */
    public void getRoomByPosition(int option) {
        this.room = this.house.getRoomOfTheRoomList(option);
    }

    /**
     * method that get the devices in the room
     *
     * @return the content of the device list.
     */
    public String getDevicesInTheRoom() {
        return this.room.getDeviceListToString();
    }

    /**
     * method that get the device, in a list of devices, by position
     *
     * @param position
     */
    public void getDeviceByPosition(int position) {
        this.device = this.room.getDeviceList().get(position);
    }

    /**
     * method that get the attributes of a device.
     *
     * @return tha attributes.
     */
    public String getDeviceAttributesToString() {
        return this.device.getAttributesToString();
    }

    /**
     * method that get the attributes that are editable.
     *
     * @return the editable attributes content.
     */
    public String getDevSpecsAttributesToString() {
        return this.device.getDevSpecsAttributesToString();
    }

    /**
     * method that set the device with a new name.
     *
     * @param newName
     * @return a boolean.
     */
    public boolean setDeviceName(String newName) {
        return this.device.setName(newName);
    }

    /**
     * method that set the specifications of a device with the position of the attribute of the list of attributes, and the value of it.
     *
     * @param attribute
     * @param value
     * @return th set of the device type.
     */
    public boolean setDeviceSpecs(String attribute, Object value) {
        return this.device.setAttributesDevType(attribute, value);
    }

    /**
     * method that set the location of the new room.
     *
     * @return the set location of the new room.
     */
    public boolean setLocation() {
        return this.device.setLocation(newRoom);
    }

    /**
     * method that check if the list of rooms is empty.
     *
     * @return a boolean.
     */
    public boolean roomListIsEmpty() {
        return this.house.getRoomList().isEmpty();
    }

    /**
     * method that get the size of the room list.
     *
     * @return room list size.
     */
    public int roomListSize() {
        return house.getRoomListSize();
    }

    /**
     * method that get the length of the device list.
     * @return length of the list of devices.
     */
    public int getDeviceListSize() {
        return this.room.getDeviceList().size();
    }

    /**
     * method that check if the list of devices is empty.
     *
     * @return a boolean.
     */
    public boolean deviceListIsEmpty() {
        return this.room.isDeviceListEmpty();
    }

    /**
     * method that get the position of a new room on the room list.
     *
     * @param position
     */
    public void getNewRoom(int position) {
        this.newRoom = house.getRoomOfTheRoomList(position);
    }

    /**
     * method that get a number of attributes in the specifications of a device.
     *
     * @return number of attributes.
     */
    public int getNumberOfAttributesInDeviceSpecs() {
        return device.getNumberOfSpecsAttributes();
    }

    public String getSpecsToString() {
        return this.device.getSpecsToString();
    }

    public List<String> getSpecsList() {
        return this.device.getSpecsList();
    }

    public String getAttributeType(String attributeName) {
        return device.getAttributeDataType(attributeName);
    }

    public Number getAttributeValue(String attributeName) {
        return (Number) device.getAttributeValue(attributeName);
    }
}
