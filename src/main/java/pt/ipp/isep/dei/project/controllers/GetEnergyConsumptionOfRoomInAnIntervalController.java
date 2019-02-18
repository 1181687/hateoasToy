package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfRoomInAnIntervalController {
    private House mHouse;
    private Room mSelectedRoom;

    public GetEnergyConsumptionOfRoomInAnIntervalController(House house) {
        this.mHouse = house;
    }

    public String getRoomListToString() {
        return mHouse.getRoomListContent();
    }

    public void getRoomByPosition(int position) {
        mSelectedRoom = this.mHouse.getRoomOfTheRoomList(position);
    }

    public boolean roomListIsEmpty() {
        return mHouse.roomListIsEmpty();
    }

    public int getRoomListSize() {
        return mHouse.getRoomListSize();
    }

    public boolean isDeviceListEmpty() {
        return mSelectedRoom.isDeviceListEmpty();
    }

    public String getRoomName(){
        return mSelectedRoom.getName();
    }

    public double getEnergyConsumptionOfRoomInInterval(LocalDateTime initialDate, LocalDateTime finalDate) {
        return mSelectedRoom.getEnergyConsumptionInAnInterval(initialDate, finalDate);
    }

    public double resultWithTwoDecimalPlaces(double value, int decimalPlaces){
        return Utils.round(value, decimalPlaces);
    }
}
