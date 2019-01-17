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
     * TODO
     *
     * @return
     */
    public int getNumberOfWaterHeaters() {
        return mHouse.getNumberOfDevicesOfAType(mType);
    }

    /**
     * TODO
     *
     * @param devicePosition
     * @return
     */
    public String getNameOfWaterHeater(int devicePosition) {
        return mHouse.getDeviceName(mType, devicePosition);
    }

    /**
     * TODO
     *
     * @param devicePosition
     * @param coldWaterTemperature
     */
    public void setColdWaterTemp(int devicePosition, double coldWaterTemperature) {
        mHouse.setAttribute(mType, devicePosition, "Cold-water temperature", coldWaterTemperature);
    }

    /**
     * TODO
     * @param devicePosition
     * @param volumeOfWaterToHeat
     */
    public void setVolumeOfWaterToHeat(int devicePosition, double volumeOfWaterToHeat) {
        mHouse.setAttribute(mType, devicePosition, "Volume of water to heat", volumeOfWaterToHeat);
    }

    /**
     * TODO
     * @param devicePosition
     * @return
     */
    public double getEnergyConsumptionOfADevice(int devicePosition) {
        return mHouse.getEnergyConsumptionOfADevice(mType, devicePosition);
    }

    /**
     * TODO
     *
     * @return
     */
    public double getTotalEnergyConsumptionOfAllDevicesOfAType() {
        return mHouse.getTotalEnergyConsumptionInTheHouse(mType);
    }
}
