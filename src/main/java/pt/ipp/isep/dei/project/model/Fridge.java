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


    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    /**
     * @return
     */
    public double getEnergyConsumptionInADay() {
        return mAnnualEnergyConsumption / 365;
    }

    /**
     * @return
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    public boolean setmFreezerCapacity(double mFreezerCapacity) {
        if (Utils.isSameDouble(this.mFreezerCapacity, mFreezerCapacity)) {
            return false;
        }
        this.mFreezerCapacity = mFreezerCapacity;
        return true;
    }

    public boolean setmRefrigeratorCapacity(double mRefrigeratorCapacity) {
        if (Utils.isSameDouble(this.mRefrigeratorCapacity, mRefrigeratorCapacity)) {
            return false;
        }
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
        return true;
    }

    public boolean setmAnnualEnergyConsumption(double mAnnualEnergyConsumption) {
        if (Utils.isSameDouble(this.mAnnualEnergyConsumption, mAnnualEnergyConsumption)) {
            return false;
        }
        this.mAnnualEnergyConsumption = mAnnualEnergyConsumption;
        return true;
    }

    public boolean setmNominalPower(double mNominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, mNominalPower)) {
            return false;
        }
        this.mNominalPower = mNominalPower;
        return true;
    }

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
        System.out.println("Please select a valid number.");
        return false;
    }

    @Override
    public int getNumberOfAttributes() {
        return 4;
    }
}
