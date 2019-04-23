package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddDeviceToRoomController {
    @Autowired
    private RoomAggregateService roomAggregateService;
    private Device device;
    private Room room;
    private Programmable programmableDevice;
    private DeviceSpecs devSpecs;
    private Program program;
    private static final String NOMINAL_POWER = "Nominal Power";
    private static final String LUMINOUS_FLUX = "Luminous Flux";
    private static final String ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String FREEZER_CAPACITY = "Freezer Capacity";
    private static final String REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String CAPACITY = "Capacity";
    private static final String HOTWATER_TEMPERATURE = "Hot-Water Temperature";
    private static final String MAXIMUM_VOLUME = "Maximum Volume";
    private static final String PERFORMANCE_RATIO = "Performance Ratio";
    private static final String ATTRIBUTE_MAXIMUM_VOLUME_WATER = "Maximum volume of Water";
    private static final String COLD_WATER_TEMPERATURE = "Cold-Water Temperature";
    private static final String VOLUME_OF_WATER_TO_HEAT = "Volume Of Water To Heat";
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_STANDBY_POWER = "Standby Power";
    private static final String ATTRIBUTE_NUMBER_OF_BOTTLES = "Number of Bottles";


    /**
     * Constructor.
     *
     * @param roomAggregateService
     */
    public AddDeviceToRoomController(RoomAggregateService roomAggregateService) {
        this.roomAggregateService = roomAggregateService;
    }

    /**
     * Method that asks for the list of rooms from the RoomRepository.
     *
     * @return List of housegrid grids.
     */
    public List<RoomDTO> getRoomListContent() {
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : roomAggregateService.getAllRooms()) {
            roomDTOS.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOS;
    }

    /**
     * Method that asks for the room with a specific RoomId.
     *
     * @param roomName Specifies the roomId of the Room.
     * @return The respective room.
     */
    public void getRoom(String roomName) {
        RoomId roomId = new RoomId(roomName);
        room = roomAggregateService.getRoomById(roomId);
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
     * @return
     */
    public int getNumberOfDeviceTypes() {
        return roomAggregateService.numberOfDeviceTypes();
    }

    /**
     * Method that asks for the content (that is the name of the device type) of list of devices from the class DeviceList.
     *
     * @return the name of the device types in the device types list.
     */
    public List<String> getDeviceTypeListToString() {
        List<String> deviceTypeListString = new ArrayList<>();
        for (DeviceType deviceType : roomAggregateService.getDeviceTypes()) {
            deviceTypeListString.add(deviceType.getTypeName());
        }
        return deviceTypeListString;
    }

    /**
     * Method that create a new FridgeSpecs in a selected Room.
     *
     * @param name                    of the FridgeSpecs
     * @param annualEnergyConsumption the annual Energy Consumption of the FridgeSpecs (a specification of the FridgeSpecs)
     * @param nominalPower            the nominal pcontroller.getRoomListContent()ower of the FridgeSpecs (a specification of the FridgeSpecs)
     * @param freezerCapacity         the freezer capacity of the FridgeSpecs (a specification of the FridgeSpecs)
     * @param refrigeratorCapacity    the refrigerator capacity of the FridgeSpecs (a specification of the FridgeSpecs)
     * @return the Device that has been created
     */
    public boolean createNewFridge(String name, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        device = roomAggregateService.createDevice("Fridge", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(FREEZER_CAPACITY, freezerCapacity);
        device.setAttributesDevType(REFRIGERATOR_CAPACITY, refrigeratorCapacity);
        return true;
    }

    /**
     * Method that create a new LampSpecs in a selected Room.
     *
     * @param name         of the LampSpecs
     * @param nominalPower the nominal power of the LampSpecs (a specification of the LampSpecs)
     * @param luminousFlux the luminous flux of the LampSpecs (a specification of the LampSpecs)
     * @return the Device that has been created
     */
    public boolean createNewLamp(String name, double nominalPower, double luminousFlux) {
        device = roomAggregateService.createDevice("Lamp", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(LUMINOUS_FLUX, luminousFlux);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return true;
    }

    /**
     * Method that create a new Dish Washer in a selected Room.
     *
     * @param name         of the Dish Washer
     * @param nominalPower the nominal power of the Dish Washer (a specification of the Dish Washer)
     * @param capacity     the capacity of the Dish Washer (a specification of the Dish Washer)
     * @return the Device that has been created
     */
    public boolean createNewDishWasher(String name, double nominalPower, int capacity) {
        device = roomAggregateService.createDevice("DishWasher", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.getSpecs().setAttributeValue(CAPACITY, capacity);
        device.getSpecs().setAttributeValue(NOMINAL_POWER, nominalPower);
        return true;
    }

    /**
     * Method that create a new Washing Machine in a selected Room.
     *
     * @param name         of the Washing Machine
     * @param nominalPower the nominal power of the Washing Machine (a specification of the Washing Machine)
     * @param capacity     the capacity of the Washing Machine (a specification of the Washing Machine)
     * @return the Device that has been created
     */
    public boolean createNewWashingMachine(String name, double nominalPower, double capacity) {
        device = roomAggregateService.createDevice("WashingMachine", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(CAPACITY, capacity);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return true;
    }

    /**
     * Method that create a new Washing Machine in a selected Room.
     *
     * @param name                 of the Kettle
     * @param nominalPower         the nominal power of the Kettle (a specification of the Kettle)
     * @param maxVolumeOfWater     the maximum volume of water for the Kettle to heat (a specification of the Kettle)
     * @param coldWaterTemperature the cold water temperature on the Kettle (a specification of the Kettle)
     * @param volumeOfWaterToHeat  the volume of water that the Kettle will heat (a specification of the Kettle)
     * @return the Device that has been created
     */
    public boolean createNewKettle(String name, double nominalPower, double maxVolumeOfWater, double coldWaterTemperature, double volumeOfWaterToHeat, double performanceRatio) {
        device = roomAggregateService.createDevice("Kettle", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(ATTRIBUTE_MAXIMUM_VOLUME_WATER, maxVolumeOfWater);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(COLD_WATER_TEMPERATURE, coldWaterTemperature);
        device.setAttributesDevType(VOLUME_OF_WATER_TO_HEAT, volumeOfWaterToHeat);
        device.setAttributesDevType(PERFORMANCE_RATIO, performanceRatio);
        return true;
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
    public boolean createNewElectricWaterHeater(String name, double hotWaterTemperature, double maximumVolume, double nominalPower, double performanceRatio) {
        device = roomAggregateService.createDevice("ElectricWaterHeater", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(HOTWATER_TEMPERATURE, hotWaterTemperature);
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(MAXIMUM_VOLUME, maximumVolume);
        device.setAttributesDevType(PERFORMANCE_RATIO, performanceRatio);
        return true;
    }

    /**
     * Method that creates a new Electric Oven in a selected Room
     *
     * @param name         of the Electric Oven
     * @param nominalPower the nominal power of the Electric Oven (a specification of the Electric Oven)
     * @param time         the time that the Electric Oven is running (a specification of the Electric Oven)
     */
    public boolean createNewElectricOven(String name, double nominalPower, double time) {
        device = roomAggregateService.createDevice("ElectricOven", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(ATTRIBUTE_TIME, time);
        return true;
    }

    /**
     * Method that creates a new Fan in a selected Room.
     *
     * @param name         of the Fan
     * @param nominalPower the nominal power of the Fan (a specification of the Fan)
     * @return the Device that has been created
     */
    public boolean createNewFan(String name, double nominalPower) {
        device = roomAggregateService.createDevice("Fan", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.getSpecs().setAttributeValue(NOMINAL_POWER, nominalPower);
        return true;
    }


    /**
     * Method that creates a new Freezer in a selected Room.
     *
     * @param name
     * @param nominalPower
     * @param freezerCapacity
     * @param annualEnergyConsumption
     */
    public boolean createNewFreezer(String name, double nominalPower, double freezerCapacity, double annualEnergyConsumption) {
        device = roomAggregateService.createDevice("Freezer", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        device.setAttributesDevType(FREEZER_CAPACITY, freezerCapacity);
        return true;
    }


    /**
     * Method that creates a new Microwave Oven in a selected Room.
     *
     * @param name
     * @param nominalPower
     */
    public boolean createNewMicroWaveOven(String name, double nominalPower) {
        device = roomAggregateService.createDevice("MicrowaveOven", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return true;
    }

    /**
     * Method that creates a new Television in a selected Room.
     *
     * @param name
     * @param nominalPower
     * @param standbyPower
     */
    public boolean createNewTelevision(String name, double nominalPower, double standbyPower) {
        device = roomAggregateService.createDevice("Television", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(ATTRIBUTE_STANDBY_POWER, standbyPower);
        return true;
    }

    /**
     * Method that creates a new Wall Towel Heater in a selected Room.
     *
     * @param name
     * @param nominalPower
     * @param time
     */
    public boolean createNewWallTowelHeater(String name, double nominalPower, double time) {
        device = roomAggregateService.createDevice("WallTowelHeater", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(ATTRIBUTE_TIME, time);
        return true;
    }

    public boolean createNewStove(String name,double nominalPower){
        device = roomAggregateService.createDevice("Stove",name,getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        return true;
    }

    /**
     * Method that creates a new Wine Cooler in a selected Room.
     *
     * @param name
     * @param nominalPower
     * @param numberOfBottles
     * @param annualEnergyConsumption
     */
    public boolean createNewWineCooler(String name, double nominalPower, int numberOfBottles, double annualEnergyConsumption) {
        device = roomAggregateService.createDevice("WineCooler", name, getSelectedRoom());
        if (Objects.isNull(device)) {
            return false;
        }
        device.setAttributesDevType(NOMINAL_POWER, nominalPower);
        device.setAttributesDevType(ATTRIBUTE_NUMBER_OF_BOTTLES, numberOfBottles);
        device.setAttributesDevType(ANNUAL_ENERGY_CONSUMPTION, annualEnergyConsumption);
        return true;
    }

    /**
     * Method that displays the device list content of a Room
     *
     * @param roomId identifier of the room
     * @return list of devices of a roomMethod that displays the content
     */
    public List<DeviceDTO> getDeviceListContentOfARoom(String roomId) {
        RoomId roomId1 = new RoomId(roomId);
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (Device dev : room.getDeviceList()) {
            deviceDTOList.add(DeviceMapper.mapToDTO(dev));
        }
        return deviceDTOList;
    }


    public DeviceSpecs getDevSpecs() {
        devSpecs = device.getSpecs();
        return devSpecs;
    }

    public Programmable asProgrammable() {
        programmableDevice = devSpecs.asProgrammable();
        return programmableDevice;
    }

    public boolean isProgrammable() {
        if (getDevSpecs().isProgrammable()) {
            programmableDevice = asProgrammable();
            return true;
        }
        return false;
    }

    public boolean setProgramAttributes(String attributeName, Object attributeValue) {
        return program.setProgramAttributes(attributeName, attributeValue);
    }

    public void createNewProgram(String name) {
        program = programmableDevice.createNewProgram(name);
    }

    public boolean addProgram() {
        return programmableDevice.addProgram(program);
    }



}
