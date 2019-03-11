package pt.ipp.isep.dei.project.model.devices.walltowelheater;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.house.Room;

import java.time.LocalDateTime;
import java.util.List;

public class WallTowelHeater implements Device {
    @Override
    public Room getLocation() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public DeviceSpecs getSpecs() {
        return null;
    }

    @Override
    public boolean setLocation(Room location) {
        return false;
    }

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

    @Override
    public boolean setName(String name) {
        return false;
    }

    @Override
    public List<Reading> getReadings() {
        return null;
    }
}
