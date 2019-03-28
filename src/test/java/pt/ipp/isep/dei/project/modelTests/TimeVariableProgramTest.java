package pt.ipp.isep.dei.project.modelTests;


import org.junit.Test;
import static org.junit.Assert.*;
import pt.ipp.isep.dei.project.model.devices.ProgramSpecs;
import pt.ipp.isep.dei.project.model.devices.TimeVariableProgram;
import pt.ipp.isep.dei.project.model.devices.TimeVariableProgramSpecs;

public class TimeVariableProgramTest {

    @Test
    public void testGetProgramSpecs() {
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
    public void testSetProgramAttributes_True() {
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
    public void testSetProgramAttributes_False() {
        //Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        //Act
        boolean result = program.setProgramAttributes("name", 200.0);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testSetProgramAttributeprogramNominalPower_NullChar() {
        //Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        //Act
        boolean result = program.setProgramAttributes("\0programNominalPower", 200.0);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testSetProgramAttributeTime_NullChar() {
        //Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        //Act
        boolean result = program.setProgramAttributes("\0time", 200.0);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGetProgramName() {
        // Arrange
        String name = "Program 1";
        ProgramSpecs programSpecs = new TimeVariableProgramSpecs();
        TimeVariableProgram program = new TimeVariableProgram(name, programSpecs);
        String expectedResult = "Program 1";
        // Act
        String result = program.getName();
        // Assert
        assertEquals(expectedResult, result);
    }

}