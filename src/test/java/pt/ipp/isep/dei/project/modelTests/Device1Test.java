package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class Device1Test {

    @Test
    void getDeviceNameTest() {
        //Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room1, deviceSpecs1);

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

        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        // Device1 Instantiation
        Device1 device = new Device1("Electric Water Heater", room, electricWaterHeater);

        int coldWaterTempPosition = 5;
        device.setSpecAttribute(coldWaterTempPosition, 30);
        int volumeOfWaterToHeatPosition = 6;
        device.setSpecAttribute(volumeOfWaterToHeatPosition, 100);

        double expectedResult = 2.09;

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
        DeviceSpecs lamp = new LampSpecs(capacity, nominalPower);
        Device1 lamp1 = new Device1(name, room1, lamp);

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
        DeviceSpecs lamp = new LampSpecs(capacity, nominalPower);
        Device1 lamp1 = new Device1(name, room1, lamp);
        Device1 lamp2 = new Device1("lamp one", room1, lamp);
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
        DeviceSpecs lamp = new LampSpecs(capacity, nominalPower);
        Device1 lamp1 = new Device1(name, room1, lamp);
        Device1 lamp2 = new Device1("lamp two", room1, lamp);
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
        DeviceSpecs lamp = new LampSpecs(capacity, nominalPower);
        Device1 lamp1 = new Device1(name, room1, lamp);
        Device1 lamp2 = new Device1("lamp two", room1, lamp);

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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);
        Device1 dev2 = new Device1("Lamp2", room, deviceSpecs1);
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(dev1);
        Device1 dev2 = new Device1("Lamp3", room, deviceSpecs1);
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

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

        // Device1 Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 device = new Device1("Electric Water Heater", room, deviceSpecs1);
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

        // Device1 Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 device = new Device1("Electric Water Heater", room, deviceSpecs1);
        DeviceList deviceList = new DeviceList();

        room.addDevice(device);

        deviceList.addDevice(device);

        // act
        boolean result = device.setLocation(room1);

        // assert
        assertTrue(result);
    }

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
        FridgeSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device1 dev = new Device1("Fridge1", room, deviceSpecs);

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

        // Device1 Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device1 device = new Device1("Electric Water Heater", room, deviceSpecs1);

        room.addDevice(device);

        int expectedResult = 2;

        // act
        int result = device.getNumberOfSpecsAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameToString() {
        //Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room1", 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 20.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device1 dev1 = new Device1("Lamp1", room, deviceSpecs1);

        String expectedResult = "Device1: Lamp1, located in room: Room1\n";

        //Act
        String result = dev1.getNameToString();

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneSolution() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgeratah V14", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = device.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }


    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoSolutions() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgeratah V14", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = device.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithNoSolutions() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgeratah V14", room, fridge);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = device.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }


    @Test
    public void getIsActiveTrue() {

        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgeratah V14", room, fridge);

        room.addDevice(device);

        // act
        boolean result = device.getIsActive();

        // assert
        assertTrue(result);
    }

    @Test
    public void getIsActiveFalse() {

        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgeratah V14", room, fridge);

        room.addDevice(device);
        device.setDeactivateDevice();

        // act
        boolean result = device.getIsActive();

        // assert
        assertFalse(result);
    }

    @Test
    public void getDataSeries() {
        //Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        // Fridge Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device1 Instantiation
        Device1 device = new Device1("Fridgeratah V14", room, fridge);

        room.addDevice(device);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);

        Map<LocalDateTime, Double> mapToTest = new TreeMap<>();

        mapToTest.put(time0, 3.0);
        mapToTest.put(time1, 5.0);
        mapToTest.put(time2, 7.0);


        Map<LocalDateTime, Double> expectedResult = mapToTest;

        //Act
        Map<LocalDateTime, Double> result = device.getDataSeries(time0, time2);

        //Assert
        assertEquals(expectedResult, result);
    }

}
