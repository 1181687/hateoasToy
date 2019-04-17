package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.services.RoomService;

public class AddRoomController {
    private RoomService roomService;

    /**
     * method constructor that receives a roomService
     *
     * @param roomService received
     */
    public AddRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * method that creates a new room invoking the method newroom from model and stores it in the private atribute
     * room of this class
     *
     *
     * @param roomDTO
     */
    public void addRoom(RoomDTO roomDTO) {
        Room room = RoomMapper.mapToEntity(roomDTO);
        roomService.addRoom(room);
    }


    /**
     * method that check if a name of a room already exists on the list of rooms.
     *
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.roomService.isNameExistant(name);
    }
}
