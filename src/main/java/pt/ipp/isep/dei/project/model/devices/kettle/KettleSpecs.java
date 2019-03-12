package pt.ipp.isep.dei.project.model.devices.kettle;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class KettleSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_MAXIMUM_VOLUME_WATER = "Maximum volume of Water";
    private static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance Ratio";
    private static final double BOILING_TEMPERATURE = 100;
    private static final double SPECIFIC_HEAT_OF_WATER = 1.163 / 1000;
    private static final String COLD_WATER_TEMPERATURE = "Cold-Water Temperature";
    private static final String VOLUME_OF_WATER_TO_HEAT = "Volume Of Water To Heat";


    private String typeName;
    private double nominalPower;
    private double performanceRatio;
    private double maxVolumeOfWater;
    private double coldWaterTemperature;
    private double volumeOfWaterToHeat;


    public KettleSpecs() {
        this.typeName = "Kettle";
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * method that determine if the value of the nominalPower is the same that the method receive.
     *
     * @param nominalPower
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setNominalPower(Object nominalPower) {
        double nomPower = (Double) nominalPower;
        if (!Utils.isSameDouble(this.nominalPower, nomPower) && !(Utils.isSameDouble(nomPower, 0))) {
            this.nominalPower = nomPower;
            return true;
        }
        return false;
    }


    public boolean setMaximumVolumeOfWater(Object maxVolumeOfWater) {
        double maxVolWater = (Double) maxVolumeOfWater;
        if (!Utils.isSameDouble(this.maxVolumeOfWater, maxVolWater) && !(Utils.isSameDouble(maxVolWater, 0))) {
            this.maxVolumeOfWater = maxVolWater;
            return true;
        }
        return false;
    }

    /**
     * method that determine if the value of the performanceRatio is the same that the method receive.
     *
     * @param performanceRatio
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setPerformanceRatio(Object performanceRatio) {
        double perfRatio = (Double) performanceRatio;
        if (!Utils.isSameDouble(this.performanceRatio, perfRatio) && !(Utils.isSameDouble(perfRatio, 0))) {
            this.performanceRatio = perfRatio;
            return true;
        }
        return false;
    }

    /**
     * Method that sets the volume of water to be heated.
     *
     * @param volumeOfWaterToHeat Volume of water to be heated.
     */
    public boolean setVolumeOfWaterToHeat(Object volumeOfWaterToHeat) {
        double volumeWater = (Double) volumeOfWaterToHeat;
        if (Double.compare(volumeWater, 0) == 1 && Double.compare(this.maxVolumeOfWater, volumeWater) == 1) {
            this.volumeOfWaterToHeat = volumeWater;
            return true;
        }
        return false;
    }

    /**
     * Method that sets the cold-water temperature.
     *
     * @param coldWaterTemperature Cold-water temperature to be used.
     */
    public boolean setColdWaterTemperature(Object coldWaterTemperature) {
        double cwt = (Double) coldWaterTemperature;
        if (Double.compare(cwt, BOILING_TEMPERATURE) == -1) {
            this.coldWaterTemperature = cwt;
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
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Number){
                    return setNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_MAXIMUM_VOLUME_WATER:
                if (attributeValue instanceof Number){
                    return setMaximumVolumeOfWater(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                if (attributeValue instanceof Number) {
                    return setPerformanceRatio(((Number) attributeValue).doubleValue());
                }
                return false;

            case COLD_WATER_TEMPERATURE:
                if (attributeValue instanceof Number) {
                    return setColdWaterTemperature(((Number) attributeValue).doubleValue());
                }
                return false;
            case VOLUME_OF_WATER_TO_HEAT:
                if (attributeValue instanceof Number) {
                    return setVolumeOfWaterToHeat(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * Method that returns the nominal power.
     *
     * @return Nominal power.
     */
    @Override
    public double getNominalPower() {
        return nominalPower;
    }

    /**
     * Method that returns the energy consumption of an Kettle in a given day based on the cold-water
     * temperature and the volume of water to be heated.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        double differenceInTemperature = BOILING_TEMPERATURE - coldWaterTemperature;
        return SPECIFIC_HEAT_OF_WATER * volumeOfWaterToHeat * differenceInTemperature * performanceRatio;
    }

    /**
     * method that get the attributes by strings.
     *
     * @return an attribute of the kettle.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Maximum Volume of Water: " + maxVolumeOfWater + "\n");
        attributes.append("2 - Performance Ratio: " + performanceRatio + "\n");
        attributes.append("3 - Nominal Power: " + nominalPower + "\n");
        return attributes.toString();
    }

    /**
     * method that get the number of the attributes of the device.
     *
     * @return the number of attributes.
     */
    @Override
    public int getNumberOfAttributes() {
        return 3;
    }


    /**
     * get method
     *
     * @return list os specs of Kettle
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_MAXIMUM_VOLUME_WATER);
        result.add(ATTRIBUTE_PERFORMANCE_RATIO);
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
            case ATTRIBUTE_MAXIMUM_VOLUME_WATER:
                return maxVolumeOfWater;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                return performanceRatio;
            case COLD_WATER_TEMPERATURE:
                return coldWaterTemperature;
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            case VOLUME_OF_WATER_TO_HEAT:
                return volumeOfWaterToHeat;
            default:
                return "not a valid attribute";
        }
    }




    /**
     * get method
     *
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    public String getAttributeDataType(String attributeName) {
        if ((getAttributeValue(attributeName).equals("not a valid attribute"))) {
            return "not a valid attribute";
        }
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}
