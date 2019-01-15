package pt.ipp.isep.dei.project.model;

public class WashingMachine implements DeviceSpecs {
    private String mTypeName ;
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;

    public WashingMachine(double mCapacity) {
        this.mTypeName = "Washing Machine";
        this.mCapacity = mCapacity;
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
