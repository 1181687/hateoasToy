package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.EstimateEnergyUsedByElectricWaterHeaterController;
import pt.ipp.isep.dei.project.model.House;

public class EstimateEnergyUsedByElectricWaterHeater {
    private EstimateEnergyUsedByElectricWaterHeaterController mCtrl;

    public EstimateEnergyUsedByElectricWaterHeater(House house) {
        this.mCtrl = new EstimateEnergyUsedByElectricWaterHeaterController(house);
    }

    public void run() {
        String label1 = "Please insert the cold-water temperature.";
        double coldWaterTemp = InputValidator.getDouble(label1);
        String label2 = "Please insert the volume of water to be heated.";
        double volumeOfWaterToBeHeated = InputValidator.getDouble(label2);
        mCtrl.setColdWaterTempAndVolumeOfWaterToHeat(coldWaterTemp, volumeOfWaterToBeHeated);
        String type = "Electric Water Heater";
        System.out.println("The total energy estimated is " + mCtrl.getEnergyConsumptionInADayOfAllDevicesOfAType(type) + ".\n");
    }
}
