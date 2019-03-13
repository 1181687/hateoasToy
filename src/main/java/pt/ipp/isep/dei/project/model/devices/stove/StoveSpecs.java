package pt.ipp.isep.dei.project.model.devices.stove;

import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoveSpecs implements DeviceSpecs, Programmable {

    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double nominalPower;
    private List<Program> programList;


    public StoveSpecs() {
        this.typeName = "Stove";
        this.programList = new ArrayList<>();
    }

    @Override
    public double getEnergyConsumptionInADay() {
        return 0;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * gets the nominal power
     *
     * @return nominal power double
     */
    @Override
    public double getNominalPower() {
        return this.nominalPower;
    }

    /**
     * method that gets the Nominal power attribute to string.
     *
     * @return nominal power attribute and its value to string
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Nominal Power: " + this.nominalPower + "\n");
        return attributes.toString();
    }

    /**
     * method that gets the number of attributes of the StoveSpecs (Nominal power).
     *
     * @return the number of attributes integer.
     */
    @Override
    public int getNumberOfAttributes() {
        return 1;
    }

    /**
     * gets SpecsList with spec (nominal power) of a Stove
     *
     * @return list of spec (nominal Power) of a Stove
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_NOMINAL_POWER);
        return result;
    }

    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }


    /**
     * gets attribute Object when given attribute name as String
     *
     * @param attributeName string name of the attribute
     * @return Objet attribute, or in case the given attribute name is wrong,
     * returns "not a valid attribute"
     */
    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            default:
                return NOT_VALID_ATTRIBUTE;
        }
    }


    /**
     * set Nominal Power of a stove
     *
     * @param nominalPower given nominal power
     * @return true if sets, false if not (if is the same time or zero
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
     * set attribute nominal Power
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
            default:
                return false;
        }
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
