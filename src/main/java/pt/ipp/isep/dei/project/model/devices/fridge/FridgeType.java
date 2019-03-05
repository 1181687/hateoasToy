package pt.ipp.isep.dei.project.model.devices.fridge;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class FridgeType implements DeviceType {
    String typeName;

    public FridgeType() {
        this.typeName = "Fridge";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs fridgeSpecs = new FridgeSpecs();
        return new Fridge(name, fridgeSpecs);
    }
}
