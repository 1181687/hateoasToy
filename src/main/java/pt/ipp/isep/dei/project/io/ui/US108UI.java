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
        System.out.println("Look at the following list and choose the number of the room you would like to edit.");
        System.out.println(ctrl.displayOfTheRoomList());
        Scanner ler = new Scanner(System.in);
        System.out.println("What features would you like to change in that room? (Choose the number of that feature).");
        int positionOfTheChosenOption = ler.nextInt() - 1;
        System.out.println(ctrl.displayOfTheChosenRoom(positionOfTheChosenOption));
    }
}