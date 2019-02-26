package pt.ipp.isep.dei.project.model.Devices.ElectricWaterHeater;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.DeviceType;
import pt.ipp.isep.dei.project.model.Room;

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
