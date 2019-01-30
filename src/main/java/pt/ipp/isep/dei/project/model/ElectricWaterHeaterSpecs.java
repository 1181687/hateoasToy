package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class ElectricWaterHeaterSpecs implements DeviceSpecs {
    private String mTypeName;
    private double mVolumeOfWaterToHeat;
    private double mColdWaterTemperature;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mMaximumVolume;
    private double mNominalPower;

    public ElectricWaterHeaterSpecs(double hotWaterTemperature, double maximumVolume, double performanceRatio, double nominalPower) {
        this.mTypeName = "Electric Water Heater";
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mMaximumVolume = maximumVolume;
        this.mPerformanceRatio = performanceRatio;
        this.mNominalPower = nominalPower;
    }

    /**
     * get method
     *
     * @return type of device
     */
    @Override
    public String getTypeName() {
        return mTypeName;
    }


    /**
     * Method that sets the volume of water to be heated.
     *
     * @param mVolumeOfWaterToHeat Volume of water to be heated.
     */
    public boolean setVolumeOfWaterToHeat(double mVolumeOfWaterToHeat) {
        if (mVolumeOfWaterToHeat > 0) {
            this.mVolumeOfWaterToHeat = mVolumeOfWaterToHeat;
            return true;
        }
        return false;
    }

    /**
     * Method that sets the cold-water temperature.
     *
     * @param coldWaterTemperature Cold-water temperature to be used.
     */
    public boolean setColdWaterTemperature(double coldWaterTemperature) {
        if (coldWaterTemperature < this.mHotWaterTemperature) {
            this.mColdWaterTemperature = coldWaterTemperature;
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
        double specificHeatOfWater = 1.163 / 1000;
        double differenceInTemperature = mHotWaterTemperature - mColdWaterTemperature;
        double formula = specificHeatOfWater * mVolumeOfWaterToHeat * differenceInTemperature
                * mPerformanceRatio;
        return Utils.round(formula, 2);
    }

    /**
     * Method that returns the nominal power.
     *
     * @return Nominal power.
     */
    @Override
    public double getNominalPower() {
        return mNominalPower;
    }

    /**
     * methods that determine if the value of the hotWaterTemperature is the same that the method receive.
     *
     * @param hotWaterTemperature
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setHotWaterTemperature(double hotWaterTemperature) {
        if (Utils.isSameDouble(this.mHotWaterTemperature, hotWaterTemperature)) {
            return false;
        }
        this.mHotWaterTemperature = hotWaterTemperature;
        return true;
    }

    /**
     * methods that determine if the value of the maximumVolume is the same that the method receive.
     *
     * @param maximumVolume
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setMaximumVolume(double maximumVolume) {
        if (Utils.isSameDouble(this.mMaximumVolume, maximumVolume)) {
            return false;
        }
        this.mMaximumVolume = maximumVolume;
        return true;
    }

    /**
     * method that determine if the value of the performanceRatio is the same that the method receive.
     *
     * @param performanceRatio
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setPerformanceRatio(double performanceRatio) {
        if (Utils.isSameDouble(this.mPerformanceRatio, performanceRatio)) {
            return false;
        }
        this.mPerformanceRatio = performanceRatio;
        return true;
    }

    /**
     * method that determine if the value of the nominalPower is the same that the method receive.
     *
     * @param nominalPower
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setNominalPower(double nominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, nominalPower)) {
            return false;
        }
        this.mNominalPower = nominalPower;
        return true;
    }

    /**
     * method that get the attributes by strings.
     *
     * @return an attribute of the electricWater.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Hot Water Temperature: " + mHotWaterTemperature + "\n");
        attributes.append("2 - Maximum Volume: " + mMaximumVolume + "\n");
        attributes.append("3 - Performance Ratio: " + mPerformanceRatio + "\n");
        attributes.append("4 - Nominal Power: " + mNominalPower + "\n");
        return attributes.toString();
    }

    /**
     * method that set a value of an attribute by a position.
     *
     * @param attribute
     * @param value
     * @return the attributes with new value if true. If not, return false.
     */
    @Override
    public boolean setAttribute(int attribute, double value) {
        switch (attribute) {
            case 1:
                return setHotWaterTemperature(value);
            case 2:
                return setMaximumVolume(value);
            case 3:
                return setPerformanceRatio(value);
            case 4:
                return setNominalPower(value);
            case 5:
                return setColdWaterTemperature(value);
            case 6:
                return setVolumeOfWaterToHeat(value);
            default:
                return false;
        }
    }

    /**
     * method that get the number of the attributes of the device.
     *
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 4;
    }
}
