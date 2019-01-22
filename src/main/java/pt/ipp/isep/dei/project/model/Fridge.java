package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class Fridge implements DeviceSpecs {
    private String mTypeName;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private double mNominalPower;

    public Fridge(double freezerCapacity, double refrigeratorCapacity, double annualEnergyConsumption, double nominalPower) {
        this.mTypeName = "Fridge";
        this.mFreezerCapacity = freezerCapacity;
        this.mRefrigeratorCapacity = refrigeratorCapacity;
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
        this.mNominalPower = nominalPower;
    }

    /**
     * get method
     *
     * @return type of device
     */
    @Override
    public String getTypeName() {
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
    public double getNominalPower() {
        return mNominalPower;
    }


    /**
     * set method
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
     * set method
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
     * set method
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
     * set method
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
                return setFreezerCapacity(value);
            case 2:
                return setRefrigeratorCapacity(value);
            case 3:
                return setAnnualEnergyConsumption(value);
            case 4:
                return setNominalPower(value);
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
