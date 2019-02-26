package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionDevice {
    private GetEnergyConsumptionController controller;

    public GetEnergyConsumptionDevice(House house) {
        controller = new GetEnergyConsumptionController(house);
    }

    public void run() {
        String exit = "0 - Exit";
        String label0 = "Please choose a device:\n" + controller.getAllDevicesToString() + exit;
        int devicePosition = InputValidator.getIntRange(label0, 0, controller.getNumberOfDevices()) - 1;
        if (devicePosition == -1) {
            return;
        }
        controller.getDeviceByPosition(devicePosition);
        String label1 = "Please insert a start date (in the format YYYY-MM-DD).";
        LocalDateTime startDate = InputValidator.getStringDateTime(label1);
        String label2 = "\nPlease insert an end date (in the format YYYY-MM-DD).";
        LocalDateTime endDate = InputValidator.getStringDateTime(label2);
        System.out.println("\nThe energy consumption in the specified period was "
                + controller.getEnergyConsumptionInAnInterval(startDate, endDate) + " kWh.\n");
    }
}
