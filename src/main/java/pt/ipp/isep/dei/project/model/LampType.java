package pt.ipp.isep.dei.project.model;

public class LampType implements DeviceType {
    public Device createDevice(String name, Room location) {
        DeviceSpecs devspec = new LampSpecs();
        return new Device(name, location, devspec);
    }
}
