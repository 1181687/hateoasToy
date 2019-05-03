package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;


/**
 * US605 As a Regular User, I want to get the current temperature in a room, in order to check
 * if it meets my personal comfort requirements.
 */

public class GetCurrentTemperatureRoom {

    private GetCurrentAndMaxTempRoomController controller;

    /*/**
     * constructor that receives a House and a SensorType
     *
     * @param house          House
     * @param sensorTypeId   SensorType
     */
    /*public GetCurrentTemperatureRoom(HouseService houseService, SensorTypeId sensorTypeId) {
        this.controller = new GetCurrentAndMaxTempRoomController(houseService, sensorTypeId);
    }
    /**
     * outputs the latest temperature with date, of the room
     *
     * @param roomName given String room name
     * @param temp     given double temperature
     * @param dateTime given string date and time
     */
    /*public void displayResults(String roomName, double temp, String dateTime) {
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

    /**
     * method that displays the rooms available to the user, so he can choose one
     * to get the current temperature.
     */
    /*public void run() {

       if (controller.getRoomListContent().isEmpty()) {
            System.out.println("There are no rooms available\n");
            return;
        }
        System.out.println(controller.getRoomListContent());

        String label0 = "Choose the room you want to get the current temperature";
        int option = InputValidator.getIntRange(label0, 1, controller.getRoomListSize());
        String roomName = controller.getRoomNameByPos(option - 1);
        Reading temp = controller.getLatestMeasurementByRoomName(roomName);

        if (Objects.isNull(temp)) {
            System.out.println("There are no temperature values available");
            return;
        }
        displayResults(roomName, temp.getValue(), temp.getDateTime().toString());
    }*/
}