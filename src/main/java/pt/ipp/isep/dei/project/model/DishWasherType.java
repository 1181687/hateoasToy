package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {
    String mTypeName;

    public DishWasherType() {
        this.mTypeName = "DishWasher";
    }

    public String getTypeName() {
        return mTypeName;
    }

    public Device createDevice(String name, Room location) {
        return new DishWasher(name, location);
    }
}
