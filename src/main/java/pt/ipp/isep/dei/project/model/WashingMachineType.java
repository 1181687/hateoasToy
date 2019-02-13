package pt.ipp.isep.dei.project.model;

public class WashingMachineType implements DeviceType {

    public WashingMachineType() {
    }

    public Device createDevice(String name, Room location) {
        return new WashingMachine(name, location);
    }
}
