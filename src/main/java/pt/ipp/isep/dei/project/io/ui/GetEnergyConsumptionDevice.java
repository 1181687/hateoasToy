package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDeviceController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionDevice {
    private GetEnergyConsumptionDeviceController mController;

    public GetEnergyConsumptionDevice(House house) {
        mController = new GetEnergyConsumptionDeviceController(house);
    }

    public void run() {
        String exit = "0 - Exit";
        String label0 = "Please choose a device:\n" + mController.getAllDevicesToString() + exit;
        int devicePosition = InputValidator.getIntRange(label0, 0, mController.getNumberOfDevices()) - 1;
        if (devicePosition == -1) {
            return;
        }
        mController.setDevice(devicePosition);
        String label1 = "Please insert a start date (in the format YYYY-MM-DD).";
        LocalDateTime startDate = InputValidator.getStringDateTime(label1);
        String label2 = "\nPlease insert an end date (in the format YYYY-MM-DD).";
        LocalDateTime endDate = InputValidator.getStringDateTime(label2);
        System.out.println("\nThe energy consumption in the specified period was "
                + mController.getEnergyConsumptionInAnInterval(startDate, endDate) + " kWh.\n");
    }
}
