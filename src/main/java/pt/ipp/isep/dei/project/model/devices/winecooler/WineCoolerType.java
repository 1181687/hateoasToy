package pt.ipp.isep.dei.project.model.devices.winecooler;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class WineCoolerType implements DeviceType {
    String typeName;

    public WineCoolerType() {
        this.typeName = "WineCooler";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs winecoolerSpecs = new WineCoolerSpecs();
        return new WineCooler(name, winecoolerSpecs);
    }
}
