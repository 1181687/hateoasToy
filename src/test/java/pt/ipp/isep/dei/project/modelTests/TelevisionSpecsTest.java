package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.television.TelevisionType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TelevisionSpecsTest {
    private Room livingRoom;
    private Device television;
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";

    @BeforeEach
    public void StartUp() {
        // Room
        Dimension dim = new Dimension(4, 4, 3);
        RoomId roomId = new RoomId("Living Room");
        livingRoom = new Room(roomId, "room", 1, dim);
        TelevisionType televisionType = new TelevisionType();
        television = televisionType.createDevice("Flat Screen TV");
        livingRoom.addDevice(television);

        // FridgeSpecs Set
        television.setAttributesDevType("Nominal Power", 100.0);
        television.setAttributesDevType("Standby Power", 33.0);
        television.setAttributesDevType("Time", 120.0);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Television";

        //Act
        String result = television.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getNominalPower() {
        // Arrange
        double expectedResult = 100.0;

        //Act
        double result = television.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        //nominal power * time
        double expectedResult = 12000;

        // Act
        double result = television.getSpecs().getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getAttributesToString() {

        // Arrange
        // TelevisionSpecs Instantiation
        String expectedResult =
                "1 - Nominal Power: 100.0\n" +
                        "2 - Standby Power: 33.0\n" +
                        "3 - Time: 120.0\n";

        // Act
        String result = television.getSpecs().getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = television.getSpecs().getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");
        expectedResult.add("Standby Power");
        expectedResult.add("Time");

        // Act
        List<String> result = television.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        Object expectedResult = 100.0;

        // Act
        Object result = television.getSpecs().getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullNominalPower (){
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = television.getSpecs().getAttributeValue("\0"+ "Nominal Power");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueStandbyPower() {
        // Arrange
        Object expectedResult = 33.0;

        // Act
        Object result = television.getSpecs().getAttributeValue("Standby Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullStandbyPower (){
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = television.getSpecs().getAttributeValue("\0" + "Standby Power");

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullTime (){
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = television.getSpecs().getAttributeValue("\0"+ "Time");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueTime() {
        // Arrange
        Object expectedResult = 120.0;

        // Act
        Object result = television.getSpecs().getAttributeValue("Time");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = television.getSpecs().getAttributeValue("Not Valid");

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String random = "random";

        // Act
        boolean result = television.getSpecs().setAttributeValue("Nominal Power", random);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueValidType() {
        // Arrange
        double nominalPower = 1.5;

        // Act
        boolean result = television.getSpecs().setAttributeValue("Nominal Power", nominalPower);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        television.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Act
        boolean result = television.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetNominalPower_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 30;

        //Act
        boolean result = television.getSpecs().setAttributeValue("\0"+ "Nominal Power", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetNominalPower_ZeroValue_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = television.getSpecs().setAttributeValue("Nominal Power", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

        @Test
    public void testSetAttributeStandbyPowerValueValidType() {
        // Arrange
        double standbyPower = 20.4;

        // Act
        boolean result = television.getSpecs().setAttributeValue("Standby Power", standbyPower);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeStandbyPowerValueNotAValidType() {
        // Arrange
        String random = "random";

        // Act
        boolean result = television.getSpecs().setAttributeValue("Standby Power", random);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeStandbyPowerSameValue() {
        // Arrange
        television.getSpecs().setAttributeValue("Standby Power", 100);

        // Act
        boolean result = television.getSpecs().setAttributeValue("Standby Power", 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetStandbyPower_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double standbyPower = 30;

        //Act
        boolean result = television.getSpecs().setAttributeValue("\0"+ "Standby Power", standbyPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetStandbyPower_ZeroValue_False() {
        //Arrange
        boolean expectedResult = false;
        double standbyPower = 0;

        //Act
        boolean result = television.getSpecs().setAttributeValue("Standby Power", standbyPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeTimeValueValidType() {
        // Arrange
        double time = 90.0;

        // Act
        boolean result = television.getSpecs().setAttributeValue("Time", time);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeTimeValueNotAValidType() {
        // Arrange
        String random = "random";

        // Act
        boolean result = television.getSpecs().setAttributeValue("Time", random);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeSameValue() {
        // Arrange
        television.getSpecs().setAttributeValue("Time", 100);

        // Act
        boolean result = television.getSpecs().setAttributeValue("Time", 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetTime_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double time = 30;

        //Act
        boolean result = television.getSpecs().setAttributeValue("\0"+ "Time", time);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = NOT_VALID_ATTRIBUTE;

        // act
        String result = television.getSpecs().getAttributeDataType("Integer");

        // assert
        assertEquals(attributeDataType, result);
    }

    @Test
    public void testIfDeviceIsProgrammableFalse() {
        //Arrange
        //Act
        boolean result = television.getSpecs().isProgrammable();
        //Assert
        assertFalse(result);
    }

    @Test
    public void testIfDeviceIsProgrammableReturnsFalseBecauseItsNotProgrammable() {
        //Arrange
        television.getSpecs().asProgrammable();
        //Act
        boolean result = television.getSpecs().isProgrammable();
        //Assert
        assertFalse(result);
    }

}

