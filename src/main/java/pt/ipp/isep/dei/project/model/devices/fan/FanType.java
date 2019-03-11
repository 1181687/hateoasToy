package pt.ipp.isep.dei.project.model.devices.fan;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class FanType implements DeviceType {
    String typeName;

    public FanType() {
        this.typeName = "Fan";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public Device createDevice(String name) {
        DeviceSpecs fanSpecs = new FanSpecs();
        return new Fan(name, fanSpecs);
    }
}
