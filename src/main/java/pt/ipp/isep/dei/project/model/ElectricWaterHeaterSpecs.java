package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ElectricWaterHeaterSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT = "Volume Of Water To Heat";
    private static final String ATTRIBUTE_HOT_WATER_TEMP = "Hot-Water Temperature";
    private static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance Ratio";
    private static final String ATTRIBUTE_COLD_WATER_TEMP = "Cold-Water Temperature";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String mTypeName;
    private double mVolumeOfWaterToHeat;
    private double mColdWaterTemperature;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mNominalPower;

//    public ElectricWaterHeaterSpecs(double hotWaterTemperature, double maximumVolume, double performanceRatio, double nominalPower) {
//        this.mTypeName = "Electric Water Heater";
//        this.mHotWaterTemperature = hotWaterTemperature;
//        this.mMaximumVolume = maximumVolume;
//        this.mPerformanceRatio = performanceRatio;
//        this.mNominalPower = nominalPower;
//    }

    public ElectricWaterHeaterSpecs() {
        this.mTypeName = "Electric Water Heater";
    }

    public String getTypeName() {
        return mTypeName;
    }



    /**
     * Method that sets the volume of water to be heated.
     *
     * @param volumeOfWaterToHeat Volume of water to be heated.
     */
    public boolean setVolumeOfWaterToHeat(Object volumeOfWaterToHeat) {
        double volumeWater = (Double) volumeOfWaterToHeat;
        if (volumeWater > 0) {
            this.mVolumeOfWaterToHeat = volumeWater;
            return true;
        }
        return false;
    }

    /**
     * Method that sets the cold-water temperature.
     *
     * @param coldWaterTemperature Cold-water temperature to be used.
     */
    public boolean setColdWaterTemperature(Object coldWaterTemperature) {
        double cwt = (Double) coldWaterTemperature;
        if (cwt < this.mHotWaterTemperature) {
            this.mColdWaterTemperature = cwt;
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
    public boolean setHotWaterTemperature(Object hotWaterTemperature) {
        double hwt = (Double) hotWaterTemperature;
        if (Utils.isSameDouble(this.mHotWaterTemperature, hwt)) {
            return false;
        }
        this.mHotWaterTemperature = hwt;
        return true;
    }


    /**
     * method that determine if the value of the performanceRatio is the same that the method receive.
     *
     * @param performanceRatio
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setPerformanceRatio(Object performanceRatio) {
        double perfRatio = (Double) performanceRatio;
        if (Utils.isSameDouble(this.mPerformanceRatio, perfRatio)) {
            return false;
        }
        this.mPerformanceRatio = perfRatio;
        return true;
    }

    /**
     * method that determine if the value of the nominalPower is the same that the method receive.
     *
     * @param nominalPower
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setNominalPower(Object nominalPower) {
        double nomPower = (Double) nominalPower;
        if (Utils.isSameDouble(this.mNominalPower, nomPower)) {
            return false;
        }
        this.mNominalPower = nomPower;
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
        attributes.append("2 - Performance Ratio: " + mPerformanceRatio + "\n");
        attributes.append("3 - Nominal Power: " + mNominalPower + "\n");
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
                return setPerformanceRatio(value);
            case 3:
                return setNominalPower(value);
            case 4:
                return setColdWaterTemperature(value);
            case 5:
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

    @Override
    public List<String> getAttributeToString() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT);
        result.add(ATTRIBUTE_HOT_WATER_TEMP);
        result.add(ATTRIBUTE_PERFORMANCE_RATIO);
        result.add(ATTRIBUTE_COLD_WATER_TEMP);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT:
                return mVolumeOfWaterToHeat;
            case ATTRIBUTE_HOT_WATER_TEMP:
                return mHotWaterTemperature;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                return mPerformanceRatio;
            case ATTRIBUTE_COLD_WATER_TEMP:
                return mColdWaterTemperature;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT:
                if (attributeValue instanceof Number) {
                    return setVolumeOfWaterToHeat(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_HOT_WATER_TEMP:
                if (attributeValue instanceof Number) {
                    return setHotWaterTemperature(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                if (attributeValue instanceof Number) {
                    return setPerformanceRatio(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_COLD_WATER_TEMP:
                if (attributeValue instanceof Number) {
                    return setColdWaterTemperature(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Number){
                    return setNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }
}
