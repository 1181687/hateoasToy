package pt.ipp.isep.dei.project.model;

public class WashingMachineType implements DeviceType {

    public Device createDevice(String name, Room location) {
        DeviceSpecs devspec = new WashingMachineSpecs();
        return new Device(name, location, devspec);
    }
}
