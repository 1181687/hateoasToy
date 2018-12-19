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

    /**
     * Method that checks if the room isn't in a house grid (basically, if the room doesn't have a value in the attribute mHouseGrid.
     *
     * @return True or false.
     */
    public boolean checkIfTheRoomIsntInAHouseGrid() {
        return mHouseGrid == null;
    }

    /**
     * Method that asks the changes the attribute mHouseGrid to the room.
     *
     * @param houseGrid Specified house grid.
     */
    public void changeTheAttributeHouseGrid(HouseGrid houseGrid) {
        mHouseGrid = houseGrid;
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
        StringBuilder conteudo = new StringBuilder();
            conteudo.append(" - Name: " + getmName());
            conteudo.append(", House Floor: " + getmHouseFloor());
            conteudo.append(", Dimensions - Height: " + getmDimensions().getmHeight());
            conteudo.append(", Dimensions - Length: " + getmDimensions().getmLength());
            conteudo.append(", Dimensions - Width: " + getmDimensions().getmWidth());
        return conteudo.toString();
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmHouseFloor(int mHouseFloor) {
        this.mHouseFloor = mHouseFloor;
    }
}
