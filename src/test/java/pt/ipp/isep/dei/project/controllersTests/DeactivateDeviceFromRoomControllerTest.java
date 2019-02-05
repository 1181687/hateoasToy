package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.DeactivateDeviceFromRoomController;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeactivateDeviceFromRoomControllerTest {

    private DeactivateDeviceFromRoomController controller;
    private House house;

    @BeforeEach
    public void StartUp(){
        //Geographical Area
        Location local = new Location(10, 10, 10);

        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList,meteringPeriodGrid,meteringPeriodDevice);
        Address address = new Address("5000", local);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);
        this.controller = new DeactivateDeviceFromRoomController(house);
    }

    @Test
    public void testGetDisplayRoomListTest() {
        //arrange
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        int position = 0;
        this.controller.getRoomPosition(position);

        //act
        String result = controller.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDisplayRoomListEmptyTest() {
        //arrange
        String expectResult = "";

        //act
        String result = this.controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange


        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        this.house.addRoom(room);

        int position = 0;
        this.controller.getRoomPosition(position);

        // Act
        boolean result = controller.deviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        DeviceSpecs specFridge = new FridgeSpecs(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        this.house.addRoom(room);
        room.addDevice(dev1);

        int position = 0;
        this.controller.getRoomPosition(position);

        // Act
        boolean result = controller.deviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        int expectResult = 2;
        //act
        int result = this.controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange

        int expectResult = 0;
        //act
        int result = this.controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void deactivateDeviceTrue() {
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
        this.house.addRoom(room);
        room.addDevice(dev1);
        deviceList.addDevice(dev1);
        this.controller.getRoomPosition(position);

        // act
        boolean result = this.controller.deactivateDevice("Lamp1");

        // assert
        assertTrue(result);
    }

    @Test
    public void deactivateDeviceFalse() {
        // Arrange

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;
        this.house.addRoom(room);
        this.controller.getRoomPosition(position);
        // act
        boolean result = this.controller.deactivateDevice("Lamp1");

        // assert
        assertFalse(result);
    }

    @Test
    public void getDeviceNameByPositionIsEmpty() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        int position = 0;

        this.house.addRoom(room);
        this.controller.getRoomPosition(position);
        String expectedResult = "There are no devices in the device list.";

        // act
        String result = this.controller.deviceNameByPosition(position);

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
        house.addRoom(room);
        controller.getRoomPosition(position);

        // act
        String result = controller.deviceNameByPosition(position);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {

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

        int position = 0;

        house.addRoom(room);
        room.addDevice(dev1);
        room.addDevice(dev2);
        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);
        controller.getRoomPosition(position);

        int expectResult = 2;
        //act
        int result = controller.deviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //act
        boolean result = controller.roomListEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        // initiate House
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);
        house.addRoom(room1);

        //act
        boolean result = controller.roomListEmpty();

        //assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        Device expectedResult = dev;

        int position = 0;
        house.addRoom(room);
        room.addDevice(dev);
        house.addRoom(room);
        house.addRoom(room);
        house.addRoom(room);
        controller.getRoomPosition(position);
        // Act
        Device result = controller.getDevice(position);

        // Assert
        assertEquals(expectedResult, result);
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
        room.addDevice(dev1);
        room.addDevice(dev2);
        house.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the device: Lamp1 - ACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - ACTIVATED\n";
        controller.getRoomPosition(position);
        // Act
        String result = controller.getActiveDeviceListToString();

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
        house.addRoom(room);
        int position = 0;

        String expectedResult =
                "1 - Name of the device: Lamp1 - DEACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - ACTIVATED\n";
        controller.getRoomPosition(position);
        // Act
        String result = controller.getActiveDeviceListToString();
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

        int position = 0;

        deviceList.addDevice(dev1);
        deviceList.addDevice(dev2);
        dev1.setDeactivateDevice();
        dev2.setDeactivateDevice();
        house.addRoom(room);

        String expectedResult =
                "1 - Name of the device: Lamp1 - DEACTIVATED\n" +
                        "2 - Name of the device: Lamp2 - DEACTIVATED\n";
        controller.getRoomPosition(position);
        // Act
        String result = controller.getActiveDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }
}