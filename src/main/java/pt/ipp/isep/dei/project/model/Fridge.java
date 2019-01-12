package pt.ipp.isep.dei.project.model;

public class Fridge implements DeviceSpecs {
    private String mTypeName = "Fridge";
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mEnergyConsumption;

    public Fridge(String mTypeName, double mFreezerCapacity, double mRefrigeratorCapacity, double mEnergyConsumption) {
        this.mTypeName = mTypeName;
        this.mFreezerCapacity = mFreezerCapacity;
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
        this.mEnergyConsumption = mEnergyConsumption;
    }

    public Fridge() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
