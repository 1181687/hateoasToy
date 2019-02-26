package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionController;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionRoomController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfRoom {
    private GetEnergyConsumptionController controller;

    public GetEnergyConsumptionOfRoom (House house) {
        controller = new GetEnergyConsumptionController(house);
    }

    public void run() {
        if (controller.roomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please add one.");
            return;
        }
        String label1 = "Please choose a number of the list below that matches the room you want to select:\n" + controller.getRoomListToString();
        int chosenRoom = InputValidator.getIntRange(label1, 0, controller.getRoomListSize()) - 1;
        controller.getRoomByPosition(chosenRoom);
        while (controller.isDeviceListEmpty()) {
            System.out.println("There are no devices in this room, so it is not possible to " +
                    "calculate its energy consumption. Please choose another room.\n");
            chosenRoom = InputValidator.getIntRange(label1, 0, controller.getRoomListSize()) - 1;
            controller.getRoomByPosition(chosenRoom);
        }
        String label2 = "Please insert the initial date/hour of the period you want to consider for the calculations in the following format: yyyy-MM-dd HH:mm. ";
        LocalDateTime initialDate = InputValidator.getStringDateTime(label2);
        String label3 = "Please insert the final date/hour of the period you want to consider for the calculations in the following format: yyyy-MM-dd HH:mm. ";
        LocalDateTime finalDate = InputValidator.getStringDateTime(label3);
        System.out.println("The energy consumption of room " + controller.getRoomName()
                + " is " + Utils.round(controller.getEnergyConsumptionInAnInterval(initialDate, finalDate), 2) + " kWh.\n");
    }
}
