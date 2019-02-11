package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

/**
 * US160 As a Power User[or administrator], i want to get a list of all devices in a grid,
 * grouped by device type. It must include device location.
 */

public class GetDevicesInHouseGridController {
    private House mHouse;

    /**
     * constructor that receives a House
     *
     * @param house House received
     */
    public GetDevicesInHouseGridController(House house) {
        this.mHouse = house;
    }

    public String getDeviceListContentNameTypeLocationByHG(int position) {
        return mHouse.getDeviceListContentNameTypeLocationByHG(position);
    }

    /**
     * Method that shows the content of the house grids in the list.
     *
     * @return String
     */
    public String getHouseGridListToString() {
        return this.mHouse.getHouseGridListToString();
    }

    /**
     * Method that checks if the house grid's list is empty.
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return mHouse.isHouseGridListEmpty();
    }

    /**
     * method that gets the size of House Grid List
     * @return integer
     */
    public int getHouseGridListSize() {
        return this.mHouse.getHouseGridListSize();
    }

    /**
     * method that checks if there are no devices in the RoomList
     * @return true if there aren't devices. False if there are devices
     */
    public boolean checkIfThereAreNoDevicesHGbyPosition(int position) {
        return this.mHouse.checkIfThereAreNoDevicesHGbyPosition(position);
    }

    /**
     * method that gets the name of House Grid by it's position in the list of house grids.
     * @param position position of the House Grid
     * @return String name
     */
    public String getHGNameByHGPosition(int position) {
        return this.mHouse.getHGNameByHGPosition(position);
    }
}
