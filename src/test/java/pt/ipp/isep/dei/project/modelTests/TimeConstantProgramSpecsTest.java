package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.ipp.isep.dei.project.model.devices.TimeConstantProgramSpecs;

public class TimeConstantProgramSpecsTest {
    private TimeConstantProgramSpecs specs;

    private static final String DURATION = "duration";
    private static final String ENERGY_CONSUMPTION = "energyConsumption";

    @Before
    public void StartUp() {
        String name = "prog1";
        specs = new TimeConstantProgramSpecs();
    }

    @Test
    public void testGetDuration (){
        //Arrange
        specs.setAttributes(DURATION, 100);
        double expectedResult = 100;
        //Act
        double result = specs.getDuration();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetEnergyConsumption (){
        //Arrange
        specs.setAttributes(ENERGY_CONSUMPTION, 100);
        double expectedResult = 100;
        //Act
        double result = specs.getEnergyConsumption();
        //Assert
        assertEquals(expectedResult, result);
    }



    @Test
    public void testSetAttributeDurationNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = specs.setAttributes(DURATION, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationNullChar() {
        // Arrange
        double attribute = 30;
        // Act
        boolean result = specs.setAttributes("\0"+DURATION, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationTrue() {
        //Arrange

        double value1 = 30.5;
        specs.setAttributes(DURATION, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = specs.setAttributes(DURATION, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeDurationSameValue_False() {
        // Arrange
        specs.setAttributes(DURATION, 20);
        // Act
        boolean result = specs.setAttributes(DURATION, 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationZeroValue_False() {
        // Arrange
        specs.setAttributes(DURATION, 0);
        // Act
        boolean result = specs.setAttributes(DURATION, 0);
        // Assert
        assertFalse(result);
    }

    /////////////

    @Test
    public void testSetAttributeEnergyConsumptionNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = specs.setAttributes(ENERGY_CONSUMPTION, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeEnergyConsumptionNullChar() {
        // Arrange
        double attribute = 10;
        // Act
        boolean result = specs.setAttributes("\0"+ENERGY_CONSUMPTION, attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeEnergyConsumptionTrue() {
        //Arrange

        double value1 = 30.5;
        specs.setAttributes(ENERGY_CONSUMPTION, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = specs.setAttributes(ENERGY_CONSUMPTION, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeEnergyConsumptionSameValue_False() {
        // Arrange
        specs.setAttributes(ENERGY_CONSUMPTION, 20);
        // Act
        boolean result = specs.setAttributes(ENERGY_CONSUMPTION, 20);
        // Assert
        assertFalse(result);
    }
}