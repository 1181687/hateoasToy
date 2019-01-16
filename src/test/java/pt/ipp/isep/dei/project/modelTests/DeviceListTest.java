package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceListTest {

    @Test
    public void testAddDeviceTrue() {
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1));

        devList.addDevice(dev1);
        List<Device> result = devList.getmDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        DeviceList devList = new DeviceList();
        Device dev1 = null;
        List<Device> expectedResult = new ArrayList<>();

        devList.addDevice(dev1);
        List<Device> result = devList.getmDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCode() {
        DeviceList deviceList = new DeviceList();
        int expectedResult = Objects.hash(deviceList.getmDeviceList());

        // Act
        int result = deviceList.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        //Arrange

        DeviceList deviceList1 = new DeviceList();
        DeviceList deviceList2 = new DeviceList();

        //Act
        boolean result = deviceList1.equals(deviceList2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsTrueFalse() {
        //Arrange

        DeviceList deviceList1 = new DeviceList();
        DeviceList deviceList2 = new DeviceList();
        HouseGridList list = new HouseGridList();

        //Act
        boolean result = deviceList1.equals(list);
        //Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        deviceList.addDeviceToDeviceList(dev1);
        deviceList.addDeviceToDeviceList(dev2);

        String expectedResult =
                "1 - Name of the device: Lamp1\n" +
                        "2 - Name of the device: Lamp2\n";

        // Act
        String result = deviceList.getDeviceListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        DeviceList deviceList = new DeviceList();

        // Act
        boolean result = deviceList.checkIfDeviceListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange

        String name = "Kitchen";
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        DeviceList deviceList = new DeviceList();

        deviceList.addDeviceToDeviceList(dev1);

        // Act
        boolean result = deviceList.checkIfDeviceListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getEnergyConsumptionInADayOfAllDevicesOfATypeTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 0.9;
        DeviceSpecs electricWaterHeater0 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);
        double hotWaterTemp1 = 60;
        double maximumVolume1 = 200;
        double nominalPower1 = 110;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, nominalPower1, performanceRatio);

        // Device Instantiation
        Device device0 = new Device("Electric Water Heater A", room, electricWaterHeater0);
        Device device1 = new Device("Electric Water Heater B", room, electricWaterHeater1);

        // DeviceList Instantiation
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device0);
        deviceList.addDevice(device1);

        deviceList.setColdWaterTempAndVolumeOfWaterToHeat(30, 100);

        double expectedResult = 5233.5;

        // Act
        double result = deviceList.getEnergyConsumptionInADayOfAllDevicesOfAType("Electric Water Heater");

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void newElectricWaterHeater() {
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0);

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        Device expectedResult = new Device("Electric", room, electricWaterHeater1);
        String name = "Electric";

        Device result = devList.newElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void newElectricWaterHeaterNegative() {
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0);

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        String name = "ELECTRIC2";

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

    }

    @Test
    public void testNewWashingMachine() {
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower);

        Device d2 = new Device("Device2", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, washingMachine);

        Device result = devList.newWashingMachine(name, room, nominalPower, capacity);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewWashingMachineNegative() {
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower);

        Device d2 = new Device("Washing Machine Bosh", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);


        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newWashingMachine(name, room, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewDishWasher() {
        String name = "Dish Washer Ariston";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs dishWasher = new DishWasher(capacity, nominalPower);

        Device d2 = new Device("Device2", room, dishWasher);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, dishWasher);

        Device result = devList.newDishWasher(name, room, nominalPower, capacity);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewDishWasherNegative() {
        String name = "Dish Washer Ariston";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs dishWasher = new DishWasher(capacity, nominalPower);

        Device d2 = new Device("Dish Washer Ariston", room, dishWasher);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newDishWasher(name, room, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewLamp() {
        String name = "Lamp one";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);

        Device d2 = new Device("Device2", room, lamp);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, lamp);

        Device result = devList.newDishWasher(name, room, nominalPower, capacity);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLampNegative() {
        String name = "Lamp one";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        DeviceSpecs lamp = new Lamp(capacity, nominalPower);

        Device d2 = new Device("LAMP ONE", room, lamp);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newLamp(name, room, nominalPower, capacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewFrigde() {
        String name = "Fridge Balay";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device d2 = new Device("Device2", room, fridgeSpecs);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, fridgeSpecs);

        Device result = devList.newFridge(name, room, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewFrigdeNegative() {
        String name = "Fridge Balay";

        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device d2 = new Device("Fridge Balay", room, fridgeSpecs);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newFridge(name, room, annualEnergyConsumption, nominalPower, freezerCapacity, refrigeratorCapacity)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

}

