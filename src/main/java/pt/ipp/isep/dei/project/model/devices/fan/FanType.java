package pt.ipp.isep.dei.project.model.devices.fan;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class FanType implements DeviceType {
    private String typeName;

    public FanType() {
        this.typeName = "Fan";
    }

    @Override
    public String getTypeName() {
        return null;
    }

    @Override
    public Device createDevice(String name) {
        return null;
    }
}
