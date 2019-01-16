package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    @Test
    void getDeviceNameTest() {
        //Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        String expectedResult = "Lamp1";

        //Act
        String result = dev1.getmName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double nominalPower1 = 20.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        double expectedResult = 20.0;

        //Act
        double result = dev1.getNominalPower();

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double nominalPower = 100;
        double performanceRatio = 0.9;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower, performanceRatio);

        // Device Instantiation
        Device device = new Device("Electric Water Heater", room, electricWaterHeater);

        device.setColdWaterTempAndVolumeOfWaterToHeat(30, 100);

        double expectedResult = 2093.4;

        // Act
        double result = device.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void testHashCode() {
        //Arrange
        String name = "LAMP ONE";
        double nominalPower = 200;
        int capacity = 100;
        Dimensions dim = new Dimensions(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);
        Device lamp1 = new Device(name, room1, lamp);

        int expectedResult = Objects.hash(name);

        // Act
        int result = lamp1.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        //Arrange
        String name = "LAMP ONE";
        double nominalPower = 200;
        int capacity = 100;
        Dimensions dim = new Dimensions(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);
        Device lamp1 = new Device(name, room1, lamp);
        Device lamp2 = new Device("lamp one", room1, lamp);
        //Act
        boolean result = lamp1.equals(lamp2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        //Arrange
        String name = "LAMP ONE";
        double nominalPower = 200;
        int capacity = 100;
        Dimensions dim = new Dimensions(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);
        Device lamp1 = new Device(name, room1, lamp);
        Device lamp2 = new Device("lamp two", room1, lamp);
        //Act
        boolean result = lamp1.equals(lamp2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseDifTypes() {
        //Arrange
        String name = "LAMP ONE";
        double nominalPower = 200;
        int capacity = 100;
        Dimensions dim = new Dimensions(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);
        Device lamp1 = new Device(name, room1, lamp);
        Device lamp2 = new Device("lamp two", room1, lamp);

        //Act
        boolean result = lamp1.equals(room1);
        //Assert
        assertFalse(result);
    }


}
