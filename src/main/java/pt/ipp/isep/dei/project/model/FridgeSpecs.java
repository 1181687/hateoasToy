package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FridgeSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_FREEZER_CAPACITY = "Freezer Capacity";
    private static final String ATTRIBUTE_REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String mTypeName;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private double mNominalPower;

    public FridgeSpecs(double freezerCapacity, double refrigeratorCapacity, double annualEnergyConsumption, double nominalPower) {
        this.mTypeName = "Fridge";
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
        this.mNominalPower = nominalPower;
    }

    public FridgeSpecs() {
        this.mTypeName = "Fridge";
    }

    public String getTypeName() {
        return mTypeName;
    }

    /**
     * get method
     *
     * @return energy consumption in a day
     */
    public double getEnergyConsumptionInADay() {
        return mAnnualEnergyConsumption / 365;
    }

    /**
     * get method of the nominal power
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return mNominalPower;
    }


    /**
     * set method of the freezer capacity
     *
     * @param freezerCapacity capacity of freezer
     * @return capacity of freezer
     */
    public boolean setFreezerCapacity(double freezerCapacity) {
        if (Utils.isSameDouble(this.mFreezerCapacity, freezerCapacity)) {
            return false;
        }
        this.mFreezerCapacity = freezerCapacity;
        return true;
    }

    /**
     * set method ro refrigerator capacity
     *
     * @param refrigeratorCapacity capacity of refrigerator
     */
    public boolean setRefrigeratorCapacity(double refrigeratorCapacity) {
        if (Utils.isSameDouble(this.mRefrigeratorCapacity, refrigeratorCapacity)) {
            return false;
        }
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        return true;
    }

    /**
     * set method of the annual energy consumption
     *
     * @param annualEnergyConsumption annual energy comsumption
     */
    public boolean setAnnualEnergyConsumption(double annualEnergyConsumption) {
        if (Utils.isSameDouble(this.mAnnualEnergyConsumption, annualEnergyConsumption)) {
            return false;
        }
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
        return true;
    }

    /**
     * set method of the nominal power.
     *
     * @param nominalPower nominal power
     */
    public boolean setNominalPower(double nominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, nominalPower)) {
            return false;
        }
        this.mNominalPower = nominalPower;
        return true;
    }

    /**
     * method that displays a string of the choosen attribute (name of the attribute and its value)
     *
     * @return fridge attributes.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Freezer Capacity: " + mFreezerCapacity + "\n");
        attributes.append("2 - Refrigerator Capacity: " + mRefrigeratorCapacity + "\n");
        attributes.append("3 - Annual Energy Consumption: " + mAnnualEnergyConsumption + "\n");
        attributes.append("4 - Nominal Power: " + mNominalPower + "\n");
        return attributes.toString();
    }

    /**
     * set method
     *
     * @param attribute position of the attribute
     * @param value
     * @return
     */
    @Override
    public boolean setAttribute(int attribute, double value) {

        switch (attribute) {
            case 1:
                return setFreezerCapacity(value);
            case 2:
                return setRefrigeratorCapacity(value);
            case 3:
                return setAnnualEnergyConsumption(value);
            case 4:
                return setNominalPower(value);
            default:
                return false;
        }
    }

    /**
     * get method of the number of attributes
     *
     * @return number of FridgeSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 4;
    }

    @Override
    public List<String> getAttributeToString() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_FREEZER_CAPACITY);
        result.add(ATTRIBUTE_REFRIGERATOR_CAPACITY);
        result.add(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
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

    @Override
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
}
