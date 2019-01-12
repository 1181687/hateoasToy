package pt.ipp.isep.dei.project.model;

public class WashingMachine implements DeviceSpecs {
    private String mTypeName = "Washing Machine";
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;

    public WashingMachine(String mTypeName, double mCapacity, double mDuration, double mEnergyConsumption) {
        this.mTypeName = mTypeName;
        this.mCapacity = mCapacity;
        this.mDuration = mDuration;
        this.mEnergyConsumption = mEnergyConsumption;
    }

    public WashingMachine() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
