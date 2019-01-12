package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

public class GetListOfDevicesRoomController {

    private House mHouse;

    public GetListOfDevicesRoomController(House mHouse) {
        this.mHouse = mHouse;
    }
    public String getDeviceListContent(int position) {
        return this.mHouse.getDeviceListContentOfARoom(position);
    }

    public boolean checkIfListIsEmpty(int position) {
        return this.mHouse.checkIfDeviceListIsEmpty(position);
    }
}