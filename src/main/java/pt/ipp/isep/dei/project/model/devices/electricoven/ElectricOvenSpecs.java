package pt.ipp.isep.dei.project.model.devices.electricoven;

import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElectricOvenSpecs implements DeviceSpecs, Programmable {
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double time;
    private double nominalPower;
    private List<Program> programList;

    public ElectricOvenSpecs() {

        this.typeName = "ElectricOven";
        this.programList = new ArrayList<>();
    }

    public String getTypeName() {
        return typeName;
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


    /**
     * get method of the nominal power of an Electric Oven
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
     * @param time
     * @return
     */
    public boolean setTime(Object time) {
        double electricOvenTime = (Double) time;
        if (Utils.isSameDouble(this.time, electricOvenTime)) {
            return false;
        }
        this.time = electricOvenTime;
        return true;
    }

    /**
     * set method to Nominal Power of an Electric Oven
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
        attributes.append("1 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }


    /**
     * method that get the number of the attributes of the device.
     *
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 1;
    }

    /**
     * get metod
     *
     * @return list os specs of an Electric Oven
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
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
    public boolean addProgram(Program program) {
        if (!Objects.isNull(program) && !(programList.contains(program))) {
            this.programList.add(program);
            return true;
        }
        return false;
    }

    @Override
    public Program createNewProgram(String programName) {
        ProgramSpecs specs = new TimeVariableProgramSpecs();
        return new TimeVariableProgram(programName, specs);
    }
}
