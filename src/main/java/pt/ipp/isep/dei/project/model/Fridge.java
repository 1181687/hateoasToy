package pt.ipp.isep.dei.project.model;

import java.util.Scanner;

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
        if (this.mFreezerCapacity == mFreezerCapacity) {
            return true;
        }
        return false;
    }

    public boolean setmRefrigeratorCapacity(double mRefrigeratorCapacity) {
        if (this.mRefrigeratorCapacity == mRefrigeratorCapacity) {
            return true;
        }
        return false;
    }

    public boolean setmAnnualEnergyConsumption(double mAnnualEnergyConsumption) {
        if (this.mAnnualEnergyConsumption == mAnnualEnergyConsumption) {
            return true;
        }
        return false;
    }

    public boolean setmNominalPower(double mNominalPower) {
        if (this.mNominalPower == mNominalPower) {
            return true;
        }
        return false;
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
        Scanner in = new Scanner(System.in);
        while (attribute < 1 || attribute > 4) {
            System.out.println("Select a number between 1 and 4");
            attribute = in.nextInt();
        }

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
}
