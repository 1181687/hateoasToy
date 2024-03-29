package pt.ipp.isep.dei.project.model.devices.washingmachine;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class WashingMachineType implements DeviceType {
    String typeName;

    public WashingMachineType() {
        this.typeName = "WashingMachine";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs washingMachineSpecs = new WashingMachineSpecs();
        return new WashingMachine(name, washingMachineSpecs);
    }
}
