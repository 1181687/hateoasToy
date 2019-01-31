package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {


    public Device createDevice(String name, Room location) {
        DeviceSpecs devspec = new DishWasherSpecs();
        return new Device(name, location, devspec);
    }
}
