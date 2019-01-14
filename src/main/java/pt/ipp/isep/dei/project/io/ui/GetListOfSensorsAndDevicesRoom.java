package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

/**
 * US201: As an Administrator, i want to get a list of all devices in a room, so that i can configure them.
 *  and US250: As an Administrator, i want to get a list of all sensors in a room, so that i can configure them.
 */
public class GetListOfSensorsAndDevicesRoom {

    private GetListOfSensorsAndDevicesRoomController controller;

    public GetListOfSensorsAndDevicesRoom(House house) {
        this.controller = new GetListOfSensorsAndDevicesRoomController(house);
    }

    public void run1() {
        System.out.println("In which room do you want to see the list of sensors?");
        RoomList listOfRooms = controller.getListOfRooms();
        Scanner read = new Scanner(System.in);
        int choosenOption = -1;

        do {
            System.out.println(controller.getRoomListContent());

            choosenOption = read.nextInt();
        }

        while (choosenOption < 1 || choosenOption > listOfRooms.listSize());

        String roomChoosed = controller.getRoomOfTheRoomList(choosenOption - 1);

        String listOfSensors = controller.getSensorsListContent(choosenOption - 1);

        if (controller.checkIfListIsEmpty(choosenOption - 1)) {
            System.out.println("The list is empty. Please, add a sensor.");

        } else {
            System.out.println("This is the list of existing sensors in the room" + roomChoosed + ":");
        }
        System.out.println(listOfSensors);
        //System.out.println(controller.getSensorsListContent(position));

    }


    public void run2() {
        System.out.println("In which room do you want to see the list of devices?");
        RoomList listOfRooms = controller.getListOfRooms();
        Scanner read = new Scanner(System.in);
        int optionChoosed = -1;

        do {
            System.out.println(controller.getRoomListContent());

            optionChoosed = read.nextInt();
        }

        while (optionChoosed < 1 || optionChoosed > listOfRooms.listSize());

        String roomChoosed = controller.getRoomOfTheRoomList(optionChoosed - 1);

        String listOfDevices = controller.getDeviceListContent(optionChoosed - 1);

        if (controller.checkIfDeviceListIsEmpty(optionChoosed - 1)) {
            System.out.println("The list is empty. Please, add a device.");

        } else {
            System.out.println("This is the list of existing devices in the room" + roomChoosed + ":");
        }
        System.out.println(listOfDevices);

    }
}
