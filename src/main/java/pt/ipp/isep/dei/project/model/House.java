package pt.ipp.isep.dei.project.model;


import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class House {
    private RoomList roomList;
    private List<HouseGrid> listHouseGrids;
    private Address address;
    private GeographicalArea insertedGeoArea;
    private List<DeviceType> deviceTypeList;
    private int meteringPeriodGrid;
    private int meteringPeriodDevice;
    private static final String CONFIG_PROPERTIES = "Configuration.properties";


    public House(List<String> deviceTypeList, int meteringPeriodGrid, int meteringPeriodDevice) {
        this.roomList = new RoomList();
        this.listHouseGrids = new ArrayList<>();
        this.deviceTypeList = new ArrayList<>();
        createDeviceTypes(deviceTypeList);
        this.meteringPeriodGrid = meteringPeriodGrid;
        this.meteringPeriodDevice = meteringPeriodDevice;
    }


    /**
     * This method create device types using a path and a class name.
     *
     * @param deviceTypeList
     */
    public void createDeviceTypes(List<String> deviceTypeList) {
        for (String className : deviceTypeList) {
            String path = Utils.readConfigFile(CONFIG_PROPERTIES, className);

            try {
                DeviceType dt = (DeviceType) Class.forName(path).newInstance();
                this.deviceTypeList.add(dt);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * creates a Device and returns true if type name exists and deviceName not exists in the
     * rooms of the house
     *
     * @param typeName   String type name of Device
     * @param deviceName String device name
     * @return true if creates and false if not
     */
    public Device createDevice(String typeName, String deviceName, Room room) {
        if (Objects.isNull(getDeviceType(typeName))) {
            return null;
        }
        for (int i = 0; i < roomList.getListOfRooms().size(); i++) {
            if (roomList.getListOfRooms().get(i).isDeviceNameExistant(deviceName)) {
                return null;
            }
        }
        Device device = getDeviceType(typeName).createDevice(deviceName);
        device.setLocation(room);
        return device;
    }

    /**
     * get method
     *
     * @param type type of device
     * @return device type
     */
    public DeviceType getDeviceType(String type) {
        for (DeviceType deviceType : this.deviceTypeList) {
            if (deviceType.getTypeName().equals(type)) {
                return deviceType;
            }
        }
        return null;
    }

    /**
     * get method
     *
     * @return metering period of the grid
     */
    public int getMeteringPeriodGrid() {
        return meteringPeriodGrid;
    }

    /**
     * get method
     *
     * @return metring period of the device
     */
    public int getMeteringPeriodDevice() {
        return meteringPeriodDevice;
    }

    /**
     * Method that adds a house grid to the list.
     *
     * @param houseGrid House grid used.
     */
    public boolean addGrid(HouseGrid houseGrid) {
        if (!(this.listHouseGrids.contains(houseGrid))) {
            listHouseGrids.add(houseGrid);
            return true;
        }
        return false;
    }

    public RoomList getRoomList() {
        return roomList;
    }

    /**
     * Get Method of Address
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set Method of address
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
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
     *
     * @return inserted geo area.
     */
    public GeographicalArea getInsertedGeoArea() {
        return insertedGeoArea;
    }

    /**
     * Set method for the inserted geo area.
     *
     * @param geoArea House area.
     */
    public void setInsertedGeoArea(GeographicalArea geoArea) {
        insertedGeoArea = geoArea;
    }

    /**
     * method that adds a room to the house's roomlist
     *
     * @param room given room to be added
     * @return true if adds, false if doesn't
     */
    public boolean addRoom(Room room) {
        return this.roomList.addRoom(room);
    }

    /**
     * Get the location of the house.
     *
     * @return the location of the house.
     */
    public Location getLocation() {
        return this.address.getLocation();
    }

    /**
     * method that get the last measurement of house area.
     *
     * @param type
     * @return the last measurement with a location and a type of sensor.
     */
    public double getLastMeasurementByTypeInHouseArea(SensorType type) {
        return insertedGeoArea.getLastMeasurementByLocationType(address.getLocation(), type);
    }

    /**
     * Method that get the average daily measurement of the house area.
     *
     * @param measurementType
     * @param startDate
     * @param endDate
     * @return the average daily measurement.
     */
    public double getAverageDailyMeasurementInHouseArea(SensorType measurementType, LocalDate startDate, LocalDate endDate) {
        List<Double> listOfDailyAverages = insertedGeoArea.getDailyAverageMeasurement(measurementType, address.getLocation(), startDate, endDate);
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
     *
     * @param measurementType
     * @param day
     * @return total daily measurement.
     */
    public double getTotalDailyMeasurementInHouseArea(SensorType measurementType, LocalDate day) {
        return insertedGeoArea.getTotalDailyMeasurement(measurementType, day, this.address.getLocation());
    }

    /**
     * @param name name of room
     * @param type type of sensor (temperature)
     * @param date given day
     * @return returns the maximum temperature in a specific day
     */
    public double getMaximumTemperatureOfRoomInSpecificDay(String name, SensorType type, LocalDate date) {
        return this.roomList.getMaximumTemperatureInRoomInGivenDay(name, type, date);
    }


    /**
     * Method that get the latest measurement by sensor type.
     *
     * @param name
     * @param type
     * @return latest measurement.
     */
    public Reading getLatestMeasurementBySensorType(String name, SensorType type) {
        Room room = roomList.getRoomByName(name);
        if (Objects.isNull(room)) {
            return null;
        }
        Reading reading = room.getLatestMeasurementBySensorType(type);
        if (Objects.isNull(reading)) {
            return null;
        }
        return reading;
    }

    /**
     * method that display a room list.
     */
    public String getRoomListContent() {
        return roomList.getRoomListContent();
    }

    /**
     * Method that get the size of the room list.
     *
     * @return size of the list of rooms.
     */
    public int getRoomListSize() {
        return roomList.getLength();
    }

    /**
     * method that get the name of the chosen room in a specific position from the list.
     *
     * @param position
     * @return a position.
     */
    public String getRoomNameByPosition(int position) {
        return roomList.getRoomNameByPosition(position);
    }

    /**
     * method that create a new room to the list, with height, length, width, name and housefloor.
     *
     * @param height
     * @param length
     * @param width
     * @param name
     * @param housefloor
     * @return a new room in the list.
     */
    public Room newRoom(double height, double length, double width, String name, int housefloor) {
        return roomList.newRoom(name, housefloor, height, length, width);
    }

    /**
     * method that check if a name of a room already exists on the list of rooms.
     *
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.roomList.isNameExistant(name);
    }

    /**
     * method that get a room of the list of rooms, from a specific position.
     *
     * @param option
     */
    public Room getRoomOfTheRoomList(int option) {
        return roomList.getRoomFromPosition(option);
    }

    /**
     * Method that displays the device list content of a Room
     *
     * @param position position of the room in the room list
     * @return list of devices of a roomMethod that displays the content
     */
    public String getDeviceListContentRoom(int position) {
        return roomList.getDeviceListContentByPosition(position);
    }


    /**
     * Method that checks if the Device List of the room is empty
     *
     * @param position chosen room
     */
    public boolean isDeviceListEmpty(int position) {
        return roomList.isDeviceListEmpty(position);
    }

    /**
     * method that displays the sensor list content of a Room
     *
     * @param position
     */
    public String getSensorListContentOfARoom(int position) {
        return roomList.getSensorListContentOfRoom(position);
    }

    /**
     * method that check if the sensor list of the room is empty
     *
     * @param position
     */
    public boolean isSensorListEmpty(int position) {
        return roomList.isSensorListEmpty(position);
    }

    /**
     * method that gets a List of all devices in a house grid, by it position in a HouseGridList
     *
     * @param position position of the grid in the houseGridList
     * @return List <Device>
     */
    public List<Device> getAllDevicesListByGridPosition(int position) {
        return this.listHouseGrids.get(position).getAllDevicesList();
    }

    /**
     * method that get the String content Name and Location of all devices in the list,
     * grouped by device type.
     *
     * @return String with Device Name and Location grouped by Type.
     */
    public String getContentNameLocationOrderedByType(List<Device> deviceList) {
        StringBuilder content = new StringBuilder();
        Map<String, List<Device>> byDeviceType = deviceList.stream()
                .collect(Collectors.groupingBy(Device::getType));


        for (Map.Entry<String, List<Device>> entry : byDeviceType.entrySet()) {
            content.append(entry.getKey());
            content.append("\n");
            for (Device dev : entry.getValue()) {

                content.append("- Device Name: ");
                content.append(dev.getName());
                content.append(", Location: ");
                content.append(dev.getLocation().getName());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * method that get the String content Name and Location of all devices in the list, of a given housegrid,
     * and grouped by device type.
     *
     * @param positionHG integer number relative to position of the housegrid
     * @return String with devices Names and Location grouped by Type.
     */
    public String getDeviceListContentNameTypeLocationByGrid(int positionHG) {
        List<Device> deviceList = getAllDevicesListByGridPosition(positionHG);
        return getContentNameLocationOrderedByType(deviceList);
    }

    /**
     * Method that checks if the house grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return listHouseGrids.isEmpty();
    }

    /**
     * Method that creates a house grid.
     *
     * @param grid Name of the grid.
     * @return New object of the class housegrid.
     */
    public boolean createHouseGrid(HouseGrid grid) {
        if (!this.gridNameAlreadyExists(grid.getName())) {
            return this.addGrid(grid);
        }
        throw new RuntimeException("Name already exists. Please, write a new one.");
    }

    public boolean gridNameAlreadyExists(String name) {
        for (HouseGrid houseGrid : listHouseGrids) {
            if (houseGrid.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that shows the content of the house grids in the list.
     *
     * @return String with the required information.
     */
    public String getHouseGridListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= listHouseGrids.size(); i++) {
            content.append(i + " - Name: " + listHouseGrids.get(i - 1).getName());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Method that gets a House Grid of the list of HouseGrids, from a specific position.
     *
     * @param position
     */
    public HouseGrid getHouseGridByPosition(int position) {
        return listHouseGrids.get(position);
    }

    /**
     * method that gets the size of House Grid List
     *
     * @return integer
     */
    public int getHouseGridListSize() {
        return this.listHouseGrids.size();
    }

    public List<HouseGrid> getHouseGridList() {
        return this.listHouseGrids;
    }

    public boolean checkIfThereAreNoDevicesInGridbyPosition(int position) {
        return this.getHouseGridList().get(position).isDeviceListOfAllRoomsEmpty();
    }

    public int houseRoomListSize() {
        return this.roomList.getLength();
    }

    /**
     * method that gets the name of House Grid by it's position in the HousegridList.
     *
     * @param position position of the House Grid
     * @return String name
     */
    public String getGridNameByPosition(int position) {
        if (listHouseGrids.isEmpty()) {
            return "There are no Grids in the house";
        }
        return listHouseGrids.get(position).getName();
    }

    /**
     * Method that checks if a room isn't already in a specific grid in the list.
     *
     * @param chosenGrid Specified house grid in the list.
     * @param room       Specified room.
     * @return True or false.
     */
    public boolean checkIfRoomIsAlreadyInHouseGrid(HouseGrid chosenGrid, Room room) {
        int index = listHouseGrids.indexOf(chosenGrid);
        return listHouseGrids.get(index).checkIfRoomIsInHouseGrid(room);
    }

    /**
     * Method that asks for the grid where the room might already be connected.
     *
     * @param room Specified room.
     * @return Grid where the room is is connected to.
     */
    public HouseGrid getTheGridWhereTheRoomIsConnected(Room room) {
        for (HouseGrid houseGrid : listHouseGrids) {
            if (houseGrid.checkIfRoomIsInHouseGrid(room)) {
                return houseGrid;
            }
        }
        return null;
    }

    /**
     * Method that displays the rooms in the house grid list
     *
     * @param position
     * @return rooms in the house grid
     */
    public String getRoomsInTheHouseGrid(int position) {
        return listHouseGrids.get(position).getRoomListContent();
    }


    /**
     * Method that calls the method in housegrid that detaches a selected room from the list of HouseGrids.
     *
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public boolean detachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = listHouseGrids.indexOf(houseGridSelected);
        return listHouseGrids.get(index).detachRoom(roomSelected);
    }

    /**
     * Method that asks the class housegrid to add a room to it's list.
     *
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public void attachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = listHouseGrids.indexOf(houseGridSelected);
        listHouseGrids.get(index).addRoom(roomSelected);
    }


    /**
     * Method that gets all the devices of a certain type in the house.
     *
     * @param type Required type.
     * @return DeviceList with all the devices of the required type.
     */
    public List<Device> getAllDevicesOfAType(String type) {
        return roomList.getAllDevicesOfAType(type);
    }

    /**
     * Method that returns the number of devices of a certain type in the house.
     *
     * @param type Required type.
     * @return Integer with the number of devices.
     */
    public int getNumberOfDevicesOfAType(String type) {
        return getAllDevicesOfAType(type).size();
    }

    /**
     * Method that returns a device in the house by its name.
     *
     * @param deviceName Device name.
     * @return Device with the specified name.
     */
    public Device getDeviceByName(String deviceName) {
        List<Device> houseDevices = getAllDevices();
        Device chosenDevice = null;
        for (Device device : houseDevices) {
            if (device.getName().equals(deviceName)) {
                chosenDevice = device;
            }
        }
        if (Objects.isNull(chosenDevice)) {
            throw new RuntimeException("There isn't any device with that name.");
        }
        return chosenDevice;
    }

    /**
     * Method that sets the value of an attribute of a device in the house.
     *
     * @param deviceName    Device name.
     * @param attributeName Name of the attribute to be set.
     * @param value         Value to be used.
     * @return True or false.
     */
    public boolean setDeviceAttribute(String deviceName, String attributeName, Object value) {
        Device chosenDevice = getDeviceByName(deviceName);
        return chosenDevice.setAttributesDevType(attributeName, value);
    }

    /**
     * Method that returns the daily energy consumption of a device in the house.
     *
     * @param deviceName Device name.
     * @return Double with the energy consumption.
     */
    public double getDailyEnergyConsumptionOfADevice(String deviceName) {
        Device chosenDevice = getDeviceByName(deviceName);
        return chosenDevice.getEnergyConsumptionInADay();
    }

    /**
     * Method that returns the combined energy consumption of all the devices of a certain type in the house.
     *
     * @param type Type of the devices.
     * @return Double with the combined energy consumption.
     */
    public double getTotalEnergyConsumptionOfDevicesOfCertainType(String type) {
        List<Device> listWithAllDevicesOfAType = getAllDevicesOfAType(type);
        double totalEnergyConsumption = 0;
        for (Device device : listWithAllDevicesOfAType) {
            totalEnergyConsumption += device.getEnergyConsumptionInADay();
        }
        return totalEnergyConsumption;
    }

    public MeasurableList getNewMeasurableObjList() {
        return new MeasurableList();
    }

    /**
     * Method that checks if the Room List is Empty
     *
     * @return true if it is empty
     */
    public boolean roomListIsEmpty() {
        return roomList.isEmpty();
    }

    /**
     * Method that returns the content of all the devices in the house.
     *
     * @return String with the list of devices content.
     */
    public String getAllDevicesToString() {
        return roomList.getAllDevicesToString();
    }

    /**
     * Method that returns all the devices in the house.
     *
     * @return DeviceList with all the devices in the house.
     */
    public List<Device> getAllDevices() {
        return roomList.getAllDevicesList();
    }

    /**
     * Method that returns all the devices in the house.
     *
     * @return DeviceList with all the devices in the house.
     */
    public int getNumberOfDevices() {
        return getAllDevices().size();
    }

    /**
     * Method that returns a device by its position in the list of all devices in the house.
     *
     * @param position Position of the device in the list of all devices.
     * @return Device chosen.
     */
    public Device getDeviceByPosition(int position) {
        return getAllDevices().get(position);
    }

    public String getDeviceNameOfATypeByPosition(String type, int devicePosition) {
        List<Device> listOfAllDevicesOffAType = getAllDevicesOfAType(type);
        return getDeviceNameFromDeviceList(listOfAllDevicesOffAType, devicePosition);
    }

    /**
     * method that get the name of the device by position.
     *
     * @param devicePosition
     * @return null if the list is empty.
     */
    public String getDeviceNameFromDeviceList(List<Device> devicelist, int devicePosition) {
        if (devicelist.isEmpty()) {
            return "There are no devices in the device list.";
        }
        return devicelist.get(devicePosition).getName();
    }

    /**
     * method that get de device type list content
     *
     * @return the content of the list by string
     */
    public String getDeviceTypeListToString() {
        StringBuilder content = new StringBuilder();
        int numberOfDeviceTypes = numberOfDeviceTypes();
        for (int i = 1; i <= numberOfDeviceTypes && i >= 1; i++) {
            String deviceType = Utils.readConfigFile(CONFIG_PROPERTIES, "devicetype.name." + i);
            content.append(i + "- ");
            content.append(deviceType);
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that get the number os existing devices on the configuration file.
     *
     * @return the number os existing devices
     */
    public int numberOfDeviceTypes() {
        return Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "devicetype.count"));
    }

    public int getDeviceSize() {
        return getAllDevices().size();
    }

    /**
     * Method that checks if the device list of all rooms is empty
     *
     * @return boolean true
     */
    public boolean isDeviceListOfAllRoomsEmpty() {
        return this.roomList.isDeviceListOfAllRoomsEmpty();
    }

    public LocalDateTime getDateOfLastMeasurementByType(SensorType type) {
        return insertedGeoArea.getDateLastMeasurementByLocationType(address.getLocation(), type);
    }

    public double getHighestReadingOfASensor(LocalDate startDate, LocalDate endDate) {
        return insertedGeoArea.getHighestReadingOfASensor(startDate, endDate).getValue();
    }

    public LocalDate getFirstHighestReadingDateHouseArea(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        return insertedGeoArea.getFirstHighestReading(location, type, startDate, endDate).getDateTime().toLocalDate();
    }

    public Double getFirstHighestReadingValueHouseArea(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        return insertedGeoArea.getFirstHighestReading(location, type, startDate, endDate).getValue();
    }

    public boolean checkMeasurementExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return insertedGeoArea.checkMeasurementExistenceBetweenDates(startDate, endDate);
    }

    /**
     * get Daily Amplitude Map <localdate, Double> in a given interval of Localdate by given sensortype and location
     *
     * @param sensorType type of sensor
     * @param location   location of the area wanted to get the daily amplitude
     * @param startDate  initial Localdate of the interval
     * @param endDate    final Localdate of the interval
     * @return Map<LocalDate   ,       Double> map Of Daily Amplitude
     */
    public Map<LocalDate, Double> getDailyAmplitudeInIntervalInHouseArea(SensorType sensorType, Location location, LocalDate startDate, LocalDate endDate) {
        return this.insertedGeoArea.getDailyAmplitudeInInterval(sensorType, location, startDate, endDate);
    }

    /**
     * receives a map Of Daily Amplitude and gets the Highest Daily Amplitude (localdate-Double)
     * if there are two equal amplitudes, it gets both.
     *
     * @param mapOfDailyAmplitude given daily Amplitude Map<LocalDate, Double>
     * @return Map<LocalDate   ,       Double> map Of Highest Daily Amplitude
     */
    public Map<LocalDate, Double> getHighestDailyAmplitudeInHouseArea(Map<LocalDate, Double> mapOfDailyAmplitude) {
        return this.insertedGeoArea.getHighestDailyAmplitude(mapOfDailyAmplitude);
    }

}