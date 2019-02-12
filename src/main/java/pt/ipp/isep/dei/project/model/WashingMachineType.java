package pt.ipp.isep.dei.project.model;

public class WashingMachineType implements DeviceType {

    private String mName;

    @Override
    public String getTypeName() {
        return this.mName;
    }

    public Device createDevice(String name, Room location) {
        WashingMachineSpecs devspec = new WashingMachineSpecs();
        return new WashingMachine(name, location, devspec);
    }
}
