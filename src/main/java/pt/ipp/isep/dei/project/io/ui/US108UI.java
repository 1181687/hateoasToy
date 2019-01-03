package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US108Controller;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

public class US108UI {

    private US108Controller ctrl;

    public US108UI(RoomList list) {
        this.ctrl = new US108Controller(list);
    }

    public void run() {
        if (ctrl.checkIfListIsEmpty()) {
            System.out.println("The list is empty. Please add a room.");
            return;
        }
        System.out.println("This is the list of existing rooms.");
        System.out.println(ctrl.displayOfTheRoomList());
    }
}