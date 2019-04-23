package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.devices.electricoven.ElectricOvenSpecs;
import pt.ipp.isep.dei.project.model.devices.electricoven.ElectricOvenType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectricOvenSpecsTest {
    private static final String ATTRIBUTE_TIME = "Time";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";


    private Room kitchen;
    private Device electricOven;
    private House house;
    private DeviceSpecs specs;

    @BeforeEach
    public void StartUp() {
        //Room
        Dimension dim = new Dimension(3, 5, 6);
        RoomId roomId = new RoomId("kitchen");
        this.kitchen = new Room(roomId, "room", 1, dim);
        ElectricOvenType electricOvenType = new ElectricOvenType();
        this.electricOven = electricOvenType.createDevice("Forno");
        kitchen.addDevice(electricOven);
        this.electricOven.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 30);
        this.electricOven.setAttributesDevType(ATTRIBUTE_TIME, 2);

        //DeviceSpecs
        this.specs = electricOven.getSpecs();
    }


    /**
     * Test the method that gives the programmable property to the device.
     * The Kettle is not programmable, so it just has the false return.
     */
    @Test
    public void testIsProgrammable_True() {
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean result = specs.isProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that returns a Programmable if the device is Programmable.
     * The Kettler is not programmable so the method only return "null" value.
     */
    @Test
    public void testAsProgrammable_null() {
        //Arrange
        Programmable expectedResult = (Programmable) specs;

        //Act
        Programmable result = specs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method really return the name of the Device Type.
     */
    @Test
    public void testGetTypeName_typeName() {
        //Arrange
        String expectedResult = "ElectricOven";

        //Act
        String result = specs.getTypeName();
        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with the already existent value.
     */
    @Test
    public void testSetNominalPower_SameDoubleValue_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 30;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with null character .
     */
    @Test
    public void testSetNominalPower_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 30;

        //Act
        boolean result = specs.setAttributeValue("\0" + ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with an invalid Data Type.
     */
    @org.junit.jupiter.api.Test
    public void testSetNominalPower_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String nominalPower = "30";

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with zero value.
     */
    @org.junit.jupiter.api.Test
    public void testSetNominalPower_ZeroValue_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with another valid value, for instance 20.
     */
    @Test
    public void testSetNominalPower_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double nominalPower = 20;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setTime method with the method setAttributeValue.
     * This test checks if we can set the Time value with the already existent value.
     */
    @org.junit.jupiter.api.Test
    public void testSetTime_SameDoubleValue_False() {
        //Arrange
        boolean expectedResult = false;
        double maxVolWater = 2;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, maxVolWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setTime method with the method setAttributeValue.
     * This test checks if we can set the Time value with null character.
     */
    @Test
    public void testSetTime_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double maxVolWater = 2;

        //Act
        boolean result = specs.setAttributeValue("\0" + ATTRIBUTE_TIME, maxVolWater);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setTime method with the method setAttributeValue.
     * This test checks if we can set the Time value with with an invalid Data Type.
     */
    @org.junit.jupiter.api.Test
    public void testSetTime_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String maxVolWater = "2";

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, maxVolWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setTime method with the method setAttributeValue.
     * This test checks if we can set the Time value with another valid value, for instance 1.
     */
    @Test
    public void testSetTime_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double maxVolWater = 1;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, maxVolWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setAttributeValue method in the case that there isn't a valid attribute name.
     */
    @org.junit.jupiter.api.Test
    public void testSetAttributeValue_InvalidAttributeName_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = specs.setAttributeValue("Not valid name", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setAttributeValue method in the case that there isn't a valid attribute name.
     */
    @Test
    public void testSetAttributeValue_NullAttributeName_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = specs.setAttributeValue("", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the getNominalPower method really returns the Nominal Power attribute value.
     */
    @Test
    public void testGetNominalPower_NominalPowerValue() {
        //Arrange
        double expectedResult = 30;
        //Act
        double result = specs.getNominalPower();
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    /**
     * Test if the getNominalPower method returns the correct value of Energy Consumption formula.
     */
    @Test
    public void testGetEnergyConsumptionInADay_EnergyConsumptionValue() {
        //Arrange
        double expectedResult = 0;
        //Act
        double result = specs.getEnergyConsumptionInADay();
        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    /**
     * Test if the method getAttributesToString returns the String of the Attributes with the respective value.
     */
    @Test
    public void testGetAttributesToString_ShowAttributesWithValue() {
        //Arrange
        String expectedResult =
                "1 - Nominal Power: " + 30.0 + "\n";
        //Act
        String result = specs.getAttributesToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that shows the number of Attributes of this Device, in this case, there are 3.
     */
    @Test
    public void testGetNumberOfAttributes_NumberOfAttributes() {
        //Arrange
        int expectedResult = 1;

        //Act
        int result = specs.getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that gets a String List of the attributes of this device.
     */
    @Test
    public void testGetSpecsList_ListOfStringsOfAttibutes() {
        //Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(ATTRIBUTE_NOMINAL_POWER);

        //Act
        List<String> result = specs.getSpecsList();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an invalid attribute.
     */
    @Test
    public void testGetAttributeValue_NotAValidAttribute() {
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = specs.getAttributeValue("Atributo Qualquer");
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterTime() {
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = specs.getAttributeValue("\0" + ATTRIBUTE_TIME);
        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @org.junit.jupiter.api.Test
    public void testGetAttributeValue_NullCharacterNomPower() {
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = specs.getAttributeValue("\0" + ATTRIBUTE_NOMINAL_POWER);
        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the return "not a valid attribute" of the method getAttributeValue.
     */
    @Test
    public void testGetAttributeValue_NullAttribute() {
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = specs.getAttributeValue("");
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the nominal power.
     */
    @Test
    public void testGetAttributeDataType_NominalPower() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = specs.getAttributeDataType(ATTRIBUTE_NOMINAL_POWER);

        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the volume Of Water To Heat.
     */
    @org.junit.jupiter.api.Test
    public void testGetAttributeDataType_volumeOfWaterToHeat() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = specs.getAttributeDataType(ATTRIBUTE_TIME);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method getAttributeDataType shows the String "not a valid attribute",
     * in case of do not insert a valid name of attribute.
     */
    @Test
    public void testGetAttributeDataType_InvalidAttribute() {
        //Arrange
        String expectedResult = "not a valid attribute";

        //Act
        String result = specs.getAttributeDataType("Atributo qualquer");

        //Assert
        assertEquals(expectedResult, result);

    }


    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        //Act
        boolean result = ((ElectricOvenSpecs) specs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.specs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        programmable.addProgram(programA);


        boolean expectedResult = false;

        //Act
        boolean result = ((ElectricOvenSpecs) specs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.specs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);


        boolean expectedResult = true;

        //Act
        boolean result = ((ElectricOvenSpecs) specs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetProgramList() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.specs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        ((ElectricOvenSpecs) specs).addProgram(programA);

        List<Program> expectedResult = new ArrayList<>();
        expectedResult.add(programA);

        //Act
        List<Program> result = ((ElectricOvenSpecs) specs).getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

}
