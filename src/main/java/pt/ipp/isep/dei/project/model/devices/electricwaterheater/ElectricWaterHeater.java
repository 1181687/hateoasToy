package pt.ipp.isep.dei.project.model.devices.electricwaterheater;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.model.Measurable;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ElectricWaterHeater implements Device, Measurable {
    private String name;
    private Room location;
    private ElectricWaterHeaterSpecs specs;
    private List<Reading> readingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;

    public ElectricWaterHeater(String name, DeviceSpecs electricWaterHeaterSpecs) {
        this.name = name;
        this.specs = (ElectricWaterHeaterSpecs) electricWaterHeaterSpecs;
        this.isActive = true;
        this.readingList = new ArrayList<>();

    }

    /**
     * -     * method that set the location (room) of a added device.
     * -     *
     * -     * @param location
     * -     * @return false if the location is equals to another device. True if not.
     * -
     */
    @Override
    public boolean setLocation(Room location) {
        if (Objects.isNull(this.location)) {
            this.location = location;
            location.addDevice(this);
            return true;
        }
        if (this.location.equals(location)) {
            return false;
        }
        this.location.getDeviceList().remove(this);
        this.location = location;
        location.addDevice(this);
        return true;
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

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    public Room getLocation() {
        return this.location;
    }

    /**
     * get method
     *
     * @return name of device
     */
    public String getName() {
        return this.name;
    }


    /**
     * method that gets the Device Specifications
     * @return DeviceSpecs
     */
    @Override
    public DeviceSpecs getSpecs() {
        return this.specs;
    }

    /**
     * method that gets the list of Reading of the Device.
     *
     * @return
     */
    @Override
    public List<Reading> getReadings() {
        return this.readingList;
    }

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    /**
     * Equals method to determine if two Device1 are equal.     *
     *
     * @param obj receives an object
     * @return boolean true if are equal and false if are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device listOne = (Device) obj;
        return this.name.equalsIgnoreCase(listOne.getName());
    }

    public LocalDateTime getDeactivationDate() {
        return this.deactivationDate;
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
     * method that get an active device.
     *
     * @return an active device.
     */
    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
    }
}
