package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.House;

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
        String label = "In which room do you want to see the list of sensors?";
        int choosenOption = -1;

        do {
            System.out.println(controller.getRoomListContent());
            choosenOption = InputValidator.getIntRange(label,1, controller.roomListSize());
        }

        while (choosenOption < 1 || choosenOption > controller.roomListSize());

        String roomChoosed = controller.getRoomOfTheRoomList(choosenOption - 1);
        String listOfSensors = controller.getSensorsListContent(choosenOption - 1);

        if (controller.checkIfListIsEmpty(choosenOption - 1)) {
            System.out.println("The list is empty. Please, add a sensor.");

        } else {
            System.out.println("This is the list of existing sensors in the room" + roomChoosed + ":");
        }
        System.out.println(listOfSensors);
    }


    public void run2() {
        String label2 = "In which room do you want to see the list of devices?";
        int chosenOption = -1;

        do {
            System.out.println(controller.getRoomListContent());
            chosenOption = InputValidator.getIntRange(label2, 1, controller.roomListSize());
        }

        while (chosenOption < 1 || chosenOption > controller.roomListSize());

        String choosenRoom = controller.getRoomOfTheRoomList(chosenOption - 1);
        String listOfDevices = controller.getDeviceListContent(chosenOption - 1);

        if (controller.checkIfDeviceListIsEmpty(chosenOption - 1)) {
            System.out.println("The list is empty. Please, add a device.");

        } else {
            System.out.println("This is the list of existing devices in the room" + choosenRoom + ":");
        }
        System.out.println(listOfDevices);

    }
}
