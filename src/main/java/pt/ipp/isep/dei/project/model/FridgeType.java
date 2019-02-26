package pt.ipp.isep.dei.project.model;

public class FridgeType implements DeviceType {
    String typeName;

    public FridgeType() {
        this.typeName = "Fridge";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs fridgeSpecs = new FridgeSpecs();
        return new Fridge(name, fridgeSpecs);
    }
}
