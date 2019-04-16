package pt.ipp.isep.dei.project.model.devices.fan;

import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FanSpecs implements DeviceSpecs, Programmable {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";

    private String typeName;
    private double nominalPower;
    private List<Program> programList;

    public FanSpecs() {
        this.typeName = "Fan";
        this.programList = new ArrayList<>();
    }

    @Override
    public double getEnergyConsumptionInADay() {
        return 0;
    }

    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * method that gets the attributes as strings.
     *
     * @return attributes of the Fan
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Nominal Power: " + this.nominalPower + "\n");
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
        if (attributeName.equals(ATTRIBUTE_NOMINAL_POWER)) {
            return nominalPower;
        }
        return NOT_VALID_ATTRIBUTE;
    }

    /**
     * set method to Nominal Power of a fan
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
     * set method
     *
     * @param attributeName  string name of the attribute
     * @param attributeValue value of the attribute
     * @return
     */
    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName.equals(ATTRIBUTE_NOMINAL_POWER) && attributeValue instanceof Number) {
            return setNominalPower(((Number) attributeValue).doubleValue());
        }
        return false;
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