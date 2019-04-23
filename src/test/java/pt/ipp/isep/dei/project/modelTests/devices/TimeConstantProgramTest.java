package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.ProgramSpecs;
import pt.ipp.isep.dei.project.model.devices.TimeConstantProgram;
import pt.ipp.isep.dei.project.model.devices.TimeConstantProgramSpecs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TimeConstantProgramTest {
    private static final String DURATION = "duration";
    private static final String ENERGY_CONSUMPTION = "energyConsumption";
    private TimeConstantProgram timeConstantProgram;
    private TimeConstantProgramSpecs specs;

    @BeforeEach
    public void StartUp() {
        String name = "prog1";
        specs = new TimeConstantProgramSpecs();
        timeConstantProgram = new TimeConstantProgram(name, specs);

    }


    @org.junit.jupiter.api.Test
    public void testGetProgramSpecs() {
        //Arrange
        ProgramSpecs expectedResult = specs;

        //Act
        ProgramSpecs result = timeConstantProgram.getProgramSpecs();

        //Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeDurationNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = timeConstantProgram.setProgramAttributes(DURATION, attribute);
        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeDurationNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = timeConstantProgram.setProgramAttributes("\0" + DURATION, attribute);
        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeDurationTrue() {
        //Arrange

        double value1 = 30.5;
        timeConstantProgram.setProgramAttributes(DURATION, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = timeConstantProgram.setProgramAttributes(DURATION, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @org.junit.jupiter.api.Test
    public void testSetAttributeDurationSameValue_False() {
        // Arrange
        timeConstantProgram.setProgramAttributes(DURATION, 20);
        // Act
        boolean result = timeConstantProgram.setProgramAttributes(DURATION, 20);
        // Assert
        assertFalse(result);
    }

    /////////////

    @org.junit.jupiter.api.Test
    public void testSetAttributeEnergyConsumptionNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = timeConstantProgram.setProgramAttributes(ENERGY_CONSUMPTION, attribute);
        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeEnergyConsumptionNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = timeConstantProgram.setProgramAttributes("\0" + ENERGY_CONSUMPTION, attribute);
        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeEnergyConsumptionTrue() {
        //Arrange

        double value1 = 30.5;
        timeConstantProgram.setProgramAttributes(ENERGY_CONSUMPTION, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = timeConstantProgram.setProgramAttributes(ENERGY_CONSUMPTION, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeEnergyConsumptionSameValue_False() {
        // Arrange
        timeConstantProgram.setProgramAttributes(ENERGY_CONSUMPTION, 20);
        // Act
        boolean result = timeConstantProgram.setProgramAttributes(ENERGY_CONSUMPTION, 20);
        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testGetProgramName() {
        // Arrange
        String expectedResult = "prog1";
        // Act
        String result = timeConstantProgram.getName();
        // Assert
        assertEquals(expectedResult, result);
    }

}