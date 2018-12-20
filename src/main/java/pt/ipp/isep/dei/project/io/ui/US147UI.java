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
        System.out.println(ctrl.listAllTheHouseGridsInTheList());
        Scanner keyboardInput = new Scanner(System.in);
        int indexOfTheChosenGrid = keyboardInput.nextInt() - 1;
        HouseGrid chosenGrid = ctrl.getHouseGridFromTheList(indexOfTheChosenGrid);

        System.out.println("\n Please choose a room to be attached to the chosen house grid.");
        System.out.println(ctrl.listAllTheRoomsInTheList());
        int indexOfTheChosenRoom = keyboardInput.nextInt() - 1;
        Room chosenRoom = ctrl.getRoomFromTheList(indexOfTheChosenRoom);
        if (ctrl.checkIfTheChosenRoomIsntAlreadyInTheChosenGrid(chosenGrid, chosenRoom)) {
            ctrl.attachRoomInTheHouseGrid(chosenGrid, chosenRoom);
            System.out.println("The specified room has been attached to the specified house grid.");
        } else {
            System.out.println("The specified room is already in the house grid. Please, choose another one.");
        }
    }
}