package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.ipp.isep.dei.project.model.devices.TimeVariableProgramSpecs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TimeVariableProgramSpecsTest {
    private TimeVariableProgramSpecs specs;

    private static final String TIME = "time";
    private static final String NOMINAL_POWER = "programNominalPower";

    @BeforeEach
    public void StartUp() {
        String name = "prog1";
        specs = new TimeVariableProgramSpecs();
    }

       @Test
    public void testGetTime (){
        //Arrange
        specs.setAttributes(TIME, 100);
        double expectedResult = 100;
        //Act
        double result = specs.getTime();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetProgramNominalPower (){
        //Arrange
        specs.setAttributes(NOMINAL_POWER, 100);
        double expectedResult = 100;
        //Act
        double result = specs.getProgramNominalPower();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeTimeNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = specs.setAttributes(TIME, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = specs.setAttributes("\0"+TIME, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeTrue() {
        //Arrange

        double value1 = 30.5;
        specs.setAttributes(TIME, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = specs.setAttributes(TIME, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeTimeSameValue_False() {
        // Arrange
        specs.setAttributes(TIME, 20);
        // Act
        boolean result = specs.setAttributes(TIME, 20);
        // Assert
        assertFalse(result);
    }

    /////

    @Test
    public void testSetAttributeNomPowerNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = specs.setAttributes(NOMINAL_POWER, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNomPowerNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = specs.setAttributes("\0"+NOMINAL_POWER, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNomPowerTrue() {
        //Arrange

        double value1 = 30.5;
        specs.setAttributes(NOMINAL_POWER, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = specs.setAttributes(NOMINAL_POWER, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeNomPowerSameValue_False() {
        // Arrange
        specs.setAttributes(NOMINAL_POWER, 20);
        // Act
        boolean result = specs.setAttributes(NOMINAL_POWER, 20);
        // Assert
        assertFalse(result);
    }




}