package pt.ipp.isep.dei.project.model.devices.microwaveoven;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class MicrowaveOvenType implements DeviceType {
    String typeName;

    public MicrowaveOvenType() {
        this.typeName = "Microwave Oven";
    }

    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs microwaveOvenSpecs = new MicrowaveOvenSpecs();
        return new MicrowaveOven(name, microwaveOvenSpecs);
    }
}
