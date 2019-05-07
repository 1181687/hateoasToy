package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeDTO;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.List;

/**
 * US253 As an Administrator, I want to add a new sensor to a room from the list of available
 * sensor types, in order to configure it.
 */

public class AddSensorToRoom {

    private final AddSensorToRoomController controller;
    private final List<RoomDTO> roomDTOList;
    private final List<SensorTypeDTO> sensorTypeDTOS;

    public AddSensorToRoom(SensorTypeService sensorTypeService, RoomService roomService, RoomSensorService roomSensorService) {
        controller = new AddSensorToRoomController(sensorTypeService, roomService, roomSensorService);
        roomDTOList = controller.getRoomListContent();
        sensorTypeDTOS = controller.getSensorTypes();
    }

    public void run() {

        if (roomDTOList.isEmpty()) {
            System.out.println("There are no rooms in the housegrid. Please create a room");
        } else if (sensorTypeDTOS.isEmpty()) {
            System.out.println("There are no sensor types created. Please create one.");
        } else {
            // Select Room to add Sensor
            System.out.println(getRoomToList());
            int positionOfTheRoom = InputValidator.getIntRange("To which room do you want to add a sensor?", 1, roomDTOList.size()) - 1;
            RoomDTO roomDTO = roomDTOList.get(positionOfTheRoom);
            // Select SensorType
            System.out.println("Please, select a sensor type");
            System.out.println(getSensorTypeList());
            int positionOfTheSensorType = InputValidator.getIntRange("Please, select a sensor type", 1, sensorTypeDTOS.size()) - 1;
            SensorTypeDTO sensorTypeDTO = sensorTypeDTOS.get(positionOfTheSensorType);

            String sensorId = InputValidator.getString("What's the Id of the new sensor?\n");
            String nameOfSensor = InputValidator.getString("What's the name of the new sensor?\n");
            String sensorUnits = InputValidator.getString("What's the units of the new sensor?\n");
            RoomSensorDTO roomSensorDTO = RoomSensorMapper.newRoomSensorDTO();
            roomSensorDTO.setId(sensorId);
            roomSensorDTO.setName(nameOfSensor);
            roomSensorDTO.setSensorType(sensorTypeDTO.getSensorType());
            roomSensorDTO.setUnits(sensorUnits);
            roomSensorDTO.setRoomId(roomDTO.getRoomId());

            if (controller.createAndAddSensorToTheList(roomSensorDTO)) {
                System.out.println("A sensor was added to the room.");
            } else {
                System.out.println("sensor was not created.");
            }
        }

    }

    /**
     * Method that generates a list of RoomDTO.
     *
     * @return String representing the list.
     */
    private String getRoomToList() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (RoomDTO roomDTO : roomDTOList) {
            content.append(numberInTheList + "-" + " Name: " + roomDTO.getRoomId() + ", House Floor: " + roomDTO.getHouseFloor() + " , " + roomDTO.getDescription() + "\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * Method that generates a list of GeographicalAreaDTO.
     *
     * @return String representing the list.
     */
    private String getSensorTypeList() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (SensorTypeDTO sensorTypeDTO : sensorTypeDTOS) {
            content.append(numberInTheList + " - " + sensorTypeDTO.getSensorType() + "\n");
            numberInTheList++;
        }
        return content.toString();
    }

}
