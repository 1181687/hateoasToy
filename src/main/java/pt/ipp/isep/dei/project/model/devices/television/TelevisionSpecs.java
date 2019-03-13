package pt.ipp.isep.dei.project.model.devices.television;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TelevisionSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_STANDBY_POWER = "Standby Power";
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String NOT_VALID_ATTRIBUTE = "Not a valid attribute";

    private String typeName;
    private double nominalPower;
    private double standbyPower;
    private double time;


    public TelevisionSpecs() {
        this.typeName = "Television";
    }

    /**
     * boolean method "Is programmable"
     * returns false because the television is not a programmable device
     */
    @Override
    public boolean isProgrammable() {
        return false;
    }

    /**
     * this class implements the Interface Device Specs which in turn has the signature of the method as programmable
     *
     * @return since the television doesn't implement a program the return is null
     */
    @Override
    public Programmable asProgrammable() {
        return null;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * get Method
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return nominalPower;
    }

    /**
     * set method
     *
     * @param nominalPower
     * @return
     */
    public boolean setNominalPower(Object nominalPower) {
        double nomPower = (Double) nominalPower;
        if (!Utils.isSameDouble(this.nominalPower, nomPower) && !(Utils.isSameDouble(nomPower, 0))) {
            this.nominalPower = nomPower;
            return true;
        }
        return false;
    }

    /**
     * get method
     *
     * @return energy consumption in a Day
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return nominalPower * time;
    }

    /**
     * set method
     *
     * @param standbyPower
     * @return
     */
    public boolean setStandbyPower(Object standbyPower) {
        double standPower = (Double) standbyPower;
        if (!Utils.isSameDouble(this.standbyPower, standPower) && !(Utils.isSameDouble(standPower, 0))) {
            this.standbyPower = standPower;
            return true;
        }
        return false;
    }

    /**
     * set method
     *
     * @param time
     * @return
     */
    public boolean setTime(Object time) {
        double lampTime = (Double) time;
        if (Utils.isSameDouble(this.time, lampTime)) {
            return false;
        }
        this.time = lampTime;
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
        attributes.append("1 - Nominal Power: " + nominalPower + "\n");
        attributes.append("2 - Standby Power: " + standbyPower + "\n");
        attributes.append("3 - Time: " + time + "\n");
        return attributes.toString();
    }


    /**
     * get method
     *
     * @return number of televisonSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 3;
    }

    /**
     * get method
     *
     * @return list os specs of television
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_NOMINAL_POWER);
        result.add(ATTRIBUTE_STANDBY_POWER);
        result.add(ATTRIBUTE_TIME);
        return result;
    }

    /**
     * get method
     *
     * @param attributeName string name of the attribute
     * @return attribute
     */

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            case ATTRIBUTE_STANDBY_POWER:
                return standbyPower;
            case ATTRIBUTE_TIME:
                return time;
            default:
                return NOT_VALID_ATTRIBUTE;
        }
    }

    /**
     * set method
     *
     * @param attributeName  string name of the attribute
     * @param attributeValue value of the attribute
     * @return
     */
    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Number) {
                    return setNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_STANDBY_POWER:
                if (attributeValue instanceof Number) {
                    return setStandbyPower(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_TIME:
                if (attributeValue instanceof Number) {
                    return setTime(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * get string of the type of attribute
     *
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     * if not a valid attribute, returns a String "not a valid attribute"
     */
    @Override
    public String getAttributeDataType(String attributeName) {
        if (NOT_VALID_ATTRIBUTE.equals(getAttributeValue(attributeName))) {
            return NOT_VALID_ATTRIBUTE;
        }
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}

