package pt.ipp.isep.dei.project.model;

public class DishWasher implements DeviceSpecs {
    private String mTypeName ;
    private int mCapacity;
    private double mDuration;
    private double mEnergyConsumption;

    public DishWasher(int mCapacity) {
        this.mTypeName = "Dish Washer";
        this.mCapacity = mCapacity;
    }

    public DishWasher() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
