package pt.ipp.isep.dei.project.model.devices.Lamp;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class LampType implements DeviceType {
    String typeName;

    public LampType() {
        this.typeName = "Lamp";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs lampSpecs = new LampSpecs();
        return new Lamp(name, lampSpecs);
    }
}
