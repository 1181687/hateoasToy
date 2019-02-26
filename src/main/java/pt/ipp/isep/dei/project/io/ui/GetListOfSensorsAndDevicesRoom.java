package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.House;

/**
 * US201: As an Administrator, i want to get a list of all devices in a room, so that i can configure them.
 * and US250: As an Administrator, i want to get a list of all sensors in a room, so that i can configure them.
 */
public class GetListOfSensorsAndDevicesRoom {

    private GetListOfSensorsAndDevicesRoomController controller;

    public GetListOfSensorsAndDevicesRoom(House house) {
        this.controller = new GetListOfSensorsAndDevicesRoomController(house);
    }

    public void listOfSensors() {
        String label = "In which room do you want to see the list of sensors?";
        int choosenOption = -1;

        do {
            System.out.println(controller.getRoomListContent());
            choosenOption = InputValidator.getIntRange(label, 1, controller.roomListSize());
        }

        while (choosenOption < 1 || choosenOption > controller.roomListSize());

        String roomChoosed = controller.getRoomNameByPosition(choosenOption - 1);
        String listOfSensors = controller.getSensorsListContent(choosenOption - 1);

        if (controller.isSensorListEmpty(choosenOption - 1)) {
            System.out.println("The list is empty. Please, add a sensor.");

        } else {
            System.out.println("This is the list of existing sensors in the room" + roomChoosed + ":");
        }
        System.out.println(listOfSensors);
    }


    public void listOfDevices() {
        StringBuilder content = new StringBuilder();

        content.append(controller.getRoomListContent());
        content.append("0- Exit\n");
        String listContentRoom = content.toString();

        String label2 = "\nIn which room do you want to see the list of devices?\n" + listContentRoom;
        int maxPosition = controller.roomListSize();
        int chosenOption = InputValidator.getIntRange(label2, 0, maxPosition);
        if (chosenOption == 0) {
            return;
        }

        content.delete(0, content.length());

        int positionRoom = chosenOption - 1;
        String choosenRoom = controller.getRoomNameByPosition(positionRoom);
        String listOfDevices = controller.getDeviceListContent(positionRoom);

        if (controller.isDeviceListEmpty(positionRoom)) {
            System.out.println("\nThe list is empty.\n");
            return;
        }

        content.append("\nThis is the list of existing devices in the room");
        content.append(choosenRoom);
        content.append(":\n\n");
        content.append(listOfDevices);
        System.out.println(content.toString());

    }
}
