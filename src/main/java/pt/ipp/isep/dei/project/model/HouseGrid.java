package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.Devices.Device;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.isNull;

public class HouseGrid implements Measurable {
    private String houseGridName;
    private PowerSourceList powerSourceList;
    private RoomList roomList;

    /**
     * constructor of a house grid that receives a name, a room list, a list of power sources and a maximum contracted power.
     *
     * @param houseGridName
     */

    public HouseGrid(String houseGridName) {
        validateName(houseGridName);
        this.houseGridName = houseGridName;
        this.roomList = new RoomList();
        this.powerSourceList = new PowerSourceList();
    }


    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new RuntimeException("Please enter a valid name. Name should not be empty");
        }
    }

    /**
     * method that creates the same hashcode to grids with the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.houseGridName);
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
        if (!(obj instanceof HouseGrid)) {
            return false;
        }
        HouseGrid houseGrid = (HouseGrid) obj;
        return this.houseGridName.equalsIgnoreCase(houseGrid.houseGridName);
    }

    /**
     * method that gets the name of the house grid.
     */
    public String getName() {
        return this.houseGridName;
    }

    /**
     * method that get the list of power sources.
     */
    public PowerSourceList getPowerSourceList() {
        return powerSourceList;
    }

    /**
     * method that get the rooms connected to the house grid.
     */
    public RoomList getRoomList() {
        return roomList;
    }

    /**
     * Method that returns a selected room through the use of an Integer.
     * This method relies of the position of the room in the list.
     *
     * @param position
     * @return
     */
    public Room getRoomFromTheListOfRoomByAPosition(int position) {
        return getRoomList().getRoomFromPosition(position);
    }

    /**
     * Method that returns a list of readings from all of the rooms in the HouseGrid.
     *
     * @return
     */
    @Override
    public List<Reading> getReadings() {
        List<Reading> listOfReadings = new ArrayList<>();
        for (Room room : this.roomList.getListOfRooms()) {
            listOfReadings.addAll(room.getReadings());
        }
        return listOfReadings;
    }

    /**
     * Method that detaches a room from a house grid. It return a true in case of success
     * and false in the case of a failure.
     *
     * @param roomToDetach
     * @return
     */
    public boolean detachRoom(Room roomToDetach) {
        for (Room room : this.roomList.getListOfRooms()) {
            if (room.equals(roomToDetach)) {
                this.roomList.getListOfRooms().remove(room);
                return true;
            }
        }
        return false;
    }

    /**
     * Method that attaches a room in the house grid's room list.
     *
     * @param room Speficied room to attach.
     */
    public void attachRoom(Room room) {
        roomList.getListOfRooms().add(room);
    }

    /**
     * Method that add a new power source to the list of power sources.
     *
     * @param newPowerSource
     * @return
     */
    public boolean addPowerSource(PowerSource newPowerSource) {
        return this.powerSourceList.addPowerSource(newPowerSource);
    }

    /**
     * Method that attaches a list of existing rooms to a house grid.
     *
     * @return a list of existing rooms attached to a house grid.
     */
    public String getRoomListContent() {
        return this.roomList.getRoomListContent();
    }


    /**
     * Method that checks if a room isn't already in the grid.
     *
     * @param room Room to be used.
     * @return True or false.
     */
    public boolean checkIfRoomIsInHouseGrid(Room room) {
        return roomList.getListOfRooms().contains(room);
    }

    public String getPowerSourceListContent() {
        return powerSourceList.getPowerSourcesListToString();
    }

    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Room room : roomList.getListOfRooms()) {
            totalNominalPower += room.getNominalPower();
        }
        return totalNominalPower;
    }

    /**
     * method that gets a List of all Devices in all Rooms of a Housegrid
     *
     * @return List <Device>
     */
    public List<Device> getAllDevicesList() {
        return roomList.getAllDevicesList();
    }

    /**
     * method that checks if there are no devices in the RoomList
     *
     * @return true if there aren't devices. False if there are devices
     */
    public boolean isDeviceListOfAllRoomsEmpty() {
        return this.getRoomList().isDeviceListOfAllRoomsEmpty();
    }


    public int getRoomListSize() {
        return roomList.getLength();
    }

    public Room getRoomByPosition(int position) {
        return roomList.getRoomFromPosition(position);
    }

    public String getDeviceListContent(int position) {
        return roomList.getRoomFromPosition(position).getDeviceListToString();
    }

    public int getDeviceListSizeByRoomPosition(int position) {
        return roomList.getRoomFromPosition(position).getSize();
    }

    public boolean isRoomListEmpty() {
        return roomList.isEmpty();
    }

    public boolean isDeviceListOfRoomEmpty(int position) {
        return roomList.getRoomFromPosition(position).isDeviceListEmpty();
    }

    public Device getDeviceByRoomAndDevicePosition(int roomPosition, int devicePosition) {
        return getRoomByPosition(roomPosition).getDeviceByPosition(devicePosition);
    }

    /**
     * method that returns the name of house grid
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder name = new StringBuilder();
        name.append("HouseGrid: " + this.houseGridName + "\n");
        return name.toString();
    }

    /**
     * Method that calculates the total energy consumption of a house grid in a given interval.
     *
     * @param startDate the initial date/hour of the period to be considered
     * @param endDate   the final date/hour of the period to be considered
     * @return Double
     */
    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {

        double totalEnergyConsumption = 0;

        if (!this.roomList.isEmpty()) {
            for (Room room : this.roomList.getListOfRooms()) {
                totalEnergyConsumption += room.getEnergyConsumptionInAnInterval(startDate, endDate);
            }

        }
        return totalEnergyConsumption;

    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        TreeMap<LocalDateTime, Double> map = new TreeMap<>();

        for (Room room : this.roomList.getListOfRooms()) {
            Map<LocalDateTime, Double> map2 = room.getDataSeries(startDate, endDate);

            for (Map.Entry<LocalDateTime, Double> entry : map2.entrySet()) {

                LocalDateTime key = entry.getKey();
                Double oldValue = map.get(key);
                map.put(key, oldValue == null ? entry.getValue() : entry.getValue() + oldValue);
            }
        }
        return map;
    }
}