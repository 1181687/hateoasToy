package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class AddRoomController {
    private House mHouse;
    private Room mRoom;


    /**
     * method constructor that receives a house
     *
     * @param house house received
     */
    public AddRoomController(House house) {
        this.mHouse = house;
    }

    /**
     * method that creates a new room invoking the method newroom from model and stores it in the private atribute
     * mRoom of this class
     * @param height given height number to the new room
     * @param length given length number to the new room
     * @param width given width number to the new room
     * @param name given name to the new room
     * @param housefloor given housefloor number to the new room
     */
    public void newRoom(double height, double length, double width, String name, int housefloor ){
        this.mRoom = mHouse.newRoom(height, length, width, name, housefloor);
    }

    /**
     * method that adds a room to the house's roomlist. Invokes the method addRoom of the model
     * who receives the new room stored in the private attribute mRoom of this class
     * @return true if adds, false if don't
     */
    public boolean addRoomToHouse() {
        return this.mHouse.addRoom(mRoom);
    }

    /**
     * method that check if a name of a room already exists on the list of rooms.
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.mHouse.isNameExistant(name);
    }
}
