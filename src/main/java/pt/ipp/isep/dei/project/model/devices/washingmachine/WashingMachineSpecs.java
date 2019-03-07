package pt.ipp.isep.dei.project.model.devices.washingmachine;

import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WashingMachineSpecs implements DeviceSpecs, Programmable {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION = "Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private int capacity;
    private double duration;
    private double energyConsumption;
    private double nominalPower;
    private List<Program> programList;

    public WashingMachineSpecs() {
        this.typeName = "Washing Machine";
        this.programList = new ArrayList<>();
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
    public boolean addNewProgram(String programName, ProgramSpecs specs) {
        TimeConstantProgram program = new TimeConstantProgram(programName, specs);
        if (!Objects.isNull(program) && !(getProgramList().contains(program))) {
            getProgramList().add(program);
            return true;
        }
        return false;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * get method
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
     * @return energy consumption
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return 0;
    }

    /**
     * set method
     *
     * @param capacity
     */
    private boolean setCapacity(Object capacity) {
        int wmcapacity = (Integer) capacity;
        if (!(Utils.isSameDouble(this.capacity, wmcapacity)) && !(Utils.isSameDouble(wmcapacity, 0))) {
            this.capacity = wmcapacity;
            return true;
        }
        return false;
    }

    /**
     * set method
     *
     * @param duration
     */
    private boolean setDuration(Object duration) {
        double wmduration = (Double) duration;
        if (!(Utils.isSameDouble(this.duration, wmduration) && !(Utils.isSameDouble(wmduration, 0)))) {
            this.duration = wmduration;
            return true;
        }
        return false;
    }

    /**
     * set method
     *
     * @param wmNominalPower
     */
    private boolean setNominalPower(Object wmNominalPower) {
        double nomPower = (Double) wmNominalPower;
        if (!Utils.isSameDouble(this.nominalPower, nomPower) && !(Utils.isSameDouble(nomPower, 0))) {
            this.nominalPower = nomPower;
            return true;
        }
        return false;
    }

    /**
     * set method
     * 
     * @param wmEnergyConsumption
     */
    private boolean setEnergyConsumption(Object wmEnergyConsumption) {
        double eConsumption = (Double) wmEnergyConsumption;
        if (!Utils.isSameDouble(this.energyConsumption, eConsumption) && !(Utils.isSameDouble(eConsumption, 0))) {
            this.energyConsumption = eConsumption;
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
        attributes.append("1 - Capacity: " + capacity + "\n");
        attributes.append("2 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }

    /**
     * get method
     *
     * @return number of Washing Machine attributes
     */
    @Override
    public int getNumberOfAttributes() {
        return 2;
    }


    /**
     * get method
     * @return list os specs of washing machine
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_CAPACITY);
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
            case ATTRIBUTE_CAPACITY:
                return capacity;
            case ATTRIBUTE_DURATION:
                return duration;
            case ATTRIBUTE_ENERGY_CONSUMPTION:
                return energyConsumption;
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
            case ATTRIBUTE_CAPACITY:
                if (attributeValue instanceof Number) {
                    return setCapacity(((Number) attributeValue).intValue());
                }
                return false;
            case ATTRIBUTE_DURATION:
                if (attributeValue instanceof Number) {
                    return setDuration(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_ENERGY_CONSUMPTION:
                if (attributeValue instanceof Number) {
                    return setEnergyConsumption(((Number) attributeValue).doubleValue());
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

    /*public boolean addProgram(TimeConstantProgramSpecs program) {
        if (!Objects.isNull(program) && !(programList.contains(program))) {
            this.programList.add(program);
            return true;
        }
        return false;
    }*/
}
