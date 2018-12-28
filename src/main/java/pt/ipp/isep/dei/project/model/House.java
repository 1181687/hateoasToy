package pt.ipp.isep.dei.project.model;


import java.util.ArrayList;
import java.util.Date;

public class House {
    private RoomList mRoomList;
    private HouseGridList mListHouseGrids;
    private Address mAddress;
    private GeographicalArea mInsertedGeoArea;

    public House(RoomList roomList, HouseGridList listHouseGrids, Address address) {
        this.mRoomList = roomList;
        this.mListHouseGrids = listHouseGrids;
        this.mAddress = address;
    }
    public House(RoomList mRoomList, HouseGridList mListHouseGrids, Address mAddress, GeographicalArea mInsertedGeoArea) {
        this.mRoomList = mRoomList;
        this.mListHouseGrids = mListHouseGrids;
        this.mAddress = mAddress;
        this.mInsertedGeoArea = mInsertedGeoArea;
    }

    /**
     * Get Method
     *
     * @return mAddress
     */
    public Address getmAddress() {
        return mAddress;
    }

    /**
     * Set Method
     *
     * @param mAddress
     */
    public void setmAddress(Address mAddress) {
        this.mAddress = mAddress;
    }

    /**
     * Method that creates a new address considering the parameters below
     *
     * @param mZipCode   String zipCode
     * @param mLatitude  attribute of Location. Double
     * @param mLongitude attribute of Location. Double
     * @param mAltitude  attribute of Location. Double
     * @return method for the creation of a new Address
     */
    public Address newAddresses(String mZipCode, double mLatitude, double mLongitude, double mAltitude) {
        Location location = new Location(mLatitude, mLongitude, mAltitude);
        return new Address(mZipCode, location);
    }

    /**
     * method that adds a room to the house's roomlist
     *
     * @param room given room to be added
     * @return true if adds, false if doesn't
     */
    public boolean addRoomToHouse(Room room) {
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
     *
     * @return the location of the house.
     */
    public Location getLocationOfTheHouse() {
        return this.mAddress.getLocation();
    }

    public double getLastTemperatureOfTheHouseArea() {
        return mInsertedGeoArea.getLastTemperatureInTheArea(mAddress.getLocation());
    }

    public double getAverageDailyMeasurementOfHouseArea(SensorType measurementType, Date startDate, Date endDate) {
        ArrayList<Double> listOfDailyAverages = mInsertedGeoArea.getDailyAverageMeasurementInTheArea(measurementType, startDate, endDate);
        double sum = 0;
        if (listOfDailyAverages.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < listOfDailyAverages.size(); i++) {
            sum += listOfDailyAverages.get(i);
        }
        return sum / listOfDailyAverages.size();
    }
}
