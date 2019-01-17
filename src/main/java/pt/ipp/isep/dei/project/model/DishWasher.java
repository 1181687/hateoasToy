package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DishWasher implements DeviceSpecs {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION_PROGRAM1 = "Energy consumption of the program";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal power";
    private static final String ATTRIBUTE_LIST_OF_PROGRAMS = "List of programs";

    private String mTypeName;
    private int mCapacity;
    private double mDuration;
    private double mEnergyConsumptionProgram1;
    private double mNominalPower;
    private ProgramList mProgramList;

    public DishWasher(int capacity, double nominalPower, ProgramList programList) {
        this.mTypeName = "Dish Washer";
        this.mCapacity = capacity;
        this.mNominalPower = nominalPower;
        this.mProgramList = programList;
    }

    /**
     * TODO - LUÍS
     *
     * @return
     */
    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_CAPACITY);
        result.add(ATTRIBUTE_DURATION);
        result.add(ATTRIBUTE_ENERGY_CONSUMPTION_PROGRAM1);
        result.add(ATTRIBUTE_NOMINAL_POWER);
        result.add(ATTRIBUTE_LIST_OF_PROGRAMS);
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
            case ATTRIBUTE_CAPACITY:
                return mCapacity;
            case ATTRIBUTE_DURATION:
                return mDuration;
            case ATTRIBUTE_ENERGY_CONSUMPTION_PROGRAM1:
                return mEnergyConsumptionProgram1;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            case ATTRIBUTE_LIST_OF_PROGRAMS:
                return mProgramList;
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
            case ATTRIBUTE_CAPACITY:
                if (attributeValue instanceof Integer) {
                    this.mCapacity = (Integer) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_DURATION:
                if (attributeValue instanceof Double) {
                    this.mDuration = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_ENERGY_CONSUMPTION_PROGRAM1:
                if (attributeValue instanceof Double) {
                    this.mEnergyConsumptionProgram1 = (Double) attributeValue;
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
        content.append("1 - " + ATTRIBUTE_CAPACITY);
        content.append("\n");
        content.append("2 - " + ATTRIBUTE_NOMINAL_POWER);
        content.append("\n");
        return content.toString();
    }

    /**
     * Get method
     * @return type of device
     */
    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    /**
     * get method
     * @return energy consumption
     */
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumptionProgram1;
    }


    /**
     * get method
     * @return nominal power
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    /**
     * set method
     *
     * @param mCapacity
     * @return
     */
    public boolean setmCapacity(int mCapacity) {
        if (Utils.isSameDouble(this.mCapacity, mCapacity)) {
            return false;
        }
        this.mCapacity = mCapacity;
        return true;
    }

    /**
     * set method
     *
     * @param mNominalPower
     * @return
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
     * @return an attribute of the Dish Washer.
     */
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String dishWasherAttributes = attributes.toString();
        return dishWasherAttributes;
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
                int intValue = (int) value;
                return setmCapacity(intValue);
            case 2:
                return setmNominalPower(value);
        }
        return false;
    }

    /**
     * method that get the number of the attributes of the device.
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }


}
