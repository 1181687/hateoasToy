package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AttachRoomToHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.RoomList;

/**
 * US147 As an Administrator, I want to attach a room to a housegrid grid, so that the room’s
 * power and energy consumption is included in that grid.
 */

public class AttachRoomToHouseGrid {
    private AttachRoomToHouseGridController controller;
    private static final String EXIT_OPTION = "\r0 - Exit";

    public AttachRoomToHouseGrid(House house, RoomList listOfRooms) {
        controller = new AttachRoomToHouseGridController(house, listOfRooms);
    }

    public void run() {
        if (controller.isHouseGridListEmpty()) {
            System.out.println("There are no housegrid grids available. Please create one.\n");
        } else {
            chooseHouseGridAndRoom();
        }
    }

    public void chooseHouseGridAndRoom() {
        if (!chooseHouseGrid()) {
            return;
        }
        if (controller.isRoomListEmpty()) {
            System.out.println("There are no rooms available. Please create one\n");
        } else {
            if (!chooseAndAttachRoomToGrid()) {
                return;
            }
        }
    }

    public boolean chooseHouseGrid() {
        String label1 = "Please choose the housegrid grid where the room will be attached: \n" + controller.getHouseGridListToString() + EXIT_OPTION;
        int indexOfTheChosenGrid = InputValidator.getIntRange(label1, 0, controller.getHouseGridListSize()) - 1;
        if (indexOfTheChosenGrid == -1) {
            return false;
        }
        controller.setGridToBeUsed(controller.getHouseGridFromTheList(indexOfTheChosenGrid));
        return true;
    }


    public boolean chooseAndAttachRoomToGrid() {
        String label2 = "Please choose a room to be attached to the chosen housegrid grid: \n" + controller.getRoomListContent() + EXIT_OPTION;
        int indexOfTheChosenRoom = InputValidator.getIntRange(label2, 0, controller.getRoomListSize()) - 1;
        if (indexOfTheChosenRoom == -1) {
            return false;
        }
        controller.setRoomToBeAttached(controller.getRoomFromTheList(indexOfTheChosenRoom));

        if(!checkIfRoomIsAlreadyInAGrid()){
            return false;
        }
        attachRoomConfirmation();
        return true;
    }


    public boolean checkIfRoomIsAlreadyInAGrid() {
        while (controller.checkIfTheChosenRoomIsAlreadyInTheChosenGrid() || controller.getTheGridWhereTheRoomIsConnected() != null) {
            if (controller.checkIfTheChosenRoomIsAlreadyInTheChosenGrid()) {
                String label3 = "The specified room is already in the housegrid grid. Please, choose another one to add to the selected grid: \n" + controller.getRoomListContent() + EXIT_OPTION;
                int indexOfTheChosenRoom2 = InputValidator.getIntRange(label3, 0, controller.getRoomListSize()) - 1;
                if (indexOfTheChosenRoom2 == -1) {
                    return false;
                }
                controller.setRoomToBeAttached(controller.getRoomFromTheList(indexOfTheChosenRoom2));
            } else {
                optionsWhenRoomConnectedToOtherGrid();
            }
        }
        return true;
    }

    public void optionsWhenRoomConnectedToOtherGrid() {
        if (controller.getTheGridWhereTheRoomIsConnected() != null) {
            String label4 = "The specified room is already in another grid. Do you want to disconnect it and connect it the the chosen grid? (y/n)";
            String answer = InputValidator.confirmValidation(label4);
            if ("y".equals(answer) || "Y".equals(answer)) {
                controller.detachRoomFromTheHouseGrid(controller.getTheGridWhereTheRoomIsConnected());
                controller.attachRoomInTheHouseGrid();
                System.out.println("The room has been attached to the housegrid grid.\n");
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
            controller.attachRoomInTheHouseGrid();
            System.out.println("The room has been attached to the housegrid grid.\n");
        } else {
            System.out.println("The room wasn't attached to the housegrid grid.\n");
        }
    }
}