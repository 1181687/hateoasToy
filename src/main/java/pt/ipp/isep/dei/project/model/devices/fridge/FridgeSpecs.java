package pt.ipp.isep.dei.project.model.devices.fridge;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FridgeSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_FREEZER_CAPACITY = "Freezer Capacity";
    private static final String ATTRIBUTE_REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double freezerCapacity;
    private double refrigeratorCapacity;
    private double annualEnergyConsumption;
    private double nominalPower;

    public FridgeSpecs() {
        this.typeName = "Fridge";
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
     * get method
     *
     * @return energy consumption in a day
     */
    public double getEnergyConsumptionInADay() {
        return annualEnergyConsumption / 365;
    }

    /**
     * get method of the nominal power
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return nominalPower;
    }


    /**
     * set method of the freezer capacity
     *
     * @param freezerCapacity capacity of freezer
     * @return capacity of freezer
     */
    public boolean setFreezerCapacity(Object freezerCapacity) {
        double freezCapacity = (Double) freezerCapacity;
        if (!Utils.isSameDouble(this.freezerCapacity, freezCapacity) && !Utils.isSameDouble(freezCapacity, 0)) {
            this.freezerCapacity = freezCapacity;
            return true;
        }
        return false;
    }

    /**
     * set method ro refrigerator capacity
     *
     * @param refrigeratorCapacity capacity of refrigerator
     */
    public boolean setRefrigeratorCapacity(Object refrigeratorCapacity) {
        double refrigCapacity = (Double) refrigeratorCapacity;
        if (!Utils.isSameDouble(this.refrigeratorCapacity, refrigCapacity) && !Utils.isSameDouble(refrigCapacity, 0)) {
            this.refrigeratorCapacity = refrigCapacity;
            return true;
        }

        return false;
    }

    /**
     * set method of the annual energy consumption
     *
     * @param annualEnConsumption annual energy comsumption
     */
    public boolean setAnnualEnergyConsumption(Object annualEnConsumption) {
        double annualEConsumption = (Double) annualEnConsumption;
        if (!Utils.isSameDouble(this.annualEnergyConsumption, annualEConsumption) && !Utils.isSameDouble(annualEConsumption, 0)) {
            this.annualEnergyConsumption = annualEConsumption;
            return true;
        }

        return false;
    }

    /**
     * set method of the nominal power.
     *
     * @param fridgeNominalPower nominal power
     */
    public boolean setNominalPower(Object fridgeNominalPower) {
        double nPower = (Double) fridgeNominalPower;
        if (!Utils.isSameDouble(this.nominalPower, nPower) && !Utils.isSameDouble(nPower, 0)) {
            this.nominalPower = nPower;
            return true;
        }

        return false;
    }

    /**
     * method that displays a string of the choosen attribute (name of the attribute and its value)
     *
     * @return fridge attributes.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Freezer Capacity: " + freezerCapacity + "\n");
        attributes.append("2 - Refrigerator Capacity: " + refrigeratorCapacity + "\n");
        attributes.append("3 - Annual Energy Consumption: " + annualEnergyConsumption + "\n");
        attributes.append("4 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }


    /**
     * get method of the number of attributes
     *
     * @return number of FridgeSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 4;
    }


    /**
     * get method
     * @return list os specs of fridge
     */

    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_FREEZER_CAPACITY);
        result.add(ATTRIBUTE_REFRIGERATOR_CAPACITY);
        result.add(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION);
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
            case ATTRIBUTE_FREEZER_CAPACITY:
                return freezerCapacity;
            case ATTRIBUTE_REFRIGERATOR_CAPACITY:
                return refrigeratorCapacity;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                return annualEnergyConsumption;
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
            case ATTRIBUTE_FREEZER_CAPACITY:
                if (attributeValue instanceof Number) {
                    return setFreezerCapacity(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_REFRIGERATOR_CAPACITY:
                if (attributeValue instanceof Number) {
                    return setRefrigeratorCapacity(((Number) attributeValue).doubleValue());
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

    /**
     * get method
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}
