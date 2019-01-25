package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Room implements Measurable {
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
    public Measurement getLatestMeasurementBySensorType(SensorType type) {
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
     * @return content of device list
     */
    public String getDeviceListToString() {
        return this.mDeviceList.getDeviceListToString();
    }

    /**
     * method that checks if Device List of the room is empty
     */
    public boolean isDeviceListEmpty() {
        return this.mDeviceList.isDeviceListEmpty();
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

    public int getDevicesListLength() {
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
    public boolean isNameExistant(String name) {
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
     * TODO
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        double totalEnergyConsumption = 0;
        if (!mDeviceList.isDeviceListEmpty()) {
            for (Device device : mDeviceList.getDeviceList()) {
                totalEnergyConsumption += device.getEnergyConsumptionInAnInterval(startDate, endDate);
            }
        }
        return totalEnergyConsumption;
    }

    public boolean deleteDevice(Device device) {
        return this.mDeviceList.deleteDevice(device);
    }
}
