package pt.ipp.isep.dei.project.model.devices.stove;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Program;
import pt.ipp.isep.dei.project.model.devices.Programmable;

import java.util.ArrayList;
import java.util.List;

public class StoveSpecs implements DeviceSpecs, Programmable {

    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private double time;
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
     * method that gets the number of editable attributes of the StoveSpecs.
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

    /**
     * gets attribute Object when given attribute name as String
     *
     * @param attributeName string name of the attribute
     * @return Objet attribute, or in case the given attribute name is wrong,
     * returns -1
     */
    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            default:
                return -1;
        }
    }

    /**
     * get string of the type of attribute
     *
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    @Override
    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}
