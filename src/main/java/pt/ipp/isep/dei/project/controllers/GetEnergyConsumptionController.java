package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

public class GetEnergyConsumptionController {
    private House house;
    private Device device;
    private Room selectedRoom;
    private HouseGrid houseGrid;
    private Measurable measurable;


    /**
     * Constructor.
     *
     * @param house House to be used.
     */
    public GetEnergyConsumptionController(House house) {
        this.house = house;
    }

    /**
     * Method that returns the content of all the devices in the house.
     *
     * @return String with the list of devices content.
     */
    public String getAllDevicesToString() {
        return house.getAllDevicesToString();
    }

    /**
     * Method that returns all the devices in the house.
     *
     * @return DeviceList with all the devices in the house.
     */
    public int getNumberOfDevices() {
        return house.getNumberOfDevices();
    }

    /**
     * Method that returns a device by its position in the list of all devices in the house.
     *
     * @param position Position of the device in the list of all devices.
     * @return Device1 chosen.
     */
    public void getDeviceByPosition(int position) {
        this.measurable = this.house.getDeviceByPosition(position);
    }

    /**
     * Method that calculates the total energy consumption of the chosen device in a given interval.
     *
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Double with the required energy consumption.
     */
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return this.measurable.getEnergyConsumptionInAnInterval(startDate, endDate);
    }

    //HOUSE GRID

    public boolean isHouseGridListEmpty() {
        return house.isHouseGridListEmpty();
    }

    public int getHouseGridListSize() {
        return house.getHouseGridListSize();
    }

    public String getHouseGridListToString() {
        return this.house.getHouseGridListToString();
    }

    public void getHouseGridByPosition(int position) {
        this.measurable = this.house.getHouseGridByPosition(position);
    }

    public String getHouseGridName(){
        return this.houseGrid.getName();
    }

    //ROOM

    public String getRoomListToString() {
        return house.getRoomListContent();
    }

    public void getRoomByPosition(int position) {
        this.measurable = this.house.getRoomOfTheRoomList(position);
    }

    public boolean roomListIsEmpty() {
        return house.roomListIsEmpty();
    }

    public int getRoomListSize() {
        return house.getRoomListSize();
    }

    public boolean isDeviceListEmpty() {
        return selectedRoom.isDeviceListEmpty();
    }

    public String getRoomName(){
        return selectedRoom.getName();
    }



}
