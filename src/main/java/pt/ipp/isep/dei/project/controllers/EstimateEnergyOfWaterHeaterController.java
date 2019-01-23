package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

public class EstimateEnergyOfWaterHeaterController {
    private House mHouse;
    private String mType;

    /**
     * Constructor.
     *
     * @param house House to be used.
     */
    public EstimateEnergyOfWaterHeaterController(House house) {
        this.mHouse = house;
        this.mType = "Electric Water Heater";
    }

    /**
     * Method that returns the number of water heaters.
     *
     * @return Integer with the number of water heaters
     */
    public int getNumberOfWaterHeaters() {
        return mHouse.getNumberOfDevicesOfAType(mType);
    }

    /**
     * Method that returns the name of the water heater.
     *
     * @param devicePosition Device position in the list of water heaters in the house.
     * @return String with the name of the water heater.
     */
    public String getNameOfWaterHeater(int devicePosition) {
        return mHouse.getDeviceName(mType, devicePosition);
    }

    /**
     * Method that sets the attribute cold-water temperature with an inputted value.
     *
     * @param devicePosition Device position in the list of water heaters in the house.
     * @param coldWaterTemperature Inputted value to be used.
     */
    public void setColdWaterTemp(int devicePosition, double coldWaterTemperature) {
        mHouse.setAttribute(mType, devicePosition, 5, coldWaterTemperature);
    }

    /**
     * Method that sets the attribute volume of water to heat with an inputted value.
     *
     * @param devicePosition Device position in the list of water heaters in the house.
     * @param volumeOfWaterToHeat Inputted value to be used.
     */
    public void setVolumeOfWaterToHeat(int devicePosition, double volumeOfWaterToHeat) {
        mHouse.setAttribute(mType, devicePosition, 6, volumeOfWaterToHeat);
    }

    /**
     * Method that gets the energy consumption of a water heater.
     * @param devicePosition Device position in the list of water heaters in the house.
     * @return
     */
    public double getEnergyConsumptionOfAWaterHeater(int devicePosition) {
        return mHouse.getEnergyConsumptionOfADevice(mType, devicePosition);
    }

    /**
     * Method that gets the combined energy consumption of all the water heaters in the house.
     *
     * @return Double with the combined energy consumption of all the water heaters in the house.
     */
    public double getTotalEnergyConsumptionOfAllDevicesOfAType() {
        return mHouse.getTotalEnergyConsumptionInTheHouse(mType);
    }
}
