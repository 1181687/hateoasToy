package pt.ipp.isep.dei.project.model;

public class LampType implements DeviceType {
    String typeName;

    public LampType() {
        this.typeName = "Lamp";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name, Room location) {
        DeviceSpecs lampSpecs = new LampSpecs();
        return new Lamp(name, location, lampSpecs);
    }


}
