package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {

    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new FridgeSpecs();
        return new Device1(name, location, devspec);
    }
}
