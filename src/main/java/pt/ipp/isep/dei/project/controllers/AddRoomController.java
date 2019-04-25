package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

public class AddRoomController {
    private RoomAggregateService roomService;

    /**
     * method constructor that receives a roomService
     *
     * @param roomService received
     */
    public AddRoomController(RoomAggregateService roomService) {
        this.roomService = roomService;
    }

    /**
     * method that creates a new room invoking the method newroom from model and stores it in the private atribute
     * room of this class
     *
     *
     * @param roomDTO
     */
    public boolean addRoom(RoomDTO roomDTO) {
        Dimension dimension = new Dimension(roomDTO.getHeight(),roomDTO.getLength(),roomDTO.getWidth());
        RoomId id = new RoomId(roomDTO.getId());
        return roomService.addRoom(id,roomDTO.getDescription(),roomDTO.getHouseFloor(),dimension);
    }


    /**
     * method that check if a name of a room already exists on the list of rooms.
     *
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.roomService.roomExists(new RoomId(name));
    }
}
