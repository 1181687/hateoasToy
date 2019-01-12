package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeater implements DeviceSpecs {
    private String mTypeName = "Electric Water Heater";
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mPerformanceRatio;
    private double mEnergyConsumption;

    public ElectricWaterHeater(String mTypeName, double mVolumeOfWater, double mHotWaterTemperature, double mColdWaterTemperature, double mPerformanceRatio, double mEnergyConsumption) {
        this.mTypeName = mTypeName;
        this.mVolumeOfWater = mVolumeOfWater;
        this.mHotWaterTemperature = mHotWaterTemperature;
        this.mColdWaterTemperature = mColdWaterTemperature;
        this.mPerformanceRatio = mPerformanceRatio;
        this.mEnergyConsumption = mEnergyConsumption;
    }

    public ElectricWaterHeater() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }
}
