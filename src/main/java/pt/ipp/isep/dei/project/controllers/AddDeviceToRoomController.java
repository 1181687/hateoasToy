package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddDeviceToRoomController {

    private House mHouse;
    private Device mDevice;
    private DeviceList mDeviceList;
    private Room mRoom;
    private ProgramList mProgramList;

    /**
     * Constructor.
     *
     * @param house
     */
    public AddDeviceToRoomController(House house) {
        this.mHouse = house;
        this.mProgramList = new ProgramList();
    }

    /**
     * Method that asks for the list of rooms from the class RoomList.
     *
     * @return List of house grids.
     */
    public String getRoomListContent() {
        return mHouse.getRoomListContent();
    }


    /** Method that asks for the room in a specific position in the list.
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public void getRoom(int position) {
        mRoom = mHouse.getRoomOfTheRoomList(position);
    }


    public void getDeviceList() {
        mDeviceList = this.mRoom.getDeviceList();
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
    public int roomListSize() {
        return mHouse.getRoomListSize();
    }

    public int numberOfDeviceTypes() {
        return mDeviceList.numberOfDeviceTypes();
    }


    /**
     * Method that asks for the content (that is the name of the device type) of list of devices from the class DeviceList.
     * @return the name of the device types in the device types list.
     */
    public String getDeviceTypeListToString(int position) {
        return mHouse.getDeviceTypeListToString(position);
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
        return mDevice = mDeviceList.newDishWasher(name, selectedRoom, nominalPower, capacity, mProgramList);
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
        return mDevice = mDeviceList.newWashingMachine(name, selectedRoom, nominalPower, capacity, mProgramList);
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

    /**
     * Method that displays the device list content of a Room
     *
     * @param selectedRoom position of the room in the room list
     * @return list of devices of a roomMethod that displays the content
     */
    public String getDeviceListContentOfARoom(int selectedRoom) {
        return mHouse.getDeviceListContentRoom(selectedRoom);
    }

    public Program createNewProgram(String programName, double duration, double energyConsumption) {
        return mProgramList.newProgram(programName, duration, energyConsumption);
    }

    public boolean addProgramToList(Program program) {
        return mProgramList.addProgram(program);
    }

    public Device getDevice(int position) {
        return this.mDeviceList.getDeviceByPosition(position);
    }
}
