package pt.ipp.isep.dei.project.model;

public class Room {
    private String mName;
    private int mHouseFloor;
    private Dimensions mDimensions;
    private HouseGrid mHouseGrid;

    public Room(String mName, int mHouseFloor, Dimensions dimensions, HouseGrid mHouseGrid) {
        this.mName = mName;
        this.mHouseFloor = mHouseFloor;
        this.mDimensions = dimensions;
        this.mHouseGrid = mHouseGrid;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two Rooms are equal.
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
                && this.mDimensions.equals(roomOne.mDimensions) && this.mHouseGrid.equals(roomOne.mHouseGrid);
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
