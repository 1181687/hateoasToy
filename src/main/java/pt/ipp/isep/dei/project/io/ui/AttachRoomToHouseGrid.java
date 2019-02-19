package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AttachRoomToHouseGridController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

/**
 * US147 As an Administrator, I want to attach a room to a house grid, so that the room’s
 * power and energy consumption is included in that grid.
 */

public class AttachRoomToHouseGrid {
    private AttachRoomToHouseGridController mCtrl;
    private static final String EXIT_OPTION = "\r0 - Exit";

    public AttachRoomToHouseGrid(House house, RoomList listOfRooms) {
        mCtrl = new AttachRoomToHouseGridController(house, listOfRooms);
    }

    public void run() {
        if (mCtrl.isHouseGridListEmpty()) {
            System.out.println("There are no house grids available. Please create one.\n");
        } else {
            chooseHouseGridAndRoom();
        }
    }

    public void chooseHouseGridAndRoom() {
        if (chooseHouseGrid() == false) {
            return;
        }
        if (mCtrl.isRoomListEmpty()) {
            System.out.println("There are no rooms available. Please create one\n");
        } else {
            if (chooseAndAttachRoomToGrid() == false) {
                return;
            }
        }
    }

    public boolean chooseHouseGrid() {
        String label1 = "Please choose the house grid where the room will be attached: \n" + mCtrl.getHouseGridListToString() + EXIT_OPTION;
        int indexOfTheChosenGrid = InputValidator.getIntRange(label1, 0, mCtrl.getHouseGridListSize()) - 1;
        if (indexOfTheChosenGrid == -1) {
            return false;
        }
        mCtrl.setGridToBeUsed(mCtrl.getHouseGridFromTheList(indexOfTheChosenGrid));
        return true;
    }


    public boolean chooseAndAttachRoomToGrid() {
        String label2 = "Please choose a room to be attached to the chosen house grid: \n" + mCtrl.getRoomListContent() + EXIT_OPTION;
        int indexOfTheChosenRoom = InputValidator.getIntRange(label2, 0, mCtrl.getRoomListSize()) - 1;
        if (indexOfTheChosenRoom == -1) {
            return false;
        }
        mCtrl.setRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom));

        if(checkIfRoomIsAlreadyInAGrid()==false){
            return false;
        }
        attachRoomConfirmation();
        return true;
    }


    public boolean checkIfRoomIsAlreadyInAGrid() {
        while (mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid() || mCtrl.getTheGridWhereTheRoomIsConnected() != null) {
            if (mCtrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid()) {
                String label3 = "The specified room is already in the house grid. Please, choose another one to add to the selected grid: \n" + mCtrl.getRoomListContent() + EXIT_OPTION;
                int indexOfTheChosenRoom2 = InputValidator.getIntRange(label3, 0, mCtrl.getRoomListSize()) - 1;
                if (indexOfTheChosenRoom2 == -1) {
                    return false;
                }
                mCtrl.setRoomToBeAttached(mCtrl.getRoomFromTheList(indexOfTheChosenRoom2));
            } else {
                optionsWhenRoomConnectedToOtherGrid();
            }
        }
        return true;
    }

    public void optionsWhenRoomConnectedToOtherGrid() {
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


    public void attachRoomConfirmation() {
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