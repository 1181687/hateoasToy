package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static java.util.Objects.isNull;

public class Room implements Measurable {
    private static final String SAME_NAME = "Name already exists. Please write a new one.";
    private String mName;
    private int mHouseFloor;
    private Dimension mDimension;
    private SensorList mSensorList;
    private DeviceList mDeviceList;

    /**
     * constructor that receives name, houseFloor, dimension
     * throw an exception if any of the parameters is invalid.
     * Invalid parameters if Dimension is null or name is null or empty
     *
     * @param name
     * @param houseFloor
     * @param dimension
     */
    public Room(String name, int houseFloor, Dimension dimension) {
        validateName(name);
        validateDimensions(dimension);
        this.mName = name.trim();
        this.mHouseFloor = houseFloor;
        this.mDimension = dimension;
        this.mSensorList = new SensorList();
        this.mDeviceList = new DeviceList();
    }

    /**
     * method that receives a name and validates it. It can not be null or empty
     * throw an exception if the name is invalid
     *
     * @param name given name
     */
    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new RuntimeException("Please enter a valid name. Name should not be empty");
        }
    }

    /**
     * method that receives an object Dimension and validates it. It can not be null
     * throw an exception if Dimension is null
     *
     * @param dimension given object dimension
     */
    private static void validateDimensions(Dimension dimension) {
        if (isNull(dimension)) {
            throw new RuntimeException("Dimension should not be null");
        }
    }

    /**
     * Get method
     *
     * @return mName
     */
    public String getName() {
        return mName;
    }

    /**
     * Method that defines the name of the room
     *
     * @param name name of a room (string)
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Get Method
     *
     * @return mHouseFloor
     */
    public int getHouseFloor() {
        return mHouseFloor;
    }

    /**
     * Method that defines the House Floor number of the room
     *
     * @param houseFloor house floor of the room (int number)
     */
    public void setHouseFloor(int houseFloor) {
        this.mHouseFloor = houseFloor;
    }

    /**
     * Get Method
     *
     * @return mDimension
     */
    public Dimension getDimension() {
        return mDimension;
    }

    /**
     * method that displays a Room with its characteristics (name, house floor, height, length and width)
     *
     * @return Rooms
     */
    public String getRoomToString() {
        StringBuilder content = new StringBuilder();
        content.append("Name: " + getName());
        content.append(", House Floor: " + getHouseFloor());
        content.append(", Dimension - Height: " + getDimension().getHeight());
        content.append(", Length: " + getDimension().getLength());
        content.append(", Width: " + getDimension().getWidth());
        return content.toString();
    }

    /**
     * method that creates the same hashcode to rooms with the same attribute name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.mName);
    }

    /**
     * Equals method to determine if two Rooms are equal.
     * They are equals if name are equal.
     * Names are case insensitive.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Room)) {
            return false;
        }
        Room roomOne = (Room) obj;
        return this.mName.equalsIgnoreCase(roomOne.mName);
    }

    /**
     * This method add a new sensor to the list of sensors in the room
     *
     * @param newSensor add to the list of sensors
     * @return a new sensor to the list of sensors
     */
    public boolean addSensorToListOfSensorsInRoom(Sensor newSensor) {
        return this.mSensorList.addSensor(newSensor);
    }

    /**
     * This method gets the sensor list.
     *
     * @return the list of sensors.
     */
    public SensorList getSensorList() {
        return mSensorList;
    }

    /**
     * @param type of sensor (temperature)
     * @param date any given day
     * @return maximum temperature
     */
    public double getMaximumMeasurementInGivenDay(SensorType type, LocalDate date) {
        return mSensorList.getMaximumMeasureOfTypeOfSensorInGivenDay(type, date);
    }

    /**
     * Method that gets the latest measurement by type of sensor
     *
     * @param type type of sensor
     * @return latest measurement by sensor type
     */
    public Readings getLatestMeasurementBySensorType(SensorType type) {
        return mSensorList.getLatestMeasurementBySensorType(type);
    }

    /**
     * Method that return the nominal power of the list of devices in the room.
     * This method is the implementation of the measurable interface method.
     *
     * @return
     */
    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        if (mDeviceList.getSize() != 0) {
            for (Device device : mDeviceList.getDeviceList()) {
                totalNominalPower += device.getNominalPower();
            }
        }
        return totalNominalPower;
    }

    /**
     * method that displays the content of the list of sesnsors
     *
     * @return sensor list content
     */
    public String getSensorListContent() {
        return this.mSensorList.getSensorListToString();
    }

    /**
     * method that check if the sensor list of the room is empty
     */
    public boolean isSensorListEmpty() {
        return this.mSensorList.isEmpty();
    }


    /**
     * method that displays the device list content
     *
     * @return content of the device list
     */
    public String getDeviceListToString() {
        StringBuilder content = new StringBuilder();
        int deviceListLength = mDeviceList.getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            content.append(numberInTheList + " - Name of the device: " + getDeviceList().getDeviceByPosition(i - 1).getName());
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * method that checks if Device List of the room is empty
     */
    public boolean isDeviceListEmpty() {
        return this.mDeviceList.isEmpty();
    }

    /**
     * get method of the device list.
     *
     * @return device list
     */
    public DeviceList getDeviceList() {
        return mDeviceList;
    }

    /**
     * Method that adds a device to the list of Devices of the room
     *
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add
     */
    public boolean addDevice(Device device) {
        if (this.equals(device.getLocation()) && this.mDeviceList.getDeviceList().contains(device)) {
            return false;
        }
        device.getLocation().removeDevice(device);
        device.setLocation(this);
        this.mDeviceList.addDevice(device);
        return true;
    }

    /**
     * method that remove a device from the list of devices
     *
     * @param device
     */
    public boolean removeDevice(Device device) {
        return this.mDeviceList.removeDevice(device);
    }

    /**
     * method that get the size of the list of devices.
     *
     * @return the size of the list.
     */

    public int getDevicesListSize() {
        return mDeviceList.getSize();
    }

    /**
     * method that get all devices of a type.
     *
     * @param type
     * @return
     */
    public DeviceList getAllDevicesOfType(String type) {
        return mDeviceList.getAllDevicesOfAType(type);
    }

    /**
     * method that check if a name of a Device already exists on the list of devices.
     *
     * @param name name of device
     * @return boolean true if exists, false if it doesn't
     */
    public boolean isDeviceNameExistant(String name) {
        return this.mDeviceList.isNameExistant(name);
    }


    /**
     * method that returns the name of room
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder name = new StringBuilder();
        name.append("Room: " + mName + "\n");
        return name.toString();
    }

    /**
     * method that get the enery consumption of the room in an interval
     *
     * @param startDate
     * @param endDate
     * @return the total energy consumption
     */
    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        double totalEnergyConsumption = 0;
        if (!mDeviceList.isEmpty()) {
            for (Device device : mDeviceList.getDeviceList()) {
                totalEnergyConsumption += device.getEnergyConsumptionInAnInterval(startDate, endDate);
            }
        }
        return totalEnergyConsumption;
    }

    /**
     * method that delete a device.
     *
     * @param device
     * @return boolean
     */
    public boolean deleteDevice(String device) {
        return this.mDeviceList.deleteDevice(device);
    }

    /**
     * method that get the device name by position.
     *
     * @param position
     * @return a string with the name of the device.
     */
    public String getDeviceNameByPosition(int position) {
        return this.mDeviceList.getDeviceNameByPosition(position);
    }

    /**
     * method that deactivate a device.
     *
     * @param device
     * @return a boolean
     */
    public boolean deactivateDevice(String device) {
        return this.mDeviceList.deactivateDevice(device);
    }

    /**
     * method that get a list of active devices to string
     *
     * @return a string with the status of the device: "activated" or "deactivated".
     */
    public String getActiveDeviceListToString() {
        return this.mDeviceList.getActiveDeviceListToString();
    }

    /**
     * method that get de device type list content
     *
     * @return the content of the list by string
     */
    public String getDeviceTypeListToString() {
        StringBuilder content = new StringBuilder();
        int numberOfDeviceTypes = numberOfDeviceTypes();
        for (int i = 1; i <= numberOfDeviceTypes; i++) {
            String deviceType = Utils.readConfigFile("devicetype.name." + i);
            content.append(i + "- ");
            content.append(deviceType);
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that get the number os existing Devices on the configuration file.
     *
     * @return the number os existing Devices
     */
    public int numberOfDeviceTypes() {
        return Integer.parseInt(Utils.readConfigFile("devicetype.count"));
    }


    /**
     * Method that create a new Device ELECTRIC WATER HEATER
     *
     * @param name                name of the device
     * @param hotWaterTemperature maximum temperature configured by user
     * @param maximumVolume       capacity in liters of the Electric Water Heater
     * @param nominalPower        nominal power of the device
     * @param performanceRatio    performance ratio introduced by user that typically is 0,9
     * @return a new device
     */
    public Device newElectricWaterHeater(String name, double hotWaterTemperature, double maximumVolume, double nominalPower, double performanceRatio) {

        if (isDeviceNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, nominalPower, performanceRatio);

        return new Device(name, this, electricWaterHeater);
    }

    /**
     * Method that create a new Device WASHING MACHINE
     *
     * @param name         name of the device
     * @param nominalPower nominal power of the device
     * @param capacity     capacity in kilograms of the Electric Water Heater
     * @param programList  list of programs
     * @return a new device
     */
    public Device newWashingMachine(String name, double nominalPower, double capacity,
                                    ProgramList programList) {
        if (isDeviceNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs(capacity, nominalPower, programList);
        return new Device(name, this, washingMachineSpecs);
    }


    /**
     * Method that create a new Device DISH WASHER
     *
     * @param name         name of the device
     * @param nominalPower nominal power of the device
     * @param capacity     capacity in dish sets of the Electric Water Heater
     * @param programList  list of programs
     * @return a new device
     */
    public Device newDishWasher(String name, double nominalPower, int capacity, ProgramList programList) {
        if (isDeviceNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DishWasherSpecs dishwasher = new DishWasherSpecs(capacity, nominalPower, programList);
        return new Device(name, this, dishwasher);
    }

    /**
     * Method that create a new Device LAMP
     *
     * @param name         name of the device
     * @param nominalPower nominal power of the device
     * @param luminousFlux luminous flux of the lamp
     * @return a new device
     */

    public Device newLamp(String name, double nominalPower, double luminousFlux) {
        if (isDeviceNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs lamp = new LampSpecs(luminousFlux, nominalPower);
        return new Device(name, this, lamp);
    }

    /**
     * Method that create a new Device FRIDGE
     *
     * @param name                    name of the device
     * @param annualEnergyConsumption annual ennergy consumption of the fridge
     * @param nominalPower            nominal power of the device
     * @param freezerCapacity         freezer Capacity
     * @param refrigeratorCapacity    refrigerator Capacity
     * @return a new device
     */
    public Device newFridge(String name, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        if (isDeviceNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        return new Device(name, this, fridgeSpecs);
    }

    public Device getDeviceByPosition(int position) {
        return mDeviceList.getDeviceByPosition(position);
    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = new TreeMap<>();
        for (Device device : mDeviceList.getDeviceList()) {
            Map<LocalDateTime, Double> map2 = device.getDataSeries(startDate, endDate);
            for (Map.Entry<LocalDateTime, Double> entry : map2.entrySet()) {
                LocalDateTime key = entry.getKey();
                Double oldValue = map.get(key);
                map.put(key, oldValue == null ? entry.getValue() : entry.getValue() + oldValue);
            }
        }
        return map;
    }
}