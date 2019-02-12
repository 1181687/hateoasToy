package pt.ipp.isep.dei.project.model;

public class LampType implements DeviceType {
    private String mName;

    public Device createDevice(String name, Room location) {
        LampSpecs devspec = new LampSpecs();
        return new Lamp(name, location, devspec);
    }

    @Override
    public String getTypeName() {
        return this.mName;
    }
}
