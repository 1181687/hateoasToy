package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.ElectricWaterHeater;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterTest {
    @Test
    public void getEnergyConsumptionInADayTest1() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        electricWaterHeater.setmColdWaterTemperature(30);
        electricWaterHeater.setmVolumeOfWaterToHeat(100);

        double expectedResult = 2093.4;

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTest2() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        electricWaterHeater.setmColdWaterTemperature(100);
        electricWaterHeater.setmVolumeOfWaterToHeat(30);

        double expectedResult = 1570.05;

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
    public void setmVolumeOfWaterToHeatEqualZero() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeater.setmVolumeOfWaterToHeat(0.0);

        // assert
        assertFalse(result);
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

    @Test
    public void setmPerformanceRatioTrue() {
        // Arrange
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeater.setmPerformanceRatio(2);

        // assert
        assertTrue(result);
    }

    @Test
    public void setmPerformanceRatioFalse() {
        // Arrange
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;

        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setmPerformanceRatio(0.9);

        // assert
        assertFalse(result);
    }

    @Test
    public void setmNominalPowerTrue() {
        // Arrange
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;

        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setmNominalPower(200);

        // assert
        assertTrue(result);
    }

    @Test
    public void setmNominalPowerFalse() {
        // Arrange
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;

        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setmNominalPower(100);

        // assert
        assertFalse(result);
    }

    @Test
    public void getAttributesToString() {
        // Arrange
        double hotWaterTemp = 50.0;
        double maximumVolume = 150.0;
        double performanceRatio = 0.9;
        double nominalPower = 100.0;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        String expectedResult = "1 - Hot Water Temperature: 50.0\n" +
                "2 - Maximum Volume: 150.0\n" +
                "3 - Performance Ratio: 0.9\n" +
                "4 - Nominal Power: 100.0\n";
        // Act
        String result = electricWaterHeater.getAttributesToString();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributeTrueHotWaterTemp() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(1, 51);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseHotWaterTemp() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(1, 50.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeTrueMaximumVolume() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(2, 151);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseMaximumVolume() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(2, 150.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeTrueNominalPower() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(4, 101);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseNominalPower() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(4, 100.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeTruePerformanceRatio() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(3, 1.0);

        // assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalsePerformanceRatio() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(3, 0.9);

        // assert
        assertFalse(result);
    }

    @Test
    public void setAttributeFalse() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeater.setAttribute(7, 30.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        double hotWaterTemperature = 50.0;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);
        int expectedResult = 4;

        // Act
        int result = electricWaterHeater.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPowerTest() {
        // Arrange
        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        double expectedResult = 100;

        // Act
        double result = electricWaterHeater.getmNominalPower();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}