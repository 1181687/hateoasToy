package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.RoomAggregateService;
import pt.ipp.isep.dei.project.services.RoomService;

/**
 * US105 As an Administrator, I want to add a new room to the house, in order to configure
 * it (name, house floor and dimensions).
 */

public class AddRoom {

    private AddRoomController controller;

    /**
     * method constructor that receives a roomService
     *
     * @param roomService
     */
    public AddRoom(RoomAggregateService roomService) {
        this.controller = new AddRoomController(roomService);
    }

    /**
     * method that creates and adds a new room to the housegrid. First asks the user to give the valid parameters necessary
     * to create the new room and then invokes the controller methods newroom and addRoom. Finally if the
     * addRoom returns true, a informative message of the sucess of it, is apresented to the user.
     */
    public void run() {

        RoomDTO roomDTO = new RoomDTO();

        String label1 = "What is the name of the room you want to add to the house?";
        String id = InputValidator.getString(label1);
        roomDTO.setId(id);

        while (controller.isNameExistant(id)) {
            System.out.println("Name already exists. Please write a new one.");
            id = InputValidator.getString(label1);
        }

        String label11 = "What is the description of the room you want to add to the house?";
        String description = InputValidator.getString(label11);
        roomDTO.setDescription(description);

        String label2 = "Write the number of housefloor's room";
        int houseFloor = InputValidator.getInt(label2);
        roomDTO.setHouseFloor(houseFloor);

        String label3 = "Write the height of the room";
        double height = InputValidator.getDoublePos(label3);
        roomDTO.setHeight(height);

        String label4 = "Write the length of the room";
        double length = InputValidator.getDoublePos(label4);
        roomDTO.setLength(length);

        String label5 = "Write the width of the room";
        double width = InputValidator.getDoublePos(label5);
        roomDTO.setWidth(width);

        if(controller.addRoom(roomDTO)){

            StringBuilder content = new StringBuilder();
            content.append("The new room ");
            content.append(id);
            content.append(" was created with success!\n");
            System.out.println(content.toString());
        }

    }
}
