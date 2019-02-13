package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {

    public DishWasherType() {
    }

    public Device createDevice(String name, Room location) {
        return new DishWasher(name, location);
    }
}
