package pt.ipp.isep.dei.project.model.Devices.ElectricOven;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;

public class ElectricOvenType implements DeviceType {
    String typeName;

    public ElectricOvenType() {
        this.typeName = "Electric Oven";
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
