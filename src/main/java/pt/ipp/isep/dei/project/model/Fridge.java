package pt.ipp.isep.dei.project.model;

public class Fridge implements DeviceSpecs {
    private String mTypeName ;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private double mNominalPower;

    public Fridge(double mFreezerCapacity, double mRefrigeratorCapacity, double mAnnualEnergyConsumption, double mNominalPower) {
        this.mTypeName = "Fridge";
        this.mFreezerCapacity = mFreezerCapacity;
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
        this.mAnnualEnergyConsumption = mAnnualEnergyConsumption;
        this.mNominalPower = mNominalPower;
    }

    public Fridge() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    /**
     * @return
     */
    public double getEnergyConsumptionInADay() {
        return mAnnualEnergyConsumption / 365;
    }

    /**
     * @return
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }
}
