package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ElectricWaterHeater implements DeviceSpecs {
    private static final String ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT = "Volume of water to heat";
    private static final String ATTRIBUTE_COLD_WATER_TEMP = "Cold-water temperature";
    private static final String ATTRIBUTE_HOT_WATER_TEMP = "Hot-water temperature";
    private static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance ratio";
    private static final String ATTRIBUTE_MAXIMUM_VOLUME = "Maximum volume";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal power";

    private String mTypeName;
    private double mVolumeOfWaterToHeat;
    private double mColdWaterTemperature;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mMaximumVolume;
    private double mNominalPower;

    public ElectricWaterHeater(double mHotWaterTemperature, double mMaximumVolume, double mPerformanceRatio, double mNominalPower) {
        this.mTypeName = "Electric Water Heater";
        this.mHotWaterTemperature = mHotWaterTemperature;
        this.mMaximumVolume = mMaximumVolume;
        this.mPerformanceRatio = mPerformanceRatio;
        this.mNominalPower = mNominalPower;
    }

    /**
     * TODO - LUÍS
     *
     * @return
     */
    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT);
        result.add(ATTRIBUTE_COLD_WATER_TEMP);
        result.add(ATTRIBUTE_HOT_WATER_TEMP);
        result.add(ATTRIBUTE_PERFORMANCE_RATIO);
        result.add(ATTRIBUTE_MAXIMUM_VOLUME);
        result.add(ATTRIBUTE_NOMINAL_POWER);
        return result;
    }

    /**
     * TODO - LUÍS
     *
     * @param attributeName
     * @return
     */
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT:
                return mVolumeOfWaterToHeat;
            case ATTRIBUTE_COLD_WATER_TEMP:
                return mColdWaterTemperature;
            case ATTRIBUTE_HOT_WATER_TEMP:
                return mHotWaterTemperature;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                return mPerformanceRatio;
            case ATTRIBUTE_MAXIMUM_VOLUME:
                return mMaximumVolume;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0;
        }
    }

    /**
     * TODO - LUÍS
     *
     * @param attributeName
     * @param attributeValue
     * @return
     */
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT:
                if (attributeValue instanceof Double) {
                    this.mVolumeOfWaterToHeat = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_COLD_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.mColdWaterTemperature = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_HOT_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.mHotWaterTemperature = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                if (attributeValue instanceof Double) {
                    this.mPerformanceRatio = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_MAXIMUM_VOLUME:
                if (attributeValue instanceof Double) {
                    this.mMaximumVolume = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * TODO
     *
     * @return
     */
    public String getEditableAttributesContent() {
        StringBuilder content = new StringBuilder();
        content.append("1 - " + ATTRIBUTE_HOT_WATER_TEMP);
        content.append("\n");
        content.append("2 - " + ATTRIBUTE_PERFORMANCE_RATIO);
        content.append("\n");
        content.append("3 - " + ATTRIBUTE_MAXIMUM_VOLUME);
        content.append("\n");
        content.append("4 - " + ATTRIBUTE_NOMINAL_POWER);
        content.append("\n");
        return content.toString();
    }

    /**
     * get method
     *
     * @return type of device
     */
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
        return Math.floor(specificHeatOfWater * mVolumeOfWaterToHeat * differenceInTemperature
                * mPerformanceRatio * 1000) / 1000;
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

    /**
     * methods that determine if the value of the hotWaterTemperature is the same that the method receive.
     * @param mHotWaterTemperature
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setmHotWaterTemperature(double mHotWaterTemperature) {
        if (Utils.isSameDouble(this.mHotWaterTemperature, mHotWaterTemperature)) {
            return false;
        }
        this.mHotWaterTemperature = mHotWaterTemperature;
        return true;
    }

    /**
     * methods that determine if the value of the maximumVolume is the same that the method receive.
     *
     * @param mMaximumVolume
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setmMaximumVolume(double mMaximumVolume) {
        if (Utils.isSameDouble(this.mMaximumVolume, mMaximumVolume)) {
            return false;
        }
        this.mMaximumVolume = mMaximumVolume;
        return true;
    }

    /**
     * method that determine if the value of the performanceRatio is the same that the method receive.
     * @param mPerformanceRatio
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setmPerformanceRatio(double mPerformanceRatio) {
        if (Utils.isSameDouble(this.mPerformanceRatio, mPerformanceRatio)) {
            return false;
        }
        this.mPerformanceRatio = mPerformanceRatio;
        return true;
    }

    /**
     * method that determine if the value of the nominalPower is the same that the method receive.
     * @param mNominalPower
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setmNominalPower(double mNominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, mNominalPower)) {
            return false;
        }
        this.mNominalPower = mNominalPower;
        return true;
    }

    /**
     * method that get the attributes by strings.
     * @return an attribute of the electricWater.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Hot Water Temperature: " + mHotWaterTemperature + "\n");
        attributes.append("2 - Maximum Volume: " + mMaximumVolume + "\n");
        attributes.append("3 - Performance Ratio: " + mPerformanceRatio + "\n");
        attributes.append("4 - Nominal Power: " + mNominalPower + "\n");
        String electricWaterHeaterAttributes = attributes.toString();
        return electricWaterHeaterAttributes;
    }

    /**
     * method that set a value of an attribute by a position.
     * @param attribute
     * @param value
     * @return the attributes with new value if true. If not, return false.
     */
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
            case 5:
                return setmColdWaterTemperature(value);
            case 6:
                return setmVolumeOfWaterToHeat(value);
        }
        return false;
    }

    /**
     * method that get the number of the attributes of the device.
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 4;
    }
}
