package pt.ipp.isep.dei.project.model;

import java.util.Date;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Room {
    private String mName;
    private int mHouseFloor;
    private Dimensions mDimensions;
    private SensorList mSensorList;

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * constructor that receives name, houseFloor, dimensions, sensorList
     * throw an exception if any of the parameters is invalid.
     * Invalid parameters if Dimensions is null or name is null or empty
     *
     * @param name
     * @param houseFloor
     * @param dimensions
     * @param sensorList
     */
    public Room(String name, int houseFloor, Dimensions dimensions, SensorList sensorList) {
        validateName(name);
        validateDimensions(dimensions);
        this.mName = name.trim();
        this.mHouseFloor = houseFloor;
        this.mDimensions = dimensions;
        this.mSensorList = sensorList;
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

    ////////////////////////////////////////////////////////////////////////////////////////////
    public String getmName() {
        return mName;
    }

    public int getmHouseFloor() {
        return mHouseFloor;
    }

    public Dimensions getmDimensions() {
        return mDimensions;
    }

    public String getRoomDisplay() {
        StringBuilder content = new StringBuilder();
        content.append("Name: " + getmName());
        content.append(", House Floor: " + getmHouseFloor());
        content.append(", Dimensions - Height: " + getmDimensions().getmHeight());
        content.append(", Dimensions - Length: " + getmDimensions().getmLength());
        content.append(", Dimensions - Width: " + getmDimensions().getmWidth());
        return content.toString();
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmHouseFloor(int mHouseFloor) {
        this.mHouseFloor = mHouseFloor;
    }

    /**
     * method that creates the same hashcode to rooms with the same attributes: name, housefloor and dimensions.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(mName, mHouseFloor, mDimensions);
    }

    /**
     * Equals method to determine if two Rooms are equal.
     * They are equals if all atributtes are equal.
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
        return this.mName.equals(roomOne.mName) && this.mHouseFloor == roomOne.mHouseFloor
                && this.mDimensions.equals(roomOne.mDimensions);
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
     * This method get de sensors list.
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
    public double getMaximumMeasurementInAGivenDay(SensorType type, Date date) {
        return mSensorList.getMaximumMeasureOfATypeOfSensorInAGivenDay(type, date);
    }
}
