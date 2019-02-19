package pt.ipp.isep.dei.project.model;

public interface Programmable {

    boolean addProgram(Program program);

    /**
     * Method that adds a program to the list of Programs.
     *
     * @param programName the program to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the device is null
     */
    default Program newProgram(String programName, double duration, double energyConsumption) {
        return new Program(programName, duration, energyConsumption);
    }

}
