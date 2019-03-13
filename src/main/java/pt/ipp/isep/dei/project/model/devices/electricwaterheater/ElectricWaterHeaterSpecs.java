package pt.ipp.isep.dei.project.model.devices.electricwaterheater;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ElectricWaterHeaterSpecs implements DeviceSpecs {
    private static final String ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT = "Volume Of Water To Heat";
    private static final String ATTRIBUTE_HOT_WATER_TEMP = "Hot-Water Temperature";
    private static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance Ratio";
    private static final String ATTRIBUTE_COLD_WATER_TEMP = "Cold-Water Temperature";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";


    private String typeName;
    private double volumeOfWaterToHeat;
    private double coldWaterTemperature;
    private double hotWaterTemperature;
    private double performanceRatio;
    private double nominalPower;

    public ElectricWaterHeaterSpecs() {
        this.typeName = "ElectricWaterHeater";
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * Method that sets the volume of water to be heated.
     *
     * @param volumeOfWaterToHeat Volume of water to be heated.
     */
    public boolean setVolumeOfWaterToHeat(Object volumeOfWaterToHeat) {
        double volumeWater = (Double) volumeOfWaterToHeat;
        if (Double.compare(volumeWater, 0) == 1 && !(Utils.isSameDouble(volumeWater, this.volumeOfWaterToHeat))) {
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
        if (Double.compare(cwt, this.hotWaterTemperature) == -1) {
            this.coldWaterTemperature = cwt;
            return true;
        }
        return false;
    }

    /**
     * Method that returns the energy consumption of an Electric Water Heater in a given day based on the cold-water
     * temperature and the volume of water to be heated.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        double specificHeatOfWater = 1.163 / 1000;
        double differenceInTemperature = hotWaterTemperature - coldWaterTemperature;
        return specificHeatOfWater * volumeOfWaterToHeat * differenceInTemperature * performanceRatio;
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
     * methods that determine if the value of the hotWaterTemperature is the same that the method receive.
     *
     * @param hotWaterTemperature
     * @return false if is the same value. Return true if not, and save the new value.
     */
    public boolean setHotWaterTemperature(Object hotWaterTemperature) {
        double hwt = (Double) hotWaterTemperature;
        if (!Utils.isSameDouble(this.hotWaterTemperature, hwt) && !(Utils.isSameDouble(hwt, 0))) {
            this.hotWaterTemperature = hwt;
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

    /**
     * method that get the attributes by strings.
     *
     * @return an attribute of the electricWater.
     */
    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Hot Water Temperature: " + hotWaterTemperature + "\n");
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
     * @return list os specs of electric water heater
     */
    @Override
    public List<String> getSpecsList() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_HOT_WATER_TEMP);
        result.add(ATTRIBUTE_PERFORMANCE_RATIO);
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
            case ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT:
                return volumeOfWaterToHeat;
            case ATTRIBUTE_HOT_WATER_TEMP:
                return hotWaterTemperature;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                return performanceRatio;
            case ATTRIBUTE_COLD_WATER_TEMP:
                return coldWaterTemperature;
            case ATTRIBUTE_NOMINAL_POWER:
                return nominalPower;
            default:
                return NOT_VALID_ATTRIBUTE;
        }
    }

    /**
     * set method
     * @param attributeName string name of the attribute
     * @param attributeValue value of the attribute
     * @return
     */
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER_TO_HEAT:
                if (attributeValue instanceof Number) {
                    return setVolumeOfWaterToHeat(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_HOT_WATER_TEMP:
                if (attributeValue instanceof Number) {
                    return setHotWaterTemperature(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                if (attributeValue instanceof Number) {
                    return setPerformanceRatio(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_COLD_WATER_TEMP:
                if (attributeValue instanceof Number) {
                    return setColdWaterTemperature(((Number) attributeValue).doubleValue());
                }
                return false;
            case ATTRIBUTE_NOMINAL_POWER:
                if (attributeValue instanceof Number){
                    return setNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }
}
