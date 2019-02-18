package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LampSpecsTest {
    private Room livingRoom;
    private Device lamp;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        livingRoom = new Room("Living Room", 1, dim);
        LampType lampType = new LampType();
        this.lamp=lampType.createDevice("Lamp Philips", livingRoom);
    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // LampSpecs Instantiation
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        lampSpecs.setTime(10.0);

        double expectedResult = 1000.0;

        // Act
        double result = lampSpecs.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        // LampSpecs Instantiation
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        double expectedResult = 100.0;

        //Act
        double result = lampSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void testEmptyConstructor() {
        // Arrange
        // LampSpecs Instantiation
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        double expectedResult = 100.0;

        //Act
        double result = lampSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getAttributesToString() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);

        String expectedResult =
                "1 - Luminous Flux: 50.0\n" +
                        "2 - Nominal Power: 100.0\n";

        // Act
        String result = lampSpecs.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        LampSpecs lampSpecs = new LampSpecs();
        lampSpecs.setAttributeValue("Luminous Flux", 50.0);
        lampSpecs.setAttributeValue("Nominal Power", 100.0);


        int expectedResult = 2;

        // Act
        int result = lampSpecs.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = lamp.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        lamp.setAttributesDevType("Luminous Flux", 50.0);
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = lamp.getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueLuminousFlux() {
        // Arrange
        // FridgeSpecs Instantiation
        lamp.setAttributesDevType("Luminous Flux", 50);
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 50.0;
        // Act
        Object result = lamp.getAttributeValue("Luminous Flux");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        lamp.setAttributesDevType("Luminous Flux", 50);
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = -1;
        // Act
        Object result = lamp.getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeTimeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        lamp.setAttributesDevType("Time", 50);

        Object expectedResult = 50.0;
        // Act
        Object result = lamp.getAttributeValue("Time");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeLuminousFlux() {
        // Arrange
        // FridgeSpecs Instantiation
        lamp.setAttributesDevType("Luminous Flux", 50);
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 50.0;
        // Act
        Object result = lamp.getAttributeValue("Luminous Flux");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeLuminousFluxNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.setAttributesDevType("Luminous Flux", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeLuminousFluxSameValue() {
        // Arrange
        lamp.setAttributesDevType("Luminous Flux", 50);
        // Act
        boolean result = lamp.setAttributesDevType("Luminous Flux", 50);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeNotValid() {
        // Arrange
        String attribute = "Time";
        // Act
        boolean result = lamp.setAttributesDevType("Stuff", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeTrue () {
        //Arrange

        double value1 = 30.5;
        String attribute = "Time";
        lamp.setAttributesDevType(attribute, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = lamp.setAttributesDevType(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeTimeSameValue() {
        // Arrange
        lamp.setAttributesDevType("Time", 20);
        // Act
        boolean result = lamp.setAttributesDevType("Time", 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.setAttributesDevType("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        lamp.setAttributesDevType("Nominal Power", 1.5);
        // Act
        boolean result = lamp.setAttributesDevType("Nominal Power", 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.setAttributesDevType(attribute, 1.5);
        // Assert
        assertFalse(result);
    }
}
