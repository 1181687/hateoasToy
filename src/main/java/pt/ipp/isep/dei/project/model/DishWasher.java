package pt.ipp.isep.dei.project.model;

public class DishWasher implements DeviceSpecs {
    private String mTypeName ;
    private int mCapacity;
    private double mDuration;
    private double mEnergyConsumptionProgram1;
    private double mNominalPower;

    public DishWasher(int mCapacity, double mNominalPower) {
        this.mTypeName = "Dish Washer";
        this.mCapacity = mCapacity;
        this.mNominalPower = mNominalPower;
    }


    /**
     * @return
     */
    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    /**
     *
     * @return
     */
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumptionProgram1;
    }

    /**
     * @return
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }
}
