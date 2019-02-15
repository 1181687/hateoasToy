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
    public boolean setCapacity(Object capacity) {
        int intCapacity = (Integer) capacity;
        if (Utils.isSameDouble(this.mCapacity, intCapacity)) {
            return false;
        }
        this.mCapacity = intCapacity;
        return true;
    }

    /**
     * set method to Nominal Power
     *
     * @param nominalPower
     * @return
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
     * @return an attribute of the Dish Washer.
     */
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("> " + ATTRIBUTE_CAPACITY + ": " + mCapacity + "\n");
        attributes.append("> " + ATTRIBUTE_NOMINAL_POWER + ": " + mNominalPower + "\n");
        return attributes.toString();
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
    public List<String> getSpecsList() {
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
                    return setCapacity(attributeValue);
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
                    return setNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }


}
