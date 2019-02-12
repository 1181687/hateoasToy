package pt.ipp.isep.dei.project.model;

public class LampType implements DeviceType {
    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new LampSpecs();
        return new Device1(name, location, devspec);
    }
}
