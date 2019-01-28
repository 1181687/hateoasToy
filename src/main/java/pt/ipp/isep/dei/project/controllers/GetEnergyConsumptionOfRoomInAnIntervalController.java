package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

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

    public String getRoomNameByPosition(int position) {
        return mHouse.getRoomNameByPosition(position);
    }

    public double getEnergyConsumptionOfRoomInInterval(LocalDateTime initialDate, LocalDateTime finalDate) {
        return mSelectedRoom.getEnergyConsumptionInAnInterval(initialDate, finalDate);
    }
}
