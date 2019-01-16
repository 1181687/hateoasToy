package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Fridge;

import static org.junit.jupiter.api.Assertions.*;

public class FridgeTest {

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

        fridge.setmFreezerCapacity(20.0);

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

        fridge.setmFreezerCapacity(100.0);

        //act
        boolean result = fridge.setmFreezerCapacity(100.0);

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

        fridge.setmRefrigeratorCapacity(100.0);

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

        fridge.setmRefrigeratorCapacity(20.0);

        //act
        boolean result = fridge.setmRefrigeratorCapacity(20.0);

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

        fridge.setmNominalPower(20.0);

        //act
        boolean result = fridge.setmNominalPower(20.0);

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

        fridge.setmNominalPower(100.0);

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

        fridge.setmAnnualEnergyConsumption(20.0);

        //act
        boolean result = fridge.setmAnnualEnergyConsumption(20.0);

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

        fridge.setmAnnualEnergyConsumption(1000.0);

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
}
