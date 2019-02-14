package pt.ipp.isep.dei.project.model;

public class WashingMachineType implements DeviceType {
    String mTypeName;

    public WashingMachineType() {
        this.mTypeName = "Washing Machine";
    }

    @Override
    public String getTypeName() {
        return this.mTypeName;
    }

    public Device createDevice(String name, Room location) {
        return new WashingMachine(name, location);
    }
}
