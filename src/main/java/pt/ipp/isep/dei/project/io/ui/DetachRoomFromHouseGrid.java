package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US149Controller;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.RoomList;

public class DetachRoomFromHouseGrid {
    private US149Controller ctrl;

    public DetachRoomFromHouseGrid(HouseGridList gridList, RoomList roomList) {
        this.ctrl = new US149Controller(gridList, roomList);
    }

    public void run() {
        if (ctrl.getNumberOfGridLists() == 0) {
            System.out.println("There are no house grids in your house. Please insert a new house grid.");
            System.out.println();
        } else {
            System.out.println(ctrl.getListOfHouseGridsAttachedToHouseGrid());
            String label1 = "Please choose the house grid where the room will be detached.";
            int firstOption = InputValidator.getIntRange(label1, 0, ctrl.getNumberOfGridLists()) - 1;

            if (ctrl.getListOfRoomsInACertainHouseGrid(firstOption).isEmpty()) {
                System.out.println("There are no rooms to detach." + "\n");

            } else {
                System.out.println(ctrl.getListOfRoomsInACertainHouseGrid(firstOption));
                String label2 = "Please choose the room to be detached from the chosen house grid.";
                int secondOption = InputValidator.getIntRange(label2, 0, ctrl.getListOfRoomsInACertainHouseGrid(firstOption).length()) - 1;
                if (ctrl.detachRoomFromGridList((ctrl.getHouseGridFromTheList(firstOption)), ctrl.getRoomFromTheListOfRoomByAPosition(secondOption))) {
                    System.out.println("The room has been detached from the grid.");
                } else {
                    System.out.println("Please select a valid room to detach.");
                }
            }
        }
    }


}
