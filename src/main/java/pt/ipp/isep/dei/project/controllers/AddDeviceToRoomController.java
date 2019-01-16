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

    public AddDeviceToRoomController(House house) {
        this.mHouse = house;
        this.mDeviceList = new DeviceList();
    }


    public String getRoomListContent() {
        return mHouse.getRoomListContent();
    }

    public void getRoom(int position) {
        mRoom = mHouse.getRoomOfTheRoomList(position);
    }

    public Room getSelectedRoom() {
        return mRoom;
    }

    public int roomListLength() {
        return mHouse.getRoomListSize();
    }


    public String getDeviceTypeListContent() {
        return mDeviceList.getDeviceTypeListContent();
    }

    /*public DeviceList getDeviceList (){
        return mDeviceList = mHouse.getRoomList().getAllDevicesList();
    }*/

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
     * Method that create a new Washing Machine in a selected Room.
     *
     * @param name         of the Washing Machine
     * @param selectedRoom room where will be created the device
     * @param nominalPower the nominal power of the Washing Machine (a specification of the Washing Machine)
     * @param capacity     the capacity of the Washing Machine (a specification of the Washing Machine)
     * @return tthe Device that has been created
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
