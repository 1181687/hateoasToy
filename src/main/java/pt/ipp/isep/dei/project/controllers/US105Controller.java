package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class US105Controller {
    private House mHouse;
    private Room mRoom;

    public US105Controller(House house) {
        this.mHouse = house;
    }

    public void newRoom(double height, double length, double width, String name, int housefloor ){
        this.mRoom= mHouse.getListOfRoom().newRoom(name,housefloor,height,length,width);
    }

    public boolean addRoomToHouse() {
        return this.mHouse.addRoomToHouse(mRoom);
    }
}
