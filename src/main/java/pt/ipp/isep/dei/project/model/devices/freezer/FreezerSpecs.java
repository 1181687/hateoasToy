package pt.ipp.isep.dei.project.model.devices.freezer;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FreezerSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_FREEZER_CAPACITY = "Freezer Capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double freezerCapacity;
    private double annualEnergyConsumption;
    private double nominalPower;

    public FreezerSpecs() {
        this.typeName = "Freezer";
    }

    /**
     * get method
     *
     * @return energy consumption
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return Utils.round((this.annualEnergyConsumption / 365), 2);
    }

    /**
     * get method
     *
     * @return freezer capacity
     */
    public double getFreezerCapacity() {
        return this.freezerCapacity;
    }

    public boolean setFreezerCapacity(Object freezerCapacity) {
        double newFreezerCapacity = (Double) freezerCapacity;
        if (Utils.isSameDouble(this.annualEnergyConsumption, newFreezerCapacity) || Utils.isSameNumber(newFreezerCapacity, 0)) {
            return false;
        }
        this.freezerCapacity = newFreezerCapacity;
        return true;
    }

    public double getAnnualEnergyConsumption() {
        return this.annualEnergyConsumption;
    }

    public boolean setAnnualEnergyConsumption(Object energyConsumption) {
        double annualEnergyConsumption = (Double) energyConsumption;
        if (Utils.isSameDouble(this.annualEnergyConsumption, annualEnergyConsumption) || Utils.isSameDouble(annualEnergyConsumption, 0)) {
            return false;
        }
        this.annualEnergyConsumption = annualEnergyConsumption;
        return true;
    }

    /**
     * get method
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    public boolean setNominalPower(Object nominalPower) {
        double freezerNomPower = (Double) nominalPower;
        if (Utils.isSameDouble(this.nominalPower, freezerNomPower)) {
            return false;
        }
        this.nominalPower = freezerNomPower;
        return true;
    }

    /**
     * method that displays a string of the chosen attribute (name of the attribute and its value)
     *
     * @return
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Freezer Capacity: " + freezerCapacity + "\n");
        attributes.append("2 - Annual Energy Consumption: " + annualEnergyConsumption + "\n");
        attributes.append("3 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }

    /**
     * get method
     *
     * @return number of Washing Machine attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 3;
    }

    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_FREEZER_CAPACITY);
        result.add(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_FREEZER_CAPACITY:
                return freezerCapacity;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                return annualEnergyConsumption;
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            default:
                return -1;
        }
    }

    @Override
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }

    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_FREEZER_CAPACITY:
                if (attributeValue instanceof Number) {
                    return setFreezerCapacity(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                if (attributeValue instanceof Number) {
                    return setAnnualEnergyConsumption(((Number) attributeValue).doubleValue());
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
}
