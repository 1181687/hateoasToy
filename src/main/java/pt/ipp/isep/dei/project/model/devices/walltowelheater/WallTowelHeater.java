package pt.ipp.isep.dei.project.model.devices.walltowelheater;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class WallTowelHeater implements Device {
    private String name;
    private Room location;
    private WallTowelHeaterSpecs specs;
    private List<Reading> readingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    @Override
    public Room getLocation() {
        return null;
    }

    /**
     * get method
     *
     * @return name of device
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * get method
     *
     * @return device Specifications
     */
    @Override
    public DeviceSpecs getSpecs() {
        return null;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
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
    public boolean setDeactivateDevice() {
        return false;
    }

    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    @Override
    public boolean setName(String name) {
        return false;
    }

    @Override
    public List<Reading> getReadings() {
        return null;
    }
}
