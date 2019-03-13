package pt.ipp.isep.dei.project.model.devices.walltowelheater;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WallTowelHeaterSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_TIME = "Time";

    private String typeName;
    private double time;
    private double nominalPower;

    public WallTowelHeaterSpecs() {
        this.typeName = "WallTowelHeater";
    }

    @Override
    public double getEnergyConsumptionInADay() {
        return this.nominalPower * this.time;
    }

    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * set method
     *
     * @param nominalPower
     * @return
     */
    private boolean setNominalPower(Object nominalPower) {
        double lampNomPower = (Double) nominalPower;
        if (Utils.isSameDouble(this.nominalPower, lampNomPower)) {
            return false;
        }
        this.nominalPower = lampNomPower;
        return true;
    }

    /**
     * set method
     *
     * @param time
     * @return
     */
    private boolean setTime(Object time) {
        double lampTime = (Double) time;
        if (Utils.isSameDouble(this.time, lampTime)) {
            return false;
        }
        this.time = lampTime;
        return true;
    }

    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }

    @Override
    public int getNumberOfAttributes() {
        return 1;
    }

    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_TIME:
                return time;
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            default:
                return NOT_VALID_ATTRIBUTE;
        }
    }

    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
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

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
    }
}
