package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LampSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_LUMINOUS_FLUX = "Luminous Flux";
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String mTypeName;
    private double mLuminousFlux;
    private double mTime;
    private double mNominalPower;

    public LampSpecs(double luminousFlux, double nominalPower) {
        this.mTypeName = "Lamp";
        this.mLuminousFlux = luminousFlux;
        this.mNominalPower = nominalPower;
    }

    public LampSpecs() {
        this.mTypeName = "Lamp";
    }

    public String getTypeName() {
        return mTypeName;
    }

    /**
     * get Method
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return mNominalPower;
    }

    /**
     * get method
     *
     * @return energy consumption in a Day
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return mNominalPower * mTime;
    }

    /**
     * set method
     *
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
     *
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
     *
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
     *
     * @return
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Luminous Flux: " + mLuminousFlux + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
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
                return setLuminousFlux(value);
            case 2:
                return setNominalPower(value);
            default:
                return false;
        }
    }

    /**
     * get method
     *
     * @return number of FridgeSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }

    @Override
    public List<String> getAttributeToString() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_LUMINOUS_FLUX);
        result.add(ATTRIBUTE_TIME);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_LUMINOUS_FLUX:
                return mLuminousFlux;
            case ATTRIBUTE_TIME:
                return mTime;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0;
        }
    }

    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_LUMINOUS_FLUX:
                if (attributeValue instanceof Double) {
                    this.mLuminousFlux = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_TIME:
                if (attributeValue instanceof Double) {
                    this.mTime = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
