package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionDeviceController {
    private House mHouse;
    private Device mDevice;

    /**
     * Constructor.
     *
     * @param house House to be used.
     */
    public GetEnergyConsumptionDeviceController(House house) {
        mHouse = house;
    }

    /**
     * Method that returns the content of all the devices in the house.
     *
     * @return String with the list of devices content.
     */
    public String getAllDevicesToString() {
        return mHouse.getAllDevicesToString();
    }

    /**
     * Method that returns all the devices in the house.
     *
     * @return DeviceList with all the devices in the house.
     */
    public int getNumberOfDevices() {
        return mHouse.getNumberOfDevices();
    }

    /**
     * Method that returns a device by its position in the list of all devices in the house.
     *
     * @param position Position of the device in the list of all devices.
     * @return Device chosen.
     */
    public void setDevice(int position) {
        mDevice = mHouse.getDeviceByPosition(position);
    }

    /**
     * Method that calculates the total energy consumption of the chosen device in a given interval.
     *
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Double with the required energy consumption.
     */
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return mDevice.getEnergyConsumptionInAnInterval(startDate, endDate);
    }
}
