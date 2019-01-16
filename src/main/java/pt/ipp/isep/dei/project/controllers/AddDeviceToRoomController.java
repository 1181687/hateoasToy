package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.DeviceList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class AddDeviceToRoomController {

    private House mHouse;
    private Device mDevice;
    private DeviceList mDeviceList;
    private Room mRoom;

    public AddDeviceToRoomController(House house) {
        this.mHouse = house;
        this.mDeviceList = new DeviceList();
    }


    public String getRoomListContent() {
        return mHouse.getRoomListContent();
    }

    public void getRoom(int position) {
        mRoom = mHouse.getRoomOfTheRoomList(position);
    }

    public Room getSelectedRoom() {
        return mRoom;
    }

    public int roomListLength() {
        return mHouse.getRoomListSize();
    }


    public String getDeviceTypeListContent() {
        return mDeviceList.getDeviceTypeListContent();
    }



    public Device createNewElectricWaterHeater(String nome, Room selectedRoom, double mHotWaterTemperature, double mMaximumVolume, double mNominalPower) {
        return mDevice = mDeviceList.newElectricWaterHeater(nome, selectedRoom, mHotWaterTemperature, mMaximumVolume, mNominalPower);
    }

    public boolean addDeviceToRoom() {
        return mRoom.addDevice(mDevice);
    }
}
