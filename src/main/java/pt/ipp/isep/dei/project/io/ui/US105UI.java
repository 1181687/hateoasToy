package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US105Controller;
import pt.ipp.isep.dei.project.model.House;

public class US105UI {

    private US105Controller mctrl;

    /**
     * method constructor that receives a house
     *
     * @param house house received
     */
    public US105UI(House house) {
        this.mctrl = new US105Controller(house);
    }

    /**
     * method that creats and adds a new room to the house. First asks the user to give the valid parameters necessary
     * to create the new room and then invokes the controller methods newroom and addRoomToHouse. Finally if the
     * addRoomToHouse returns true, a informative message of the sucess of it, is apresented to the user.
     */
    public void run() {

        //System.out.println("What is the name of the room you want to add to the house?");
        //Scanner read = new Scanner(System.in);
        //String name = read.nextLine();

        String label0 = "What is the name of the room you want to add to the house?";
        String name = InputValidator.getString(label0);

        //System.out.println("Write the number of housefloor's room");
        String label1 = "Write the number of housefloor's room";
        //int houseFloor = read.nextInt();
        int houseFloor = InputValidator.getInt(label1);

        //System.out.println("Write the height of the room");
        String label2 = "Write the height of the room";
        //double height = read.nextDouble();
        double height = InputValidator.getDoublePos(label2);

        //System.out.println("Write the length of the room");
        String label3 = "Write the length of the room";
        //double length = read.nextDouble();
        double length = InputValidator.getDoublePos(label3);

        //System.out.println("Write the width of the room");
        String label4 = "Write the width of the room";
        //double width = read.nextDouble();
        double width = InputValidator.getDoublePos(label4);

        mctrl.newRoom(height, length, width, name, houseFloor);
        mctrl.addRoomToHouse();

        //System.out.println("The new room " + name " was created with sucess!");

        StringBuilder content = new StringBuilder();
        content.append("The new room ");
        content.append(name);
        content.append(" was created with sucess!\n");
        System.out.println(content.toString());
    }
}
