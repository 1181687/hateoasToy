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

    public String getHouseGridListToString() {
        return this.mHouse.getHouseGridList().getHouseGridListToString();
    }

    public boolean checkIfHouseGridListIsEmpty() {
        return mHouse.checkIfHouseGridListIsEmpty();
    }

    public int getHouseGridListLength() {
        return this.mHouse.getHouseGridListLength();
    }

    public boolean checkIfThereAreNoDevicesHGbyPosition(int position) {
        return this.mHouse.checkIfThereAreNoDevicesHGbyPosition(position);
    }

    public String getHGNameByHGPosition(int position) {
        return this.mHouse.getHGNameByHGPosition(position);
    }

}
