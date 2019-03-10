package pt.ipp.isep.dei.project.model.devices.fan;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fan implements Device {
    private String name;
    private Room location;
    private FanSpecs specs;
    private List<Reading> readingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;

    public Fan(String name, DeviceSpecs specs) {
        this.name = name;
        this.specs = (FanSpecs) specs;
        this.isActive = true;
        this.readingList = new ArrayList<>();
    }

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
