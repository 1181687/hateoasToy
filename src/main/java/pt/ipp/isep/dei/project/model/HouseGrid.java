package pt.ipp.isep.dei.project.model;

public class HouseGrid {
    private double mMaximumContractedPower;
    private DeviceList mDeviceListOnHouseGrid = new DeviceList();
    private PowerSourceList mPowerSourceList= new PowerSourceList();
    private RoomList mRoomsConnectedToHouseGrid;

    public HouseGrid(double maximumContractedPower) {
        this.mMaximumContractedPower = maximumContractedPower;
    }

    /**
     * Method that attaches a room in the house grid's room list.
     *
     * @param room Speficied room to attach.
     */
    public void attachRoomInTheHouseGridRoomList(Room room) {
        mRoomsConnectedToHouseGrid.getmList().add(room);
    }
}
