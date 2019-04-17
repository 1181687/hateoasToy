package pt.ipp.isep.dei.project.model.devices;

import pt.ipp.isep.dei.project.utils.Utils;

public class TimeVariableProgramSpecs implements ProgramSpecs {
    private double programNominalPower;
    private double time;

    public TimeVariableProgramSpecs() {
        this.time = 0;
    }

    public double getTime() {
        return this.time;
    }

    public boolean setTime(Object time) {
        double programTime = (Double) time;
        if (Utils.isSameDouble(this.time, programTime)) {
            return false;
        }
        this.time = programTime;
        return true;
    }

    public double getProgramNominalPower() {
        return this.programNominalPower;
    }

    public boolean setProgramNominalPower(Object programNominalPower) {
        double nominalPower = (Double) programNominalPower;
        if (Utils.isSameDouble(this.programNominalPower, nominalPower)) {
            return false;
        }
        this.programNominalPower = nominalPower;
        return true;
    }

    @Override
    public double getEnergyConsumption() {
        return this.programNominalPower * this.time;
    }

    @Override
    public boolean setAttributes(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case "time":
                if (attributeValue instanceof Number) {
                    return setTime(((Number) attributeValue).doubleValue());
                }
                return false;
            case "programNominalPower":
                if (attributeValue instanceof Number) {
                    return setProgramNominalPower(((Number) attributeValue).doubleValue());
                }
                return false;
            default:
                return false;

        }
    }
}
