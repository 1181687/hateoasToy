package pt.ipp.isep.dei.project.model;

public class LampType implements DeviceType {
    String mTypeName;

    public LampType() {
        this.mTypeName = "Lamp";
    }

    @Override
    public String getTypeName() {
        return this.mTypeName;
    }

    public Device createDevice(String name, Room location) {
        return new Lamp(name, location);
    }


}
