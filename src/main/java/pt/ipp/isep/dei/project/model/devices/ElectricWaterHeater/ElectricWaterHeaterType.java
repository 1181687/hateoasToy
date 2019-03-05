package pt.ipp.isep.dei.project.model.devices.ElectricWaterHeater;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class ElectricWaterHeaterType implements DeviceType {
    String typeName;

    public ElectricWaterHeaterType() {
        this.typeName = "Electric Water Heater";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs();
        return new ElectricWaterHeater(name, electricWaterHeaterSpecs);
    }
}
