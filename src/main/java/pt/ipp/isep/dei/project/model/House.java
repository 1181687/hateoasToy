package pt.ipp.isep.dei.project.model;


public class House {
    private RoomList mRoomList;
    private HouseGridList mListHouseGrids;
    private Address mAddress;


    public House(RoomList roomList, HouseGridList listHouseGrids, Address address) {
        this.mRoomList = roomList;
        this.mListHouseGrids = listHouseGrids;
        this.mAddress = address;
    }

    public void setmAddress(Address mAddress) {
        this.mAddress = mAddress;
    }

    /**
     * @param mZipCode
     * @param mLatitude
     * @param mLongitude
     * @param mAltitude
     * @return method for the creation of a new Address
     */
    public Address newAddresses(String mZipCode, double mLatitude, double mLongitude, double mAltitude) {
        Location location = new Location(mLatitude, mLongitude, mAltitude);
        return new Address(mZipCode, location);
    }


    public House(RoomList RoomList) {
        this.mRoomList = RoomList;
    }

    public boolean addRoomToHouse(Room room) {
        return this.mRoomList.addRoomToRoomList(room);
    }

    /**
     * @return
     */
    public RoomList getListOfRoom() {
        return this.mRoomList;
    }

}
