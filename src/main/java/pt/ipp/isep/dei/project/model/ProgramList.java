package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramList {
    private List<Program> mProgramList;

    public ProgramList() {
        this.mProgramList = new ArrayList<>();
    }


    public Program newProgram(String programName, double duration, double energyConsumption) {
        return new Program(programName, duration, energyConsumption);
    }

    public boolean addProgram(Program program) {
        if (Objects.isNull(program)) {
            return false;
        }
        if (!(mProgramList.contains(program))) {
            this.mProgramList.add(program);
            return true;
        }
        return false;
    }
}
