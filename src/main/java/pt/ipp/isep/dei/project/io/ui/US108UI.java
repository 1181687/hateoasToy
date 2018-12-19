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
        Scanner read = new Scanner(System.in);
        int positionOfTheChosenRoom = read.nextInt() - 1;
        System.out.println("What features would you like to change in that room? (Choose the number of one feature you would like to change).");
        System.out.println(ctrl.displayOfTheChosenRoom(positionOfTheChosenRoom));
        int positionOfTheChosenFeature = read.nextInt();
        while (positionOfTheChosenFeature > 0 && positionOfTheChosenFeature < 6) {
            if (positionOfTheChosenFeature == 1) {
                System.out.println("Please insert the new room's name.");
                String change = read.nextLine();
                ctrl.changeNameOfTheRoom(positionOfTheChosenRoom, change);
            }
            if (positionOfTheChosenFeature == 2) {
                System.out.println("Please insert the new room's house floor.");
                Integer change = read.nextInt();
                ctrl.changeHouseFloorOfTheRoom(positionOfTheChosenRoom, change);
            }
            if (positionOfTheChosenFeature == 3) {
                System.out.println("Please insert the new room's height.");
                Double change = read.nextDouble();
                ctrl.changeDimensionsOfTheRoom(positionOfTheChosenRoom, positionOfTheChosenFeature, change);
            }
            if (positionOfTheChosenFeature == 4) {
                System.out.println("Please insert the new room's length.");
                Double change = read.nextDouble();
                ctrl.changeDimensionsOfTheRoom(positionOfTheChosenRoom, positionOfTheChosenFeature, change);
            }
            if (positionOfTheChosenFeature == 5) {
                System.out.println("Please insert the new room's width.");
                Double change = read.nextDouble();
                ctrl.changeDimensionsOfTheRoom(positionOfTheChosenRoom, positionOfTheChosenFeature, change);
            }
            System.out.println("Would you like to change any other features in that room? (If no, please write down: 0 . If yes, please choose the number of one feature you would like to change).");
            positionOfTheChosenFeature = read.nextInt();
        }
        if (positionOfTheChosenRoom>=6 || positionOfTheChosenRoom<0){
            System.out.println("Please choose a number between 1 and 5");
        }
        System.out.println("Here's your new room!");
        System.out.println(ctrl.displayOfTheChosenRoom(positionOfTheChosenRoom));
    }
}