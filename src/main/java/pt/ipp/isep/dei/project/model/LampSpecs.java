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
    public boolean setLuminousFlux(Object luminousFlux) {
        double lumFLux = (Double) luminousFlux;
        if (Utils.isSameDouble(this.mLuminousFlux, lumFLux)) {
            return false;
        }
        this.mLuminousFlux = lumFLux;
        return true;
    }

    /**
     * set method
     *
     * @param time
     * @return
     */
    public boolean setTime(Object time) {
        double lampTime = (Double) time;
        if (Utils.isSameDouble(this.mTime, lampTime)) {
            return false;
        }
        this.mTime = lampTime;
        return true;
    }

    /**
     * set method
     *
     * @param nominalPower
     * @return
     */
    public boolean setNominalPower(Object nominalPower) {
        double lampNomPower = (Double) nominalPower;
        if (Utils.isSameDouble(this.mNominalPower, lampNomPower)) {
            return false;
        }
        this.mNominalPower = lampNomPower;
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
     * get method
     *
     * @return number of FridgeSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }

    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add("1 - " + ATTRIBUTE_LUMINOUS_FLUX + " - " + mLuminousFlux);
        result.add("2 - " + ATTRIBUTE_NOMINAL_POWER + " - " + mNominalPower);

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
                if (attributeValue instanceof Number) {
                    return setLuminousFlux(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_TIME:
                if (attributeValue instanceof Number) {
                    return setTime(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Number) {
                    return setNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }
}
