package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Lamp implements DeviceSpecs {
    private static final String ATTRIBUTE_LUMINIOUS_FLUX = "Luminous flux";
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal power";

    private String mTypeName ;
    private double mLuminousFlux;
    private double mTime;
    private double mNominalPower;

    public Lamp(double mLuminousFlux, double mNominalPower) {
        this.mTypeName = "Lamp";
        this.mLuminousFlux = mLuminousFlux;
        this.mNominalPower = mNominalPower;
    }

    /**
     * TODO - LUÍS
     *
     * @return
     */
    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_LUMINIOUS_FLUX);
        result.add(ATTRIBUTE_TIME);
        result.add(ATTRIBUTE_NOMINAL_POWER);
        return result;
    }

    /**
     * TODO - LUÍS
     *
     * @param attributeName
     * @return
     */
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_LUMINIOUS_FLUX:
                return mLuminousFlux;
            case ATTRIBUTE_TIME:
                return mTime;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0;
        }
    }

    /**
     * TODO - LUÍS
     *
     * @param attributeName
     * @param attributeValue
     * @return
     */
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_LUMINIOUS_FLUX:
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

    /**
     * get method
     *
     * @return type of device
     */
    @Override
    public String getmTypeName() {
        return mTypeName;
    }


    /**
     * get Method
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
                return setmLuminousFlux(value);
            case 2:
                return setmNominalPower(value);
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
