package pt.ipp.isep.dei.project.model.devices;

import pt.ipp.isep.dei.project.model.house.RoomDTO;

public class DeviceDTO {
    private String name;
    private String deviceType;
    private RoomDTO location;
    private double nominalPower;

    public DeviceDTO() {
        // Empty
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public RoomDTO getLocation() {
        return location;
    }

    public void setLocation(RoomDTO location) {
        this.location = location;
    }

    public double getNominalPower() {
        return nominalPower;
    }

    public void setNominalPower(double nominalPower) {
        this.nominalPower = nominalPower;
    }


}
