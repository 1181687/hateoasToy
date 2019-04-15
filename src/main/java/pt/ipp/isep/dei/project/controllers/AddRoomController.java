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
    public void addRoom(String id, String description, double height, double length, double width, int housefloor) {
        this.room = house.newRoom(id, description, housefloor, height, length, width);
    }


    /**
     * method that check if a name of a room already exists on the list of rooms.
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.house.isNameExistant(name);
    }
}
