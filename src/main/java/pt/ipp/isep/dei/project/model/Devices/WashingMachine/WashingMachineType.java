package pt.ipp.isep.dei.project.model.Devices.WashingMachine;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;

public class WashingMachineType implements DeviceType {
    String typeName;

    public WashingMachineType() {
        this.typeName = "Washing Machine";
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