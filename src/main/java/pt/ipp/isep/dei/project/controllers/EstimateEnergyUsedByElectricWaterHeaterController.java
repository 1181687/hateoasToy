package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

public class EstimateEnergyUsedByElectricWaterHeaterController {
    private House mHouse;

    public EstimateEnergyUsedByElectricWaterHeaterController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * Method that allows the possibility of setting the cold-water temperature and the volume of water to heat in the
     * class Electric Water Heater.
     *
     * @param coldWaterTemp       Sets the current temperature of the water that is going to be heated.
     * @param volumeOfWaterToHeat Sets the amount of water to be heated.
     */
    public void setColdWaterTempAndVolumeOfWaterToHeat(double coldWaterTemp, double volumeOfWaterToHeat) {
        mHouse.setColdWaterTempAndVolumeOfWaterToHeat(coldWaterTemp, volumeOfWaterToHeat);
    }

    /**
     * @param type
     * @return
     */
    public double getEnergyConsumptionInADayOfAllDevicesOfAType(String type) {
        return mHouse.getEnergyConsumptionInADayOfAllDevicesOfAType(type);
    }
}
