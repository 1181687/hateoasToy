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

    public Address getmAddress() {
        return mAddress;
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

    /**
     * method that adds a room to the house's roomlist
     * @param room given room we pretend to add
     * @return true if adds, false if don't
     */
    public boolean addRoomToHouse(Room room) {
        if (room == null) {
            return false;
        }
        return this.mRoomList.addRoomToRoomList(room);
    }

    /**
     * @return
     */
    public RoomList getListOfRoom() {
        return this.mRoomList;
    }

    /**
     * Get the location of the house.
     * @return the location of the house.
     */
    public Location getLocationOfTheHouse () {
        return this.mAddress.getLocation();
    }

}
