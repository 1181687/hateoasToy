package pt.ipp.isep.dei.project.model.devices.winecooler;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WineCoolerSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_NUMBER_OF_BOTTLES = "Number of Bottles";
    private static final String ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION = "Annual Energy Consumption";

    private String typeName;
    private double nominalPower;
    private int numberOfBottles;
    private double annualEnergyConsumption;

    public WineCoolerSpecs() {
        this.typeName = "WineCooler";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * boolean method "Is programmable"
     *
     * @return returns false because the winecooler is not a programmable device
     */
    @Override
    public boolean isProgrammable() {
        return false;
    }

    /**
     *
     * this class implements the Interface Device Specs which in turn has the signature of the method as programmable
     * @return since the winecooler doesn't implement a program the return is null
     *
     */
    @Override
    public Programmable asProgrammable() {
        return null;
    }

    /**
     * get method
     *
     * @return type of device
     */
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
        double wineCoolerNomPower = (Double) nominalPower;
        if (Utils.isSameDouble(this.nominalPower, wineCoolerNomPower)) {
            return false;
        }
        this.nominalPower = wineCoolerNomPower;
        return true;
    }

    /**
     * set method
     *
     * @param numberOfBottles
     * @return
     */
    private boolean setNumberOfBottles(Object numberOfBottles) {
        int wineCoolerNumberOfBottles = (Integer) numberOfBottles;
        if (!Utils.isSameDouble(this.numberOfBottles, wineCoolerNumberOfBottles) && !(Utils.isSameDouble(wineCoolerNumberOfBottles, 0))) {
            this.numberOfBottles = wineCoolerNumberOfBottles;
            return true;
        }
        return false;
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
     * set method of the annual energy consumption
     *
     * @param annualEnergyConsumption annual energy comsumption
     */
    public boolean setAnnualEnergyConsumption(Object annualEnergyConsumption) {
        double annualEConsumption = (Double) annualEnergyConsumption;
        if (!Utils.isSameDouble(this.annualEnergyConsumption, annualEConsumption) && !Utils.isSameDouble(annualEConsumption, 0)) {
            this.annualEnergyConsumption = annualEConsumption;
            return true;
        }

        return false;
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
        attributes.append("2 - Number of Bottles: " + numberOfBottles + "\n");
        attributes.append("3 - Annual Energy Consumption: " + annualEnergyConsumption + "\n");

        return attributes.toString();
    }

    /**
     * get method
     *
     * @return number of winecoolerSpecs attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 3;
    }

    /**
     * get method
     *
     * @return list os specs of winecooler
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_NOMINAL_POWER);
        result.add(ATTRIBUTE_NUMBER_OF_BOTTLES);
        result.add(ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION);

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
            case ATTRIBUTE_NUMBER_OF_BOTTLES:
                return numberOfBottles;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                return annualEnergyConsumption;
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
            case ATTRIBUTE_NUMBER_OF_BOTTLES:
                if (attributeValue instanceof Number) {
                    return setNumberOfBottles(((Number) attributeValue).intValue());
                }
                return false;
            case ATTRIBUTE_ANNUAL_ENERGY_CONSUMPTION:
                if (attributeValue instanceof Number) {
                    return setAnnualEnergyConsumption(((Number) attributeValue).doubleValue());
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

