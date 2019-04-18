package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.RoomService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


/**
 * US605 As a Regular User, I want to get the current temperature in a room, in order to check
 * if it meets my personal comfort requirements.
 */

public class GetCurrentTemperatureRoom {

    private GetCurrentAndMaxTempRoomController controller;
    private List<RoomDTO> roomDTOS;

    /**
     * Constructor.
     * @param roomService roomService to be used
     */
    public GetCurrentTemperatureRoom(RoomService roomService) {
        this.controller = new GetCurrentAndMaxTempRoomController(roomService);
        this.roomDTOS = controller.getListRoomDTo();
    }

    /**
     * outputs the latest value of temperature and its date, of the choosen room
     *
     * @param roomId given String room name
     * @param temp     given double temperature
     * @param localDateTime given localDateTime
     */
    public void displayResults(String roomId, double temp, LocalDateTime localDateTime) {
        StringBuilder content = new StringBuilder();
        content.append("The latest temperature of the room ");
        content.append(roomId);
        content.append(" is ");
        content.append(temp);
        content.append(" Celsius, and was registered at ");
        content.append(localDateTime.truncatedTo(ChronoUnit.SECONDS).toString());
        content.append(".\n");
        System.out.println(content.toString());
    }

    /**
     * method that list to string the rooms available, so he can choose one
     * to get the current temperature.
     */
    public String getRoomListToString() {
        StringBuilder list = new StringBuilder();

        if (roomDTOS.isEmpty()) {
            return "\nThere are no rooms in the house. Please create a new room.";
        }
        int listOrderByNumber = 1;
        for (RoomDTO roomDTO : roomDTOS) {
            list.append(listOrderByNumber);
            list.append(" - Id: ");
            list.append(roomDTO.getId());
            list.append(", Description: ");
            list.append(roomDTO.getDescription());
            listOrderByNumber++;
        }
        return list.toString();
    }

    public void run() {

        if (controller.isListofRoomEmpty()) {
            System.out.println("There are no rooms available in the house.\n");
            return;
        }
        System.out.println(this.getRoomListToString());

        String label0 = "Choose the room you want to get the current temperature:";
        int option = InputValidator.getIntRange(label0, 1, roomDTOS.size() - 1);
        if (option == -1) {
            return;
        }

        String roomId = roomDTOS.get(option).getId();
        controller.newChoosenRoomId(roomId);

        if (controller.isRoomWithoutTemperatureSensor()) {
            System.out.println("There are no temperature sensor available in the choosen room.\n");
            return;
        }

        controller.latestTemperatureReading();
        double tempValue = controller.getvalue();
        LocalDateTime localDateTime = controller.getLocalDateTime();

        if (controller.isLatestTemperatureReadingNull()) {
            System.out.println("There are no temperature values available.");
            return;
        }

        displayResults(roomId, tempValue, localDateTime);
    }
}