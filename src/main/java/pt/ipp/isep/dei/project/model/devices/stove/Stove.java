package pt.ipp.isep.dei.project.model.devices.stove;

import pt.ipp.isep.dei.project.model.GeoAreaReading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.house.Room;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stove implements Device {

    private String name;
    private Room location;
    private StoveSpecs specs;
    private List<GeoAreaReading> geoAreaReadingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;

    public Stove(String name, DeviceSpecs specs) {
        this.name = name;
        this.specs = (StoveSpecs) specs;
        this.geoAreaReadingList = new ArrayList<>();
        this.isActive = true;
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
     * method that gets a location (room) of a device.
     *
     * @return the location.
     */
    @Override
    public Room getLocation() {
        return this.location;
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
     * method that gets the list of GeoAreaReading of the Device.
     *
     * @return reading list
     */
    @Override
    public List<GeoAreaReading> getReadings() {
        return geoAreaReadingList;
    }

    /**
     * method that gets the state of the device.
     *
     * @return true if it is active, false if it is deactivated.
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
        return deactivationDate;
    }

    /**
     * method that set the location (room) of device and it to the room.
     *
     * @param location given location
     * @return false if the location of device is already the same. Sets and return True if the device has a location null,
     * or is diferent than the given location.
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
     * method that sets the deactivate device, turning it to false and registering the
     * date of deactivation
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
     * method that sets the given name only if the name doesn't exist in DeviceList
     * and if it is different from the name that the Device has.
     * throw new RuntimeException if Name already exists:"Name already exists. Please write a new one."
     *
     * @param name String given name of device
     * @return true if sets false if don't
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
     * method that creates the same hashcode to two devices that have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    /**
     * Equals method to determine if two Devices are equal.     *
     * They are equal if they have the same name.
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
