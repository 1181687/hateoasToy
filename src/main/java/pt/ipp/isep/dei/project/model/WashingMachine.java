package pt.ipp.isep.dei.project.model;

public class WashingMachine implements DeviceSpecs {
    private String mTypeName ;
    private double mCapacity;
    private double mDuration;
    private double mEnergyConsumption;
    private double mNominalPower;

    public WashingMachine(double mCapacity, double mNominalPower) {
        this.mTypeName = "Washing Machine";
        this.mCapacity = mCapacity;
        this.mNominalPower = mNominalPower;
    }

    public WashingMachine() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    @Override
    public double getEnergyConsumptionInADay() {
        return mEnergyConsumption;
    }

    public boolean setmCapacity(double mCapacity) {
        if (this.mCapacity == mCapacity) {
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
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String dishWasherAttributes = attributes.toString();
        return dishWasherAttributes;
    }

    @Override
    public boolean setAttribute(int attribute, double value) {
        switch (attribute) {
            case 1:
                return setmCapacity(value);
            case 2:
                return setmNominalPower(value);
        }
        System.out.println("Please select a valid number.");
        return false;
    }
}
