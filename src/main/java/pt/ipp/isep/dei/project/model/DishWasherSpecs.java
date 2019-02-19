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

    public DishWasherSpecs() {
        this.mTypeName = "Dishwasher";
    }

    public String getTypeName() {
        return mTypeName;
    }

    /**
     * get method of the energy consumption of a DishWasher
     *
     * @return energy consumption
     */
    public double getEnergyConsumptionInADay() {
        return 0;
    }


    /**
     * get method of the nominal power of a DishWasher
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
    private boolean setCapacity(Object capacity) {
        int dWCapacity = (Integer) capacity;
        if (!Utils.isSameDouble(this.mCapacity, dWCapacity) && !(Utils.isSameDouble(dWCapacity, 0))) {
            this.mCapacity = dWCapacity;
            return true;
        }
        return false;
    }

    /**
     * set method
     *
     * @param duration
     * @return
     */
    private boolean setDuration(Object duration) {
        Double dWDuration = (Double) duration;
        if (!Utils.isSameDouble(this.mDuration, dWDuration) && !(Utils.isSameDouble(dWDuration, 0))) {
            this.mDuration = dWDuration;
            return true;
        }
        return false;
    }

    /**
     * set method to Nominal Power of a DishWasher
     *
     * @param nominalPower
     * @return
     */
    private boolean setNominalPower(Object nominalPower) {
        double nomPower = (Double) nominalPower;
        if (!Utils.isSameDouble(this.mNominalPower, nomPower) && !(Utils.isSameDouble(nomPower, 0))) {
            this.mNominalPower = nomPower;
            return true;
        }
        return false;
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
     * method that get the number of the attributes of the device.
     *
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }

    /**
     * get metod
     * @return list os specs of dishwasher
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_CAPACITY);
        result.add(ATTRIBUTE_NOMINAL_POWER);
        return result;
    }

    /**
     * get method
     * @param attributeName string name of the attribute
     * @return  attribute
     */
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
                return -1;
        }
    }

    /**
     * set method
     * @param attributeName string name of the attribute
     * @param attributeValue value of the attribute
     * @return
     */
    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_CAPACITY:
                if (attributeValue instanceof Number) {
                    return setCapacity(((Number) attributeValue).intValue());
                }
                return false;
            case ATTRIBUTE_DURATION:
                if (attributeValue instanceof Number) {
                    return setDuration(((Number) attributeValue).doubleValue());
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

    /**
     * get method
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}
