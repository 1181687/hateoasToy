package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

public class GetListOfDevicesController {
    private House mHouse;


    public String getDeviceListContent(int position) {
        return this.mHouse.getDeviceListContentOfARoom(position);
    }

}


