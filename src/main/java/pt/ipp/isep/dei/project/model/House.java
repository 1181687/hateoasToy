package pt.ipp.isep.dei.project.model;


import java.time.LocalDate;
import java.util.List;

public class House {
    private RoomList mRoomList;
    private HouseGridList mListHouseGrids;
    private Address mAddress;
    private GeographicalArea mInsertedGeoArea;

    /**
     * constructor of house that receives a room list, a list of house grids, an address and an insertedGeoArea.
     * @param mRoomList
     * @param mListHouseGrids
     * @param mAddress
     * @param mInsertedGeoArea
     */
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
     * Get Method of Address
     *
     * @return mAddress
     */
    public Address getmAddress() {
        return mAddress;
    }

    /**
     * Set Method of address
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
     * method that get inserted geo area
     * @return inserted geo area.
     */
    public GeographicalArea getmInsertedGeoArea() {
        return mInsertedGeoArea;
    }

    /**
     * method that adds a room to the house's roomlist
     *
     * @param room given room to be added
     * @return true if adds, false if doesn't
     */
    public boolean addRoom(Room room) {
        return this.mRoomList.addRoom(room);
    }

    /**
     * Get the location of the house.
     *
     * @return the location of the house.
     */
    public Location getLocation() {
        return this.mAddress.getLocation();
    }

    /**
     * method that get the last measurement of house area.
     * @param type
     * @return the last measurement with a location and a type of sensor.
     */
    public double getLastMeasurement(SensorType type) {
        return mInsertedGeoArea.getTheLastMeasurement(mAddress.getLocation(), type);
    }

    /**
     * Method that get the average daily measurement of the house area.
     * @param measurementType
     * @param startDate
     * @param endDate
     * @return the average daily measurement.
     */
    public double getAverageDailyMeasurement(SensorType measurementType, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = mInsertedGeoArea.getDailyAverageMeasurement(measurementType, startDate, endDate);
        double sum = 0;
        if (listOfDailyAverages.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < listOfDailyAverages.size(); i++) {
            sum += listOfDailyAverages.get(i);
        }
        return sum / listOfDailyAverages.size();
    }

    /**
     * method that get the total daily measurement of the house area.
     * @param measurementType
     * @param day
     * @return total daily measurement.
     */
    public double getTotalDailyMeasurement(SensorType measurementType, LocalDate day) {
        return mInsertedGeoArea.getTotalDailyMeasurement(measurementType, day);
    }

    /**
     * @param name name of room
     * @param type type of sensor (temperature)
     * @param date given day
     * @return returns the maximum temperature in a specific day
     */
    public double getMaximumTemperatureOfARoomInASpecificDay(String name, SensorType type, LocalDate date) {
        return this.mRoomList.getMaximumTemperatureInARoomInAGivenDay(name, type, date);
    }

    /**
     * This method create a new date, with a year, a month and a day.
     * @param year
     * @param month
     * @param day
     * @return date.
     */
    public LocalDate createANewDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    /**
     * Method that get the latest measurement by sensor type.
     * @param name
     * @param type
     * @return latest measurement.
     */
    public Measurement getLatestMeasurementBySensorType(String name, SensorType type) {
        Room room = mRoomList.getRoomByName(name);
        if (room == null)
            return null;
        Measurement measurement = room.getLatestMeasurementBySensorType(type);
        if (measurement == null)
            return null;
        return measurement;
    }

    /**
     * method that display a room list.
     */
    public String getRoomListContent() {
        return mRoomList.getRoomListContent();
    }

    /**
     * Method that get the size of the room list.
     * @return size of the list of rooms.
     */
    public int listSize() {
        return mRoomList.listSize();
    }

    /**
     * method that get the name of the chosen room in a specific position from the list.
     * @param position
     * @return a position.
     */
    public String getNameOfTheChosenRoomInSpecificPos(int position) {
        return mRoomList.getNameOfTheChosenRoomInSpecificPosition(position);
    }

    /**
     * method that create a new room to the list, with height, length, width, name and housefloor.
     * @param height
     * @param length
     * @param width
     * @param name
     * @param housefloor
     * @return a new room in the list.
     */
    public Room newRoom(double height, double length, double width, String name, int housefloor) {
        return mRoomList.newRoom(name, housefloor, height, length, width);
    }

    /**
     * method that check if a name of a room already exists on the list of rooms.
     * @param name
     */
    public boolean checkIfNameAlreadyExists(String name) {
        return this.mRoomList.checkIfNameAlreadyExists(name);
    }

    /**
     * method that get a room of the list of rooms, from a specific position.
     * @param option
     */
    public Room getRoomOfTheRoomList(int option) {
        return mRoomList.getRoomFromAPosition(option);
    }

    /**
     * Method that displays the device list content of a Room
     *
     * @param position position of the room in the room list
     * @return list of devices of a roomMethod that displays the content
     */
    public String getDeviceListContentOfARoom(int position) {
        return mRoomList.getDeviceListContentOfARoom(position);
    }

    /**
     * method that displays the sensor list content of a Room
     * @param position
     */
    public String getSensorListContentOfARoom (int position) {
        return mRoomList.getSensorListContentOfARoom(position);
    }

    /**
     * method that check if the sensor list of the room is empty
     * @param position
     */
    public boolean checkIfSensorListIsEmpty (int position) {
        return mRoomList.checkIfSensorListIsEmpty(position);
    }
}
