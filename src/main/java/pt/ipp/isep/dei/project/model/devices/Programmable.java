package pt.ipp.isep.dei.project.model.devices;

import java.util.List;

public interface Programmable {

    //boolean addProgram(Program program);

    /*default Program newProgram(String programName, double duration, double energyConsumption) {
        return new Program(programName, duration, energyConsumption);
    }*/

    List<Program> getProgramList();

    boolean addProgram(Program program);

    Program createNewProgram(String programName);

    //boolean addNewProgram(String programName, ProgramSpecs specs);

    //Program getProgram();
}
