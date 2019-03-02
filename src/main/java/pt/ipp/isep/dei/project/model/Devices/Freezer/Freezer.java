package pt.ipp.isep.dei.project.model.Devices.Freezer;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.Programmable;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Freezer implements Device {
    private String name;
    private Room location;
    private FreezerSpecs specs;
    private List<Reading> readingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;

    public Freezer(String name, DeviceSpecs freezerSpecs) {
        this.name = name;
        this.specs = (FreezerSpecs) freezerSpecs;
        this.isActive = true;
        this.readingList = new ArrayList<>();
    }

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    @Override
    public Room getLocation() {
        return this.location;
    }

    /**
     * get method
     *
     * @return name of device
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * get method
     *
     * @return device Specifications
     */
    @Override
    public DeviceSpecs getSpecs() {
        return this.specs;
    }

    /**
     * Method that gets the boolean attribute from each device.
     *
     * @return boolean
     */
    @Override
    public boolean isActive() {
        return false;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    @Override
    public boolean setLocation(Room location) {
        return false;
    }

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    @Override
    public boolean getIsActive() {
        return false;
    }

    @Override
    public List<String> getSpecsList() {
        return null;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        return null;
    }

    @Override
    public String getSpecsToString() {
        return null;
    }

    @Override
    public String getAttributeDataType(String attributeName) {
        return null;
    }

    @Override
    public LocalDateTime getDeactivationDate() {
        return null;
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
    }

    @Override
    public double getNominalPower() {
        return 0;
    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public List<Reading> getReadings() {
        return null;
    }
}
