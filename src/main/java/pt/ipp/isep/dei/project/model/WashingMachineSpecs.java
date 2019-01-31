package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class WashingMachineSpecs implements DeviceSpecs {
    private String mTypeName;
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;
    private double mNominalPower;
    private ProgramList mProgramList;

    public WashingMachineSpecs(double capacity, double nominalPower, ProgramList programList) {
        this.mTypeName = "Washing Machine";
        this.mCapacity = capacity;
        this.mNominalPower = nominalPower;
        this.mProgramList = programList;
    }

    public WashingMachineSpecs() {
    }

    /**
     * get method tht get the name of the device type.
     * @return type of device
     */
    @Override
    public String getTypeName() {
        return mTypeName;
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
     * get method
     * @return energy consumption
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumption;
    }

    /**
     * set method
     * @param capacity
     */
    public boolean setCapacity(double capacity) {
        if (Utils.isSameDouble(this.mCapacity, capacity)) {
            return false;
        }
        this.mCapacity = capacity;
        return true;
    }

    /**
     * set method
     * @param nominalPower
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
     * @return
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        return attributes.toString();
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
                return setCapacity(value);
            case 2:
                return setNominalPower(value);
            default:
                return false;
        }
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
