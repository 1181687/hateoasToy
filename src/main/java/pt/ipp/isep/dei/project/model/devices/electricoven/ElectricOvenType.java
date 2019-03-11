package pt.ipp.isep.dei.project.model.devices.electricoven;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class ElectricOvenType implements DeviceType {
    String typeName;

    public ElectricOvenType() {
        this.typeName = "ElectricOven";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs electricOvenSpecs = new ElectricOvenSpecs();
        return new ElectricOven(name, electricOvenSpecs);
    }
}
