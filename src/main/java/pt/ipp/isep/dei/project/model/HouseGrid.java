package pt.ipp.isep.dei.project.model;

public class HouseGrid implements Measurable {
    private String mHouseGridName;
    private double mMaximumContractedPower;
    private PowerSourceList mPowerSourceList;
    private RoomList mRoomsConnectedToHouseGrid;

    /**
     * constructor of a house grid that receives a name, a room list, a list of power sources and a maximum contracted power.
     * @param mHouseGridName
     */
    public HouseGrid(String mHouseGridName) {
        this.mHouseGridName = mHouseGridName;
        this.mRoomsConnectedToHouseGrid = new RoomList();
        this.mPowerSourceList = new PowerSourceList();
        this.mMaximumContractedPower = Double.NaN;
    }

    /**
     * constructor of a house grid that receives a name, a maximum contracted power and a list of rooms connected to the house grid.
     *
     * @param houseGridName
     * @param maximumContractedPower
     * @param roomsConnectedToHouseGrid
     */
    public HouseGrid(String houseGridName, double maximumContractedPower, RoomList roomsConnectedToHouseGrid) {
        this.mHouseGridName = houseGridName;
        this.mMaximumContractedPower = maximumContractedPower;
        this.mRoomsConnectedToHouseGrid = roomsConnectedToHouseGrid;
    }

    /**
     * method that get the name of the house grid.
     */
    public String getmHouseGridName() {
        return mHouseGridName;
    }

    /**
     * method that get the list of power sources.
     */
    public PowerSourceList getPowerSourceList() {
        return mPowerSourceList;
    }

    /**
     * method that get the rooms connected to the house grid.
     */
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
    public boolean detachRoom(Room roomToDetach) {
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
     * @param room Speficied room to attach.
     */
    public void attachRoomToTheRoomList(Room room) {
        mRoomsConnectedToHouseGrid.getmRoomList().add(room);
    }

    /**
     * Method that add a new power source to the list of power sources.
     * @param newPowerSource
     * @return
     */
    public boolean addPowerSource(PowerSource newPowerSource) {
        return this.mPowerSourceList.addPowerSource(newPowerSource);
    }

    /**
     * Method that attaches a list of existing rooms to a house grid.
     *
     * @return a list of existing rooms attached to a house grid.
     */
    public String getRoomsAttached() {
        return this.mRoomsConnectedToHouseGrid.getRoomListContent();
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

    public String listPowerSources() {
        return mPowerSourceList.getPowerSourcesListContent();
    }

    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Room room : mRoomsConnectedToHouseGrid.getmRoomList()) {
            totalNominalPower += room.getNominalPower();
        }
        return totalNominalPower;
    }

    /**
     * method that gets a List of all Devices in all Rooms of a Housegrid
     * @return List <Device>
     */
    public DeviceList getAllDevicesList() {
        return mRoomsConnectedToHouseGrid.getAllDevicesList();
    }

    public boolean checkIfThereAreNoDevices() {
        return this.getmRoomsConnectedToHouseGrid().checkIfThereAreNoDevices();
    }


    public String getRoomListToStringInAGrid() {
        return mRoomsConnectedToHouseGrid.getRoomListToString();
    }

    public int sizeOfTheRoomListInAGrid() {
        return mRoomsConnectedToHouseGrid.listSize();
    }

    public Room getRoomInTheGridByPosition(int position) {
        return mRoomsConnectedToHouseGrid.getRoomFromAPosition(position);
    }

    public String getDeviceListContent(int position) {
        return mRoomsConnectedToHouseGrid.getRoomFromAPosition(position).getDeviceListContent();
    }

    public int getSizeOfListOfDevicesOfRoomAttachedToGrid(int position) {
        return mRoomsConnectedToHouseGrid.getRoomFromAPosition(position).getSizeOfDevicesList();
    }

    public boolean checkIsRoomListIsEmpty() {
        return mRoomsConnectedToHouseGrid.checkIfRoomListIsEmpty();
    }

    public boolean checkIfDeviceListIsEmpty(int position) {
        return mRoomsConnectedToHouseGrid.getRoomFromAPosition(position).checkIfDeviceListIsEmpty();
    }

    //public Device getDeviceFromPositionInList(int positionRoom, int positionDevice){
    //return mRoomsConnectedToHouseGrid.getRoomFromAPosition(positionRoom).getDeviceList().getDeviceByPosition(positionDevice);
}