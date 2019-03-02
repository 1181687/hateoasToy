package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid.HouseGrid;
import pt.ipp.isep.dei.project.model.Measurable;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;

public class GetEnergyConsumptionController {
    private House house;
    private Measurable measurable;


    /**
     * Constructor.
     *
     * @param house House to be used.
     */
    public GetEnergyConsumptionController(House house) {
        this.house = house;
    }

    //DEVICE
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



    //HOUSE GRID

    /**
     * Method that verify if HouseGrid List is empty.
     *
     * @return True if it is empty or false if it is not.
     */
    public boolean isHouseGridListEmpty() {
        return house.isHouseGridListEmpty();
    }

    /**
     * Method that gets the size of the HouseGrid List.
     * @return the number of HouseGrids in the list.
     */
    public int getHouseGridListSize() {
        return house.getHouseGridListSize();
    }

    /**
     * Method that show a String of the HouseGrid List content.
     * @return the HouseGrid List content.
     */
    public String getHouseGridListToString() {
        return this.house.getHouseGridListToString();
    }

    /**
     * Method that gets a House Grid of the list of HouseGrids, from a specific position.
     * @param position
     */
    public void getHouseGridByPosition(int position) {
        this.measurable = this.house.getHouseGridByPosition(position);
    }

    /**
     * Method that gets the name of the house grid.
     * @return
     */
    public String getHouseGridName(){
        return ((HouseGrid)this.measurable).getName();
    }

    //ROOM

    /**
     * method that display a room list.
     */
    public String getRoomListToString() {
        return house.getRoomListContent();
    }

    /**
     * method that get a room of the list of rooms, from a specific position.
     *
     * @param position
     */
    public void getRoomByPosition(int position) {
        this.measurable = this.house.getRoomOfTheRoomList(position);
    }

    /**
     * Method that checks if the Room List is Empty
     *
     * @return true if it is empty
     */
    public boolean roomListIsEmpty() {
        return house.roomListIsEmpty();
    }

    /**
     * Method that get the size of the room list.
     *
     * @return size of the list of rooms.
     */
    public int getRoomListSize() {
        return house.getRoomListSize();
    }

    /**
     * method that checks if Device List of the room is empty
     */
    public boolean isDeviceListEmpty() {
        return ((Room)this.measurable).isDeviceListEmpty();
    }

    /**
     * Method that gets the name of the room.
     * @return
     */
    public String getRoomName(){
        return ((Room)this.measurable).getName();
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


}
