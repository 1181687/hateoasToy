package pt.ipp.isep.dei.project.model;

public class Room {
    private String mName;
    private int mHouseFloor;
    private Dimensions mDimensions;

    public Room(String mName, int mHouseFloor, Dimensions dimensions) {
        this.mName = mName;
        this.mHouseFloor = mHouseFloor;
        this.mDimensions = dimensions;
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
}