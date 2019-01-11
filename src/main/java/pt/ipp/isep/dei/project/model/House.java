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

    public RoomList getRoomList() {
        return mRoomList;
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

    public double getTotalDailyMeasurementOfHouseArea(SensorType measurementType, Date day) {
        return mInsertedGeoArea.getTotalDailyMeasurementInTheArea(measurementType, day);
    }

    /**
     * @param name name of room
     * @param type type of sensor (temperature)
     * @param date given day
     * @return returns the maximum temperature in a specific day
     */
    public double getMaximumTemperatureOfARoomInASpecificDay(String name, SensorType type, Date date) {
        return this.mRoomList.getMaximumTemperatureInARoomInAGivenDay(name, type, date);
    }

    public Date createANewDate (int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        //Convert LocalDate to Date
        Date newDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return newDate;
    }


    public Measurement getLatestMeasurementBySensorType(String name, SensorType type) {
        Room room = mRoomList.getRoomByName(name);
        if (room == null)
            return null;
        Measurement measurement = room.getLatestMeasurementBySensorType(type);
        if (measurement == null)
            return null;
        return measurement;
    }

    public String getDisplayRoomList() {
        return mRoomList.getDisplayRoomList();
    }

    public int listSize() {
        return mRoomList.listSize();
    }

    public String getNameOfTheChosenRoomInSpecificPos(int position) {
        return mRoomList.getNameOfTheChosenRoomInSpecificPos(position);
    }

    public Room newRoom(double height, double length, double width, String name, int housefloor) {
        return mRoomList.newRoom(name, housefloor, height, length, width);
    }

    public boolean checkIfNameAlreadyExists(String name) {
        return this.mRoomList.checkIfNameAlreadyExists(name);
    }
}
