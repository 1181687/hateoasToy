package pt.ipp.isep.dei.project.model;

public class WashingMachine implements DeviceSpecs {
    private String mTypeName ;
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;
    private double mNominalPower;

    public WashingMachine(double mCapacity, double mNominalPower) {
        this.mTypeName = "Washing Machine";
        this.mCapacity = mCapacity;
        this.mNominalPower = mNominalPower;
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    @Override
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumption;
    }
}
