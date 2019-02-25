package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;

public class GetEnergyConsumptionRoomController {
    private House house;
    private Room selectedRoom;

    public GetEnergyConsumptionRoomController(House house) {
        this.house = house;
    }

    public String getRoomListToString() {
        return house.getRoomListContent();
    }

    public void getRoomByPosition(int position) {
        selectedRoom = this.house.getRoomOfTheRoomList(position);
    }

    public boolean roomListIsEmpty() {
        return house.roomListIsEmpty();
    }

    public int getRoomListSize() {
        return house.getRoomListSize();
    }

    public boolean isDeviceListEmpty() {
        return selectedRoom.isDeviceListEmpty();
    }

    public String getRoomName(){
        return selectedRoom.getName();
    }

    public double getRoomEnergyConsumptionOfRoomInInterval(LocalDateTime initialDate, LocalDateTime finalDate) {
        return selectedRoom.getEnergyConsumptionInAnInterval(initialDate, finalDate);
    }
}
