package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramList {
    private List<Program> mProgramList;

    public ProgramList() {
        this.mProgramList = new ArrayList<>();
    }

    /**
     * Method that adds a program to the list of Programs.
     *
     * @param programName the program to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the device is null
     */
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
