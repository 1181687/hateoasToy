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

    @Override
    public int hashCode() {
        return 1;
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
}
