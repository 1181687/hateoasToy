package pt.ipp.isep.dei.project.model.devices.stove;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class StoveType implements DeviceType {
    String typeName;

    public StoveType() {
        this.typeName = "Stove";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public Device createDevice(String name) {
        DeviceSpecs stoveSpecs = new StoveSpecs();
        return new Stove(name, stoveSpecs);
    }
}
