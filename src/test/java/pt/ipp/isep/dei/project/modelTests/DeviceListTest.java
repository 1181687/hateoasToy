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
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        List<Device> expectedResult = new ArrayList<>(Arrays.asList(dev1));

        devList.addDevice(dev1);
        List<Device> result = devList.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        DeviceList devList = new DeviceList();
        Device dev1 = null;
        List<Device> expectedResult = new ArrayList<>();

        devList.addDevice(dev1);
        List<Device> result = devList.getDeviceList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCode() {
        DeviceList deviceList = new DeviceList();
        int expectedResult = Objects.hash(deviceList.getDeviceList());

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
    void testEqualsTrueSameObj() {
        //Arrange

        DeviceList deviceList1 = new DeviceList();

        //Act
        boolean result = deviceList1.equals(deviceList1);
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
        Dimension dim = new Dimension(3, 3.5, 3.5);
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

        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);

        String expectedResult =
                "1 - Name of the device: Lamp1\n" +
                        "2 - Name of the device: Lamp2\n";

        // Act
        String result = deviceList.getDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        DeviceList deviceList = new DeviceList();

        // Act
        boolean result = deviceList.isDeviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
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
        boolean result = deviceList.isDeviceListEmpty();

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
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double performanceRatio = 0.9;
        double nominalPower0 = 100;

        DeviceSpecs electricWaterHeater0 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, performanceRatio, nominalPower0);

        // Device Instantiation
        Device device0 = new Device("Electric Water Heater", room, electricWaterHeater0);

        // DeviceList Instantiation
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device0);

        int coldWaterTempPosition = 5;
        int volumeOfWaterToHeatPosition = 6;
        deviceList.setAttribute(0, coldWaterTempPosition, 30);
        deviceList.setAttribute(0, volumeOfWaterToHeatPosition, 100);

        double expectedResult = 2.09;

        // Act
        double result = deviceList.getEnergyConsumptionOfADevice(0);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void newElectricWaterHeater() {
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        Device expectedResult = new Device("Electric", room, electricWaterHeater1);
        String name = "Electric";

        Device result = devList.newElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        assertEquals(expectedResult, result);
    }

    @Test
    public void newElectricWaterHeaterNegative() {
        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 100;

        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        Device d2 = new Device("Electric2", room, electricWaterHeater1);

        DeviceList devList = new DeviceList();
        devList.addDevice(d2);
        String name = "ELECTRIC2";

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newElectricWaterHeater(name, room, hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

    }

    @Test
    public void testNewWashingMachine() {
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        ProgramList programList = new ProgramList();
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower, programList);

        Device d2 = new Device("Device2", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, washingMachine);

        Device result = devList.newWashingMachine(name, room, nominalPower, capacity, programList);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewWashingMachineNegative() {
        // newWashingMachine Instantiation
        String name = "Washing Machine Bosh";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double capacity = 100;
        ProgramList programList = new ProgramList();
        DeviceSpecs washingMachine = new WashingMachine(capacity, nominalPower, programList);

        Device d2 = new Device("Washing Machine Bosh", room, washingMachine);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);


        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newWashingMachine(name, room, nominalPower, capacity, programList)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewDishWasher() {
        String name = "Dish Washer Ariston";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        ProgramList programList = new ProgramList();

        DeviceSpecs dishWasher = new DishWasher(capacity, nominalPower, programList);

        Device d2 = new Device("Device2", room, dishWasher);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Device expectedResult = new Device(name, room, dishWasher);

        Device result = devList.newDishWasher(name, room, nominalPower, capacity, programList);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewDishWasherNegative() {
        String name = "Dish Washer Ariston";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        int capacity = 100;
        ProgramList programList = new ProgramList();

        DeviceSpecs dishWasher = new DishWasher(capacity, nominalPower, programList);

        Device d2 = new Device("Dish Washer Ariston", room, dishWasher);
        DeviceList devList = new DeviceList();
        devList.addDevice(d2);

        Throwable exception = assertThrows(RuntimeException.class, () ->
                devList.newDishWasher(name, room, nominalPower, capacity, programList)
        );

        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void testNewLamp() {
        String name = "Lamp one";

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        double nominalPower = 200;
        double luminousFlux = 100;
        DeviceSpecs lamp = new Lamp(luminousFlux, nominalPower);
        ProgramList programList = new ProgramList();

        //Device d2 = new Device("Device2", room, lamp);
        DeviceList devList = room.getDeviceList();
        Room room2 = new Room("Room2", 2, dim);

        //  devList.addDevice(d2);

        Device expectedResult = new Device(name, room2, lamp);

        Device result = devList.newLamp(name, room, luminousFlux, nominalPower);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLampNegative() {
        String name = "Lamp one";

        Dimension dim = new Dimension(3, 3.5, 3.5);
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

        Dimension dim = new Dimension(3, 3.5, 3.5);
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
    public void testNewFridgeNegative() {
        String name = "Fridge Balay";

        Dimension dim = new Dimension(3, 3.5, 3.5);
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

    @Test
    public void testAddDeviceToDeviceList() {
        //Arrange

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        String name = "Fridge Balay";
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device d2 = new Device(name, room, fridgeSpecs);
        Device d3 = new Device(name, room, fridgeSpecs);

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d2);

        boolean expectedResult = false;

        //Act
        boolean result = deviceList.addDevice(d3);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceTypeListContent() {
        //Arrange
        DeviceList deviceList = new DeviceList();
        String expectedResult = "1- Fridge\n" +
                "2- Lamp\n" +
                "3- Dish Washer\n" +
                "4- Washing Machine\n" +
                "5- Electric Water Heater\n";
        //Act
        String result = deviceList.getDeviceTypeListToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceName() {
        //Arrange
        //device d1
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        String name = "Fridge Balay";
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device fridge = new Device(name, room, fridgeSpecs);

        //device d2
        Dimension dim2 = new Dimension(3, 3.5, 3.5);
        Room room2 = new Room("Room", 2, dim);
        String lampName = "Kitchen Lamp";
        double lampNominalPower = 200;
        double luminousFlux = 1000;
        DeviceSpecs lampSpec = new Lamp(luminousFlux, lampNominalPower);

        Device lamp = new Device(lampName, room, fridgeSpecs);

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(fridge);
        deviceList.addDevice(lamp);

        int devicePosition = 1;

        String expectedResult = "Kitchen Lamp";
        //Act
        String result = deviceList.getDeviceName(devicePosition);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetTotalEnergyConsumption() {
        //Arrange
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
        double hotWaterTemp1 = 50;
        double maximumVolume1 = 150;
        double performanceRatio1 = 0.8;
        double nominalPower1 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);

        // Device Instantiation
        Device device = new Device("Titan RX-Coiso", room, electricWaterHeater);
        Device device1 = new Device("Bosch Tronic 3000", room, electricWaterHeater1);

        // DeviceList Instantiation
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device);
        deviceList.addDevice(device1);

        int coldWaterTempPosition = 5;
        int volumeOfWaterToHeatPosition = 6;
        deviceList.setAttribute(0, coldWaterTempPosition, 30);
        deviceList.setAttribute(0, volumeOfWaterToHeatPosition, 100);
        deviceList.setAttribute(1, coldWaterTempPosition, 30);
        deviceList.setAttribute(1, volumeOfWaterToHeatPosition, 100);

        double expectedResult = 3.95;

        //Act
        double result = deviceList.getTotalEnergyConsumption();
        //Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void testGetContentNameLocationOrderedByType() {
        // Arrange
        String roomKitchen = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room kitchen = new Room(roomKitchen, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", kitchen, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;
        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", kitchen, deviceSpecs2);

        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev3 = new Device("Fridge Balay", kitchen, fridgeSpecs);

        Room basement = new Room("Basement", -1, dim);
        double luminousFlux3 = 30.0;
        double nominalPower3 = 1.0;
        DeviceSpecs deviceSpecs4 = new Lamp(luminousFlux3, nominalPower3);
        Device dev4 = new Device("Lamp4", basement, deviceSpecs4);
        DeviceSpecs fridgeSpecs5 = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev5 = new Device("Fridge Teka", basement, fridgeSpecs5);

        DeviceList allDeviceList = new DeviceList();
        allDeviceList.addDevice(dev1);
        allDeviceList.addDevice(dev2);
        allDeviceList.addDevice(dev3);
        allDeviceList.addDevice(dev4);
        allDeviceList.addDevice(dev5);

        String expectedResult =
                "Lamp\n" +
                        "- Device Name: Lamp1, Location: Kitchen.\n" +
                        "- Device Name: Lamp2, Location: Kitchen.\n" +
                        "- Device Name: Lamp4, Location: Basement.\n" +
                        "\n" +
                        "Fridge\n" +
                        "- Device Name: Fridge Balay, Location: Kitchen.\n" +
                        "- Device Name: Fridge Teka, Location: Basement.\n" +
                        "\n";

        // Act
        String result = allDeviceList.getContentNameLocationOrderedByType();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testsRemoveDevice() {
        //Arrange
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
        double hotWaterTemp1 = 50;
        double maximumVolume1 = 150;
        double performanceRatio1 = 0.8;
        double nominalPower1 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);


        // Device Instantiation
        Device device = new Device("Titan RX-Coiso", room, electricWaterHeater);
        Device device1 = new Device("Bosch Tronic 3000", room, electricWaterHeater1);


        room.addDevice(device);
        room.addDevice(device1);

        room.removeDevice(device1);

        DeviceList compareList = new DeviceList();

        compareList.addDevice(device);

        DeviceList expectedResult = compareList;

        //Act
        DeviceList result = room.getDeviceList();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testsRemoveDeviceBoolean() {
        //Arrange
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
        double hotWaterTemp1 = 50;
        double maximumVolume1 = 150;
        double performanceRatio1 = 0.8;
        double nominalPower1 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);

        // Device Instantiation
        Device device = new Device("Titan RX-Coiso", room, electricWaterHeater);
        Device device1 = new Device("Bosch Tronic 3000", room, electricWaterHeater1);

        room.addDevice(device);
        room.addDevice(device1);

        //Act
        boolean result = room.removeDevice(device1);

        //Assert
        assertTrue(result);
    }
}

