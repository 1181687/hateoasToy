package pt.ipp.isep.dei.project.model.devices.fan;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.house.Room;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fan implements Device {

    private String name;
    private Room location;
    private FanSpecs specs;
    private List<DeviceReading> readingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;

    public Fan(String name, DeviceSpecs specs) {
        this.name = name;
        this.specs = (FanSpecs) specs;
        this.isActive = true;
        this.readingList = new ArrayList<>();
    }

    /**
     * method that gets a location (room) of a device.
     *
     * @return the location.
     */
    @Override
    public Room getLocation() {
        return this.location;
    }

    /**
     * method that gets the name of a device.
     *
     * @return the name of device
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * method that gets the Device Specifications
     *
     * @return DeviceSpecs
     */
    @Override
    public DeviceSpecs getSpecs() {
        return this.specs;
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
     * method that gets an active device.
     *
     * @return an active device.
     */
    @Override
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * method that gets the deactivation date of a device
     *
     * @return the date of deactivation
     */
    @Override
    public LocalDateTime getDeactivationDate() {
        return this.deactivationDate;
    }

    /**
     * method that sets the deactivate device, turning it to false and giving it a date
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
     * -     * method that sets the given name only if the name doesn't exist in DeviceList
     * -     * and if it is different from the name that the Device has.
     * -     *
     * -     * @param name String given name of device
     * -     * @return true if sets false if don't
     * -
     */
    @Override
    public boolean setName(String name) {
        if (this.location.isDeviceNameExistant(name)) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.name = name;
        return true;
    }

    /**
     * method that gets the list of Reading of the Device.
     *
     * @return reading list
     */
    @Override
    public List<DeviceReading> getReadings() {
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
     * Equals method to determine if two Devices are equal.     *
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
}
