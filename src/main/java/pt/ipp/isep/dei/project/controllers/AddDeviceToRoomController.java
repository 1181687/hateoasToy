package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.DeviceList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.List;

public class AddDeviceToRoomController {

    private Device mDevice;
    private DeviceList mDeviceList;
    private RoomList mListOfRooms;
    private Room mRoom;

    public AddDeviceToRoomController(DeviceList deviceList, RoomList roomList) {
        this.mDeviceList = deviceList;
        this.mListOfRooms = roomList;
    }

    /*public boolean checkIfRoomListIsEmpty() {
        return mListOfRooms.checkIfRoomListIsEmpty();
    } --> para ser usado caso se decida adicionar a validação dos quartos*/

    public String getRoomListContent() {
        return mListOfRooms.getRoomListContent();
    }

    public Room getRoomFromList(int position) {
        return mRoom = mListOfRooms.getRoomFromAPosition(position);
    }

    public int roomListLength() {
        return mListOfRooms.getmRoomList().size();
    }

    public List<String> getTypeNamesList() {
        return mDevice.getTypeNames();
    }

    public Device createNewElectricWaterHeater(String nome, Room selectedRoom, double nominalPower, double volumeOfWater) {
        return mDevice = mDeviceList.newElectricWaterHeater(nome, selectedRoom, nominalPower, volumeOfWater);
    }

    public boolean addDeviceToRoom() {
        return mRoom.addDevice(mDevice);
    }
}
