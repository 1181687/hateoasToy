package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeDTO;
import pt.ipp.isep.dei.project.services.HouseService;

import java.time.LocalDate;
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
        List<SensorTypeDTO> getSensorTypeListDTO = controller.getSensorTypeList();
        if (getRoomListDTO.isEmpty()) {
            System.out.println("\nThere are no rooms in the house. Please add one.");
            return;
        }
        if (getSensorTypeListDTO.isEmpty()) {
            System.out.println("\nThere are no sensor types available. Please add one.");
            return;
        }
        String label1 = "Please select which type of sensor you would like to create: \n" + getSensorTypeDTOListToString();
        int positionOfSensorType = InputValidator.getIntRange(label1, 1, getSensorTypeListDTO.size() - 1);
        SensorTypeDTO selectedSensorType = getSensorTypeListDTO.get(positionOfSensorType - 1);

        RoomSensorDTO roomSensorDTO = new RoomSensorDTO();

        roomSensorDTO.setSensorTypeId(selectedSensorType.getSensorType());

        String label2 = "Please type down the id of the new sensor.";
        String sensorId = InputValidator.getString(label2);
        roomSensorDTO.setId(sensorId);

        String label3 = "Please insert the name of the new sensor.";
        String sensorName = InputValidator.getString(label3);
        roomSensorDTO.setName(sensorName);

        roomSensorDTO.setStartingDate(LocalDate.now());

        String label4 = "Please insert the unit of measurement of the sensor you're now creating.";
        String sensorUnits = InputValidator.getString(label4);
        roomSensorDTO.setUnits(sensorUnits);

        String label5 = "To which room would you like to add this sensor to?: \n" + getRoomListDTO;
        int positionOfRoom = InputValidator.getIntRange(label5, 1, getRoomListDTO.size() - 1);
        RoomDTO roomWhereSensorIs = getRoomListDTO.get(positionOfRoom);
        roomSensorDTO.setRoomId(roomWhereSensorIs.getId());


       /* if (controller.addSensorToRoom(roomSensorDTO)) {
            System.out.println("Success! A new sensor was created with the following features:\n"+getSensorFeatures(roomSensorDTO));
        } else {
            System.out.println("This sensor already exists in this room.");
        }*/
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

    public String getSensorTypeDTOListToString() {
        List<SensorTypeDTO> sensorTypeListDTO = controller.getSensorTypeList();
        StringBuilder content = new StringBuilder();
        int listOrderByNumber = 1;
        for (SensorTypeDTO sensorTypeDTO : sensorTypeListDTO) {
            content.append(listOrderByNumber);
            content.append(" - Id: ");
            content.append(sensorTypeDTO.getSensorType());
            listOrderByNumber++;
        }
        return content.toString();
    }

    public String getSensorFeatures(RoomSensorDTO roomSensorDTO) {
        StringBuilder content = new StringBuilder();
        content.append(roomSensorDTO.getId() + "\n");
        content.append(roomSensorDTO.getName() + "\n");
        content.append(roomSensorDTO.getSensorTypeId() + "\n");
        content.append(roomSensorDTO.getStartingDate() + "\n");
        content.append(roomSensorDTO.isActive() + "\n");
        content.append(roomSensorDTO.getUnits() + "\n");
        content.append(roomSensorDTO.getRoomId() + "\n");
        return content.toString();
    }
}
