package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class ElectricWaterHeater implements DeviceSpecs {
    private String mTypeName;
    private double mVolumeOfWaterToHeat;
    private double mColdWaterTemperature;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mMaximumVolume;
    private double mNominalPower;

    public ElectricWaterHeater(double mHotWaterTemperature, double mMaximumVolume, double mNominalPower, double mPerformanceRatio) {
        this.mTypeName = "Electric Water Heater";
        this.mHotWaterTemperature = mHotWaterTemperature;
        this.mMaximumVolume = mMaximumVolume;
        this.mPerformanceRatio = mPerformanceRatio;
        this.mNominalPower = mNominalPower;
    }



    @Override
    public String getmTypeName() {
        return mTypeName;
    }


    /** Method that sets the volume of water to be heated.
     * @param mVolumeOfWaterToHeat Volume of water to be heated.
     */
    public void setmVolumeOfWaterToHeat(double mVolumeOfWaterToHeat) {
        if (mVolumeOfWaterToHeat > mMaximumVolume) {
            throw new RuntimeException("The device can only take up a volume of " + mMaximumVolume
                    + ". Please insert a valid value.");
        }
        this.mVolumeOfWaterToHeat = mVolumeOfWaterToHeat;
    }

    /** Method that sets the cold-water temperature.
     * @param mColdWaterTemperature Cold-water temperature to be used.
     */
    public void setmColdWaterTemperature(double mColdWaterTemperature) {
        this.mColdWaterTemperature = mColdWaterTemperature;
    }

    /**
     * Method that returns the energy consumption of an Electric Water Heater in a given day based on the cold-water
     * temperature and the volume of water to be heated.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        double specificHeatOfWater = 1.163;
        double differenceInTemperature = mHotWaterTemperature - mColdWaterTemperature;
        return specificHeatOfWater * mVolumeOfWaterToHeat * differenceInTemperature * mPerformanceRatio;
    }

    /**
     * Method that returns the nominal power.
     *
     * @return Nominal power.
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    public boolean setmHotWaterTemperature(double mHotWaterTemperature) {
        if (Utils.isSameDouble(this.mHotWaterTemperature, mHotWaterTemperature)) {
            return false;
        }
        this.mHotWaterTemperature = mHotWaterTemperature;
        return true;
    }

    public boolean setmMaximumVolume(double mMaximumVolume) {
        if (Utils.isSameDouble(this.mMaximumVolume, mMaximumVolume)) {
            return false;
        }
        this.mMaximumVolume = mMaximumVolume;
        return true;
    }

    public boolean setmPerformanceRatio(double mPerformanceRatio) {
        if (Utils.isSameDouble(this.mPerformanceRatio, mPerformanceRatio)) {
            return false;
        }
        this.mPerformanceRatio = mPerformanceRatio;
        return true;
    }

    public boolean setmNominalPower(double mNominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, mNominalPower)) {
            return false;
        }
        this.mNominalPower = mNominalPower;
        return true;
    }

    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Hot Water Temperature: " + mHotWaterTemperature + "\n");
        attributes.append("2 - Maximum Volume: " + mMaximumVolume + "\n");
        attributes.append("3 - Performance Ratio:" + mPerformanceRatio + "\n");
        attributes.append("4 - Nominal Power: " + mNominalPower + "\n");
        String electricWaterHeaterAttributes = attributes.toString();
        return electricWaterHeaterAttributes;
    }

    @Override
    public boolean setAttribute(int attribute, double value) {
        switch (attribute) {
            case 1:
                return setmHotWaterTemperature(value);
            case 2:
                return setmMaximumVolume(value);
            case 3:
                return setmPerformanceRatio(value);
            case 4:
                return setmNominalPower(value);
        }
        System.out.println("Please select a valid number.");
        return false;
    }

    @Override
    public int getNumberOfAttributes() {
        return 4;
    }
}
