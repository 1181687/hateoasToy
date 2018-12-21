package pt.ipp.isep.dei.project.model;

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

    public boolean addSensorToTheListOfSensorsInTheRoom (Sensor newSensor) {
        return this.mSensorList.addSensorToTheListOfSensors(newSensor);
    }

    public SensorList getSensorList() {
        return mSensorList;
    }
}
