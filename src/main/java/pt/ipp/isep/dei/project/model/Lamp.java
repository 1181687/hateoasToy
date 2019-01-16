package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class Lamp implements DeviceSpecs {
    private String mTypeName ;
    private double mLuminousFlux;
    private double mTime;
    private double mNominalPower;

    public Lamp(double mLuminousFlux, double mNominalPower) {
        this.mTypeName = "Lamp";
        this.mLuminousFlux = mLuminousFlux;
        this.mNominalPower = mNominalPower;
    }


    @Override
    public String getmTypeName() {
        return mTypeName;
    }


    /**
     * get Method
     *
     * @return nominal power
     */
    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    /**
     * get method
     * @return energy consumption in a Day
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return mNominalPower * mTime;
    }

    /**
     * set method
     * @param mLuminousFlux
     * @return
     */
    public boolean setmLuminousFlux(double mLuminousFlux) {
        if (Utils.isSameDouble(this.mLuminousFlux, mLuminousFlux)) {
            return false;
        }
        this.mLuminousFlux = mLuminousFlux;
        return true;
    }

    /**
     * set method
     *
     * @param mTime
     * @return
     */
    public boolean setmTime(double mTime) {
        if (Utils.isSameDouble(this.mTime, mTime)) {
            return false;
        }
        this.mTime = mTime;
        return true;
    }

    /**
     * set method
     * @param mNominalPower
     * @return
     */
    public boolean setmNominalPower(double mNominalPower) {
        if (Utils.isSameDouble(this.mNominalPower, mNominalPower)) {
            return false;
        }
        this.mNominalPower = mNominalPower;
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Luminous Flux: " + mLuminousFlux + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String lampAttributes = attributes.toString();
        return lampAttributes;
    }

    @Override
    public boolean setAttribute(int attribute, double value) {
        switch (attribute) {
            case 1:
                return setmLuminousFlux(value);
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
