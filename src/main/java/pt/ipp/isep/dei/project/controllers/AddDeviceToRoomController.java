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

    public AddDeviceToRoomController (DeviceList deviceList){
        this.mDeviceList = deviceList;
    }

    /*public boolean checkIfRoomListIsEmpty() {
        return mListOfRooms.checkIfRoomListIsEmpty();
    } --> para ser usado caso se decida adicionar a validação dos quartos*/

    public String getRoomListContent() {
        return mListOfRooms.getRoomListContent();
    }

    public Room getRoomFromList(int position) {
        return mListOfRooms.getRoomFromAPosition(position);
    }

    public int roomListLength() {
        return mListOfRooms.getmRoomList().size();
    }

    public List<String> getTypeNamesList (){
        return mDevice.getTypeNames();
    }

    public Device createNewElectricWaterHeater(String nome, Room selectedRoom, double mHotWaterTemperature, double mMaximumVolume, double mNominalPower) {
        return mDeviceList.newElectricWaterHeater(nome, selectedRoom, mHotWaterTemperature, mMaximumVolume, mNominalPower);
    }

}
