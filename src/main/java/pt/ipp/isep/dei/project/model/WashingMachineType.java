package pt.ipp.isep.dei.project.model;

public class WashingMachineType implements DeviceType {

    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new WashingMachineSpecs();
        return new Device1(name, location, devspec);
    }
}
