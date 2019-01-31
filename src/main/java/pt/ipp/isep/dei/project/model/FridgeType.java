package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {

    public Device createDevice(String name, Room location) {
        DeviceSpecs devspec = new FridgeSpecs();
        return new Device(name, location, devspec);
    }
}
