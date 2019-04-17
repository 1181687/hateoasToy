package pt.ipp.isep.dei.project.controllers;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
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
     * @param height given height number to the new room
     * @param length given length number to the new room
     * @param width given width number to the new room
     * @param id given name to the new room
     * @param description description of the room
     * @param housefloor given housefloor number to the new room
     */
    public void addRoom(String id, String description,  int housefloor, double height, double length, double width) {
        Dimension dimension = new Dimension(height,length,width);
        RoomId roomId = new RoomId(id);
        Room room = new Room(roomId,description,housefloor,dimension);
        roomService.addRoom(room);
    }


    /**
     * method that check if a name of a room already exists on the list of rooms.
     * @param name
     */
    public boolean isNameExistant(String name) {
        return this.roomService.isNameExistant(name);
    }
}
