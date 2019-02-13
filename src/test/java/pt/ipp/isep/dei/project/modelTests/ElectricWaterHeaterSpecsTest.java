package pt.ipp.isep.dei.project.modelTests;

class ElectricWaterHeaterSpecsTest {
    /*
    @Test
    public void getEnergyConsumptionInADayTest1() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs();

        electricWaterHeaterSpecs.setColdWaterTemperature(30);
        electricWaterHeaterSpecs.setVolumeOfWaterToHeat(100);

        double expectedResult = 2.09;

        // Act
        double result = electricWaterHeaterSpecs.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTestCoiso() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        electricWaterHeaterSpecs.setColdWaterTemperature(50);
        electricWaterHeaterSpecs.setVolumeOfWaterToHeat(100);

        double expectedResult = 5.23;

        // Act
        double result = electricWaterHeaterSpecs.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTest2() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        electricWaterHeaterSpecs.setColdWaterTemperature(100);
        electricWaterHeaterSpecs.setVolumeOfWaterToHeat(30);

        double expectedResult = 1.57;

        // Act
        double result = electricWaterHeaterSpecs.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setmHotWaterTemperatureTrue() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeaterSpecs.setHotWaterTemperature(2000.0);

        // assert
        assertTrue(result);
    }

    @Test
    public void setmVolumeOfWaterToHeatEqualZero() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeaterSpecs.setVolumeOfWaterToHeat(0.0);

        // assert
        assertFalse(result);
    }

    @Test
    public void setmHotWaterTemperatureFalse() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeaterSpecs.setHotWaterTemperature(50);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeaterSpecs.setMaximumVolume(2000);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeaterSpecs.setMaximumVolume(150);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Act
        boolean result = electricWaterHeaterSpecs.setPerformanceRatio(2);

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

        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setPerformanceRatio(0.9);

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

        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setNominalPower(200);

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

        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setNominalPower(100);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        String expectedResult = "1 - Hot Water Temperature: 50.0\n" +
                "2 - Maximum Volume: 150.0\n" +
                "3 - Performance Ratio: 0.9\n" +
                "4 - Nominal Power: 100.0\n";
        // Act
        String result = electricWaterHeaterSpecs.getAttributesToString();
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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(1, 51);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(1, 50.0);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(2, 151);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(2, 150.0);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(4, 101);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(4, 100.0);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(3, 1.0);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(3, 0.9);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);

        // Act
        boolean result = electricWaterHeaterSpecs.setAttribute(7, 30.0);

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
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemperature, maximumVolume, performanceRatio, nominalPower);
        int expectedResult = 4;

        // Act
        int result = electricWaterHeaterSpecs.getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPowerTest() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        double expectedResult = 100;

        // Act
        double result = electricWaterHeaterSpecs.getNominalPower();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void testEmptyConstructor() {
        // Arrange
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs();

        electricWaterHeaterSpecs.setNominalPower(nominalPower);

        double expectedResult = 100;

        // Act
        double result = electricWaterHeaterSpecs.getNominalPower();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
    */
}