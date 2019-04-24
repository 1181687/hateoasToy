package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * US610 As a Regular User, I want to get the maximum temperature in a room in a given day,
 * in order to check if heating/cooling in that room was effective.
 */

public class GetMaxTemperatureRoom {


    private GetCurrentAndMaxTempRoomController controller;
    private List<RoomDTO> roomDTOS;


    public GetMaxTemperatureRoom(RoomAggregateService roomAggregateService) {
        this.controller = new GetCurrentAndMaxTempRoomController(roomAggregateService);
        this.roomDTOS = controller.getListRoomDTo();
    }

    /**
     * outputs the maximum value of temperature on a given day, of the choosen room
     *
     * @param roomId        given String room name
     * @param temp          given double temperature
     * @param localDateTime given localDateTime
     */
    public void displayResults(String roomId, double temp, LocalDateTime localDateTime) {
        StringBuilder content = new StringBuilder();
        content.append("The maximum temperature of the room ");
        content.append(roomId);
        content.append(" in the date ");
        content.append(localDateTime.toLocalDate());
        //content.append(localDateTime.truncatedTo(ChronoUnit.SECONDS).toString());
        content.append(" is ");
        content.append(temp);
        content.append(" Celsius, and was registered at ");
        // content.append(localDateTime.toLocalTime().toString());
        content.append(localDateTime.toString().substring(11));
        content.append("h.\n");
        System.out.println(content.toString());
    }

    /**
     * method that list to string the rooms available, so he can choose one
     * to get the maximum temperature.
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
            list.append("\n");
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

        String label0 = "Choose the room you want to get the maximum temperature:";
        int option = InputValidator.getIntRange(label0, 1, roomDTOS.size()) - 1;
        if (option == -1) {
            return;
        }

        String roomId = roomDTOS.get(option).getId();
        controller.newChoosenRoomId(roomId);

        if (controller.isRoomWithoutTemperatureSensor()) {
            System.out.println("There are no temperature sensor available in the choosen room.\n");
            return;
        }
        controller.setRoomSensorId();

        String label1 = "Please insert the date when you want to get the maximum temperature (yyyy-MM-dd):";
        LocalDate date = InputValidator.getStringDate(label1);

        controller.setMaximumTemperatureInAroomInADay(date);
        double tempMaxValue = controller.getValueMaximumTemperature();
        LocalDateTime localDateTime = controller.getLocalDateTimeMaximumTemperature();

        if (controller.isListOfTemperatureReadingsByDayEmpty(date) || Double.isNaN(tempMaxValue) || (Objects.isNull(localDateTime))) {
            System.out.println("There are no temperature values available.");
            return;
        }

        displayResults(roomId, tempMaxValue, localDateTime);
    }

}

      /*  System.out.println(controller.getRoomListContent());
        if (controller.getRoomListContent().isEmpty()) {
            System.out.println("There are no rooms available\n");
            return;
        }

        String label0 = "Choose the room you want to get the maximum temperature";
        int option = InputValidator.getIntRange(label0, 1, controller.getRoomListSize());
        String roomName = controller.getRoomNameByPos(option - 1);

        String label1 = "Please insert the date when you want to get the maximum temperature (yyyy-MM-dd):";
        LocalDate dateLD = InputValidator.getStringDate(label1);

        double temp = controller.getMaximumTemperatureOfRoomInGivenDay(roomName, controller.getType(), dateLD);
        if (Double.isNaN(temp)) {
            System.out.println("There are no temperature values available");
            return;
        }

        System.out.println("The maximum temperature is " + temp + "ºC");*/