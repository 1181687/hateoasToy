package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.EstimateEnergyOfWaterHeaterController;
import pt.ipp.isep.dei.project.model.House;

public class EstimateEnergyOfWaterHeater {
    private EstimateEnergyOfWaterHeaterController controller;

    public EstimateEnergyOfWaterHeater(House house) {
        this.controller = new EstimateEnergyOfWaterHeaterController(house);
    }

    public void run() {
        System.out.println("Currently, there are " + controller.getNumberOfWaterHeaters() + " electric water heaters in the house. " +
                "To estimate the energy used, you will now insert some required values for each one.\n");
        int devicePosition = 0;
        while (devicePosition != controller.getNumberOfWaterHeaters()) {
            String nameOfDevice = controller.getNameOfWaterHeater(devicePosition);
            System.out.println(nameOfDevice);
            String label0 = "Please insert the cold-water temperature to be used.";
            double coldWaterTemperature = InputValidator.getDouble(label0);
            controller.setColdWaterTemp(nameOfDevice, coldWaterTemperature);
            String label1 = "Please insert the volume of water to be heated.";
            double volumeOfWaterToHeat = InputValidator.getDouble(label1);
            controller.setVolumeOfWaterToHeat(nameOfDevice, volumeOfWaterToHeat);
            System.out.println("The energy used by this device will be "
                    + controller.getEnergyConsumptionOfAWaterHeater(nameOfDevice) + " kWh.\n");
            devicePosition++;
        }
        System.out.println("The total energy used by the electric water heaters in the house (based on the values " +
                "inserted) is " + controller.getTotalEnergyConsumptionOfAllElectricWaterHeaters() + " kWh.\n");
    }
}
