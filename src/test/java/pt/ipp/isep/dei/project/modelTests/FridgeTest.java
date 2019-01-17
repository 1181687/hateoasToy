package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Fridge;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FridgeTest {

    @Test
    public void testgetAttributeNames() {
        //Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 20;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer capacity");
        expectedResult.add("Refrigerator capacity");
        expectedResult.add("Annual energy consumption");
        expectedResult.add("Nominal power");

        //Act
        List<String> result = fridge.getAttributeNames();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueRefrigeratorCapacity() {
        //Arrange
        double refrigeratorCapacity = 20;
        double refrigeratorCapacity2 = 30;

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Refrigerator capacity";
        Object obj = new Double(refrigeratorCapacity2);
        fridge.setAttributeValue(attributeName, obj);
        double expectedResult = 30.0;

        //Act
        Object result = fridge.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumption() {
        //Arrange;
        double annualEnergyConsumption1 = 30;

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Annual energy consumption";
        Object obj = new Double(annualEnergyConsumption1);
        fridge.setAttributeValue(attributeName, obj);
        double expectedResult = 30;

        //Act
        Object result = fridge.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributeValueNominalPower() {
        //Arrange
        double nominalPower2 = 10;

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Nominal power";
        Object obj = new Double(nominalPower2);
        fridge.setAttributeValue(attributeName, obj);
        double expectedResult = 10;

        //Act
        Object result = fridge.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeNonexistent() {
        //Arrange
        int nominalPower2 = 10;

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "NonExistent";
        Object obj = new Integer(nominalPower2);
        fridge.setAttributeValue(attributeName, obj);
        int expectedResult = 0;

        //Act
        Object result = fridge.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseFreezerCapacity() {
        //Arrange
        String freezerCapacity2 = "30";

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Capacity";
        Object obj = new Double(freezerCapacity2);

        boolean expectedResult = false;

        //Act
        Object result = fridge.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseRefrigeratorCapacity() {
        //Arrange
        String refrigeratorCapacity1 = "30";

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Duration";
        Object obj = new Integer(refrigeratorCapacity1);

        boolean expectedResult = false;

        //Act
        Object result = fridge.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseAnnualEnergyConsumption() {
        //Arrange;
        String energyConsumption = "30";

        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Annual Energy consumption of the program";
        Object obj = new Integer(energyConsumption);

        boolean expectedResult = false;

        //Act
        Object result = fridge.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseNominalPower() {
        //Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        String nominalPower2 = "30";

        double nominalPower = 30;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        String attributeName = "Nominal power";
        Object obj = new Integer(nominalPower2);

        boolean expectedResult = false;

        //Act
        Object result = fridge.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        String expectedResult = "Fridge";

        //Act
        String result = fridge.getmTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        double expectedResult = 27.3972;

        // Act
        double result = fridge.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        double expectedResult = 100.0;

        //Act
        double result = fridge.getmNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void setmFreezerCapacityTrue() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridge.setmFreezerCapacity(100.0);

        assertTrue(result);
    }

    @Test
    public void setmFreezerCapacityFalse() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridge.setmFreezerCapacity(20.0);

        assertFalse(result);
    }

    @Test
    public void setmRefrigeratorCapacityTrue() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridge.setmRefrigeratorCapacity(20.0);

        assertTrue(result);
    }

    @Test
    public void setmRefrigeratorCapacityFalse() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridge.setmRefrigeratorCapacity(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerFalse() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridge.setmNominalPower(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerTrue() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridge.setmNominalPower(20.0);

        assertTrue(result);
    }

    @Test
    public void setmAnnualEnergyConsumptionFalse() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridge.setmAnnualEnergyConsumption(10000.0);

        assertFalse(result);
    }

    @Test
    public void setmAnnualEnergyConsumptionTrue() {
        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridge.setmAnnualEnergyConsumption(2000.0);

        assertTrue(result);
    }


    @Test
    public void getAttributesToString() {

        // Arrange
        // Fridge Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        String expectedResult =
                "1 - Freezer Capacity: 20.0\n" +
                        "2 - Refrigerator Capacity: 100.0\n" +
                        "3 - Annual Energy Consumption: 10000.0\n" +
                        "4 - Nominal Power: 100.0\n";

        // Act
        String result = fridge.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeTrueFreezer() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(1, 21);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseFreezer() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(1, 20.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeTrueRefrigerator() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(2, 101);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseRefrigerator() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(2, 100.0);

        // assert
        assertFalse(result);
    }


    @Test
    public void setAttributeTrueAnnualEnergyConsumption() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(3, 10001);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseAnnualEnergyConsumption() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(3, 10000.0);

        // assert
        assertFalse(result);
    }


    @Test
    public void setAttributeTrueNominalPower() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(4, 101);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseNominalPower() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(4, 100.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeFalse() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridge.setAttribute(5, 100.0);

        // assert
        assertFalse(result);
    }


    @Test
    public void getNumberOfAttributes() {
        // Arrange
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        int expectedResult = 4;

        // Act
        int result = fridge.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }
}
