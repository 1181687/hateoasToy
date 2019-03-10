package pt.ipp.isep.dei.project.model.devices.lamp;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LampSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_LUMINOUS_FLUX = "Luminous Flux";
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double luminousFlux;
    private double time;
    private double nominalPower;

    public LampSpecs() {
        this.typeName = "Lamp";
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
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
     * @param luminousFlux
     * @return
     */
    public boolean setLuminousFlux(Object luminousFlux) {
        double lumFLux = (Double) luminousFlux;
        if (Utils.isSameDouble(this.luminousFlux, lumFLux)) {
            return false;
        }
        this.luminousFlux = lumFLux;
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
        if (Utils.isSameDouble(this.time, lampTime)) {
            return false;
        }
        this.time = lampTime;
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
        if (Utils.isSameDouble(this.nominalPower, lampNomPower)) {
            return false;
        }
        this.nominalPower = lampNomPower;
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
        attributes.append("1 - Luminous Flux: " + luminousFlux + "\n");
        attributes.append("2 - Nominal Power: " + nominalPower + "\n");
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

    /**
     * get method
     * @return list os specs of lamp
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_LUMINOUS_FLUX);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    /**
     * get method
     * @param attributeName string name of the attribute
     * @return  attribute
     */

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_LUMINOUS_FLUX:
                return luminousFlux;
            case ATTRIBUTE_TIME:
                return time;
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            default:
                return -1;
        }
    }

    /**
     * set method
     * @param attributeName string name of the attribute
     * @param attributeValue value of the attribute
     * @return
     */
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

    /**
     * get method
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }

}
