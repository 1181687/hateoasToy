package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.devices.stove.StoveSpecs;
import pt.ipp.isep.dei.project.model.devices.stove.StoveType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoveSpecsTest {

    private Room kitchen;
    private Device stove;
    private House house;
    private DeviceSpecs stoveSpecs;

    @BeforeEach
    public void StartUp() {
        //Room
        Dimension dim = new Dimension(3, 5, 6);
        RoomId roomId = new RoomId("kitchen");
        this.kitchen = new Room(roomId, "room", 1, dim);
        StoveType stoveType = new StoveType();
        this.stove = stoveType.createDevice("Stove200");
        kitchen.addDevice(stove);
        this.stove.setAttributesDevType("Nominal Power", 30);
        this.stoveSpecs = stove.getSpecs();

    }


    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Stove";

        //Act
        String result = stove.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange

        double expectedResult = 30;

        //Act
        double result = stove.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange
        stove.setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = stove.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange

        String expectedResult = "1 - Nominal Power: 30.0\n";

        //Act
        String result = stove.getSpecs().getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange

        int expectedResult = 1;

        //Act

        int result = stove.getSpecs().getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInADay() {

        double expectedResult = 0;

        //Act
        double result = stove.getSpecs().getEnergyConsumptionInADay();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = stove.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        stove.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = stove.getSpecs().getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValue_notAValidSpec() {
        // Arrange
        Object expectedResult = "not a valid attribute";
        // Act
        Object result = stove.getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNominalPowerValue_NotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = stove.getSpecs().setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPower_ValidValue() {
        // Act
        boolean result = stove.getSpecs().setAttributeValue("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttribute_NotAValidAttribute() {
        // Act
        boolean result = stove.getSpecs().setAttributeValue("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPower_SameValue() {
        // Arrange
        stove.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = stove.getSpecs().setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }


    @Test
    public void getAttributeDataTypeTest_notValidAttributte() {
        // arrange
        String expectedResult = "not a valid attribute";
        // act
        String result = stove.getSpecs().getAttributeDataType("wrong attribute name");
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String expectedResult = "Double";
        // act
        String result = stove.getSpecs().getAttributeDataType("Nominal Power");
        // assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        DeviceSpecs stoveSpecs = stove.getSpecs();
        //Act
        boolean result = ((StoveSpecs) stoveSpecs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.stoveSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        programmable.addProgram(programA);

        DeviceSpecs stoveSpecs = stove.getSpecs();

        boolean expectedResult = false;

        //Act
        boolean result = ((StoveSpecs) stoveSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.stoveSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);

        DeviceSpecs stoveSpecs = stove.getSpecs();

        boolean expectedResult = true;

        //Act
        boolean result = ((StoveSpecs) stoveSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetProgramList() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.stoveSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        DeviceSpecs stoveSpecs = stove.getSpecs();
        ((StoveSpecs) stoveSpecs).addProgram(programA);

        List<Program> expectedResult = new ArrayList<>();
        expectedResult.add(programA);

        //Act
        List<Program> result = ((StoveSpecs) stoveSpecs).getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsProgrammable() {
        //Act
        boolean result = stoveSpecs.isProgrammable();

        //Assert
        assertTrue(result);
    }


    @Test
    public void testAsProgrammable() {
        //Arrange
        Programmable expectedResult = (Programmable) stoveSpecs;

        //Act
        Programmable result = stoveSpecs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }
}