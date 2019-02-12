package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

public class AddDeviceToRoomController {

    private House mHouse;
    private Device1 mDevice;
    private List<Device1> mDeviceList;
    private Room mRoom;
    private ProgramList mProgramList;
    private DeviceType mDeviceType;

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

    /**
     * Method that show the room selected.
     * @return selected room
     */
    public Room getSelectedRoom() {
        return mRoom;
    }


    public void getDeviceList() {
        mDeviceList = this.mRoom.getDeviceList();
    }

    /**
     * Method that asks for the size of the list of rooms.
     *
     * @return Size of the list.
     */
    public int roomListSize() {
        return mHouse.getRoomListSize();
    }

    /**
     * @return
     */
    public int getNumberOfDeviceTypes() {
        return mHouse.numberOfDeviceTypes();
    }


    /**
     * Method that asks for the content (that is the name of the device type) of list of devices from the class DeviceList.
     * @return the name of the device types in the device types list.
     */
    public String getDeviceTypeListToString() {
        return mHouse.getDeviceTypeListToString();
    }

    /**
     * Method that create a new FridgeSpecs in a selected Room.
     *
     * @param name                    of the FridgeSpecs
     * @param annualEnergyConsumption the annual Energy Consumption of the FridgeSpecs (a specification of the FridgeSpecs)
     * @param nominalPower            the nominal power of the FridgeSpecs (a specification of the FridgeSpecs)
     * @param freezerCapacity         the freezer capacity of the FridgeSpecs (a specification of the FridgeSpecs)
     * @param refrigeratorCapacity    the refrigerator capacity of the FridgeSpecs (a specification of the FridgeSpecs)
     * @return the Device1 that has been created
     */
    public Device1 createNewFridge(String name, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        mDevice = mRoom.newFridge(name, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);

        return mDevice;
    }

    /**
     * Method that create a new LampSpecs in a selected Room.
     *
     * @param name         of the LampSpecs
     * @param nominalPower the nominal power of the LampSpecs (a specification of the LampSpecs)
     * @param luminousFlux the luminous flux of the LampSpecs (a specification of the LampSpecs)
     * @return the Device1 that has been created
     */
    public Device1 createNewLamp(String name, double nominalPower, double luminousFlux) {
        mDevice = mRoom.newLamp(name, nominalPower, luminousFlux);
        return mDevice;
    }

    /**
     * Method that create a new Dish Washer in a selected Room.
     *
     * @param name         of the Dish Washer
     * @param nominalPower the nominal power of the Dish Washer (a specification of the Dish Washer)
     * @param capacity     the capacity of the Dish Washer (a specification of the Dish Washer)
     * @return the Device1 that has been created
     */
    public Device1 createNewDishWasher(String name, double nominalPower, int capacity) {
        mDevice = mRoom.newDishWasher(name, nominalPower, capacity, mProgramList);
        return mDevice;
    }

    /**
     * Method that create a new Washing Machine in a selected Room.
     *
     * @param name         of the Washing Machine
     * @param nominalPower the nominal power of the Washing Machine (a specification of the Washing Machine)
     * @param capacity     the capacity of the Washing Machine (a specification of the Washing Machine)
     * @return the Device1 that has been created
     */
    public Device1 createNewWashingMachine(String name, double nominalPower, double capacity) {
        mDevice = mRoom.newWashingMachine(name, nominalPower, capacity, mProgramList);
        return mDevice;
    }

    /**
     * Method that create a new Electric Water Heater in a selected Room.
     *
     * @param name                 of the Electric Water Heater
     * @param mHotWaterTemperature the temperature that is configured by the user (a specification of the Electric Water Heater and the user)
     * @param mMaximumVolume       the capacity in l of the Electric Water Heater (a specification of the Electric Water Heater)
     * @param mNominalPower        the nominal power of the Electric Water Heater (a specification of the Electric Water Heater)
     * @return the Device1 that has been created
     */
    public Device1 createNewElectricWaterHeater(String name, double mHotWaterTemperature, double mMaximumVolume, double mNominalPower, double mPerformanceRatio) {
        mDevice = mRoom.newElectricWaterHeater(name, mHotWaterTemperature, mMaximumVolume, mNominalPower, mPerformanceRatio);
        return mDevice;
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

    /**
     * Method that add a Device1 Program to the list of Programs
     *
     * @param program
     * @return a list of programs
     */
    public boolean addProgramToList(Program program) {
        return mProgramList.addProgram(program);
    }

    /**
     * method that get a Device by it's position
     * @param position
     * @return Device
     */
    public Device1 getDevice(int position) {
        return mRoom.getDeviceByPosition(position);
    }
}
