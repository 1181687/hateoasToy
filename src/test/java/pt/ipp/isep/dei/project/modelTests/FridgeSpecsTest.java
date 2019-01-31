package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.FridgeSpecs;

import static org.junit.jupiter.api.Assertions.*;

public class FridgeSpecsTest {

    @Test
    public void testGetTypeName() {
        //Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        String expectedResult = "Fridge";

        //Act
        String result = fridgeSpecs.getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        double expectedResult = 27.3972;

        // Act
        double result = fridgeSpecs.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        double expectedResult = 100.0;

        //Act
        double result = fridgeSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void testEmptyConstructor() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs();

        fridgeSpecs.setNominalPower(nominalPower);

        double expectedResult = 100.0;

        //Act
        double result = fridgeSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void setmFreezerCapacityTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridgeSpecs.setFreezerCapacity(100.0);

        assertTrue(result);
    }

    @Test
    public void setmFreezerCapacityFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridgeSpecs.setFreezerCapacity(20.0);

        assertFalse(result);
    }

    @Test
    public void setmRefrigeratorCapacityTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridgeSpecs.setRefrigeratorCapacity(20.0);

        assertTrue(result);
    }

    @Test
    public void setmRefrigeratorCapacityFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridgeSpecs.setRefrigeratorCapacity(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridgeSpecs.setNominalPower(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridgeSpecs.setNominalPower(20.0);

        assertTrue(result);
    }

    @Test
    public void setmAnnualEnergyConsumptionFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);


        //act
        boolean result = fridgeSpecs.setAnnualEnergyConsumption(10000.0);

        assertFalse(result);
    }

    @Test
    public void setmAnnualEnergyConsumptionTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        //act
        boolean result = fridgeSpecs.setAnnualEnergyConsumption(2000.0);

        assertTrue(result);
    }


    @Test
    public void getAttributesToString() {

        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        String expectedResult =
                "1 - Freezer Capacity: 20.0\n" +
                        "2 - Refrigerator Capacity: 100.0\n" +
                        "3 - Annual Energy Consumption: 10000.0\n" +
                        "4 - Nominal Power: 100.0\n";

        // Act
        String result = fridgeSpecs.getAttributesToString();

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(1, 21);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(1, 20.0);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(2, 101);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(2, 100.0);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(3, 10001);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(3, 10000.0);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(4, 101);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(4, 100.0);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        // Act
        boolean result = fridgeSpecs.setAttribute(5, 100.0);

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
        FridgeSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        int expectedResult = 4;

        // Act
        int result = fridgeSpecs.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }
}
