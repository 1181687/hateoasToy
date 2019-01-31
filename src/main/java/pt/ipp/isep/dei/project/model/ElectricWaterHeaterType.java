package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeaterType implements DeviceType {
    public Device createDevice(String name, Room location) {
        DeviceSpecs devspec = new ElectricWaterHeaterSpecs();
        return new Device(name, location, devspec);
    }
}
