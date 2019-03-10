package pt.ipp.isep.dei.project.model.devices.fan;

import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Program;
import pt.ipp.isep.dei.project.model.devices.ProgramSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;

import java.util.List;

public class FanSpecs implements DeviceSpecs, Programmable {
    private String typeName;
    private double nominalPower;
    private List<Program> programList;

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

    @Override
    public List<Program> getProgramList() {
        return null;
    }

    @Override
    public boolean addProgram(Program program) {
        return false;
    }

    @Override
    public Program createNewProgram(String programName, ProgramSpecs specs) {
        return null;
    }
}
