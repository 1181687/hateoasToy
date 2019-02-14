package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DishWasherSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String mTypeName;
    private int mCapacity;
    private double mDuration;
    private double mNominalPower;
    private ProgramList mProgramList;

    public DishWasherSpecs(int capacity, double nominalPower, ProgramList programList) {
        this.mTypeName = "Dishwasher";
        this.mCapacity = capacity;
        this.mNominalPower = nominalPower;
        this.mProgramList = programList;
    }

    public DishWasherSpecs() {
        this.mTypeName = "Dishwasher";
    }

    public String getTypeName() {
        return mTypeName;
    }

    /**
     * get method
     *
     * @return energy consumption
     */
    public double getEnergyConsumptionInADay() {
        return 0;
    }


    /**
     * get method
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return mNominalPower;
    }

    /**
     * set method
     *
     * @param capacity
     * @return
     */
    public boolean setCapacity(int capacity) {
        if (Utils.isSameDouble(this.mCapacity, capacity)) {
            return false;
        }
        this.mCapacity = capacity;
        return true;
    }

    /**
     * set method to Nominal Power
     *
     * @param nominalPower
     * @return
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
     * @return an attribute of the Dish Washer.
     */
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
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
                int intValue = (int) value;
                return setCapacity(intValue);
            case 2:
                return setNominalPower(value);
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
        return 2;
    }

    @Override
    public List<String> getAttributeToString() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_CAPACITY);
        result.add(ATTRIBUTE_DURATION);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_CAPACITY:
                return mCapacity;
            case ATTRIBUTE_DURATION:
                return mDuration;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0;
        }
    }

    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_CAPACITY:
                if (attributeValue instanceof Integer) {
                    this.mCapacity = (Integer) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_DURATION:
                if (attributeValue instanceof Number) {
                    this.mDuration = ((Number) attributeValue).doubleValue();
                    return true;
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Number) {
                    this.mNominalPower = ((Number) attributeValue).doubleValue();
                    return true;
                }
                return false;
            default:
                return false;
        }
    }


}
