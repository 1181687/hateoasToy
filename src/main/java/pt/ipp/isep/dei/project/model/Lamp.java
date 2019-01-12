package pt.ipp.isep.dei.project.model;

public class Lamp implements DeviceSpecs {
    private String mTypeName = "Fridge";
    private double mLuminousFlux;
    private double mEnergyConsumption;

    public Lamp(String mTypeName, double mLuminousFlux, double mEnergyConsumption) {
        this.mTypeName = mTypeName;
        this.mLuminousFlux = mLuminousFlux;
        this.mEnergyConsumption = mEnergyConsumption;
    }

    public Lamp() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }

}
