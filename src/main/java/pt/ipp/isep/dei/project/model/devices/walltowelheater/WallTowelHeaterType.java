package pt.ipp.isep.dei.project.model.devices.walltowelheater;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class WallTowelHeaterType implements DeviceType {
    private String typeName;

    public WallTowelHeaterType() {
        this.typeName = "WallTowelHeater";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public Device createDevice(String name) {
        DeviceSpecs wallTowelHeaterSpecs = new WallTowelHeaterSpecs();
        return new WallTowelHeater(name, wallTowelHeaterSpecs);
    }
}
