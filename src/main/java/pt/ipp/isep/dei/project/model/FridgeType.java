package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {

    public FridgeType() {
    }

    public Device createDevice(String name, Room location) {
        return new Fridge(name, location);
    }
}
