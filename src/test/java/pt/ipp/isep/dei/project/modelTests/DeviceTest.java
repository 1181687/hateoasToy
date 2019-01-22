package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    @Test
    void getDeviceNameTest() {
        //Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        String expectedResult = "Lamp1";

        //Act
        String result = dev1.getName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
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
    public void getLocation() {

        //Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room(name, 2, dim);

        String name2 = "Bedroom";
        Dimension dim2 = new Dimension(3, 3.5, 3.5);
        Room room2 = new Room(name2, 2, dim2);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room1, deviceSpecs1);

        room1.addDevice(dev1);
        room2.addDevice(dev1);

        Room expectedResult = room2;

        //act
        Room result = dev1.getLocation();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Device Instantiation
        Device device = new Device("Electric Water Heater", room, electricWaterHeater);

        int coldWaterTempPosition = 5;
        device.setAttributesDevType(coldWaterTempPosition, 30);
        int volumeOfWaterToHeatPosition = 6;
        device.setAttributesDevType(volumeOfWaterToHeatPosition, 100);

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
        Dimension dim = new Dimension(3.5, 6.5, 7.5);
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
        Dimension dim = new Dimension(3.5, 6.5, 7.5);
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
        Dimension dim = new Dimension(3.5, 6.5, 7.5);
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
        Dimension dim = new Dimension(3.5, 6.5, 7.5);
        Room room1 = new Room("Room1", 2, dim);
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);
        Device lamp1 = new Device(name, room1, lamp);
        Device lamp2 = new Device("lamp two", room1, lamp);

        //Act
        boolean result = lamp1.equals(room1);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testSetNameSameNameFalse() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dev1);

        // Assert
        Throwable exception = assertThrows(RuntimeException.class, () -> dev1.setName("Lamp1"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testSetNameAlreadyInListFalse() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        Device dev2 = new Device("Lamp2", room, deviceSpecs1);
        room.addDevice(dev1);
        room.addDevice(dev2);


        Throwable exception = assertThrows(RuntimeException.class, () -> dev1.setName("Lamp1"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

    }

    @Test
    public void testSetNameEmpty() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dev1);

        // Act
        boolean result = dev1.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetNameTrue() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dev1);
        Device dev2 = new Device("Lamp3", room, deviceSpecs1);
        deviceList.addDevice(dev2);

        // Act
        boolean result = dev1.setName("Lamp10");

        // Assert
        assertTrue(result);
    }

    @Test
    public void getType() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        String expectedResult = "Lamp";
        room.addDevice(dev1);
        // act
        String result = dev1.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetLocationSame() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);
        DeviceList deviceList = new DeviceList();

        room.addDevice(device);
        deviceList.addDevice(device);

        // act
        boolean result = device.setLocation(room);

        // assert
        assertFalse(result);
    }

    @Test
    void testSetLocationDiferent() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);
        Room room1 = new Room("Room1", 2, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);
        DeviceList deviceList = new DeviceList();

        room.addDevice(device);

        deviceList.addDevice(device);

        // act
        boolean result = device.setLocation(room1);

        // assert
        assertTrue(result);
    }

    /* @Test
    void testGetSpecsAttributesToString() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        room.addDevice(device);

        String expectedResult = "1 - Luminous Flux: 10.0\n" +
                "2 - Nominal Power: 1.0\n";

        // act
        String result = device.getSpecsAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    } */

    @Test
    void testGetDeviceAttributesToString() {
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        Fridge deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        room.addDevice(dev);

        String expectedResult = "1 - Freezer Capacity: 5.5\n" +
                "2 - Refrigerator Capacity: 15.5\n" +
                "3 - Annual Energy Consumption: 3000.0\n" +
                "4 - Nominal Power: 100.5\n";
        // act
        String result = deviceSpecs.getAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNumberOfAttributesInDeviceSpecs() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        room.addDevice(device);

        int expectedResult = 2;

        // act
        int result = device.getNumberOfSpecsAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

}
