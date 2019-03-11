package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;

public class AddDeviceToRoomController {
    private House house;
    private Device device;
    private Room room;
    private Programmable programmableDevice;
    private DeviceSpecs devSpecs;
    private Program program;
    private static final String NOMINAL_POWER = "Nominal Power";
    private static final String LUMINOUS_FLUX = "Luminous Flux";
    private static final String ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String FREEZER_CAPACITY = "freezer Capacity";
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
        this.house = house;

    }

    /**
     * Method that asks for the list of rooms from the class RoomList.
     *
     * @return List of housegrid grids.
     */
    public String getRoomListContent() {
        return house.getRoomListContent();
    }

    /**
     * Method that asks for the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public void getRoom(int position) {
        room = house.getRoomOfTheRoomList(position);
    }

    /**
     * Method that show the room selected.
     *
     * @return selected room
     */
    public Room getSelectedRoom() {
        return room;
    }


    /**
     * Method that asks for the size of the list of rooms.
     *
     * @return Size of the list.
     */
    public int roomListSize() {
        return house.getRoomListSize();
    }

    /**
     * @return
     */
    public int getNumberOfDeviceTypes() {
        return house.numberOfDeviceTypes();
    }


    /**
     * Method that asks for the content (that is the name of the device type) of list of devices from the class DeviceList.
     *
     * @return the name of the device types in the device types list.
     */
    public String getDeviceTypeListToString() {
        return house.getDeviceTypeListToString();
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
        device = house.createDevice("Fridge", name, getSelectedRoom());
        device.setAttributesDevType(ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(FREEZER_CAPACITY, freezerCapacity);
        device.setAttributesDevType(REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        return device;
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
        device = house.createDevice("Lamp", name, getSelectedRoom());
        device.setAttributesDevType(LUMINOUS_FLUX, luminousFlux);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return device;
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
        device = house.createDevice("DishWasher", name, getSelectedRoom());
        device.getSpecs().setAttributeValue(CAPACITY, capacity);
        device.getSpecs().setAttributeValue(NOMINAL_POWER, nominalPower);
        return device;
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
        device = house.createDevice("Washing Machine", name, getSelectedRoom());
        device.setAttributesDevType(CAPACITY, capacity);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return device;
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
        device = house.createDevice("Electric Water Heater", name, getSelectedRoom());
        device.setAttributesDevType(HOTWATER_TEMPERATURE, hotWaterTemperature);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(MAXIMUM_VOLUME, maximumVolume);
        device.setAttributesDevType(PERFORMANCE_RATIO, performanceRatio);
        return device;
    }

    /**
     * Method that displays the device list content of a Room
     *
     * @param selectedRoom position of the room in the room list
     * @return list of devices of a roomMethod that displays the content
     */
    public String getDeviceListContentOfARoom(int selectedRoom) {
        return house.getDeviceListContentRoom(selectedRoom);
    }


    /*public boolean createAndAddProgram(String programName, ProgramSpecs specs) {
        return programmableDevice.addNewProgram(programName, specs);
    }*/

    /**
     * method that get a Device by it's position
     *
     * @param position
     * @return Device
     */
    public Device getDevice(int position) {
        return room.getDeviceByPosition(position);
    }

    /*public boolean isProgrammable() {
        if (device.isProgrammable()) {
            programmableDevice = device.asProgrammable();
            return true;
        }
        return false;
    }*/

    public DeviceSpecs getDevSpecs() {
        return devSpecs = device.getSpecs();
    }

    public Programmable asProgrammable () {
        return programmableDevice = devSpecs.asProgrammable();
    }

    public boolean isProgrammable() {
        if (getDevSpecs().isProgrammable()) {
            programmableDevice = asProgrammable();
            return true;
        }
        return false;
    }

    public ProgramSpecs getProgramSpecs() {
        return program.getProgramSpecs();
    }

    public boolean setProgramAttributes(String attributeName, Object attributeValue) {
        return program.setProgramAttributes(attributeName, attributeValue);
    }

    public Program createNewProgram(String name) {
        return program = programmableDevice.createNewProgram(name);
    }

    public boolean addProgram(Program program) {
        return programmableDevice.addProgram(program);
    }

    /**
     * Method that create a new Fan in a selected Room.
     *
     * @param name         of the Fan
     * @param nominalPower the nominal power of the Fan (a specification of the Fan)
     * @return the Device that has been created
     */
    public Device createFan(String name, double nominalPower) {
        device = house.createDevice("Fan", name, getSelectedRoom());
        device.getSpecs().setAttributeValue(NOMINAL_POWER, nominalPower);
        return device;
    }

}
