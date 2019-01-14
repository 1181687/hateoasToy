package pt.ipp.isep.dei.project.model;

public class ElectricWaterHeater implements DeviceSpecs {
    private String mTypeName;
    private double mVolumeOfWaterToHeat;
    private double mColdWaterTemperature;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mMaximumVolume;
    private double mNominalPower;

    public ElectricWaterHeater(double mHotWaterTemperature, double mMaximumVolume, double mNominalPower) {
        this.mTypeName = "Electric Water Heater";
        this.mHotWaterTemperature = mHotWaterTemperature;
        this.mMaximumVolume = mMaximumVolume;
        this.mPerformanceRatio = 0.9;
        this.mNominalPower = mNominalPower;
    }

    public ElectricWaterHeater() {
    }


    @Override
    public String getmTypeName() {
        return mTypeName;
    }


    /**
     * @param mVolumeOfWaterToHeat
     */
    public void setmVolumeOfWaterToHeat(double mVolumeOfWaterToHeat) {
        this.mVolumeOfWaterToHeat = mVolumeOfWaterToHeat;
    }

    /**
     * @param mColdWaterTemperature
     */
    public void setmColdWaterTemperature(double mColdWaterTemperature) {
        this.mColdWaterTemperature = mColdWaterTemperature;
    }

    /**
     * Method that returns the energy consumption of an Electric Water Heater in a given day based on the hours in use,
     * the cold-water temperature and the volume of water produced.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        double specificHeatOfWater = 1.163;
        double differenceInTemperature = mHotWaterTemperature - mColdWaterTemperature;
        return specificHeatOfWater * mVolumeOfWaterToHeat * differenceInTemperature * mPerformanceRatio;
    }

    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }
}
