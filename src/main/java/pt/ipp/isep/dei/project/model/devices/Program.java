package pt.ipp.isep.dei.project.model.devices;

public interface Program {

    String getName();

    ProgramSpecs getProgramSpecs();

    boolean setProgramAttributes(String attributeName, Object value);
}
