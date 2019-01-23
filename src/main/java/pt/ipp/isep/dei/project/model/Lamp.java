package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

public class Lamp implements DeviceSpecs {
    private String mTypeName ;
    private double mLuminousFlux;
    private double mTime;
    private double mNominalPower;

    public Lamp(double luminousFlux, double nominalPower) {
        this.mTypeName = "Lamp";
        this.mLuminousFlux = luminousFlux;
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


    /**
     * get Method
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
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
     * @param luminousFlux
     * @return
     */
    public boolean setLuminousFlux(double luminousFlux) {
        if (Utils.isSameDouble(this.mLuminousFlux, luminousFlux)) {
            return false;
        }
        this.mLuminousFlux = luminousFlux;
        return true;
    }

    /**
     * set method
     * @param time
     * @return
     */
    public boolean setTime(double time) {
        if (Utils.isSameDouble(this.mTime, time)) {
            return false;
        }
        this.mTime = time;
        return true;
    }

    /**
     * set method
     * @param nominalPower
     * @return
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
        attributes.append("1 - Luminous Flux: " + mLuminousFlux + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String lampAttributes = attributes.toString();
        return lampAttributes;
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
                return setLuminousFlux(value);
            case 2:
                return setNominalPower(value);
        }
        return false;
    }

    /**
     * get method
     * @return number of Fridge attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }
}
