package pt.ipp.isep.dei.project.model;

import static java.util.Objects.isNull;

public class HouseGrid implements Measurable {
    private String mName;
    private double mMaximumContractedPower;
    private PowerSourceList mPowerSourceList;
    private RoomList mRoomList;

    /**
     * constructor of a house grid that receives a name, a room list, a list of power sources and a maximum contracted power.
     * @param houseGridName
     */

    public HouseGrid(String houseGridName) {
        validateName(houseGridName);

        this.mName = houseGridName;
        this.mRoomList = new RoomList();
        this.mPowerSourceList = new PowerSourceList();
        this.mMaximumContractedPower = 0;
    }

    /**
     * constructor of a house grid that receives a name, a maximum contracted power and a list of rooms connected to the house grid.
     *
     * @param houseGridName
     * @param maximumContractedPower
     * @param roomsConnectedToHouseGrid
     */
    public HouseGrid(String houseGridName, double maximumContractedPower, RoomList roomsConnectedToHouseGrid) {
        validateName(houseGridName);

        this.mName = houseGridName;
        this.mMaximumContractedPower = maximumContractedPower;
        this.mRoomList = roomsConnectedToHouseGrid;
    }

    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new RuntimeException("Please enter a valid name. Name should not be empty");
        }
    }

    /**
     * method that get the name of the house grid.
     */
    public String getName() {
        return mName;
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
    public RoomList getRoomList() {
        return mRoomList;
    }

    /**
     * Method that detaches a room from a house grid. It return a true in case of success
     * and false in the case of a failure.
     *
     * @param roomToDetach
     * @return
     */
    public boolean detachRoom(Room roomToDetach) {
        for (Room room : this.mRoomList.getmRoomList()) {
            if (room.equals(roomToDetach)) {
                this.mRoomList.getmRoomList().remove(room);
                return true;
            }
        }
        return false;
    }

    /**
     * Method that attaches a room in the house grid's room list.
     * @param room Speficied room to attach.
     */
    public void attachRoom(Room room) {
        mRoomList.getmRoomList().add(room);
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
    public String getRoomListContent() {
        return this.mRoomList.getRoomListContent();
    }


    /**
     * Method that checks if a room isn't already in the grid.
     *
     * @param room Room to be used.
     * @return True or false.
     */
    public boolean checkIfRoomIsInHouseGrid(Room room) {
        return mRoomList.getmRoomList().contains(room);
    }

    public String getPowerSourceListContent() {
        return mPowerSourceList.getPowerSourcesListContent();
    }

    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Room room : mRoomList.getmRoomList()) {
            totalNominalPower += room.getNominalPower();
        }
        return totalNominalPower;
    }

    /**
     * method that gets a List of all Devices in all Rooms of a Housegrid
     * @return List <Device>
     */
    public DeviceList getAllDevicesList() {
        return mRoomList.getAllDevicesList();
    }

    /**
     * method that checks if there are no devices in the RoomList
     *
     * @return true if there aren't devices. False if there are devices
     */
    public boolean checkIfThereAreNoDevices() {
        return this.getRoomList().checkIfThereAreNoDevices();
    }


    public int getRoomListSize() {
        return mRoomList.listSize();
    }

    public Room getRoomByPosition(int position) {
        return mRoomList.getRoomFromAPosition(position);
    }

    public String getDeviceListContent(int position) {
        return mRoomList.getRoomFromAPosition(position).getDeviceListContent();
    }

    public int getDeviceListSizeByRoomPosition(int position) {
        return mRoomList.getRoomFromAPosition(position).getSizeOfDevicesList();
    }

    public boolean checkIfRoomListIsEmpty() {
        return mRoomList.checkIfRoomListIsEmpty();
    }

    public boolean checkIfDeviceListIsEmpty(int position) {
        return mRoomList.getRoomFromAPosition(position).checkIfDeviceListIsEmpty();
    }

    public Device getDeviceFromPositionInList(int pos1, int pos2) {
        return getRoomByPosition(pos1).getmDeviceList().getDeviceByPosition(pos2);
    }

}