package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeaterType implements DeviceType {
    private String mName;

    public Device createDevice(String name, Room location) {
        ElectricWaterHeaterSpecs devspec = new ElectricWaterHeaterSpecs();
        return new ElectricWaterHeater(name, location, devspec);
    }

    @Override
    public String getTypeName() {
        return this.mName;
    }
}
