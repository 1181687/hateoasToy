package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {


    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new DishWasherSpecs();
        return new Device1(name, location, devspec);
    }
}
