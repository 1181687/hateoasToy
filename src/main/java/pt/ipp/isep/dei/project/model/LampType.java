package pt.ipp.isep.dei.project.model;

public class LampType implements DeviceType {

    public LampType() {
    }

    public Device createDevice(String name, Room location) {
        return new Lamp(name, location);
    }


}
