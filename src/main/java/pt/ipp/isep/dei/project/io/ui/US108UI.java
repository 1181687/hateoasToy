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
        System.out.println("Look at the following list and choose the number of the room you would like to edit.");
        System.out.println(ctrl.displayOfTheRoomList());
        Scanner read = new Scanner(System.in);
        int positionOfTheChosenRoom = read.nextInt() - 1;
        if(positionOfTheChosenRoom<0 || positionOfTheChosenRoom>ctrl.sizeOfTheList()-1){
            System.out.println("Invalid number. Please choose a number from the list above.");
            positionOfTheChosenRoom = read.nextInt() - 1;
        }
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
                int change = read.nextInt();
                ctrl.changeHouseFloorOfTheRoom(positionOfTheChosenRoom, change);
            }
            if (positionOfTheChosenFeature == 3) {
                System.out.println("Please insert the new room's height.");
                double change = read.nextDouble();
                ctrl.changeDimensionsOfTheRoom(positionOfTheChosenRoom, positionOfTheChosenFeature, change);
            }
            if (positionOfTheChosenFeature == 4) {
                System.out.println("Please insert the new room's length.");
                double change = read.nextDouble();
                ctrl.changeDimensionsOfTheRoom(positionOfTheChosenRoom, positionOfTheChosenFeature, change);
            }
            if (positionOfTheChosenFeature == 5) {
                System.out.println("Please insert the new room's width.");
                double change = read.nextDouble();
                ctrl.changeDimensionsOfTheRoom(positionOfTheChosenRoom, positionOfTheChosenFeature, change);
            }
            System.out.println("Would you like to change any other features in that room? (If no, please write down the zero numerical digit: 0. If yes, please choose the number of one feature you would like to change).");
            System.out.println(ctrl.displayOfTheChosenRoom(positionOfTheChosenRoom));
            positionOfTheChosenFeature = read.nextInt();
        }
        System.out.println("Here's your new room!");
        System.out.println(ctrl.displayOfTheChosenRoom(positionOfTheChosenRoom));
    }
}