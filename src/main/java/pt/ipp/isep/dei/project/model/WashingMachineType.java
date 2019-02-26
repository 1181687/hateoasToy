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

    public Device createDevice(String name) {
        DeviceSpecs washingMachineSpecs = new WashingMachineSpecs();
        return new WashingMachine(name, washingMachineSpecs);
    }
}
