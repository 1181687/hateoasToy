package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

public class EstimateEnergyOfWaterHeaterController {
    private House mHouse;
    private String mType = "Electric Water Heater";

    /**
     * Constructor.
     *
     * @param mHouse House to be used.
     */
    public EstimateEnergyOfWaterHeaterController(House mHouse) {
        this.mHouse = mHouse;
    }

    /**
     * TO DO
     *
     * @return
     */
    public int getNumberOfWaterHeaters() {
        return mHouse.getNumberOfDevicesOfAType(mType);
    }

    /**
     * TO DO
     *
     * @param devicePosition
     * @param coldWaterTemperature
     */
    public void setColdWaterTemp(int devicePosition, double coldWaterTemperature) {
        mHouse.setAttribute(mType, devicePosition, 5, coldWaterTemperature);
    }

    /**
     * TO DO
     * @param devicePosition
     * @param volumeOfWaterToHeat
     */
    public void setVolumeOfWaterToHeat(int devicePosition, double volumeOfWaterToHeat) {
        mHouse.setAttribute(mType, devicePosition, 6, volumeOfWaterToHeat);
    }

    /**
     * TO DO
     * @param devicePosition
     * @return
     */
    public double getEnergyConsumptionOfADevice(int devicePosition) {
        return mHouse.getEnergyConsumptionOfADevice(mType, devicePosition);
    }

    /**
     * TO DO
     *
     * @return
     */
    public double getTotalEnergyConsumptionOfAllDevicesOfAType() {
        return mHouse.getTotalEnergyConsumptionInTheHouse(mType);
    }
}
