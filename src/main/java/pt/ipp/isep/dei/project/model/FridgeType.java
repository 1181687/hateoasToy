package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {
    String mTypeName;

    public FridgeType() {
        this.mTypeName = "Fridge";
    }

    @Override
    public String getTypeName() {
        return this.mTypeName;
    }

    public Device createDevice(String name, Room location) {
        return new Fridge(name, location);
    }
}
