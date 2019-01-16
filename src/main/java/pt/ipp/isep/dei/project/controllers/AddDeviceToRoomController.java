package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.DeviceList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class AddDeviceToRoomController {

    private House mHouse;
    private Device mDevice;
    private DeviceList mDeviceList;
    private Room mRoom;

    /**
     * Constructor.
     *
     * @param house
     */
    public AddDeviceToRoomController(House house) {
        this.mHouse = house;
        this.mDeviceList = new DeviceList();
    }


    /**
     * Method that asks for the list of rooms from the class RoomList.
     *
     * @return List of house grids.
     */
    public String getRoomListContent() {
        return mHouse.getRoomListContent();
    }


    /**
     * Method that asks for the room in a specific position in the list.
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public void getRoom(int position) {
        mRoom = mHouse.getRoomOfTheRoomList(position);
    }


    /**
     * Method that show the room selected.
     * @return selected room
     */
    public Room getSelectedRoom() {
        return mRoom;
    }


    /**
     * Method that asks for the size of the list of rooms.
     *
     * @return Size of the list.
     */
    public int roomListLength() {
        return mHouse.getRoomListSize();
    }


    /**
     * Method that asks for the content (that is the name of the device type) of list of devices from the class DeviceList.
     * @return the name of the device types in the device types list.
     */
    public String getDeviceTypeListContent() {
        return mDeviceList.getDeviceTypeListContent();
    }


    /**
     * Method that verifies if the device has been added to the room.
     *
     * @return true if it has been added or false if it hasn't.
     */
    public boolean addDeviceToRoom() {
        return mRoom.addDevice(mDevice);
    }


    /**
     * Method that create a new Fridge in a selected Room.
     *
     * @param name                    of the Fridge
     * @param selectedRoom            room where will be created the device
     * @param annualEnergyConsumption the annual Energy Consumption of the Fridge (a specification of the Fridge)
     * @param nominalPower            the nominal power of the Fridge (a specification of the Fridge)
     * @param freezerCapacity         the freezer capacity of the Fridge (a specification of the Fridge)
     * @param refrigeratorCapacity    the refrigerator capacity of the Fridge (a specification of the Fridge)
     * @return the Device that has been created
     */
    public Device createNewFridge(String name, Room selectedRoom, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        return mDevice = mDeviceList.newFridge(name, selectedRoom, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);
    }

    /**
     * Method that create a new Lamp in a selected Room.
     *
     * @param name         of the Lamp
     * @param selectedRoom room where will be created the device
     * @param nominalPower the nominal power of the Lamp (a specification of the Lamp)
     * @param luminousFlux the luminous flux of the Lamp (a specification of the Lamp)
     * @return the Device that has been created
     */
    public Device createNewLamp(String name, Room selectedRoom, double nominalPower, double luminousFlux) {
        return mDevice = mDeviceList.newLamp(name, selectedRoom, nominalPower, luminousFlux);
    }


    /**
     * Method that create a new Dish Washer in a selected Room.
     *
     * @param name         of the Dish Washer
     * @param selectedRoom room where will be created the device
     * @param nominalPower the nominal power of the Dish Washer (a specification of the Dish Washer)
     * @param capacity     the capacity of the Dish Washer (a specification of the Dish Washer)
     * @return the Device that has been created
     */
    public Device createNewDishWasher(String name, Room selectedRoom, double nominalPower, int capacity) {
        return mDevice = mDeviceList.newDishWasher(name, selectedRoom, nominalPower, capacity);
    }


    /**
     * Method that create a new Washing Machine in a selected Room.
     *
     * @param name         of the Washing Machine
     * @param selectedRoom room where will be created the device
     * @param nominalPower the nominal power of the Washing Machine (a specification of the Washing Machine)
     * @param capacity     the capacity of the Washing Machine (a specification of the Washing Machine)
     * @return the Device that has been created
     */
    public Device createNewWashingMachine(String name, Room selectedRoom, double nominalPower, double capacity) {
        return mDevice = mDeviceList.newWashingMachine(name, selectedRoom, nominalPower, capacity);
    }


    /**
     * Method that create a new Electric Water Heater in a selected Room.
     *
     * @param name                 of the Electric Water Heater
     * @param selectedRoom         room where will be created the device
     * @param mHotWaterTemperature the temperature that is configured by the user (a specification of the Electric Water Heater and the user)
     * @param mMaximumVolume       the capacity in l of the Electric Water Heater (a specification of the Electric Water Heater)
     * @param mNominalPower        the nominal power of the Electric Water Heater (a specification of the Electric Water Heater)
     * @return the Device that has been created
     */
    public Device createNewElectricWaterHeater(String name, Room selectedRoom, double mHotWaterTemperature, double mMaximumVolume, double mNominalPower, double mPerformanceRatio) {
        return mDevice = mDeviceList.newElectricWaterHeater(name, selectedRoom, mHotWaterTemperature, mMaximumVolume, mNominalPower, mPerformanceRatio);
    }


}
