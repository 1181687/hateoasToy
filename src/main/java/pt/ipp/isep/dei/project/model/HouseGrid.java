package pt.ipp.isep.dei.project.model;

public class HouseGrid {
    private String mHouseGridName;
    private double mMaximumContractedPower;
    private DeviceList mDeviceListOnHouseGrid = new DeviceList();
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


}
