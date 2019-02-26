package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {
    String typeName;

    public DishWasherType() {
        this.typeName = "DishWasher";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs dishWasherSpecs = new DishWasherSpecs();
        return new DishWasher(name, dishWasherSpecs);
    }
}
