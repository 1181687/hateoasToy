package pt.ipp.isep.dei.project.model;

public class Fridge implements DeviceSpecs {
    private String mTypeName ;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mEnergyConsumption;

    public Fridge(double mFreezerCapacity, double mRefrigeratorCapacity) {
        this.mTypeName = "Fridge";
        this.mFreezerCapacity = mFreezerCapacity;
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
    }

    public Fridge() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
