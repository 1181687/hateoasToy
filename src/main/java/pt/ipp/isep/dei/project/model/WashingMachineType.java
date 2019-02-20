package pt.ipp.isep.dei.project.model;

public class WashingMachineType implements DeviceType {
    String typeName;

    public WashingMachineType() {
        this.typeName = "Washing Machine";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name, Room location) {
        return new WashingMachine(name, location);
    }
}
