package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddRoomController;
import pt.ipp.isep.dei.project.model.House;
/**US105 As an Administrator, I want to add a new room to the house, in order to configure
it (name, house floor and dimensions).*/

public class AddRoom {

    private AddRoomController controller;

    /**
     * method constructor that receives a house
     *
     * @param house house received
     */
    public AddRoom(House house) {
        this.controller = new AddRoomController(house);
    }

    /**
     * method that creats and adds a new room to the house. First asks the user to give the valid parameters necessary
     * to create the new room and then invokes the controller methods newroom and addRoom. Finally if the
     * addRoom returns true, a informative message of the sucess of it, is apresented to the user.
     */
    public void run() {

        String label1 = "What is the name of the room you want to add to the house?";
        String name = InputValidator.getString(label1);

        while (controller.isNameExistant(name)) {
            System.out.println("Name already exists. Please write a new one.");
            name = InputValidator.getString(label1);
        }

        String label2 = "Write the number of housefloor's room";
        int houseFloor = InputValidator.getInt(label2);

        String label3 = "Write the height of the room";
        double height = InputValidator.getDoublePos(label3);

        String label4 = "Write the length of the room";
        double length = InputValidator.getDoublePos(label4);

        String label5 = "Write the width of the room";
        double width = InputValidator.getDoublePos(label5);

        controller.newRoom(height, length, width, name, houseFloor);
        controller.addRoomToHouse();

        StringBuilder content = new StringBuilder();
        content.append("The new room ");
        content.append(name);
        content.append(" was created with sucess!\n");
        System.out.println(content.toString());
    }
}
