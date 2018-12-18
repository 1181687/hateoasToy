package pt.ipp.isep.dei.project.model;

public class HouseGrid {
    private double mMaximumContractedPower;
    private DeviceList mDeviceListOnHouseGrid = new DeviceList();
    private PowerSourceList mPowerSourceList= new PowerSourceList();
    private RoomList mRoomsConnectedToHouseGrid;

    public HouseGrid(double maximumContractedPower) {
        this.mMaximumContractedPower = maximumContractedPower;
    }

    public boolean addPowerSourceToHouseGrid(PowerSource newPowerSource){
        return this.mPowerSourceList.addPowerSourceToList(newPowerSource);
    }
}
