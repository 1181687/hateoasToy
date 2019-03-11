package pt.ipp.isep.dei.project.model.devices.walltowelheater;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;

import java.util.List;

public class WallTowelHeaterSpecs implements DeviceSpecs {
    @Override
    public double getEnergyConsumptionInADay() {
        return 0;
    }

    @Override
    public double getNominalPower() {
        return 0;
    }

    @Override
    public String getAttributesToString() {
        return null;
    }

    @Override
    public int getNumberOfAttributes() {
        return 0;
    }

    @Override
    public List<String> getSpecsList() {
        return null;
    }

    @Override
    public Object getAttributeValue(String attributeName) {
        return null;
    }

    @Override
    public String getAttributeDataType(String attributeName) {
        return null;
    }

    @Override
    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return false;
    }

    @Override
    public String getTypeName() {
        return null;
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }

    @Override
    public Programmable asProgrammable() {
        return null;
    }
}
