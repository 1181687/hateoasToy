package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LampTest {

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // Lamp Instantiation

        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        fridge.getEnergyConsumptionInADay();

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

        fridge.getmNominalPower();
        double expectedResult = 100.0;

        //Act
        double result = fridge.getmNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

}
