package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.List;

/**
 * US253 As an Administrator, I want to add a new sensor to a room from the list of available
 * sensor types, in order to configure it.
 */

public class AddSensorToRoom {

    private AddSensorToRoomController controller;

    public AddSensorToRoom(HouseService houseService) {
        this.controller = new AddSensorToRoomController(houseService);
    }

    public void run() {

        List<RoomDTO> getRoomListDTO = controller.getRoomDTOList();

        if (getRoomListDTO.isEmpty()) {
            System.out.println("\nThere are no rooms in the house. Please create a new room.");
            return;
        }
        String label1 = "Please select which type of sensor you want to create: \n" + getRoomDTOListToString();
        int positionOfSensorType = InputValidator.getIntRange(label1, 1, getRoomListDTO.size() - 1);

    }

    public String getRoomDTOListToString() {
        List<RoomDTO> getRoomListDTO = controller.getRoomDTOList();
        StringBuilder content = new StringBuilder();
        int listOrderByNumber = 1;
        for (RoomDTO roomDTO : getRoomListDTO) {
            content.append(listOrderByNumber);
            content.append(" - Id: ");
            content.append(roomDTO.getId());
            content.append(", Description: ");
            content.append(roomDTO.getDescription());
            listOrderByNumber++;
        }
        return content.toString();
    }

    /*private AddSensorToRoomController controller;

    public AddSensorToRoom (RoomSensorService roomSensorService){
    this.controller = new AddSensorToRoomController(roomSensorService);
}

    public AddSensorToRoom(House house, RoomList roomList, SensorTypeList listSensorsType) {
        controller = new AddSensorToRoomController(listSensorsType, roomList, house);
    }

    public void run() {
        controller.
    }

       /* if (controller.isRoomListEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room");
        } else if (controller.isSensorTypeListEmpty()) {
            System.out.println("There are no sensor types created. Please create one.");
        } else {
            System.out.println("To which room do you want to add a sensor?");
            System.out.println(controller.getRoomListContent());
            int positionOfTheRoom = read.nextInt() - 1;
            read.nextLine();
            controller.getRoomByIndex(positionOfTheRoom);

            System.out.println("What's the name of the new sensor?");
            String nameOfSensor = read.nextLine();
            String idOfSensor = read.nextLine();
            String units = read.nextLine();

            System.out.println("Please, select a sensor type");
            System.out.println(controller.displayListOfSensorsType());
            int positionOfTheSensorType = read.nextInt() - 1;
            read.nextLine();
            controller.getSensorTypeByIndex(positionOfTheSensorType);

            controller.getLocationOfTheHouse();

            if (controller.createAndAddSensorToTheList(idOfSensor, nameOfSensor, units)) {
                System.out.println("A sensor was added to the room.");
            } else {
                System.out.println("sensor was not created.");
            }

        }

    }*/
}
