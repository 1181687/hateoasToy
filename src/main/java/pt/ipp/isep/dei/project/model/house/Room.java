package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.Measurable;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

@Entity
public class Room implements Measurable {

    //@Id
    @Column(name = "RoomID", insertable = false, updatable = false)
    @EmbeddedId
    private RoomId roomId;
    private String description;
    private int houseFloor;

    @Embedded
    private HouseGridId houseGridId;

    @Embedded
    private Dimension dimension;

    //  @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JoinColumn
    // private RoomSensorService sensorList;

    @Transient
    private List<Device> deviceList;

    /**
     * constructor that receives name, houseFloor, dimension
     * throw an exception if any of the parameters is invalid.
     * Invalid parameters if Dimension is null or name is null or empty
     * <p>
     * //@param roomId
     * //@param houseFloor
     * //@param dimension
     */
    public Room(RoomId roomId, String description, int houseFloor, Dimension dimension) {
        validateName(roomId.getId());
        validateDimensions(dimension);
        this.roomId = new RoomId(roomId.getId().trim());
        this.description = description;
        this.houseFloor = houseFloor;
        this.dimension = dimension;
        this.deviceList = new ArrayList<>();
    }

    protected Room() {
        // empty
    }

    public void setHouseGridId(HouseGridId houseGridId) {
        this.houseGridId = houseGridId;
    }

    /**
     * method that receives a name and validates it. It can not be null or empty
     * throw an exception if the name is invalid
     *
     * @param name given name
     */
    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new RuntimeException("Please enter a valid name. Name should not be empty");
        }
    }

    /**
     * method that receives an object Dimension and validates it. It can not be null
     * throw an exception if Dimension is null
     *
     * @param dimension given object dimension
     */
    private static void validateDimensions(Dimension dimension) {
        if (isNull(dimension)) {
            throw new RuntimeException("Dimension should not be null");
        }
    }

    /**
     * Get method.
     *
     * @return String with the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get method.
     *
     * @return HouseGridId.
     */
    public HouseGridId getHouseGridId() {
        return houseGridId;
    }

    /**
     * Get method
     *
     * @return name
     */
    public RoomId getRoomId() {
        return this.roomId;
    }


    /**
     * Get Method
     *
     * @return houseFloor
     */
    public int getHouseFloor() {
        return houseFloor;
    }

    /*@Override
    public List<GeoAreaReading> getReadings() {
        List<GeoAreaReading> listOfGeoAreaReadings = new ArrayList<>();
        for (Device device : this.deviceList) {
            listOfGeoAreaReadings.addAll(device.getReadings());
        }
        return listOfGeoAreaReadings;
    }*/

    /**
     * Method that defines the House Floor number of the room
     *
     * @param houseFloor housegrid floor of the room (int number)
     */
    public void setHouseFloor(int houseFloor) {
        this.houseFloor = houseFloor;
    }

    /**
     * Get Method
     *
     * @return dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * method that displays a Room with its characteristics (name, housegrid floor, height, length and width)
     *
     * @return Rooms
     */
    public String getRoomToString() {
        StringBuilder content = new StringBuilder();
        content.append("Name: " + getRoomId());
        content.append(", House Floor: " + getHouseFloor());
        content.append(", Dimension - Height: " + getDimension().getHeight());
        content.append(", Length: " + getDimension().getLength());
        content.append(", Width: " + getDimension().getWidth());
        return content.toString();
    }



    /**
     * method that creates the same hashcode to rooms with the same attribute name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.roomId);
    }

    /**
     * Equals method to determine if two Rooms are equal.
     * They are equals if name are equal.
     * Names are case insensitive.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Room)) {
            return false;
        }
        Room roomOne = (Room) obj;
        return this.roomId.getId().equalsIgnoreCase(roomOne.roomId.getId());
    }

    public boolean detachRoomFromHouseGrid() {
        if (Objects.isNull(this.houseGridId)) {
            return false;
        }
        this.houseGridId = null;
        return true;
    }


    /**
     * This method add a new sensor to the list of sensors in the room
     *
     * @param newSensor add to the list of sensors
     * @return a new sensor to the list of sensors
     */

   /* public boolean addSensorToListOfSensorsInRoom(RoomSensor newSensor) {
        return this.sensorList.addGeoAreaSensor(newSensor);
    }*/


