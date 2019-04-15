package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;

/**
 * US160 As a Power User[or administrator], i want to get a list of all devices in a grid,
 * grouped by device type. It must include device location.
 */

public class GetDevicesInHouseGridController {
    private House house;

    /**
     * constructor that receives a House
     *
     * @param house House received
     */
    public GetDevicesInHouseGridController(House house) {
        this.house = house;
    }

    public String getDeviceListContentNameTypeLocationByGrid(int position) {
        return house.getDeviceListContentNameTypeLocationByGrid(position);
    }

    /**
     * Method that shows the content of the housegrid grids in the list.
     *
     * @return String
     */
    public String getHouseGridListToString() {
        return this.house.getHouseGridListToString();
    }

    /**
     * Method that checks if the housegrid grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return house.isHouseGridListEmpty();
    }

    /**
     * method that gets the size of House Grid List
     *
     * @return integer
     */
    public int getHouseGridListSize() {
        return this.house.getHouseGridListSize();
    }

    /**
     * method that checks if there are no devices in the RoomList
     *
     * @return true if there aren't devices. False if there are devices
     */
    public boolean checkIfThereAreNoDevicesInGridbyPosition(int position) {
        return this.house.checkIfThereAreNoDevicesInGridbyPosition(position);
    }

    /**
     * method that gets the name of House Grid by it's position in the list of housegrid grids.
     *
     * @param position position of the House Grid
     * @return String name
     */
    public String getGridNameByPosition(int position) {
        return this.house.getGridNameByPosition(position);
    }
}
