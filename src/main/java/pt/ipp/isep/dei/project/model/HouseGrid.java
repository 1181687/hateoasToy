package pt.ipp.isep.dei.project.model;

public class HouseGrid {
    private String mHouseGridName;
    private double mMaximumContractedPower;
    private PowerSourceList mPowerSourceList= new PowerSourceList();
    private RoomList mRoomsConnectedToHouseGrid= new RoomList();

    public HouseGrid(double maximumContractedPower) {
        this.mMaximumContractedPower = maximumContractedPower;
    }

    public HouseGrid(double mMaximumContractedPower, RoomList mRoomsConnectedToHouseGrid) {
        this.mMaximumContractedPower = mMaximumContractedPower;
        this.mRoomsConnectedToHouseGrid = mRoomsConnectedToHouseGrid;
    }

    public void setmHouseGridName(String mHouseGridName) {
        this.mHouseGridName = mHouseGridName;
    }

    public String getmHouseGridName() {
        return mHouseGridName;
    }

    public PowerSourceList getPowerSourceList() {
        return mPowerSourceList;
    }

    public double getmMaximumContractedPower() {
        return mMaximumContractedPower;
    }

    public void setmMaximumContractedPower(double mMaximumContractedPower) {
        this.mMaximumContractedPower = mMaximumContractedPower;
    }

    public boolean addRoomToHouseGrid (Room houseRoom){
       if (!(mRoomsConnectedToHouseGrid.getmList().contains(houseRoom))){
           mRoomsConnectedToHouseGrid.getmList().add(houseRoom);
           return true;
       }
            return false;
    }

    public RoomList getmRoomsConnectedToHouseGrid() {
        return mRoomsConnectedToHouseGrid;
    }



    /**
     * Method that detaches a room from a house grid. It return a true in case of success
     * and false in the case of a failure.
     * @param roomToDetach
     * @return
     */
    public boolean detachRoomFromHouseGrid(Room roomToDetach) {
        for (Room room : this.mRoomsConnectedToHouseGrid.getmList()) {
            if (room.equals(roomToDetach)) {
                this.mRoomsConnectedToHouseGrid.getmList().remove(room);
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
        mRoomsConnectedToHouseGrid.getmList().add(room);
    }

    public boolean addPowerSourceToHouseGrid(PowerSource newPowerSource){
        return this.mPowerSourceList.addPowerSourceToList(newPowerSource);
    }
}
