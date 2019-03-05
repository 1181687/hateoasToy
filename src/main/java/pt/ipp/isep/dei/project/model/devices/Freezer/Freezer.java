package pt.ipp.isep.dei.project.model.devices.Freezer;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    public List<Reading> getReadings() {
        return null;
    }

    /**
     * method that set the deactivate device, turning it to false and giving a date
     */
    @Override
    public boolean setDeactivateDevice() {
        if (this.isActive) {
            this.isActive = false;
            this.deactivationDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            return true;
        }
        return false;
    }

    /**
     * -     * method that set the given name only if the name don't exists in DeviceList
     * -     * and if it is different than the name that the Device1 has.
     * -     *
     * -     * @param name String given name
     * -     * @return true if sets false if don't
     * -
     */
    @Override
    public boolean setName(String name) {
        if (this.location.isDeviceNameExistant(name) || this.name == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.name = name;
        return true;
    }

}
