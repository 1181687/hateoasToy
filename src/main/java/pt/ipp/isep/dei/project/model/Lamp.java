package pt.ipp.isep.dei.project.model;

public class Lamp implements DeviceSpecs {
    private String mTypeName ;
    private double mLuminousFlux;
    private double mEnergyConsumption;

    public Lamp (double mLuminousFlux) {
        this.mTypeName = "Lamp";
        this.mLuminousFlux = mLuminousFlux;
    }

    public Lamp() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }

}
