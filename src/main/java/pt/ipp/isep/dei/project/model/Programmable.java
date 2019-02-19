package pt.ipp.isep.dei.project.model;

public interface Programmable {

    boolean addProgram(Program program);

    default Program newProgram(String programName, double duration, double energyConsumption) {
        return new Program(programName, duration, energyConsumption);
    }

}