/**
 * This method gets the sensor list.
 *
 * @return the list of sensors.
 */
/*
public RoomSensorService getSensorList() {
        return sensorList;
    }
*/

/**
 * @param type of sensor (temperature)
 * @param date any given day
 * @return maximum temperature
 */

  /*  public double getMaximumMeasurementInGivenDay(SensorType type, LocalDate date) {
        return sensorList.getMaximumMeasureOfTypeOfSensorInGivenDay(type, date);
    }*/


/**
 * Method that gets the latest measurement by type of sensor
 *
 * @param type type of sensor
 * @return latest measurement by sensor type
 */

/*public RoomReading getLatestMeasurementBySensorType(SensorType type) {
    return new RoomReading(sensorList.getLatestMeasurementBySensorType(type).getValue(), sensorList.getLatestMeasurementBySensorType(type).getDateTime());
    }
*/

    @Override
    public String getNameToString() {
        return null;
    }

    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return 0;
    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public List<DeviceReading> getReadings() {
        return null;
    }

    /**
     * Method that return the nominal power of the list of devices in the room.
     * This method is the implementation of the measurable interface method.
     *
     * @return
     */
    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        if (!this.deviceList.isEmpty()) {
            for (Device device : this.deviceList) {
                totalNominalPower += device.getNominalPower();
            }
        }
        return totalNominalPower;
    }

    /*
     */
/**
 * method that displays the content of the list of sesnsors
 *
 * @return sensor list content
 *//*

    public String getSensorListContent() {
        return this.sensorList.getSensorListToString();
    }

    */
