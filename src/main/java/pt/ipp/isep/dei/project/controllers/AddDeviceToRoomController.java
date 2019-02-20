package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddDeviceToRoomController {
    private House mHouse;
    private Device mDevice;
    private Room mRoom;
    private Programmable programmableDevice;
    private static final String NOMINAL_POWER = "Nominal Power";
    private static final String LUMINOUS_FLUX = "Luminous Flux";
    private static final String ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String FREEZER_CAPACITY = "Freezer Capacity";
    private static final String REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String CAPACITY = "Capacity";
    private static final String HOTWATER_TEMPERATURE = "Hot-Water Temperature";
    private static final String MAXIMUM_VOLUME = "Maximum Volume";
    private static final String PERFORMANCE_RATIO = "Performance Ratio";


    /**
     * Constructor.
     *
     * @param house
     */
    public AddDeviceToRoomController(House house) {
        this.mHouse = house;

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
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public void getRoom(int position) {
        mRoom = mHouse.getRoomOfTheRoomList(position);
    }

    /**
     * Method that show the room selected.
     *
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

    /**
     * @return
     */
    public int getNumberOfDeviceTypes() {
        return mHouse.numberOfDeviceTypes();
    }


    /**
     * Method that asks for the content (that is the name of the device type) of list of devices from the class DeviceList.
     *
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
     * @return the Device that has been created
     */
    public Device createNewFridge(String name, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        mDevice = mHouse.getDeviceType("Fridge").createDevice(name, mRoom);
        mDevice.setAttributesDevType(ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        mDevice.setAttributesDevType(NOMINAL_POWER, nominalPower);
        mDevice.setAttributesDevType(FREEZER_CAPACITY, freezerCapacity);
        mDevice.setAttributesDevType(REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        return mDevice;
    }

    /**
     * Method that create a new LampSpecs in a selected Room.
     *
     * @param name         of the LampSpecs
     * @param nominalPower the nominal power of the LampSpecs (a specification of the LampSpecs)
     * @param luminousFlux the luminous flux of the LampSpecs (a specification of the LampSpecs)
     * @return the Device that has been created
     */
    public Device createNewLamp(String name, double nominalPower, double luminousFlux) {
        mDevice = mHouse.getDeviceType("Lamp").createDevice(name, mRoom);
        mDevice.setAttributesDevType(LUMINOUS_FLUX, luminousFlux);
        mDevice.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return mDevice;
    }

    /**
     * Method that create a new Dish Washer in a selected Room.
     *
     * @param name         of the Dish Washer
     * @param nominalPower the nominal power of the Dish Washer (a specification of the Dish Washer)
     * @param capacity     the capacity of the Dish Washer (a specification of the Dish Washer)
     * @return the Device that has been created
     */
    public Device createNewDishWasher(String name, double nominalPower, int capacity) {
        mDevice = mHouse.getDeviceType("DishWasher").createDevice(name, mRoom);
        mDevice.setAttributesDevType(CAPACITY, capacity);
        mDevice.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return mDevice;
    }

    /**
     * Method that create a new Washing Machine in a selected Room.
     *
     * @param name         of the Washing Machine
     * @param nominalPower the nominal power of the Washing Machine (a specification of the Washing Machine)
     * @param capacity     the capacity of the Washing Machine (a specification of the Washing Machine)
     * @return the Device that has been created
     */
    public Device createNewWashingMachine(String name, double nominalPower, double capacity) {
        mDevice = mHouse.getDeviceType("Washing Machine").createDevice(name, mRoom);
        mDevice.setAttributesDevType(CAPACITY, capacity);
        mDevice.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return mDevice;
    }

    /**
     * Method that create a new Electric Water Heater in a selected Room.
     *
     * @param name                of the Electric Water Heater
     * @param hotWaterTemperature the temperature that is configured by the user (a specification of the Electric Water Heater and the user)
     * @param maximumVolume       the capacity in l of the Electric Water Heater (a specification of the Electric Water Heater)
     * @param nominalPower        the nominal power of the Electric Water Heater (a specification of the Electric Water Heater)
     * @return the Device that has been created
     */
    public Device createNewElectricWaterHeater(String name, double hotWaterTemperature, double maximumVolume, double nominalPower, double performanceRatio) {
        mDevice = mHouse.getDeviceType("Electric Water Heater").createDevice(name, mRoom);
        mDevice.setAttributesDevType(HOTWATER_TEMPERATURE, hotWaterTemperature);
        mDevice.setAttributesDevType(NOMINAL_POWER, nominalPower);
        mDevice.setAttributesDevType(MAXIMUM_VOLUME, maximumVolume);
        mDevice.setAttributesDevType(PERFORMANCE_RATIO, performanceRatio);
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

/*    public Program createNewProgram(String programName, double duration, double energyConsumption) {
        return mProgramList.newProgram(programName, duration, energyConsumption);
    }

    /**
     * Method that add a Device Program to the list of Programs
     *
     * @param program
     * @return a list of programs
     *//*
    public boolean addProgramToList(Program program) {
        return mProgramList.addProgram(program);
    }*/

    public boolean createAndAddProgram(String programName, double duration, double energyConsumption) {
        Program newProgram = programmableDevice.newProgram(programName, duration, energyConsumption);
        return programmableDevice.addProgram(newProgram);
    }

    /**
     * method that get a Device by it's position
     *
     * @param position
     * @return Device
     */
    public Device getDevice(int position) {
        return mRoom.getDeviceByPosition(position);
    }

    public boolean isProgrammable() {
        if (mDevice.isProgrammable()) {
            programmableDevice = mDevice.asProgrammable();
            return true;
        }
        return false;
    }

}
