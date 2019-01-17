package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.EstimateEnergyOfWaterHeaterController;
import pt.ipp.isep.dei.project.model.House;

public class EstimateEnergyOfWaterHeater {
    private EstimateEnergyOfWaterHeaterController mCtrl;

    public EstimateEnergyOfWaterHeater(House house) {
        this.mCtrl = new EstimateEnergyOfWaterHeaterController(house);
    }

    public void run() {
        System.out.println("Currently, there are " + mCtrl.getNumberOfWaterHeaters() + " electric water heaters in the house. " +
                "To estimate the energy used, you will now insert some required values for each one.\n");
        int devicePosition = 0;
        while (devicePosition != mCtrl.getNumberOfWaterHeaters()) {
            System.out.println("Electric Water Heater " + devicePosition);
            String label0 = "Please inserted the cold-water temperature to be used.";
            double coldWaterTemperature = InputValidator.getDouble(label0);
            mCtrl.setColdWaterTemp(devicePosition, coldWaterTemperature);
            String label1 = "Please inserted the volume of water to be heated.";
            double volumeOfWaterToHeat = InputValidator.getDouble(label1);
            mCtrl.setVolumeOfWaterToHeat(devicePosition, volumeOfWaterToHeat);
            System.out.println("This device will use " + mCtrl.getEnergyConsumptionOfADevice(devicePosition) + " kWh.\n");
            devicePosition++;
        }
        System.out.println("The total energy used by the electric water heaters in the house (based on the values " +
                "inserted is " + mCtrl.getTotalEnergyConsumptionOfAllDevicesOfAType() + " kWh.\n");
    }
}
