package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US149Controller;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

public class US149UI {
    private US149Controller ctrl;

    public US149UI(HouseGridList gridList, RoomList roomList) {
        this.ctrl = new US149Controller(gridList, roomList);
    }

    public void run() {
        if (ctrl.getNumberOfGridLists() == 0) {
            System.out.println("There are no house grids in your house. Please insert a new house grid.");
            System.out.println();
        } else {
            System.out.println("Please choose the house grid where the room will be detached.");
            System.out.println(ctrl.getListOfHouseGridsAttachedToHouseGrid());
            Scanner ler = new Scanner(System.in);
            int firstOption = ler.nextInt() - 1;

            if (ctrl.getListOfRoomsInACertainHouseGrid(firstOption).isEmpty()) {
                System.out.println("There are no rooms to detach." + "\n");

            } else {
                System.out.println("Please choose the room to be detached from the chosen house grid.");
                System.out.println(ctrl.getListOfRoomsInACertainHouseGrid(firstOption));
                int secondOption = ler.nextInt() - 1;
                if (ctrl.detachRoomFromGridList((ctrl.getHouseGridFromTheList(firstOption)), ctrl.getRoomFromTheListOfRoomByAPosition(secondOption))) {
                    System.out.println("The room has been detached from the grid.");
                } else {
                    System.out.println("Please select a valid room to detach.");
                }
            }
        }
    }


}
