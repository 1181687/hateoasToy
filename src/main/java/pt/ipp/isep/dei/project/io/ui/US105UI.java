package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US105Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Scanner;

public class US105UI {

    private US105Controller mctrl;

    public US105UI(House house) {
        this.mctrl = new US105Controller(house);
    }

    public void run(){

        System.out.println("What is the name of the room you want to add to the house?");
        Scanner read = new Scanner(System.in);
        String name = read.nextLine();
        System.out.println("Write the number of housefloor's room");
        int houseFloor = read.nextInt();
        System.out.println("Write the height of the room");
        double height = read.nextDouble();
        System.out.println("Write the length of the room");
        double length = read.nextDouble();
        System.out.println("Write the width of the room");
        double width = read.nextDouble();

        Room newRoom = mctrl.addANewRoom(height,length,width,name,houseFloor);

        mctrl.addRoomToRoomList(newRoom);

        System.out.println("The new room " + name + " was created with sucess!");

        StringBuilder content = new StringBuilder();
        content.append("The new room " + name);
        content.append(" was created with sucess!");
        content.append("\n");
        content.toString();
    }
}
