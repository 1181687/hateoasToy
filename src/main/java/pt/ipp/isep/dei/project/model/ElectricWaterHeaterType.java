package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeaterType implements DeviceType {

    public ElectricWaterHeaterType() {
    }

    public Device createDevice(String name, Room location) {
        return new ElectricWaterHeater(name, location);
    }
}
