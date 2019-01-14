package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeater implements DeviceSpecs {
    private String mTypeName ;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mPerformanceRatio;
    private double mEnergyConsumption;

    public ElectricWaterHeater(double mVolumeOfWater) {
        this.mTypeName = "Electric Water Heater";
        this.mVolumeOfWater = mVolumeOfWater;
    }

    public ElectricWaterHeater() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
