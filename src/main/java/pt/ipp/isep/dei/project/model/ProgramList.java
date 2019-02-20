package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramList {
    private List<Program> listOfPrograms;

    public ProgramList() {
        this.listOfPrograms = new ArrayList<>();
    }


    public Program newProgram(String programName, double duration, double energyConsumption) {
        return new Program(programName, duration, energyConsumption);
    }

    public boolean addProgram(Program program) {
        if (Objects.isNull(program)) {
            return false;
        }
        if (!(listOfPrograms.contains(program))) {
            this.listOfPrograms.add(program);
            return true;
        }
        return false;
    }
}
