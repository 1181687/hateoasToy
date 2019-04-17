package pt.ipp.isep.dei.project.model.devices;

import java.util.List;

public interface Programmable {


    List<Program> getProgramList();

    boolean addProgram(Program program);

    Program createNewProgram(String programName);

}
