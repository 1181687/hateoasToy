package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {
    private String mName;

    @Override
    public String getTypeName() {
        return this.mName;
    }

    public Device createDevice(String name, Room location) {
        FridgeSpecs devspec = new FridgeSpecs();
        return new Fridge(name, location, devspec);
    }
}
