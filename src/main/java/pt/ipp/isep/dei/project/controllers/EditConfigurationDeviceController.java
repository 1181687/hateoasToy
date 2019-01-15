package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class EditConfigurationDeviceController {

    private House mHouse;
    private Device mDevice;
    private Room mRoom;

    public EditConfigurationDeviceController(House mHouse) {
        this.mHouse = mHouse;
        this.mDevice = mDevice;
        this.mRoom = mRoom;
    }

    public String getRoomListContent () {
        return this.mHouse.getRoomListContent();
    }

    public void getRoomByPosition (int option) {
        mRoom = this.mHouse.getRoomOfTheRoomList(option);
    }

    public String getDevicesInTheRoom () {
        return this.mRoom.getDeviceListContent();
    }

    public Device getDeviceByPosition (int position) {
        return this.mRoom.getmDeviceList().getDeviceByPosition(position);
    }

}
