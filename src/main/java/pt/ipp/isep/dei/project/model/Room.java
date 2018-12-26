package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class Room {
    private String mName;
    private int mHouseFloor;
    private Dimensions mDimensions;
    private SensorList mSensorList;

    public Room(String mName, int mHouseFloor, Dimensions dimensions) {
        this.mName = mName;
        this.mHouseFloor = mHouseFloor;
        this.mDimensions = dimensions;
        this.mSensorList = new SensorList();
    }

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
     * @param newSensor add to the list of sensors
     * @return a new sensor to the list of sensors
     */
    public boolean addSensorToTheListOfSensorsInTheRoom (Sensor newSensor) {
        return this.mSensorList.addSensorToTheListOfSensors(newSensor);
    }

    /**
     * This method get de sensors list.
     * @return the list of sensors.
     */
    public SensorList getSensorList() {
        return mSensorList;
    }
}
