package pt.ipp.isep.dei.project.model.devices.microwaveoven;

import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MicrowaveOvenSpecs implements DeviceSpecs, Programmable {
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double time;
    private double nominalPower;
    private List<Program> programList;

    public MicrowaveOvenSpecs() {
        this.typeName = "MicrowaveOven";
        this.programList = new ArrayList<>();
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public boolean isProgrammable() {
        return true;
    }

    @Override
    public Programmable asProgrammable() {
        return this;
    }

    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }

    /**
     * get method of the energy consumption of an Electric Oven
     *
     * @return energy consumption
     */
    public double getEnergyConsumptionInADay() {
        return 0;
    }

    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * set method
     *
     * @param time
     * @return
     */
    public boolean setTime(Object time) {
        double microwaveOvenTime = (Double) time;
        if (Utils.isSameDouble(this.time, microwaveOvenTime)) {
            return false;
        }
        this.time = microwaveOvenTime;
        return true;
    }

    /**
     * set method to Nominal Power of an microwave oven
     *
     * @param nominalPower
     * @return
     */
    private boolean setNominalPower(Object nominalPower) {
        double nomPower = (Double) nominalPower;
        if (!Utils.isSameDouble(this.nominalPower, nomPower) && !(Utils.isSameDouble(nomPower, 0))) {
            this.nominalPower = nomPower;
            return true;
        }
        return false;
    }

    /**
     * method that get the attributes by strings.
     *
     * @return an attribute of the Electric Oven
     */
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Time: " + time + "\n");
        attributes.append("2 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }


    /**
     * method that get the number of the attributes of the device.
     *
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }

    /**
     * get metod
     *
     * @return list os specs of an Electric Oven
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_TIME);
        result.add(ATTRIBUTE_NOMINAL_POWER);
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
     *
     * @param attributeName  string name of the attribute
     * @param attributeValue value of the attribute
     * @return
     */
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

    /**
     * get method
     *
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }

    @Override
    public boolean addProgram(Program program) {
        if (!Objects.isNull(program) && !(getProgramList().contains(program))) {
            getProgramList().add(program);
            return true;
        }
        return false;
    }

    @Override
    public Program createNewProgram(String programName, ProgramSpecs specs) {
        return new TimeVariableProgram(programName, specs);
    }

}
