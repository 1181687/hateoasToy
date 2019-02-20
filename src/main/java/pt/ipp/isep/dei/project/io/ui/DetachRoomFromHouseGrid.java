package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DetachRoomFromHouseGridController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

/**
 * US149 As an Administrator, I want to detach a room from a house grid, so that the room’s
 * power and energy consumption is not included in that grid. The room’s
 * characteristics are not changed.
 */

public class DetachRoomFromHouseGrid {
    private DetachRoomFromHouseGridController controller;

    public DetachRoomFromHouseGrid(House house, RoomList roomList) {
        this.controller = new DetachRoomFromHouseGridController(house, roomList);
    }

    public void showFinalList ( int firstOption){
        String label = "Do you want to see the list of room(s) on the selected Grid? (Y/N)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equals(answer) || "Y".equals(answer)) {
            if (controller.getListOfRoomsInACertainHouseGrid(firstOption).isEmpty()) {
                System.out.println("There's no rooms in this list now.");
            } else {
                System.out.println(controller.getListOfRoomsInACertainHouseGrid(firstOption));
            }
        } else {
            return;
        }
    }

    public void run() {
        if (controller.getGridListSize() == 0) {
            System.out.println("There are no house grids in your house. Please insert a new house grid.");
            System.out.println();
        } else {
            System.out.println(controller.getListOfHouseGridsAttachedToHouseGrid());
            String label1 = "Please choose the house grid where the room will be detached.";
            int firstOption = InputValidator.getIntRange(label1, 0, controller.getGridListSize()) - 1;

            if (controller.getListOfRoomsInACertainHouseGrid(firstOption).isEmpty()) {
                System.out.println("There are no rooms to detach." + "\n");

            } else {
                System.out.println(controller.getListOfRoomsInACertainHouseGrid(firstOption));
                String label2 = "Please choose the room to be detached from the chosen house grid.";
                int secondOption = InputValidator.getIntRange(label2, 0, controller.getListOfRoomsInACertainHouseGrid(firstOption).length()) - 1;
                if (controller.detachRoomFromGridList((controller.getHouseGridFromTheList(firstOption)), controller.getRoomFromTheListOfRoomByAPosition(secondOption))) {
                    System.out.println("The room has been detached from the grid.");
                   showFinalList(firstOption);
                } else {
                    System.out.println("Please select a valid room to detach.");
                }
            }
        }

    }


}
