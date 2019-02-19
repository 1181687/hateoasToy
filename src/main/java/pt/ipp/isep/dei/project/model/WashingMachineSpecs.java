package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WashingMachineSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_DURATION = "Duration";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION = "Energy Consumption";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";

    private String mTypeName;
    private int mCapacity;
    private double mDuration;
    private double mEnergyConsumption;
    private double mNominalPower;
    private List<Program> mProgramList;

    public WashingMachineSpecs() {
        this.mTypeName = "Washing Machine";
    }

    public String getTypeName() {
        return mTypeName;
    }

    /**
     * get method
     *
     * @return nominal power
     */
    @Override
    public double getNominalPower() {
        return mNominalPower;
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
        if (!(Utils.isSameDouble(this.mCapacity, wmcapacity)) && !(Utils.isSameDouble(wmcapacity, 0))) {
            this.mCapacity = wmcapacity;
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
        if (!(Utils.isSameDouble(this.mDuration, wmduration) && !(Utils.isSameDouble(wmduration, 0)))) {
            this.mDuration = wmduration;
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
        if (!Utils.isSameDouble(this.mNominalPower, nomPower) && !(Utils.isSameDouble(nomPower, 0))) {
            this.mNominalPower = nomPower;
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
        double energyConsumption = (Double) wmEnergyConsumption;
        if (!Utils.isSameDouble(this.mEnergyConsumption, energyConsumption) && !(Utils.isSameDouble(energyConsumption, 0))) {
            this.mEnergyConsumption = energyConsumption;
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
        attributes.append("1 - Capacity: " + mCapacity + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
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

    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_CAPACITY);
        result.add(ATTRIBUTE_NOMINAL_POWER);

        return result;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_CAPACITY:
                return mCapacity;
            case ATTRIBUTE_DURATION:
                return mDuration;
            case ATTRIBUTE_ENERGY_CONSUMPTION:
                return mEnergyConsumption;
            case ATTRIBUTE_NOMINAL_POWER:
                return mNominalPower;
            default:
                return -1;
        }
    }

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

    public String getAttributeDataType(String attributeName) {
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }

    public boolean addProgram(Program program) {
        if (Objects.isNull(program)) {
            return false;
        }
        if (!(mProgramList.contains(program))) {
            this.mProgramList.add(program);
            return true;
        }
        return false;
    }
}
