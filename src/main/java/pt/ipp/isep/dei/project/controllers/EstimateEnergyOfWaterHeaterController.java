package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

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
     * @return Integer with the number of water heaters.
     */
    public int getNumberOfWaterHeaters() {
        return mHouse.getNumberOfDevicesOfAType(mType);
    }

    /**
     * Method that returns the name of the water heater.
     *
     * @param devicePosition Device1 position in the list of water heaters in the house.
     * @return String with the name of the water heater.
     */
    public String getNameOfWaterHeater(int devicePosition) {
        return mHouse.getDeviceNameOfATypeByPosition(mType, devicePosition);
    }

    /**
     * Method that sets the attribute cold-water temperature with an inputted value.
     *
     * @param deviceName Device name.
     * @param coldWaterTemperature Inputted value to be used.
     */
    public void setColdWaterTemp(String deviceName, double coldWaterTemperature) {
        mHouse.setDeviceAttribute(deviceName, "Cold-Water Temperature", coldWaterTemperature);
    }

    /**
     * Method that sets the attribute volume of water to heat with an inputted value.
     *
     * @param deviceName Device name.
     * @param volumeOfWaterToHeat Inputted value to be used.
     */
    public void setVolumeOfWaterToHeat(String deviceName, double volumeOfWaterToHeat) {
        mHouse.setDeviceAttribute(deviceName, "Volume Of Water To Heat", volumeOfWaterToHeat);
    }

    /**
     * Method that gets the energy consumption of a water heater.
     * @param deviceName Device name.
     * @return Double with the energy consumption.
     */
    public double getEnergyConsumptionOfAWaterHeater(String deviceName) {
        return Utils.round(mHouse.getDailyEnergyConsumptionOfADevice(deviceName), 2);
    }

    /**
     * Method that gets the combined energy consumption of all the water heaters in the house.
     *
     * @return Double with the combined energy consumption of all the water heaters in the house.
     */
    public double getTotalEnergyConsumptionOfAllElectricWaterHeaters() {
        return Utils.round(mHouse.getTotalEnergyConsumptionOfDevicesOfCertainType("Electric Water Heater"), 2);
    }
}
