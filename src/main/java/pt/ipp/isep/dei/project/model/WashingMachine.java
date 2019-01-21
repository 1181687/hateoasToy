package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class WashingMachine implements DeviceSpecs {
    private String mTypeName;
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;
    private double mNominalPower;
    private ProgramList mProgramList;

    public WashingMachine(double capacity, double nominalPower, ProgramList programList) {
        this.mTypeName = "Washing Machine";
        this.mCapacity = capacity;
        this.mNominalPower = nominalPower;
        this.mProgramList = programList;
    }

    /**
     * get method tht get the name of the device type.
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
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 2.");
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
}
