package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeaterType implements DeviceType {
    String mTypeName;

    public ElectricWaterHeaterType() {
        this.mTypeName = "Electric Water Heater";
    }

    public String getTypeName() {
        return mTypeName;
    }

    public Device createDevice(String name, Room location) {
        return new ElectricWaterHeater(name, location);
    }
}
