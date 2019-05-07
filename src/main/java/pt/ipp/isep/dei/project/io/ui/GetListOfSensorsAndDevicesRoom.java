package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.devices.DeviceDTO;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.util.List;

/**
 * US201: As an Administrator, i want to get a list of all devices in a room, so that i can configure them.
 * and US250: As an Administrator, i want to get a list of all sensors in a room, so that i can configure them.
 */


public class GetListOfSensorsAndDevicesRoom {

    private GetListOfSensorsAndDevicesRoomController controller;
    private List<RoomDTO> roomDTOList;
    private List<RoomSensorDTO> roomSensorDTOList;
    private List<DeviceDTO> deviceDTOList;

    public GetListOfSensorsAndDevicesRoom(RoomService roomService, RoomSensorService roomSensorService) {
        this.controller = new GetListOfSensorsAndDevicesRoomController(roomService, roomSensorService);
    }


    public void listOfSensors() {
        String label = "In which room do you want to see the list of sensors?";
        int chosenOption = chooseRoom(label);
        String roomId = this.roomDTOList.get(chosenOption).getRoomId();
        this.roomSensorDTOList = this.controller.getRoomSensorDTOList(roomId);

        if (this.roomSensorDTOList.isEmpty()) {
            System.out.println("The list is empty. Please, add a sensor.");

        } else {
            String listOfSensors = this.getSensorListToString();
            System.out.println("This is the list of existing sensors in the room" + "> ");
            System.out.println(listOfSensors);
        }
    }


    public void listOfDevices() {
        String label = "In which room do you want to see the list of devices?";
        int chosenOption = chooseRoom(label);


        RoomDTO chosenRoom = roomDTOList.get(chosenOption);


        String listOfDevices = this.getDeviceListToString(chosenRoom.getRoomId());
        if (this.getDeviceListSize() == 0) {
            System.out.println("The list is empty. Please, add a device.");

        } else {
            System.out.println("This is the list of existing devices in the room" + "> ");
        }
        System.out.println(listOfDevices);
    }


    // Necessary methods
    public String getRoomListToString() {
        this.roomDTOList = this.controller.getRoomDTOList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (RoomDTO roomDTO : this.roomDTOList) {
            content.append(number);
            content.append(" - ");
            content.append(roomDTO.getRoomId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getRoomListSize() {
        return this.roomDTOList.size();
    }

    public String getSensorListToString() {
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (RoomSensorDTO roomSensorDTO : this.roomSensorDTOList) {
            content.append(number);
            content.append(" - ID: ");
            content.append(roomSensorDTO.getId());
            content.append(", name: ");
            content.append(roomSensorDTO.getName());
            content.append("\n");
            number++;
        }
        return content.toString();
    }


    public String getDeviceListToString(String id) {
        this.deviceDTOList = this.controller.getDeviceDTOList(id);
        int number = 1;

        StringBuilder content = new StringBuilder();


        for (DeviceDTO deviceDTO : this.deviceDTOList) {
            content.append(number);
            content.append(" - Name: ");
            content.append(deviceDTO.getName());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getDeviceListSize() {
        return this.deviceDTOList.size();
    }

    private int chooseRoom(String label) {
        int chosenOption;

        do {
            System.out.println(this.getRoomListToString());
            chosenOption = InputValidator.getIntRange(label, 1, this.getRoomListSize());
        } while (chosenOption < 1 || chosenOption > this.getRoomListSize());

        return chosenOption;

    }
}
