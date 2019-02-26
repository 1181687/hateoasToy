package pt.ipp.isep.dei.project.model.Devices.Fridge;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;
import pt.ipp.isep.dei.project.model.Room;

public class FridgeType implements DeviceType {
    String typeName;

    public FridgeType() {
        this.typeName = "Fridge";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name, Room location) {
        DeviceSpecs fridgeSpecs = new FridgeSpecs();
        return new Fridge(name, location, fridgeSpecs);
    }
}
