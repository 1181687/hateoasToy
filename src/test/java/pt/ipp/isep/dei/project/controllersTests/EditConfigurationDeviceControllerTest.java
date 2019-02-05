package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.EditConfigurationDeviceController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EditConfigurationDeviceControllerTest {
    private EditConfigurationDeviceController controller;
    private House houseEdificioB;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("devicetype.count", "devicetype.name");

        this.houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        houseEdificioB.setAddress(address);
        houseEdificioB.setInsertedGeoArea(insertedGeoArea);

        this.controller = new EditConfigurationDeviceController(houseEdificioB);

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

        houseEdificioB.addRoom(room1);
        houseEdificioB.addRoom(room2);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        int position = 0;
        controller.getRoomByPosition(position);

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDisplayRoomListEmptyTest() {
        //arrange

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        String expectResult = "";

        //act
        String result = controller.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetRoomName() {
        //Arrange
        Dimension dim0 = new Dimension(4, 4, 4);
        Room room0 = new Room("RoomOne", 1, dim0);
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("RoomTwo", 1, dim1);

        houseEdificioB.addRoom(room0);
        houseEdificioB.addRoom(room1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        String expectedResult = "RoomTwo";
        int roomPos = 1;
        //Act
        String result = controller.getRoomName(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomNameEmpty() {
        //Arrange

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        String expectedResult = null;
        int roomPos = 0;
        //Act
        String result = controller.getRoomName(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDevicesInTheRoomTest() {
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

        double luminousFlux = 10.0;
        double nominalPower1 = 0.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);
        houseEdificioB.addRoom(room);
        int option = 0;

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        controller.getRoomByPosition(option);
        // Act
        String result = controller.getDevicesInTheRoom();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDeviceAttributesToString() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate Devices
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        FridgeSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);

        int position = 0;
        room.addDevice(dev);
        houseEdificioB.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        String expectedResult = "1 - Name: Fridge1" + "\n" +
                "2 - Device Specifications\n" +
                "3 - Location: Room\n";
        // act
        String result = controller.getDeviceAttributesToString();

        // assert
        assertEquals(expectedResult, result);
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
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        Device dev2 = new Device("Lamp2", room, deviceSpecs1);
        room.addDevice(dev1);
        room.addDevice(dev2);

        int position = 0;
        houseEdificioB.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        Throwable exception = assertThrows(RuntimeException.class, () -> dev1.setName("Lamp1"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

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
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);
        room.addDevice(dev1);

        int position = 0;
        houseEdificioB.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        // Act
        boolean result = controller.setDeviceName("Lamp10");

        // Assert
        assertTrue(result);
    }

    @Test
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device device = new Device("Lamp1", room, deviceSpecs1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);

        int position = 0;
        room.addDevice(device);
        houseEdificioB.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        String expectedResult = "1 - Luminous Flux: 10.0\n" +
                "2 - Nominal Power: 1.0\n";
        // act
        String result = controller.getDevSpecsAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetLocation() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);
        Room room2 = new Room("Bedroom", 1, dim);

        // Device Instantiation
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);

        int position = 0;
        room.addDevice(device);
        houseEdificioB.addRoom(room);
        houseEdificioB.addRoom(room2);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(1);

        // act
        boolean result = controller.setLocation();

        // assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        //act
        boolean result = controller.roomListIsEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        String name1 = "Kitchen";
        int houseEdificioBFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseEdificioBFloor1, dimension1);

        houseEdificioB.addRoom(room1);
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        //act
        boolean result = controller.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange
        String name1 = "Kitchen";
        int houseEdificioBFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseEdificioBFloor1, dimension1);

        String name2 = "Living Room";
        int houseEdificioBFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseEdificioBFloor2, dimension2);

        houseEdificioB.addRoom(room1);
        houseEdificioB.addRoom(room2);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);

        int expectResult = 2;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        int expectResult = 0;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testAddDeviceTrue() {
        // arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);
        DeviceList devList = new DeviceList();

        DeviceSpecs specFridge = new FridgeSpecs(100, 100, 100, 100);
        Device dev1 = new Device("FridgeAriston", room, specFridge);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        room.addDevice(dev1);
        houseEdificioB.addRoom(room);
        int position = 0;
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        // act
        int expectedResult = 1;

        devList.addDevice(dev1);
        int result = controller.getDeviceListSize();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        // arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        houseEdificioB.addRoom(room);
        int position = 0;
        controller.getRoomByPosition(position);
        // act
        int expectedResult = 0;

        int result = controller.getDeviceListSize();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        houseEdificioB.addRoom(room);

        int position = 0;
        controller.getRoomByPosition(position);

        // Act
        boolean result = controller.deviceListIsEmpty();

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

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        houseEdificioB.addRoom(room);
        room.addDevice(dev1);

        int position = 0;
        controller.getRoomByPosition(position);

        // Act
        boolean result = controller.deviceListIsEmpty();

        // Assert
        assertFalse(result);
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
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device device = new Device("Electric Water Heater", room, deviceSpecs1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);

        int position = 0;
        room.addDevice(device);
        houseEdificioB.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        int expectedResult = 2;

        // act
        int result = controller.getNumberOfAttributesInDeviceSpecs();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeviceSpecs() {
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

        int coldWaterTempPosition = 5;
        // Device Instantiation
        Device device = new Device("Electric Water Heater", room, electricWaterHeater);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);

        int position = 0;
        room.addDevice(device);
        houseEdificioB.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(0);

        // Act
        boolean result = controller.setDeviceSpecs(coldWaterTempPosition, 30);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetEditableAttributesContent() {
        //Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // Device Instantiation
        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        // Device Instantiation
        Device device = new Device("Electric Water Heater", room, electricWaterHeaterSpecs);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(houseEdificioB);
        int position = 0;
        room.addDevice(device);
        houseEdificioB.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(0);

        String expectedResult = "1 - Hot Water Temperature: 50.0\n" +
                "2 - Maximum Volume: 150.0\n" +
                "3 - Performance Ratio: 0.9\n" +
                "4 - Nominal Power: 100.0\n";

        //Act
        String result = controller.getDevSpecsAttributesToString();

        //Assert
        assertEquals(expectedResult, result);

    }
}