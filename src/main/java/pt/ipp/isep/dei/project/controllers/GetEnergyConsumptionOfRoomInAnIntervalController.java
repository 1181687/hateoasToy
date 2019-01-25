package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfRoomInAnIntervalController {
    private House mHouse;
    private Room SelectedRoom;

    public GetEnergyConsumptionOfRoomInAnIntervalController(House house) {
        this.mHouse = house;
    }

    public double getEnergyConsumptionOfRoomInInterval(LocalDateTime initialDate, LocalDateTime finalDate) {
        return SelectedRoom.getEnergyConsumptionInAnInterval(initialDate, finalDate);
    }

    public String getRoomListToString() {
        return SelectedRoom.getRoomToString();
    }

    public Room getRoomByPosition(int position) {
        return mHouse.getRoomOfTheRoomList(position);
    }

    public boolean deviceListIsEmpty() {
        return SelectedRoom.isDeviceListEmpty();
    }
}
