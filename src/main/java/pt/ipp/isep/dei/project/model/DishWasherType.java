package pt.ipp.isep.dei.project.model;

public class DishWasherType implements DeviceType {
    private String mName;


    public Device createDevice(String name, Room location) {
        DishWasherSpecs devspec = new DishWasherSpecs();
        return new DishWasher(name, location, devspec);
        //String mName, Room mLocation, FridgeSpecs mSpecs, FridgeType mType
    }

    @Override
    public String getTypeName() {
        return this.mName;
    }
}
