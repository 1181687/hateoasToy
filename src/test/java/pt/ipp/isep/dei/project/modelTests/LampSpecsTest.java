package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.LampSpecs;
import pt.ipp.isep.dei.project.model.LampType;
import pt.ipp.isep.dei.project.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LampSpecsTest {
    Room livingRoom;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        livingRoom = new Room("Living Room", 1, dim);
        LampType lampType = new LampType();
        lampType.createDevice("Lamp Philips", livingRoom);
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
        List<String> result = livingRoom.getDeviceByPosition(0).getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        livingRoom.getDeviceByPosition(0).setAttributesDevType("Luminous Flux", 50.0);
        livingRoom.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = livingRoom.getDeviceByPosition(0).getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueLuminousFlux() {
        // Arrange
        // FridgeSpecs Instantiation
        livingRoom.getDeviceByPosition(0).setAttributesDevType("Luminous Flux", 50);
        livingRoom.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 50.0;
        // Act
        Object result = livingRoom.getDeviceByPosition(0).getAttributeValue("Luminous Flux");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        livingRoom.getDeviceByPosition(0).setAttributesDevType("Luminous Flux", 50);
        livingRoom.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        Object expectedResult = -1;
        // Act
        Object result = livingRoom.getDeviceByPosition(0).getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }
}
