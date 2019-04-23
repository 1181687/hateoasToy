package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.List;

/**
 * US201: As an Administrator, i want to get a list of all devices in a room, so that i can configure them.
 * and US250: As an Administrator, i want to get a list of all sensors in a room, so that i can configure them.
 */
public class GetListOfSensorsAndDevicesRoom {

    private GetListOfSensorsAndDevicesRoomController controller;
    private List<RoomDTO> roomDTOList;
    private List<RoomSensorDTO> roomSensorDTOList;

    public GetListOfSensorsAndDevicesRoom(RoomAggregateService roomAggregateService) {
        this.controller = new GetListOfSensorsAndDevicesRoomController(roomAggregateService);
    }


    public void runListOfSensors() {
        String label = "In which room do you want to see the list of sensors?";
        int choosenOption;

        do {
            System.out.println(this.getRoomListToString());
            choosenOption = InputValidator.getIntRange(label, 1, this.getRoomListSize());
        }

        while (choosenOption < 1 || choosenOption > this.getRoomListSize());

        RoomDTO roomChoosed = roomDTOList.get(choosenOption - 1);

        String listOfSensors = this.getSensorListToString();
        if (this.getRoomSensorListSize() == 0) {
            System.out.println("The list is empty. Please, add a sensor.");

        } else {
            System.out.println("This is the list of existing sensors in the room" + roomChoosed + ":");
        }
        System.out.println(listOfSensors);
    }


    /*public void listOfDevices() {
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

    }*/


    public String getRoomListToString() {
        this.roomDTOList = this.controller.getRoomDTOList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (RoomDTO roomDTO : this.roomDTOList) {
            content.append(number);
            content.append(" - ");
            content.append(roomDTO.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getRoomListSize() {
        return this.roomDTOList.size();
    }

    public String getSensorListToString() {
        this.roomSensorDTOList = this.controller.getRoomSensorDTOList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (RoomSensorDTO roomSensorDTO : this.roomSensorDTOList) {
            content.append(number);
            content.append(" - ");
            content.append(roomSensorDTO.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getRoomSensorListSize() {
        return this.roomSensorDTOList.size();
    }
}
