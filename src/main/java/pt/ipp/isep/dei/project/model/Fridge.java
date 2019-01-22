package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class Fridge implements DeviceSpecs {
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
     * get method of the type name.
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

    /** get method of the nominal power
     * @return nominal power
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }


    /**
     * set method of the freezer capacity
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
     * set method ro refrigerator capacity
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
     * set method of the annual energy consumption
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
     * set method of the nominal power.
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
     * @return fridge attributes.
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
     * get method of the number of attributes
     * @return number of Fridge attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 4;
    }
}
