package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.ElectricWaterHeater;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterTest {

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        electricWaterHeater.setmColdWaterTemperature(30);
        electricWaterHeater.setmVolumeOfWaterToHeat(100);

        double expectedResult = 2093.4;

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setmHotWaterTemperatureTrue() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeater.setmHotWaterTemperature(2000.0);

        // assert
        assertTrue(result);
    }

    @Test
    public void setmHotWaterTemperatureFalse() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeater.setmHotWaterTemperature(50);

        // assert
        assertFalse(result);
    }

    @Test
    public void setmMaximumVolumeTrue() {
        // Arrange
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeater.setmMaximumVolume(2000);

        // assert
        assertTrue(result);
    }

    @Test
    public void setmMaximumVolumeFalse() {
        // Arrange
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeater.setmMaximumVolume(150);

        // assert
        assertFalse(result);
    }
}