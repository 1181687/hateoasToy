package pt.ipp.isep.dei.project.model.devices.freezer;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class FreezerType implements DeviceType {
    String typeName;

    public FreezerType() {
        this.typeName = "Freezer";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs freezerSpecs = new FreezerSpecs();
        return new Freezer(name, freezerSpecs);
    }
}
