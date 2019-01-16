package pt.ipp.isep.dei.project.model;

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
    public boolean setmVolumeOfWaterToHeat(double mVolumeOfWaterToHeat) {
        if (mVolumeOfWaterToHeat > 0) {
            this.mVolumeOfWaterToHeat = mVolumeOfWaterToHeat;
            return true;
        }
        return false;
    }

    /** Method that sets the cold-water temperature.
     * @param mColdWaterTemperature Cold-water temperature to be used.
     */
    public boolean setmColdWaterTemperature(double mColdWaterTemperature) {
        if (mColdWaterTemperature < this.mHotWaterTemperature) {
            this.mColdWaterTemperature = mColdWaterTemperature;
            return true;
        }
        return false;
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
        return this.mHotWaterTemperature == mHotWaterTemperature;
    }

    public boolean setmMaximumVolume(double mMaximumVolume) {
        return this.mMaximumVolume == mMaximumVolume;
    }

    public boolean setmNominalPower(double mNominalPower) {
        return this.mNominalPower == mNominalPower;
    }

    /**
     * Method that returns the content of the non-optional attributes of the class.
     *
     * @return String with the non-optional attributes.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Hot Water Temperature: " + mHotWaterTemperature + "\n");
        attributes.append("2 - Maximum Volume: " + mMaximumVolume + "\n");
        attributes.append("3 - Nominal Power: " + mNominalPower + "\n");
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
                return setmNominalPower(value);
            case 4:
                return setmColdWaterTemperature(value);
            case 5:
                return setmVolumeOfWaterToHeat(value);
        }
        System.out.println("Please select a valid number.");
        return false;
    }

    @Override
    public int getNumberOfAttributes() {
        return 3;
    }
}
