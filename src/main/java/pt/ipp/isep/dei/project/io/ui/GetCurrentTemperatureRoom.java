package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.util.List;
import java.util.Objects;


/**
 * US605 As a Regular User, I want to get the current temperature in a room, in order to check
 * if it meets my personal comfort requirements.
 */

public class GetCurrentTemperatureRoom {

    private GetCurrentAndMaxTempRoomController controller;
    private List<RoomDTO> allRoomsDTOList;

    public GetCurrentTemperatureRoom(SensorTypeId typeId, RoomSensorService sensorService, RoomService rService) {
        this.controller = new GetCurrentAndMaxTempRoomController(typeId, sensorService,rService);
        this.allRoomsDTOList=controller.getRoomDTOList();
    }
    /**
     * outputs the latest temperature with date, of the room
     *
     * @param roomName given String room name
     * @param temp     given double temperature
     * @param dateTime given string date and time
     */
    public void displayResults(String roomName, double temp, String dateTime) {
        StringBuilder content = new StringBuilder();
        content.append("The latest temperature of the room ");
        content.append(roomName);
        content.append(" is ");
        content.append(temp);
        content.append(" Celsius, and was registered at ");
        content.append(dateTime);
        content.append(".\n");
        System.out.println(content.toString());
    }

    public void getRoomDTOListToString(){
        StringBuilder roomListContent = new StringBuilder();
        int numberOfRoom = 1;
        for (RoomDTO roomDto:allRoomsDTOList) {
            roomListContent.append (numberOfRoom+" - Id: "+roomDto.getRoomId());
            numberOfRoom++;
        }
        System.out.println(roomListContent.toString());
    }

    /**
     * method that displays the rooms available to the user, so he can choose one
     * to get the current temperature.
     */
    public void run() {
       if (allRoomsDTOList.isEmpty()) {
            System.out.println("There are no rooms available.\n");
            return;
        }
        getRoomDTOListToString();
        String label0 = "Please, choose the room you would like to get the current temperature.";
        int option = InputValidator.getIntRange(label0, 1, allRoomsDTOList.size())-1;
        RoomDTO selectedRoom = allRoomsDTOList.get(option);
        String roomDTOId = selectedRoom.getRoomId();
        ReadingDTO temp = controller.getLatestMeasurementOfRoomSensor(roomDTOId);
        if (Objects.isNull(temp)) {
            System.out.println("There are no temperature values available.");
            return;
        }
        displayResults(roomDTOId, temp.getValue(), temp.getDateTime().toString());
    }
}