package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DishWasherSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String typeName;
    private int capacity;
    private double duration;
    private double nominalPower;
    private List<Program> programList;

    public DishWasherSpecs() {
        this.typeName = "Dishwasher";
        this.programList = new ArrayList<>();
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * get method of the energy consumption of a DishWasher
     *
     * @return energy consumption
     */
    public double getEnergyConsumptionInADay() {
        return 0;
    }


    /**
     * get method of the nominal power of a DishWasher
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
     * @param capacity
     * @return
     */
    private boolean setCapacity(Object capacity) {
        int dWCapacity = (Integer) capacity;
        if (!Utils.isSameDouble(this.capacity, dWCapacity) && !(Utils.isSameDouble(dWCapacity, 0))) {
            this.capacity = dWCapacity;
            return true;
        }
        return false;
    }

    /**
     * set method
     *
     * @param duration
     * @return
     */
    private boolean setDuration(Object duration) {
        Double dWDuration = (Double) duration;
        if (!Utils.isSameDouble(this.duration, dWDuration) && !(Utils.isSameDouble(dWDuration, 0))) {
            this.duration = dWDuration;
            return true;
        }
        return false;
    }

    /**
     * set method to Nominal Power of a DishWasher
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
     * @return an attribute of the Dish Washer.
     */
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Capacity: " + capacity + "\n");
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
     * @return list os specs of dishwasher
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

    public boolean addProgram(Program program) {
        if (!Objects.isNull(program) && !(programList.contains(program))) {
            this.programList.add(program);
            return true;
        }
        return false;
    }
}
