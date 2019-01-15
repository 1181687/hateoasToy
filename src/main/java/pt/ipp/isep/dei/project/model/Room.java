package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Room implements Measurable{
    private String mName;
    private int mHouseFloor;
    private Dimensions mDimensions;
    private SensorList mSensorList;
    private DeviceList mDeviceList;

    /**
     * constructor that receives name, houseFloor, dimensions
     * throw an exception if any of the parameters is invalid.
     * Invalid parameters if Dimensions is null or name is null or empty
     *
     * @param name
     * @param houseFloor
     * @param dimensions
     */
    public Room(String name, int houseFloor, Dimensions dimensions) {
        validateName(name);
        validateDimensions(dimensions);
        this.mName = name.trim();
        this.mHouseFloor = houseFloor;
        this.mDimensions = dimensions;
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
     * method that receives an object Dimensions and validates it. It can not be null
     * throw an exception if Dimensions is null
     *
     * @param dimensions given object dimensions
     */
    private static void validateDimensions(Dimensions dimensions) {
        if (isNull(dimensions)) {
            throw new RuntimeException("Dimensions should not be null");
        }
    }

    /**
     * Get method
     *
     * @return mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * Method that defines the name of the room
     *
     * @param mName name of a room (string)
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * Get Method
     * @return mHouseFloor
     */
    public int getmHouseFloor() {
        return mHouseFloor;
    }

    /**
     * Method that defines the House Floor number of the room
     *
     * @param mHouseFloor house floor of the room (int number)
     */
    public void setmHouseFloor(int mHouseFloor) {
        this.mHouseFloor = mHouseFloor;
    }

    /**
     * Get Method
     * @return mDimensions
     */
    public Dimensions getmDimensions() {
        return mDimensions;
    }

    /**
     * method that displays a Room with its characteristics (name, house floor, height, length and width)
     * @return Rooms
     */
    public String getRoomContent() {
        StringBuilder content = new StringBuilder();
        content.append("Name: " + getmName());
        content.append(", House Floor: " + getmHouseFloor());
        content.append(", Dimensions - Height: " + getmDimensions().getmHeight());
        content.append(", Dimensions - Length: " + getmDimensions().getmLength());
        content.append(", Dimensions - Width: " + getmDimensions().getmWidth());
        return content.toString();
    }

    /**
     * method that creates the same hashcode to rooms with the same attribute name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(mName);
    }

    /**
     * Equals method to determine if two Rooms are equal.
     * They are equals if name are equal.
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
        return this.mName.equals(roomOne.mName);
    }

    /**
     * This method add a new sensor to the list of sensors in the room
     *
     * @param newSensor add to the list of sensors
     * @return a new sensor to the list of sensors
     */
    public boolean addSensorToTheListOfSensorsInTheRoom(Sensor newSensor) {
        return this.mSensorList.addSensorToTheListOfSensors(newSensor);
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
    public double getMaximumMeasurementInAGivenDay(SensorType type, LocalDate date) {
        return mSensorList.getMaximumMeasureOfATypeOfSensorInAGivenDay(type, date);
    }

    /**
     * Method that gets the latest measurement by type of sensor
     * @param type type of sensor
     * @return latest measurement by sensor type
     */
    public Measurement getLatestMeasurementBySensorType(SensorType type) {
        return mSensorList.getLatestMeasurementBySensorType(type);
    }

    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        if (mDeviceList.getLength() != 0) {
            for (Device device : mDeviceList.getmDeviceList()) {
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
    public String getSensorListContent () {
        return this.mSensorList.getSensorsListContent();
    }

    /**
     * method that check if the sensor list of the room is empty
     */
    public boolean checkIfSensorListIsEmpty () {
        return this.mSensorList.checkIfSensorListIsEmpty();
    }


    /**
     * method that displays the device list content
     *
     * @return content of device list
     */
    public String getDeviceListContent() {
        return this.mDeviceList.getDeviceListContent();
    }

    /**
     * method that checks if Device List of the room is empty
     */
    public boolean checkIfDeviceListIsEmpty() {
        return this.mDeviceList.checkIfDeviceListIsEmpty();
    }

    /**
     * get method
     * @return device list
     */
    public DeviceList getmDeviceList() {
        return mDeviceList;
    }

    /**
     * method that gets a List of all Devices in a Room
     *
     * @return List <Device>
     */
    public DeviceList getDeviceList() {
        Device dev;
        DeviceList allDeviceList = new DeviceList();

        for (int j = 0; j < mDeviceList.getLength(); j++) {
            dev = mDeviceList.getDeviceByPosition(j);
            allDeviceList.addDevice(dev);
        }
        return allDeviceList;
    }

    /**
     * Method that adds a device to the list of Devices of the room
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add
     */
    public boolean addDevice(Device device) {
        return mDeviceList.addDevice(device);
    }

    public int getSizeOfDevicesList(){
        return mDeviceList.getLength();
    }

    /**
     * Method that allows the possibility of setting the cold-water temperature and the volume of water to heat in the
     * class Electric Water Heater.
     *
     * @param coldWaterTemp       Sets the current temperature of the water that is going to be heated.
     * @param volumeOfWaterToHeat Sets the amount of water to be heated.
     */
    public void setColdWaterTempAndVolumeOfWaterToHeat(double coldWaterTemp, double volumeOfWaterToHeat) {
        mDeviceList.setColdWaterTempAndVolumeOfWaterToHeat(coldWaterTemp, volumeOfWaterToHeat);
    }

    /**
     * @param type
     * @return
     */
    public double getEnergyConsumptionInADayOfAllDevicesOfAType(String type) {
        return mDeviceList.getEnergyConsumptionInADayOfAllDevicesOfAType(type);
    }
}
