package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US147Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

public class US147UI {
    private US147Controller ctrl;

    public US147UI(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        ctrl = new US147Controller(listOfHouseGrids, listOfRooms);
    }

    public void run() {
        System.out.println("Please choose the house grid where the room will be attached.");
        System.out.println(ctrl.getmListOfHouseGrids());
        Scanner ler = new Scanner(System.in);
        int firstOptionPosition = ler.nextInt() - 1;
        HouseGrid chosenGrid = ctrl.getHouseGridFromTheList(firstOptionPosition);

        System.out.println("Please choose the room to be attached to the chosen house grid.");
        System.out.println(ctrl.getmListOfRooms());
        int secondOptionPosition = ler.nextInt() - 1;
        Room chosenRoom = ctrl.getRoomFromTheList(secondOptionPosition);

        if (ctrl.checkIfRoomIsntInAHouseGrid(chosenRoom)) {
            ctrl.attachRoomInTheHouseGrid(chosenGrid, chosenRoom);
            ctrl.changeTheAttributeHouseGridInTheRoomObject(chosenRoom, chosenGrid);
            System.out.println("The specified room has been attached to the specified house grid.");
        } else {
            System.out.println("The specified room is already attached to a house grid. Please, use another room.");
        }


    }
}