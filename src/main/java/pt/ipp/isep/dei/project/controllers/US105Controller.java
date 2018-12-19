package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class US105Controller {
    private House mHouse;


    public US105Controller(House house) {
        this.mHouse = house;
    }

    public Room addANewRoom(double height, double length, double width, String name, int housefloor ){
        return mHouse.getListOfRoom().newRoom(name,housefloor,height,length,width);
    }

    public boolean addRoomToRoomList(Room room) {
        return this.mHouse.addRoomToHouse(room);
    }


}
