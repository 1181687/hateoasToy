package pt.ipp.isep.dei.project.model.devices.dishwasher;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class DishWasherType implements DeviceType {
    String typeName;

    public DishWasherType() {
        this.typeName = "Dishwasher";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs dishWasherSpecs = new DishWasherSpecs();
        return new DishWasher(name, dishWasherSpecs);
    }
}
