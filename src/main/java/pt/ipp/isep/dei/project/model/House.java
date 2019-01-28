package pt.ipp.isep.dei.project.model;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class House {
    private RoomList mRoomList;
    private HouseGridList mListHouseGrids;
    private Address mAddress;
    private GeographicalArea mInsertedGeoArea;

    /**
     * constructor of house that receives a room list, a list of house grids, an address and an insertedGeoArea.
     * @param roomList
     * @param listHouseGrids
     * @param address
     * @param insertedGeoArea
     */
    public House(RoomList roomList, HouseGridList listHouseGrids, Address address, GeographicalArea insertedGeoArea) {
        this.mRoomList = roomList;
        this.mListHouseGrids = listHouseGrids;
        this.mAddress = address;
        this.mInsertedGeoArea = insertedGeoArea;
    }

    public RoomList getRoomList() {
        return mRoomList;
    }

    /**
     * Get Method of Address
     *
     * @return mAddress
     */
    public Address getAddress() {
        return mAddress;
    }

    /**
     * Set Method of address
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.mAddress = address;
    }

    /**
     * Method that creates a new address considering the parameters below
     *
     * @param zipCode   String zipCode
     * @param latitude  attribute of Location. Double
     * @param longitude attribute of Location. Double
     * @param altitude  attribute of Location. Double
     * @return method for the creation of a new Address
     */
    public Address newAddresses(String zipCode, double latitude, double longitude, double altitude) {
        Location location = new Location(latitude, longitude, altitude);
        return new Address(zipCode, location);
    }

    /**
     * method that get inserted geo area
     * @return inserted geo area.
     */
    public GeographicalArea getInsertedGeoArea() {
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
    public double getMaximumTemperatureOfRoomInSpecificDay(String name, SensorType type, LocalDate date) {
        return this.mRoomList.getMaximumTemperatureInRoomInGivenDay(name, type, date);
    }


    /**
     * Method that get the latest measurement by sensor type.
     * @param name
     * @param type
     * @return latest measurement.
     */
    public Measurement getLatestMeasurementBySensorType(String name, SensorType type) {
        Room room = mRoomList.getRoomByName(name);
        if (Objects.isNull(room)) {
            return null;
        }
        Measurement measurement = room.getLatestMeasurementBySensorType(type);
        if (Objects.isNull(measurement)) {
            return null;
        }
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
    public int getRoomListSize() {
        return mRoomList.getLength();
    }

    /**
     * method that get the name of the chosen room in a specific position from the list.
     * @param position
     * @return a position.
     */
    public String getRoomNameByPosition(int position) {
        return mRoomList.getRoomNameByPosition(position);
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
    public boolean isNameExistant(String name) {
        return this.mRoomList.isNameExistant(name);
    }

    /**
     * method that get a room of the list of rooms, from a specific position.
     * @param option
     */
    public Room getRoomOfTheRoomList(int option) {
        return mRoomList.getRoomFromPosition(option);
    }

    /**
     * Method that displays the device list content of a Room
     *
     * @param position position of the room in the room list
     * @return list of devices of a roomMethod that displays the content
     */
    public String getDeviceListContentRoom(int position) {
        return mRoomList.getDeviceListContentByPosition(position);
    }


    /**
     * Method that checks if the Device List of the room is empty
     *
     * @param position chosen room
     */
    public boolean isDeviceListEmpty(int position) {
        return mRoomList.isDeviceListEmpty(position);
    }

    /**
     * method that displays the sensor list content of a Room
     * @param position
     */
    public String getSensorListContentOfARoom(int position) {
        return mRoomList.getSensorListContentOfRoom(position);
    }

    /**
     * method that check if the sensor list of the room is empty
     * @param position
     */
    public boolean isSensorListEmpty(int position) {
        return mRoomList.isSensorListEmpty(position);
    }

    /**
     * method that gets a List of all Devices in a house grid, by it position in a HouseGridList
     *
     * @param position position of the grid in the houseGridList
     * @return List <Device>
     */
    public DeviceList getAllDevicesListByGridPosition(int position) {
        return this.mListHouseGrids.getmHouseGridsList().get(position).getAllDevicesList();
    }

    /**
     * method that get the String content Name and Location of all devices in the list, of a given HouseGrid,
     * and grouped by device type.
     *
     * @param positionHG integer number relative to position of the HouseGrid
     * @return String with Devices Names and Location grouped by Type.
     */
    public String getDeviceListContentNameTypeLocationByHG(int positionHG) {
        return this.mListHouseGrids.getHouseGridByPosition(positionHG).
                getAllDevicesList().getContentNameLocationOrderedByType();
    }

    /**
     * Method that checks if the house grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return mListHouseGrids.isHouseGridListEmpty();
    }

    public String getHouseGridListContent() {
        return mListHouseGrids.getHouseGridListToString();
    }

    public HouseGrid getHouseGridByPosition(int position){
        return mListHouseGrids.getHouseGridByPosition(position);
    }

    /**
     * method that gets the size of House Grid List
     * @return integer
     */
    public int getHouseGridListSize() {
        return this.mListHouseGrids.getmHouseGridsList().size();
    }

    public HouseGridList getHouseGridList() {
        return this.mListHouseGrids;
    }

    public boolean checkIfThereAreNoDevicesHGbyPosition(int position) {
        return this.getHouseGridList().getHouseGridByPosition(position).isDeviceListOfAllRoomsEmpty();
    }

    public int houseRoomListSize() {
        return this.mRoomList.getLength();
    }

    /**
     * method that gets the name of House Grid by it's position in the HousegridList.
     * @param position position of the House Grid
     * @return String name
     */
    public String getHGNameByHGPosition(int position) {
        return this.mListHouseGrids.getNameByHGPosition(position);
    }

    /**
     * Method that gets all the devices of a certain type in the house.
     *
     * @param type Required type.
     * @return DeviceList with all the devices of the required type.
     */
    public DeviceList getAllDevicesOfAType(String type) {
        return mRoomList.getAllDevicesOfAType(type);
    }

    /**
     * Method that returns the number of devices of a certain type in the house.
     *
     * @param type Required type.
     * @return Integer with the number of devices.
     */
    public int getNumberOfDevicesOfAType(String type) {
        return getAllDevicesOfAType(type).getSize();
    }

    /**
     * Method that gets the name of a device of a certain type in the house.
     * @param type Type of the device.
     * @param devicePosition Device position in the list of devices.
     * @return String with the device name.
     */
    public String getDeviceNameOfATypeByPosition(String type, int devicePosition) {
        DeviceList listWithAllDevicesOfAType = getAllDevicesOfAType(type);
        return listWithAllDevicesOfAType.getDeviceName(devicePosition);
    }

    /**
     * Method that sets the value of an attribute of a device of a certain type in the house.
     *
     * @param type Type of the device.
     * @param devicePosition Device position in the list of devices.
     * @param attributePosition Position of the attribute to be set.
     * @param value Value to be used.
     * @return True or false.
     */
    public boolean setAttribute(String type, int devicePosition, int attributePosition, double value) {
        DeviceList listWithAllDevicesOfAType = getAllDevicesOfAType(type);
        return listWithAllDevicesOfAType.setAttribute(devicePosition, attributePosition, value);
    }

    /**
     * Method that returns the energy consumption of a device of a certain type in the house.
     *
     * @param type Type of the device.
     * @param devicePosition Device position in the list of devices.
     * @return Double with the energy consumption.
     */
    public double getEnergyConsumptionOfADevice(String type, int devicePosition) {
        DeviceList listWithAllDevicesOfAType = getAllDevicesOfAType(type);
        return listWithAllDevicesOfAType.getEnergyConsumptionOfADevice(devicePosition);
    }

    /**
     * Method that returns the combined energy consumption of all the devices of a certain type in the house.
     *
     * @param type Type of the devices.
     * @return Double with the combined energy consumption.
     */
    public double getTotalEnergyConsumptionInTheHouse(String type) {
        DeviceList listWithAllDevicesOfAType = getAllDevicesOfAType(type);
        return listWithAllDevicesOfAType.getTotalEnergyConsumption();
    }

    public MeasurableList getNewMeasurableObjList() {
        return new MeasurableList();
    }

    public int getDevicesListLength(int position) {
        return this.mRoomList.getDevicesListLength(position);
    }

    public boolean deleteDevice(String device, int choosenRoom) {
        return this.mRoomList.deleteDevice(device, choosenRoom);
    }

    public String getDeviceNameByPosition(int position) {
        return this.mRoomList.getDeviceNameByPosition(position);
    }

    /**
     * Method that checks if the Room List is Empty
     *
     * @return true if it is empty
     */
    public boolean roomListIsEmpty() {
        return mRoomList.isEmpty();
    }

    /**
     * Method that returns the content of all the devices in the house.
     *
     * @return String with the list of devices content.
     */
    public String getAllDevicesToString() {
        return mRoomList.getAllDevicesToString();
    }

    /**
     * Method that returns all the devices in the house.
     *
     * @return DeviceList with all the devices in the house.
     */
    public DeviceList getAllDevices() {
        return mRoomList.getAllDevicesList();
    }

    /**
     * Method that returns a device by its position in the list of all devices in the house.
     *
     * @param position Position of the device in the list of all devices.
     * @return Device chosen.
     */
    public Device getDeviceByPosition(int position) {
        return getAllDevices().getDeviceByPosition(position);
    }
}
