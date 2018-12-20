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


    public Address newAddresses (String mZipCode,double mLatitude, double mLongitude, double mAltitude) {
        Location newLocation = new Location (mLatitude,mLongitude,mAltitude);
        return new Address(mZipCode,newLocation);
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
