package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;

public class AddRoomController {
    private House house;
    private Room room;


    /**
     * method constructor that receives a housegrid
     *
     * @param house housegrid received
     */
    public AddRoomController(House house) {
        this.house = house;
    }

    /**
     * method that creates a new room invoking the method newroom from model and stores it in the private atribute
     * room of this class
     * @param height given height number to the new room
     * @param length given length number to the new room
     * @param width given width number to the new room
     * @param id given name to the new room
     * @param description description of the room
     * @param housefloor given housefloor number to the new room
     */
    public void newRoom(double height, double length, double width, String id, String description, int housefloor) {
        this.room = house.newRoom(height, length, width, id, description, housefloor);
    }

    /**
     * method that adds a room to the housegrid's roomlist. Invokes the method addRoom of the model
     * who receives the new room stored in the private attribute room of this class
     *
     * @return true if adds, false if don't
     */
    public boolean addRoomToHouse() {
        return this.house.addRoom(room);
    }

    /**
     * method that check if a name of a room already exists on the list of rooms.
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.house.isNameExistant(name);
    }
}
