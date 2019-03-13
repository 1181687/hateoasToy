package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.ProgramSpecs;
import pt.ipp.isep.dei.project.model.devices.TimeVariableProgram;
import pt.ipp.isep.dei.project.model.devices.TimeVariableProgramSpecs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TimeVariableProgramTest {

    @Test
    void testGetProgramSpecs() {
        //Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        ProgramSpecs expectedResult = programSpecs;
        //Act
        ProgramSpecs result = program.getProgramSpecs();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetProgramAttributes_True() {
        //Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        //Act
        boolean result = program.setProgramAttributes("programNominalPower", 200.0);
        //Assert
        assertTrue(result);
    }

    @Test
    void testSetProgramAttributes_False() {
        //Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        //Act
        boolean result = program.setProgramAttributes("name", 200.0);
        //Assert
        assertFalse(result);
    }

}