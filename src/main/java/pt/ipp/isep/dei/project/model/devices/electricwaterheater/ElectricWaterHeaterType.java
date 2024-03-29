package pt.ipp.isep.dei.project.model.devices.electricwaterheater;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class ElectricWaterHeaterType implements DeviceType {
    String typeName;

    public ElectricWaterHeaterType() {
        this.typeName = "ElectricWaterHeater";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs();
        return new ElectricWaterHeater(name, electricWaterHeaterSpecs);
    }
}
