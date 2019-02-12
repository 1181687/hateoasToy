package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {
    private String mName;

    @Override
    public String getTypeName() {
        return this.mName;
    }

    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new FridgeSpecs();
        return new Device1(name, location, devspec);
    }
}
