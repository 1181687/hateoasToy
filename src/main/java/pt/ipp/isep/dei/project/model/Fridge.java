package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Fridge implements DeviceSpecs {
    private static final String ATTRIBUTE_FREEZER_CAPACITY = "Freezer capacity";
    private static final String ATTRIBUTE_REFRIGERATOR_CAPACITY = "Refrigerator capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual energy consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal power";

    private String mTypeName;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private double mNominalPower;

    public Fridge(double mFreezerCapacity, double mRefrigeratorCapacity, double mAnnualEnergyConsumption, double mNominalPower) {
        this.mTypeName = "Fridge";
        this.mFreezerCapacity = mFreezerCapacity;
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
        this.mAnnualEnergyConsumption = mAnnualEnergyConsumption;
        this.mNominalPower = mNominalPower;
    }

    /**
     * TODO - LUÍS
     *
     * @return
     */
    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_FREEZER_CAPACITY);
        result.add(ATTRIBUTE_REFRIGERATOR_CAPACITY);
        result.add(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION);
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
            case ATTRIBUTE_FREEZER_CAPACITY:
                return mFreezerCapacity;
            case ATTRIBUTE_REFRIGERATOR_CAPACITY:
                return mRefrigeratorCapacity;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                return mAnnualEnergyConsumption;
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
            case ATTRIBUTE_FREEZER_CAPACITY:
                if (attributeValue instanceof Double) {
                    this.mFreezerCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_REFRIGERATOR_CAPACITY:
                if (attributeValue instanceof Double) {
                    this.mRefrigeratorCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                if (attributeValue instanceof Double) {
                    this.mAnnualEnergyConsumption = (Double) attributeValue;
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
        content.append("1 - " + ATTRIBUTE_FREEZER_CAPACITY);
        content.append("\n");
        content.append("2 - " + ATTRIBUTE_REFRIGERATOR_CAPACITY);
        content.append("\n");
        content.append("3 - " + ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION);
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

    /** get method
     * @return energy consumption in a day
     */
    public double getEnergyConsumptionInADay() {
        return mAnnualEnergyConsumption / 365;
    }

    /** get method
     * @return nominal power
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }


    /**
     * set method
     *
     * @param mFreezerCapacity capacity of freezer
     * @return capacity of freezer
     */
    public boolean setmFreezerCapacity(double mFreezerCapacity) {
        if (Utils.isSameDouble(this.mFreezerCapacity, mFreezerCapacity)) {
            return false;
        }
        this.mFreezerCapacity = mFreezerCapacity;
        return true;
    }

    /**
     * set method
     * @param mRefrigeratorCapacity capacity of refrigerator
     */
    public boolean setmRefrigeratorCapacity(double mRefrigeratorCapacity) {
        if (Utils.isSameDouble(this.mRefrigeratorCapacity, mRefrigeratorCapacity)) {
            return false;
        }
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
        return true;
    }

    /**
     * set method
     * @param mAnnualEnergyConsumption annual energy comsumption
     */
    public boolean setmAnnualEnergyConsumption(double mAnnualEnergyConsumption) {
        if (Utils.isSameDouble(this.mAnnualEnergyConsumption, mAnnualEnergyConsumption)) {
            return false;
        }
        this.mAnnualEnergyConsumption = mAnnualEnergyConsumption;
        return true;
    }

    /**
     * set method
     * @param mNominalPower nominal power
     */
    public boolean setmNominalPower(double mNominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, mNominalPower)) {
            return false;
        }
        this.mNominalPower = mNominalPower;
        return true;
    }

    /**
     * method that displays a string of the choosen attribute (name of the attribute and its value)
     *
     * @return
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Freezer Capacity: " + mFreezerCapacity + "\n");
        attributes.append("2 - Refrigerator Capacity: " + mRefrigeratorCapacity + "\n");
        attributes.append("3 - Annual Energy Consumption: " + mAnnualEnergyConsumption + "\n");
        attributes.append("4 - Nominal Power: " + mNominalPower + "\n");
        String fridgeAttributes = attributes.toString();
        return fridgeAttributes;
    }

    /**
     * set method
     * @param attribute position of the attribute
     * @param value
     * @return
     */
    @Override
    public boolean setAttribute(int attribute, double value) {

        switch (attribute) {
            case 1:
                return setmFreezerCapacity(value);
            case 2:
                return setmRefrigeratorCapacity(value);
            case 3:
                return setmAnnualEnergyConsumption(value);
            case 4:
                return setmNominalPower(value);
        }
        return false;
    }

    /**
     * get method
     *
     * @return number of Fridge attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 4;
    }
}
