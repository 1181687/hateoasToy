package pt.ipp.isep.dei.project.model;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class House {
    private RoomList mRoomList;
    private HouseGridList mListHouseGrids;
    private Address mAddress;
    private GeographicalArea mInsertedGeoArea;

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

    public GeographicalArea getmInsertedGeoArea() {
        return mInsertedGeoArea;
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

    public double getLastMeasurementOfTheHouseArea(SensorType type) {
        return mInsertedGeoArea.getTheLastMeasurementInTheArea(mAddress.getLocation(), type);
    }

    public double getAverageDailyMeasurementOfHouseArea(SensorType measurementType, Date startDate, Date endDate) {
        List<Double> listOfDailyAverages = mInsertedGeoArea.getDailyAverageMeasurementInTheArea(measurementType, startDate, endDate);
        double sum = 0;
        if (listOfDailyAverages.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < listOfDailyAverages.size(); i++) {
            sum += listOfDailyAverages.get(i);
        }
        return sum / listOfDailyAverages.size();
    }

    public double getTotalDailyMeasurementOfHouseArea (SensorType measurementType, Date day){
        return mInsertedGeoArea.getTotalDailyMeasurementInTheArea(measurementType, day);
    }

    public Date createANewDate (int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        //Convert LocalDate to Date
        Date newDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return newDate;
    }

}
