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

        DeviceSpecs specFridge = new FridgeSpecs(100, 100, 100, 100);
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
        HouseGridList list = new HouseGridList();

        //Act
        boolean result = deviceList1.equals(list);
        //Assert
        assertFalse(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        DeviceList deviceList = new DeviceList();

        deviceList.addDevice(dev1);

        // Act
        boolean result = deviceList.isEmpty();

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

        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double performanceRatio = 0.9;
        double nominalPower0 = 100;

        DeviceSpecs electricWaterHeater0 = new ElectricWaterHeaterSpecs(hotWaterTemp0, maximumVolume0, performanceRatio, nominalPower0);

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
    public void testAddDeviceToDeviceList() {
        //Arrange

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        String name = "FridgeSpecs Balay";
        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

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

        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        double hotWaterTemp1 = 50;
        double maximumVolume1 = 150;
        double performanceRatio1 = 0.8;
        double nominalPower1 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeaterSpecs(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);

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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", kitchen, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;
        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", kitchen, deviceSpecs2);

        double nominalPower = 200;
        double annualEnergyConsumption = 1000;
        double freezerCapacity = 20;
        double refrigeratorCapacity = 50;
        DeviceSpecs fridgeSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev3 = new Device("Fridge Balay", kitchen, fridgeSpecs);

        Room basement = new Room("Basement", -1, dim);
        double luminousFlux3 = 30.0;
        double nominalPower3 = 1.0;
        DeviceSpecs deviceSpecs4 = new LampSpecs(luminousFlux3, nominalPower3);
        Device dev4 = new Device("Lamp4", basement, deviceSpecs4);
        DeviceSpecs fridgeSpecs5 = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
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

        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        double hotWaterTemp1 = 50;
        double maximumVolume1 = 150;
        double performanceRatio1 = 0.8;
        double nominalPower1 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeaterSpecs(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);


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

        // ElectricWaterHeaterSpecs Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        double hotWaterTemp1 = 50;
        double maximumVolume1 = 150;
        double performanceRatio1 = 0.8;
        double nominalPower1 = 100;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeaterSpecs(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);

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

    @Test
    public void getActiveDeviceListToString_Active() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);

        String expectedResult =
                "1 - Name of the device: Lamp1 - ACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - ACTIVATED\n";
        // Act
        String result = deviceList.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_OneDeviceDeactivated() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);
        dev1.setDeactivateDevice();

        String expectedResult =
                "1 - Name of the device: Lamp1 - DEACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - ACTIVATED\n";
        // Act
        String result = deviceList.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getActiveDeviceListToString_Deactivated() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);
        dev1.setDeactivateDevice();
        dev2.setDeactivateDevice();

        String expectedResult =
                "1 - Name of the device: Lamp1 - DEACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - DEACTIVATED\n";
        // Act
        String result = deviceList.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void deleteDeviceTrue() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);
        deviceList.addDevice(dev1);
        dev1.setDeactivateDevice();

        // act
        boolean result = deviceList.deleteDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deleteDeviceFalse() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // act
        boolean result = deviceList.deleteDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void deleteDeviceFalseList() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        // act
        boolean result = deviceList.deleteDevice("Lamp3");

        // assert
        assertFalse(result);
    }

    @Test
    public void deactivationDeleteFalse_DeviceNotExistant() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);


        // act
        boolean result = deviceList.deleteDevice("Lamp2");

        // assert
        assertFalse(result);
    }


    @Test
    public void deactivationDeleteFalse_DeviceNotFoundByName() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // act
        boolean result = deviceList.deleteDevice("Lamp2");

        // assert
        assertFalse(result);
    }

    @Test
    public void deleteDeviceFalse_notEquals() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;
        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        // act
        boolean result = deviceList.deleteDevice("Lamp2");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDeviceNameByPositionIsEmpty() {
        // Arrange
        DeviceList deviceList = new DeviceList();

        int position = 0;

        String expectedResult = "There are no devices in the device list.";

        // act
        String result = deviceList.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameByPosition() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        int position = 0;

        String expectedResult = "Lamp1";
        deviceList.addDevice(dev1);

        // act
        String result = deviceList.getDeviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void deactivationDeviceTrue() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);
        deviceList.addDevice(dev1);
        dev1.setDeactivateDevice();

        // act
        boolean result = deviceList.deactivateDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deactivationDeviceFalse() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // act
        boolean result = deviceList.deactivateDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void deactivationDeviceFalse_DeviceNotFoundByName() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        // act
        boolean result = deviceList.deactivateDevice("Lamp2");

        // assert
        assertFalse(result);
    }

    @Test
    public void deactivationDeviceFalse_notEquals() {

        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList deviceList = new DeviceList();

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;
        DeviceSpecs deviceSpecs2 = new LampSpecs(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

        // act
        boolean result = deviceList.deactivateDevice("Lamp2");

        // assert
        assertFalse(result);
    }
}

