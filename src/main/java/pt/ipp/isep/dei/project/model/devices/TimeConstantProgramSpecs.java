package pt.ipp.isep.dei.project.model.devices;

import pt.ipp.isep.dei.project.utils.Utils;

public class TimeConstantProgramSpecs implements ProgramSpecs {
    private double duration;
    private double energyConsumption;

    public TimeConstantProgramSpecs() {
        //intentionally empty
    }

    public double getDuration() {
        return duration;
    }

    public boolean setDuration(Object duration) {
        double programDuration = (Double) duration;
        if (Utils.isSameDouble(this.duration, programDuration)) {
            return false;
        }
        this.duration = programDuration;
        return true;
    }

    public boolean setEnergyConsumption(Object energyConsumption) {
        double programEnergyConsumption = (Double) energyConsumption;
        if (Utils.isSameDouble(this.energyConsumption, programEnergyConsumption)) {
            return false;
        }
        this.energyConsumption = programEnergyConsumption;
        return true;
    }

    @Override
    public double getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public boolean setAttributes(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case "duration":
                if (attributeValue instanceof Number) {
                    return setDuration(((Number) attributeValue).doubleValue());
                }
                return false;
            case "energyConsumption":
                if (attributeValue instanceof Number) {
                    return setEnergyConsumption(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;
        }
    }
}
