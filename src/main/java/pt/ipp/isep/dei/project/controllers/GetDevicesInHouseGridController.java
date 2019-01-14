package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

/**
 * US160
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
}