/**
 * method that check if the sensor list of the room is empty
 *//*

    public boolean isSensorListEmpty() {
        return this.sensorList.isEmpty();
    }
*/


    /**
     * method that displays the device list content
     *
     * @return content of the device list
     */
   /* public String getDeviceListToString() {
        StringBuilder content = new StringBuilder();
        int deviceListLength = this.getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            content.append(numberInTheList + " - Name of the device: " + getDeviceByPosition(i - 1).getName());
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }*/

    /**
     * method that checks if Device List of the room is empty
     */
    /*public boolean isDeviceListEmpty() {
        return this.deviceList.isEmpty();
    }*/

    /**
     * Method that adds a device to the list of devices if device not null
     * and the roomlist has not device with the same name
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add
     */
    public boolean addDevice(Device device) {
        if (Objects.isNull(device)) {
            throw new RuntimeException("Device is null.");
        }
        if (this.equals(device.getLocation()) && this.deviceList.contains(device)) {
            throw new RuntimeException("Device with same name is already in the roomList");
        }
        this.deviceList.add(device);
        return true;
    }

    /**
     * method that returns the name of room
     *
     * @return String
     */
    /*
    @Override
    public String getNameToString() {
        StringBuilder name = new StringBuilder();
        name.append("Room: " + this.roomId + "\n");
        return name.toString();
    }
    */

    /**
     * method that get the enery consumption of the room in an interval
     *
     * @param startDate
     * @param endDate
     * @return the total energy consumption
     */
   /* @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        double totalEnergyConsumption = 0;
        if (!this.deviceList.isEmpty()) {
            for (Device device : this.deviceList) {
                totalEnergyConsumption += device.getEnergyConsumptionInAnInterval(startDate, endDate);
            }
        }
        return totalEnergyConsumption;
    }


    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = new TreeMap<>();
        for (Device device : this.deviceList) {
            Map<LocalDateTime, Double> map2 = device.getDataSeries(startDate, endDate);
            for (Map.Entry<LocalDateTime, Double> entry : map2.entrySet()) {
                LocalDateTime key = entry.getKey();
                Double oldValue = map.get(key);
                map.put(key, oldValue == null ? entry.getValue() : entry.getValue() + oldValue);
            }
        }
        return map;
    }*/

    /**
     * get List of devices
     *
     * @return List<Device>
     */
    public List<Device> getDeviceList() {
        return this.deviceList;
    }

    /**
     * get size of list of devices
     *
     * @return integer
     */
   /* public int getSize() {
        return this.deviceList.size();
    }*/

    /**
     * method that get a Device by it's position
     * @param position integer position of Device
     * @return Device
     */
    /*public Device getDeviceByPosition(int position) {
        return this.deviceList.get(position);
    }*/

    /**
     * method that check if a name of a Device already exists on the list of devices.
     *
     * @param name name of device
     * @return boolean true if exists, false if it doesn't
     */
    public boolean isDeviceNameExistant(String name) {
        for (int i = 0; i < this.deviceList.size(); i++) {
            if (isNull(this.deviceList.get(i).getName())) {
                break;
            }
            if (this.deviceList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that check if the device list is empty
     */
  /*  public boolean isEmpty() {
        return this.deviceList.isEmpty();
    }*/

    /**
     * method that gets the state of every device in the list of devices.
     *
     * @return String of the name and the status of the device ("Activated" or "Deactivated").
     */
   /* public String getActiveDeactiveDeviceListToString() {
        String deviceName = " - Device name: ";
        StringBuilder content = new StringBuilder();
        int deviceListLength = getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            if (deviceList.get(i - 1).getIsActive()) {
                content.append(numberInTheList + deviceName + getDeviceList().get(i - 1).getName() + " - ACTIVATED");
                content.append("\n");
                numberInTheList++;
            } else {
                String dateHour = deviceList.get(0).getDateDeactivateDeviceToString();
                content.append(numberInTheList + deviceName + getDeviceList().get(i - 1).getName() + " - DEACTIVATED at " + dateHour);
                content.append("\n");
                numberInTheList++;
            }
        }
        return content.toString();
    }*/

    /**
     * Method that remove a device from the list of devices
     */
   /* public boolean removeDevice(Device device) {
        return this.deviceList.remove(device);
    }*/

    /**
     * Method that gets all the devices of a certain type.
     *
     * @param type Required type.
     * @return DeviceList with all the devices of the required type.
     */
  /*  public List<Device> getAllDevicesOfAType(String type) {
        List<Device> listOfDevicesWithTheType = new ArrayList<>();
        for (Device device : this.deviceList) {
            if (device.getType().equals(type)) {
                listOfDevicesWithTheType.add(device);
            }
        }
        return listOfDevicesWithTheType;
    }*/

    /**
     * method that delete a device from the list of devices.
     *
     * @param device
     * @return true if the device was removed. False if not.
     */
   /* public boolean deleteDevice(String device) {
        for (Device searchDevice : this.deviceList) {
            if (device.equals(searchDevice.getName())) {
                this.deviceList.remove(searchDevice);
                return true;
            }
        }
        return false;
    }
*/
    /**
     * method that get the name of the device by position.
     *
     * @param position
     * @return null if the list is empty.
     */
    /*public String getDeviceNameByPosition(int position) {
        if (this.deviceList.isEmpty()) {
            return "There are no devices in the device list.";
        }
        return this.deviceList.get(position).getName();
    }*/

    /**
     * method that deactivate the device.
     *
     * @param
     * @return true if the device was deactivated. False, if not.
     */
   /* public boolean deactivateDevice(String device) {

        for (Device searchDevice : this.deviceList) {
            if (device.equals(searchDevice.getName())) {
                return searchDevice.setDeactivateDevice();
            }
        }
        return false;
    }*/

    public void setDescription(String description) {
        this.description = description;
    }

    /*public RoomSensor getSensorById(String sensorId) {
        if (!Objects.isNull(sensorList.getSensorById(sensorId))) {
            return sensorList.getSensorById(sensorId);
        }
        return null;
    }*/
}