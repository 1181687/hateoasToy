package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.isNull;

public class HouseGrid implements Measurable {
    private String mName;
    private PowerSourceList mPowerSourceList;
    private RoomList mRoomList;

    /**
     * constructor of a house grid that receives a name, a room list, a list of power sources and a maximum contracted power.
     *
     * @param houseGridName
     */

    public HouseGrid(String houseGridName) {
        validateName(houseGridName);
        this.mName = houseGridName;
        this.mRoomList = new RoomList();
        this.mPowerSourceList = new PowerSourceList();
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
        return Objects.hash(this.mName);
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
        return this.mName.equalsIgnoreCase(houseGrid.mName);
    }

    /**
     * method that get the name of the house grid.
     */
    public String getName() {
        return mName;
    }

    /**
     * method that get the list of power sources.
     */
    public PowerSourceList getPowerSourceList() {
        return mPowerSourceList;
    }

    /**
     * method that get the rooms connected to the house grid.
     */
    public RoomList getRoomList() {
        return mRoomList;
    }

    /**
     * Method that returns a selected room through the use of an Integer.
     * This method relies of the position of the room in the list.
     *
     * @param position
     * @return
     */
    public Room getRoomFromTheListOfRoomByAPosition (int position){
        return getRoomList().getRoomFromPosition(position);
    }

    /**
     * Method that returns a list of readings from all of the rooms in the HouseGrid.
     *
     * @return
     */
    @Override
    public List<Readings> getReadings() {
        List<Readings> listOfReadings = new ArrayList<>();
            for (Room room : this.mRoomList.getRoomList()) {
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
        for (Room room : this.mRoomList.getRoomList()) {
            if (room.equals(roomToDetach)) {
                this.mRoomList.getRoomList().remove(room);
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
        mRoomList.getRoomList().add(room);
    }

    /**
     * Method that add a new power source to the list of power sources.
     *
     * @param newPowerSource
     * @return
     */
    public boolean addPowerSource(PowerSource newPowerSource) {
        return this.mPowerSourceList.addPowerSource(newPowerSource);
    }

    /**
     * Method that attaches a list of existing rooms to a house grid.
     *
     * @return a list of existing rooms attached to a house grid.
     */
    public String getRoomListContent() {
        return this.mRoomList.getRoomListContent();
    }


    /**
     * Method that checks if a room isn't already in the grid.
     *
     * @param room Room to be used.
     * @return True or false.
     */
    public boolean checkIfRoomIsInHouseGrid(Room room) {
        return mRoomList.getRoomList().contains(room);
    }

    public String getPowerSourceListContent() {
        return mPowerSourceList.getPowerSourcesListToString();
    }

    @Override
    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Room room : mRoomList.getRoomList()) {
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
        return mRoomList.getAllDevicesList();
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
        return mRoomList.getLength();
    }

    public Room getRoomByPosition(int position) {
        return mRoomList.getRoomFromPosition(position);
    }

    public String getDeviceListContent(int position) {
        return mRoomList.getRoomFromPosition(position).getDeviceListToString();
    }

    public int getDeviceListSizeByRoomPosition(int position) {
        return mRoomList.getRoomFromPosition(position).getSize();
    }

    public boolean isRoomListEmpty() {
        return mRoomList.isEmpty();
    }

    public boolean isDeviceListOfRoomEmpty(int position) {
        return mRoomList.getRoomFromPosition(position).isDeviceListEmpty();
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
        name.append("HouseGrid: " + mName + "\n");
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
        if (this.mRoomList.isEmpty()) {
            throw new RuntimeException("There are no rooms connected to this house grid.");
        } else {
            for (Room room : this.mRoomList.getRoomList()) {
                totalEnergyConsumption += room.getEnergyConsumptionInAnInterval(startDate, endDate);
            }
            return totalEnergyConsumption;
        }
    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        TreeMap<LocalDateTime, Double> map = new TreeMap<>();

        for (Room room : this.mRoomList.getRoomList()) {
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