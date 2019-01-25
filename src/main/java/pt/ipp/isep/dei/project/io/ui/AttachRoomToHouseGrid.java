package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AttachRoomToHouseGridController;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.RoomList;

/**
 * US147 As an Administrator, I want to attach a room to a house grid, so that the room’s
 * power and energy consumption is included in that grid.
 */

public class AttachRoomToHouseGrid {
    private AttachRoomToHouseGridController mCtrl;

    public AttachRoomToHouseGrid(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        mCtrl = new AttachRoomToHouseGridController(listOfHouseGrids, listOfRooms);
    }

    public void run() {
        if (mCtrl.isHouseGridListEmpty()) {
            System.out.println("There are no house grids available. Please create one.\n");
        } else {
            String exit = "\r0 - Exit";
            String label1 = "Please choose the house grid where the room will be attached: \n" + mCtrl.getHouseGridListToString() + exit;
            int indexOfTheChosenGrid = InputValidator.getIntRange(label1, 0, mCtrl.getHouseGridListSize()) - 1;
            if (indexOfTheChosenGrid == -1) {
                return;
            }
            mCtrl.setGridToBeUsed(mCtrl.getHouseGridFromTheList(indexOfTheChosenGrid));
            if (mCtrl.isRoomListEmpty()) {
                System.out.println("There are no rooms available. Please create one\n");
            } else {
                String label2 = "Please choose a room to be attached to the chosen house grid: \n" + mCtrl.getRoomListContent() + exit;
                int indexOfTheChosenRoom = InputValidator.getIntRange(label2, 0, mCtrl.getRoomListSize()) - 1;
                if (indexOfTheChosenRoom == -1) {
                    return;
                }
                mCtrl.setRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom));
                while (mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid() || mCtrl.getTheGridWhereTheRoomIsConnected() != null) {
                    if (mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid()) {
                        String label3 = "The specified room is already in the house grid. Please, choose another one to add to the selected grid: \n" + mCtrl.getRoomListContent() + exit;
                        int indexOfTheChosenRoom2 = InputValidator.getIntRange(label3, 0, mCtrl.getRoomListSize()) - 1;
                        if (indexOfTheChosenRoom2 == -1) {
                            return;
                        }
                        mCtrl.setRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom2));
                    } else {
                        if (mCtrl.getTheGridWhereTheRoomIsConnected() != null) {
                            String label4 = "The specified room is already in another grid. Do you want to disconnect it and connect it the the chosen grid? (y/n)";
                            String answer = InputValidator.confirmValidation(label4);
                            if ("y".equals(answer) || "Y".equals(answer)) {
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
                String label5 = "Confirm? (y/n)";
                String answer = InputValidator.confirmValidation(label5);
                if ("y".equals(answer) || "Y".equals(answer)) {
                    mCtrl.attachRoomInTheHouseGrid();
                    System.out.println("The room has been attached to the house grid.\n");
                } else {
                    System.out.println("The room wasn't attached to the house grid.\n");
                }
            }
        }
    }
}