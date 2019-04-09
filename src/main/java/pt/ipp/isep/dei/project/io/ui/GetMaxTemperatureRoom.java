package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

/** US610 As a Regular User, I want to get the maximum temperature in a room in a given day,
 in order to check if heating/cooling in that room was effective.*/

public class GetMaxTemperatureRoom {


    private GetCurrentAndMaxTempRoomController controller;

    /**
     * constructor that receives a House and a SensorTypeList
     *
     * @param house      House
     * @param sensorType SensorType
     */
    public GetMaxTemperatureRoom(House house, SensorType sensorType) {
        this.controller = new GetCurrentAndMaxTempRoomController(house, sensorType);
    }

    public void run() {

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
    }
}
