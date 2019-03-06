package pt.ipp.isep.dei.project.model.devices.tv;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TVSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_STANDBY_POWER = "Standby Power";
    private static final String ATTRIBUTE_TIME = "Time";

    private String typeName;
    private double nominalPower;
    private double standbyPower;
    private double time;

    public TVSpecs() {
        this.typeName = "TV";
    }

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
        double standbyPowa = (Double) standbyPower;
        if (Utils.isSameDouble(this.standbyPower, standbyPowa)) {
            return false;
        }
        this.standbyPower = standbyPowa;
        return true;
    }

    /**
     * set method
     *
     * @param time
     * @return
     */
    public boolean setTime(Object time) {
        double tvTime = (Double) time;
        if (Utils.isSameDouble(this.time, tvTime)) {
            return false;
        }
        this.time = tvTime;
        return true;
    }

    /**
     * set method
     *
     * @param nominalPower
     * @return
     */
    public boolean setNominalPower(Object nominalPower) {
        double tvNomPower = (Double) nominalPower;
        if (Utils.isSameDouble(this.nominalPower, tvNomPower)) {
            return false;
        }
        this.nominalPower = tvNomPower;
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
     * @return number of FridgeSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 3;
    }

    /**
     * get method
     *
     * @return list os specs of lamp
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
                return -1;
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
     * get method
     *
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}

