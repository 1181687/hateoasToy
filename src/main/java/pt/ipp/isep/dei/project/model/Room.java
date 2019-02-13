package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.isNull;

public class Room implements Measurable {
    private static final String SAME_NAME = "Name already exists. Please write a new one.";
    private String mName;
    private int mHouseFloor;
    private Dimension mDimension;
    private SensorList mSensorList;
    private List<Device> mDeviceList;

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
        this.mDeviceList = new ArrayList<>();
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
        if (this.getSize() != 0) {
            for (Device device : this.mDeviceList) {
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
        int deviceListLength = this.getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            content.append(numberInTheList + " - Name of the device: " + getDeviceByPosition(i - 1).getName());
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
     * Method that adds a device to the list of Devices
     *
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add
     */
    public boolean addDevice(Device device) {
        if (Objects.isNull(device) || this.equals(device.getLocation()) && this.mDeviceList.contains(device)) {
            return false;
        }
        device.getLocation().removeDevice(device);
        device.setLocation(this);
        this.mDeviceList.add(device);
        return true;
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
        if (!this.mDeviceList.isEmpty()) {
            for (Device device : this.mDeviceList) {
                totalEnergyConsumption += device.getEnergyConsumptionInAnInterval(startDate, endDate);
            }
        }
        return totalEnergyConsumption;
    }


    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = new TreeMap<>();
        for (Device device : this.mDeviceList) {
            Map<LocalDateTime, Double> map2 = device.getDataSeries(startDate, endDate);
            for (Map.Entry<LocalDateTime, Double> entry : map2.entrySet()) {
                LocalDateTime key = entry.getKey();
                Double oldValue = map.get(key);
                map.put(key, oldValue == null ? entry.getValue() : entry.getValue() + oldValue);
            }
        }
        return map;
    }

    /**
     * get List of Devices
     *
     * @return List<Device>
     */
    public List<Device> getDeviceList() {
        return this.mDeviceList;
    }

    /**
     * get size of list of devices
     *
     * @return integer
     */
    public int getSize() {
        return this.mDeviceList.size();
    }

    /**
     * method that get a Device by it's position
     * @param position integer position of Device
     * @return Device
     */
    public Device getDeviceByPosition(int position) {
        return this.mDeviceList.get(position);
    }

    /**
     * method that check if a name of a Device already exists on the list of devices.
     *
     * @param name name of device
     * @return boolean true if exists, false if it doesn't
     */
    public boolean isDeviceNameExistant(String name) {

        for (int i = 0; i < this.mDeviceList.size(); i++) {
            if (this.mDeviceList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that check if the device list is empty
     */
    public boolean isEmpty() {
        return this.mDeviceList.isEmpty();
    }

    /**
     * method that gets the state of every device in the list of devices.
     *
     * @return String of the name and the status of the device ("Activated" or "Deactivated").
     */
    public String getActiveDeviceListToString() {
        String deviceName = " - Device name: ";
        StringBuilder content = new StringBuilder();
        int deviceListLength = getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            if (mDeviceList.get(i - 1).getIsActive()) {
                content.append(numberInTheList + deviceName + getDeviceList().get(i - 1).getName() + " - ACTIVATED");
                content.append("\n");
                numberInTheList++;
            } else {
                content.append(numberInTheList + deviceName + getDeviceList().get(i - 1).getName() + " - DEACTIVATED");
                content.append("\n");
                numberInTheList++;
            }
        }
        return content.toString();
    }

    /**
     * Method that remove a device from the list of devices
     */
    public boolean removeDevice(Device device) {
        return this.mDeviceList.remove(device);
    }

    /**
     * Method that gets all the devices of a certain type.
     *
     * @param type Required type.
     * @return DeviceList with all the devices of the required type.
     */
    public List<Device> getAllDevicesOfAType(String type) {
        List<Device> listOfDevicesWithTheType = new ArrayList<>();
        for (Device device : this.mDeviceList) {
            if (device.getType().equals(type)) {
                listOfDevicesWithTheType.add(device);
            }
        }
        return listOfDevicesWithTheType;
    }

    /**
     * Method that sets the value of an attribute of a device.
     *
     * @param devicePosition    Device position in the list of devices.
     * @param attributePosition Position of the attribute to be set.
     * @param value             Value to be used.
     * @return True or false.
     */
    public boolean setDeviceSpecAttribute(int devicePosition, int attributePosition, double value) {
        Device device = this.mDeviceList.get(devicePosition);
        return device.setAttributesDevType(attributePosition, value);
    }

    /**
     * Method that returns the energy consumption of a device.
     *
     * @param devicePosition Device position in the list of devices.
     * @return Double with the energy consumption.
     */
    public double getEnergyConsumptionOfADevice(int devicePosition) {
        Device device = this.mDeviceList.get(devicePosition);
        return device.getEnergyConsumptionInADay();
    }

    /**
     * Method that returns the combined energy consumption of all the devices in a list.
     *
     * @return Double with the combined energy consumption.
     */
    public double getTotalEnergyConsumption() {
        double totalEnergyConsumption = 0;
        for (Device device : this.mDeviceList) {
            totalEnergyConsumption += device.getEnergyConsumptionInADay();
        }
        return Utils.round(totalEnergyConsumption, 2);
    }

    /**
     * method that delete a device from the list of devices.
     *
     * @param device
     * @return true if the device was removed. False if not.
     */
    public boolean deleteDevice(String device) {
        for (Device searchDevice : this.mDeviceList) {
            if (device.equals(searchDevice.getName())) {
                this.mDeviceList.remove(searchDevice);
                return true;
            }
        }
        return false;
    }

    /**
     * method that get the name of the device by position.
     *
     * @param position
     * @return null if the list is empty.
     */
    public String getDeviceNameByPosition(int position) {
        if (this.mDeviceList.isEmpty()) {
            return "There are no devices in the device list.";
        }
        return this.mDeviceList.get(position).getName();
    }

    /**
     * method that deactivate the device.
     *
     * @param device
     * @return true if the device was deactivated. False, if not.
     */
    public boolean deactivateDevice(String device) {
        for (Device searchDevice : this.mDeviceList) {
            if (device.equals(searchDevice.getName())) {
                searchDevice.setDeactivateDevice();
                return true;
            }
        }
        return false;
    }
}