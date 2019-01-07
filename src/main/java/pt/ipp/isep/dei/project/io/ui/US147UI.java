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
            System.out.println("There are no house grids available. Please create one.\n");
        } else {
            String label1 = "Please choose the house grid where the room will be attached: \n" + mCtrl.listAllTheHouseGridsInTheList() + "\r0 - Exit";
            int indexOfTheChosenGrid = InputValidator.getIntRange(label1, 0, mCtrl.houseGridListLength()) - 1;
            if (indexOfTheChosenGrid == -1) {
                return;
            }
            mCtrl.setmGridToBeUsed(mCtrl.getHouseGridFromTheList(indexOfTheChosenGrid));
            if (mCtrl.checkIfRoomListIsEmpty()) {
                System.out.println("There are no rooms available. Please create one\n");
            } else {
                String label2 = "Please choose a room to be attached to the chosen house grid: \n" + mCtrl.listAllTheRoomsInTheList() + "\r0 - Exit";
                int indexOfTheChosenRoom = InputValidator.getIntRange(label2, 0, mCtrl.roomListLength()) - 1;
                if (indexOfTheChosenRoom == -1) {
                    return;
                }
                mCtrl.setmRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom));
                while (mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid() || mCtrl.getTheGridWhereTheRoomIsConnected() != null) {
                    if (mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid()) {
                        String label3 = "The specified room is already in the house grid. Please, choose another one to add to the selected grid: \n" + mCtrl.listAllTheRoomsInTheList() + "\r0 - Exit";
                        int indexOfTheChosenRoom2 = InputValidator.getIntRange(label3, 0, mCtrl.roomListLength()) - 1;
                        if (indexOfTheChosenRoom2 == -1) {
                            return;
                        }
                        mCtrl.setmRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom2));
                    } else {
                        if (mCtrl.getTheGridWhereTheRoomIsConnected() != null) {
                            System.out.println("The specified room is already in another grid. Do you want to disconnect it and connect it the the chosen grid? (Press 'y' to confirm)");
                            Scanner keyboardInput = new Scanner(System.in);
                            String answer = keyboardInput.nextLine();
                            if (answer.equals("y") || answer.equals("Y")) {
                                mCtrl.detachRoomFromTheHouseGrid(mCtrl.getTheGridWhereTheRoomIsConnected());
                                mCtrl.attachRoomInTheHouseGrid();
                                System.out.println("The room has been attached to the house grid.\n");
                                return;
                            } else {
                                System.out.println("No changes where made.\n");
                                return;
                            }
                        }
                    }
                }
                System.out.println("Confirm? (Press 'y' to confirm)");
                Scanner keyboardInput2 = new Scanner(System.in);
                String answer = keyboardInput2.nextLine();
                if (answer.equals("y") || answer.equals("Y")) {
                    mCtrl.attachRoomInTheHouseGrid();
                    System.out.println("The room has been attached to the house grid.\n");
                } else {
                    System.out.println("The room wasn't attached to the house grid.\n");
                }
            }
        }
    }
}