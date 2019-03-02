package pt.ipp.isep.dei.project.model.Devices.Freezer;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;

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
