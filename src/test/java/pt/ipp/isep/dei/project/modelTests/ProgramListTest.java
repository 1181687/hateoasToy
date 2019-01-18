package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Program;
import pt.ipp.isep.dei.project.model.ProgramList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramListTest {
    @Test
    public void testNewProgram() {
        //Arrange
        ProgramList programList = new ProgramList();
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Program expectedResult = new Program(programName, duration, energyConsumption);


        //Act
        Program result = programList.newProgram(programName, duration, energyConsumption);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram() {
        //Arrange
        ProgramList programList = new ProgramList();
        Program program = null;
        boolean expectedResult = false;

        //Act
        boolean result = programList.addProgram(program);
        //Assert
        assertEquals(expectedResult, result);
    }
}