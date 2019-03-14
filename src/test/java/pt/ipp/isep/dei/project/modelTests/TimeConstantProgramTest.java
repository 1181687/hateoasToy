package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.ProgramSpecs;
import pt.ipp.isep.dei.project.model.devices.TimeConstantProgram;
import pt.ipp.isep.dei.project.model.devices.TimeConstantProgramSpecs;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeConstantProgramTest {

    @Test
    public void testGetProgramSpecs (){
        //Arrange
        String name = "prog1";
        TimeConstantProgramSpecs specs= new TimeConstantProgramSpecs();
        TimeConstantProgram timeConstantProgram = new TimeConstantProgram(name, specs);

        ProgramSpecs expectedResult= specs;

        //Act
        ProgramSpecs result = timeConstantProgram.getProgramSpecs();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetProgramAttributes_duration (){
        //Arrange
        String name = "prog1";
        TimeConstantProgramSpecs specs= new TimeConstantProgramSpecs();
        TimeConstantProgram timeConstantProgram = new TimeConstantProgram(name, specs);

        //Act


        //Assert
    }



}