package pt.ipp.isep.dei.project.model.Devices.Fridge;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;

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
