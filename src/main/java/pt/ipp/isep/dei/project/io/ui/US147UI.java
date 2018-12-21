package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US147Controller;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

public class US147UI {
    private US147Controller mCtrl;

    public US147UI(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        mCtrl = new US147Controller(listOfHouseGrids, listOfRooms);
    }

    public void run() {
        if (mCtrl.checkIfHouseGridListIsEmpty()) {
            System.out.println("There are no house grids available. Please create one. \n");
        } else {
            System.out.println("Please choose the house grid where the room will be attached.");
            System.out.println(mCtrl.listAllTheHouseGridsInTheList());
            Scanner keyboardInput = new Scanner(System.in);
            int indexOfTheChosenGrid = keyboardInput.nextInt() - 1;
            mCtrl.setmGridToBeUsed(mCtrl.getHouseGridFromTheList(indexOfTheChosenGrid));

            System.out.println("Please choose a room to be attached to the chosen house grid.");
            System.out.println(mCtrl.listAllTheRoomsInTheList());
            int indexOfTheChosenRoom = keyboardInput.nextInt() - 1;
            mCtrl.setmRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom));
            if (!mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid()) {
                System.out.println("Confirm? (y/n)");
                Scanner keyboardInput2 = new Scanner(System.in);
                String answer = keyboardInput2.nextLine();
                if (answer.equals("y")) {
                    mCtrl.attachRoomInTheHouseGrid();
                    System.out.println("The specified room has been attached to the specified house grid.");
                }
            } else {
                System.out.println("The specified room is already in the house grid.");
            }
        }
    }
}