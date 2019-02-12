package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeaterType implements DeviceType {
    private String mName;

    public Device1 createDevice(String name, Room location) {
        DeviceSpecs devspec = new ElectricWaterHeaterSpecs();
        return new Device1(name, location, devspec);
    }

    @Override
    public String getTypeName() {
        return this.mName;
    }
}
