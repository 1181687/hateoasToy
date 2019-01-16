package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class DishWasher implements DeviceSpecs {
    private String mTypeName;
    private int mCapacity;
    private double mDuration;
    private double mEnergyConsumptionProgram1;
    private double mNominalPower;

    public DishWasher(int mCapacity, double mNominalPower) {
        this.mTypeName = "Dish Washer";
        this.mCapacity = mCapacity;
        this.mNominalPower = mNominalPower;
    }


    /**
     * @return
     */
    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    /**
     * @return
     */
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumptionProgram1;
    }

    /**
     * @return
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    public boolean setmCapacity(int mCapacity) {
        if (Utils.isSameDouble(this.mCapacity, mCapacity)) {
            return false;
        }
        this.mCapacity = mCapacity;
        return true;
    }

    public boolean setmNominalPower(double mNominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, mNominalPower)) {
            return false;
        }
        this.mNominalPower = mNominalPower;
        return true;
    }

    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String dishWasherAttributes = attributes.toString();
        return dishWasherAttributes;
    }

    @Override
    public boolean setAttribute(int attribute, double value) {
        switch (attribute) {
            case 1:
                int intValue = (int) value;
                return setmCapacity(intValue);
            case 2:
                return setmNominalPower(value);
        }
        System.out.println("Please select a valid number.");
        return false;
    }

    @Override
    public int getNumberOfAttributes() {
        return 2;
    }
}
