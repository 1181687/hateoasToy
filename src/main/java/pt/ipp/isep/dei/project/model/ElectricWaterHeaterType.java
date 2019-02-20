package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeaterType implements DeviceType {
    String typeName;

    public ElectricWaterHeaterType() {
        this.typeName = "Electric Water Heater";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice(String name, Room location) {
        return new ElectricWaterHeater(name, location);
    }
}
