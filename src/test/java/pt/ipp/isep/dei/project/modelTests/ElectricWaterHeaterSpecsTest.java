package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectricWaterHeaterSpecsTest {
    Room kitchen;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        electricWaterHeaterType.createDevice("DishWasher Bosch", kitchen);
    }

    @Test
    public void getEnergyConsumptionInADayTest1() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 50);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 2.09;

        // Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTestCoiso() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 80);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 5.23;

        // Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTest2() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 70);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 4.19;

        // Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getAttributesToString() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 50);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        String expectedResult = "1 - Hot Water Temperature: 50.0\n" +
                "2 - Performance Ratio: 0.9\n" +
                "3 - Nominal Power: 30.0\n";
        // Act
        String result = kitchen.getDeviceByPosition(0).getSpecsToString();
        // assert
        assertEquals(expectedResult, result);
    }



}