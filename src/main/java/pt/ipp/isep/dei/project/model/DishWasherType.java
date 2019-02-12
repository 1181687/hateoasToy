package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {
    private String mName;


    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new DishWasherSpecs();
        return new Device1(name, location, devspec);
    }

    @Override
    public String getTypeName() {
        return this.mName;
    }
}
