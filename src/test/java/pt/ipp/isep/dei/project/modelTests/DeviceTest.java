package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower);

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
    public void getSpecsAttributes () {
        DeviceSpecs lamp = new Lamp(100, 30);
        Dimensions di = new Dimensions(45, 45, 45);
        Room room = new Room("name", 4, di);
        Device lamp1 = new Device("cenas", room, lamp);

        String lampAttributes = lamp1.getSpecsAttributes();


        DeviceSpecs washMach = new WashingMachine(4, 67);
        Device washMach1 = new Device("coiso", room, washMach);

        String washMachAttributes = washMach1.getSpecsAttributes();

        System.out.println(lampAttributes + washMachAttributes);


    }
}
