package pt.ipp.isep.dei.project.model.devices;

public class TimeVariableProgram implements Program {
    private String name;
    private TimeVariableProgramSpecs specs;

    public TimeVariableProgram(String name, ProgramSpecs specs) {
        this.name = name;
        this.specs = (TimeVariableProgramSpecs) specs;
    }

    @Override
    public ProgramSpecs getProgramSpecs() {
        return this.specs;
    }

    @Override
    public boolean setProgramAttributes(String attributeName, Object value) {
        return this.specs.setAttributes(attributeName, value);
    }
}
