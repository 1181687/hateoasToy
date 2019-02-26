package pt.ipp.isep.dei.project.model.Devices.DishWasher;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;
import pt.ipp.isep.dei.project.model.Room;

public class DishWasherType implements DeviceType {
    String typeName;

    public DishWasherType() {
        this.typeName = "DishWasher";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs dishWasherSpecs = new DishWasherSpecs();
        return new DishWasher(name, dishWasherSpecs);
    }
}
