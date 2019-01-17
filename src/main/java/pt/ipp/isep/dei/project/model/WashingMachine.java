package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine implements DeviceSpecs {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION = "Energy consumption of the program";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal power";
    private static final String ATTRIBUTE_LIST_OF_PROGRAMS = "List of programs";

    private String mTypeName;
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;
    private double mNominalPower;
    private List<Program> mProgramList;

    public WashingMachine(double capacity, double nominalPower, List<Program> programList) {
        this.mTypeName = "Washing Machine";
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
        result.add(ATTRIBUTE_ENERGY_CONSUMPTION);
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
            case ATTRIBUTE_ENERGY_CONSUMPTION:
                return mEnergyConsumption;
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
            case ATTRIBUTE_ENERGY_CONSUMPTION:
                if (attributeValue instanceof Double) {
                    this.mEnergyConsumption = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
                /*
            case ATTRIBUTE_LIST_OF_PROGRAMS:
                if (attributeValue instanceof List) {
                    this.mProgramList = (List<Program>) attributeValue;
                    return true;
                }
                return false;*/
            default:
                return false;
        }
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

    /**
     * get method
     *
     * @return nominal power
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    /**
     * get method
     * @return energy consumption
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumption;
    }

    /**
     * set method
     * @param mCapacity
     */
    public boolean setmCapacity(double mCapacity) {
        if (Utils.isSameDouble(this.mCapacity, mCapacity)) {
            return false;
        }
        this.mCapacity = mCapacity;
        return true;
    }

    /**
     * set method
     * @param mNominalPower
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
     * @return
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String dishWasherAttributes = attributes.toString();
        return dishWasherAttributes;
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
                return setmCapacity(value);
            case 2:
                return setmNominalPower(value);
        }
        return false;
    }

    /**
     * get method
     * @return number of Washing Machine attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }

    /**
     * Method that adds a program to the list of Programs.
     *
     * @param program the program to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the device is null
     */




}
