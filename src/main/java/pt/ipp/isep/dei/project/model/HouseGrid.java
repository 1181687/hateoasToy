package pt.ipp.isep.dei.project.model;

public class HouseGrid {
    private String mHouseGridName;
    private double mMaximumContractedPower;
    private PowerSourceList mPowerSourceList;
    private RoomList mRoomsConnectedToHouseGrid;

    public HouseGrid(String mHouseGridName) {
        this.mHouseGridName = mHouseGridName;
        this.mRoomsConnectedToHouseGrid = new RoomList();
        this.mPowerSourceList = new PowerSourceList();
        this.mMaximumContractedPower = Double.NaN;
    }


    public HouseGrid(String HouseGridName, double mMaximumContractedPower, RoomList mRoomsConnectedToHouseGrid) {
        this.mHouseGridName = HouseGridName;
        this.mMaximumContractedPower = mMaximumContractedPower;
        this.mRoomsConnectedToHouseGrid = mRoomsConnectedToHouseGrid;
    }

    public String getmHouseGridName() {
        return mHouseGridName;
    }

    public PowerSourceList getPowerSourceList() {
        return mPowerSourceList;
    }

    public RoomList getmRoomsConnectedToHouseGrid() {
        return mRoomsConnectedToHouseGrid;
    }


    /**
     * Method that detaches a room from a house grid. It return a true in case of success
     * and false in the case of a failure.
     *
     * @param roomToDetach
     * @return
     */
    public boolean detachRoomFromHouseGrid(Room roomToDetach) {
        for (Room room : this.mRoomsConnectedToHouseGrid.getmRoomList()) {
            if (room.equals(roomToDetach)) {
                this.mRoomsConnectedToHouseGrid.getmRoomList().remove(room);
                return true;
            }
        }
        return false;
    }

    /**
     * Method that attaches a room in the house grid's room list.
     *
     * @param room Speficied room to attach.
     */
    public void attachRoomInTheHouseGridRoomList(Room room) {
        mRoomsConnectedToHouseGrid.getmRoomList().add(room);
    }

    public boolean addPowerSourceToHouseGrid(PowerSource newPowerSource) {
        return this.mPowerSourceList.addPowerSourceToList(newPowerSource);
    }

    /**
     * Method that attaches a list of existing rooms to a house grid.
     *
     * @return a list of existing rooms attached to a house grid.
     */
    public String displayRoomsAttachedToHouseGrid() {
        return this.mRoomsConnectedToHouseGrid.getDisplayRoomList();
    }


    /**
     * Method that checks if a room isn't already in the grid.
     *
     * @param room Room to be used.
     * @return True or false.
     */
    public boolean checkIfARoomIsAlreadyInTheGrid(Room room) {
        return mRoomsConnectedToHouseGrid.getmRoomList().contains(room);
    }

    public String listPowerSources(){
        return mPowerSourceList.listPowerSources();
    }
}