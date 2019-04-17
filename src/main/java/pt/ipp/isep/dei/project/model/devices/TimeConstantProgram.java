package pt.ipp.isep.dei.project.model.devices;

public class TimeConstantProgram implements Program {
    private String name;
    private TimeConstantProgramSpecs specs;

    public TimeConstantProgram(String name, ProgramSpecs specs) {
        this.name = name;
        this.specs = (TimeConstantProgramSpecs) specs;
    }

    @Override
    public String getName() {
        return this.name;
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