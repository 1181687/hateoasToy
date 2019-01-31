package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class FridgeSpecs implements DeviceSpecs {
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
    }

    /**
     * get method of the type name.
     *
     * @return type of device
     */
    @Override
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
}
