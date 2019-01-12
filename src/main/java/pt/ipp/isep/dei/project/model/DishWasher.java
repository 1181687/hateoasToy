package pt.ipp.isep.dei.project.model;

public class DishWasher implements DeviceSpecs{
    private String mTypeName ="Dish Washer";
    private int mCapacity;
    private double mDuration;
    private double mEnergyConsumption;

    public DishWasher(String mTypeName, int mCapacity, double mDuration, double mEnergyConsumption) {
        this.mTypeName = mTypeName;
        this.mCapacity = mCapacity;
        this.mDuration = mDuration;
        this.mEnergyConsumption = mEnergyConsumption;
    }

    public DishWasher() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
