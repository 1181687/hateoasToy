package pt.ipp.isep.dei.project.model.Devices;

import pt.ipp.isep.dei.project.model.Program;

public interface Programmable {

    boolean addProgram(Program program);

    default Program newProgram(String programName, double duration, double energyConsumption) {
        return new Program(programName, duration, energyConsumption);
    }

}
