package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfSensorsRoomController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.List;
import java.util.Scanner;

/**
 * US250: As an Administrator, i want to get a list of all sensors in a room, so that i can configure them.
 */
public class GetListOfSensorsRoom {

    private GetListOfSensorsRoomController controller;

    public GetListOfSensorsRoom (House house) {
        this.controller = new GetListOfSensorsRoomController(house);
    }

    public void run() {
        System.out.println("In which room do you want to see the list of sensors?");
        RoomList listOfRooms = controller.getListOfRooms();
        Scanner read = new Scanner(System.in);
        int optionChoosed = -1;
        int position = 0;

        do {
            System.out.println(controller.getRoomListContent());

            optionChoosed = read.nextInt();
        }

        while (optionChoosed < 1 || optionChoosed > listOfRooms.listSize());

        String roomChoosed = controller.getRoomOfTheRoomList(optionChoosed-1);

        String listOfSensors = controller.getSensorsListContent(optionChoosed-1);

        if (controller.checkIfListIsEmpty(position)) {
            System.out.println("The list is empty. Please, add a sensor.");

        } else {
            System.out.println("This is the list of existing sensors in the room" + roomChoosed + ":");
        }
        System.out.println(listOfSensors);
            //System.out.println(controller.getSensorsListContent(position));

        }
}
