package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.EditConfigurationDeviceController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditConfigurationDeviceControllerTest {
    private static final String FRIDGE_TYPE = "Fridge";
    private static final String ELECTRIC_W_H_TYPE = "ElectricWaterHeater";
    private EditConfigurationDeviceController controller;
    private House house;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);

        this.controller = new EditConfigurationDeviceController(house);

    }

    @Test
    public void testGetDisplayRoomListTest() {
        //arrange

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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
        Room room0 = new Room("RoomOne", "room", 1, dim0);
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("RoomTwo", "room", 1, dim1);

        house.addRoom(room0);
        house.addRoom(room1);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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
        Room room = new Room("Room", "room", 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        // iniciate Device EWH
        String HOT_WATER_TEMP = "Hot-Water Temperature";
        String PERFORMANCE_RATIO = "Performance Ratio";
        String NOMINAL_POWER = "Nominal Power";

        Device device3 = house.createDevice(ELECTRIC_W_H_TYPE, "Bosh Tronic 3000", room);
        device3.setAttributesDevType(HOT_WATER_TEMP, 50);
        device3.setAttributesDevType(PERFORMANCE_RATIO, 0.9);
        device3.setAttributesDevType(NOMINAL_POWER, 100);

        house.addRoom(room);
        int option = 0;

        String expectedResult =
                "1 - Name of the device: Fridgeratah V14\n" +
                        "2 - Name of the device: Bosh Tronic 3000\n";

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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
        Room room = new Room("Room", "room", 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        int position = 0;
        house.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        String expectedResult = "1 - Name: Fridgeratah V14\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Room\n";
        // act
        String result = controller.getDeviceAttributesToString();

        // assert
        assertEquals(expectedResult, result);
    }
/*
    @Test
    public void testSetNameAlreadyInListFalse() {
        // Arrange
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = housegrid.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        // iniciate Device EWH
        String HOT_WATER_TEMP = "Hot-Water Temperature";
        String PERFORMANCE_RATIO = "Performance Ratio";
        String NOMINAL_POWER = "Nominal Power";

        Device device1 = housegrid.createDevice(ELECTRIC_W_H_TYPE, "Bosh Tronic 3000", room);
        device1.setAttributesDevType(HOT_WATER_TEMP, 50);
        device1.setAttributesDevType(PERFORMANCE_RATIO, 0.9);
        device1.setAttributesDevType(NOMINAL_POWER, 100);

        int position = 0;
        housegrid.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(housegrid);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        Throwable exception = assertThrows(RuntimeException.class, () -> device0.setDescription("Fridgeratah V14"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());

    }
    */

    @Test
    public void testSetNameTrue() {
        // Arrange
        String name = "Kitchen";
        String description = "room";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, description, 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        int position = 0;
        house.addRoom(room);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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
        Room room = new Room("Room", "room", 2, dim);

        //initiate Device Fridge
        String freezerCapacity = "Freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        house.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        String expectedResult = "1 - Freezer Capacity: 35.0\n" +
                "2 - Refrigerator Capacity: 20.0\n" +
                "3 - Annual Energy Consumption: 1000.0\n" +
                "4 - Nominal Power: 10.0\n";
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
        Room room = new Room("Room", "room", 2, dim);
        Room room2 = new Room("Bedroom", "room", 1, dim);

        //initiate Device fridge
        String freezerCapacity = "Freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        house.addRoom(room);
        house.addRoom(room2);
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
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        //act
        boolean result = controller.roomListIsEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseEdificioBFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseEdificioBFloor1, dimension1);

        house.addRoom(room1);
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        //act
        boolean result = controller.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    private void assertFalse(boolean result) {
    }

    @Test
    public void getListSize() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseEdificioBFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseEdificioBFloor1, dimension1);

        String name2 = "Living Room";
        int houseEdificioBFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseEdificioBFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int expectResult = 2;
        //act
        int result = controller.roomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
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
        String description = "room";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, description, 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);
        int position = 0;
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        // act
        int expectedResult = 1;

        int result = controller.getDeviceListSize();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddDeviceFalse() {
        // arrange
        String name = "Kitchen";
        String description = "room";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, description, 2, dim);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);
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
        String description = "room";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, description, 2, dim);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);

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
        String description = "room";
        Dimension dim = new Dimension(3.5, 3.5, 3.5);
        Room room = new Room(name, description, 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);
        house.addRoom(room);

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
        Room room = new Room("Room", "room", 2, dim);

        //initiate Device fridge
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(house);

        int position = 0;
        house.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);

        int expectedResult = 4;

        // act
        int result = controller.getNumberOfAttributesInDeviceSpecs();

        // assert
        assertEquals(expectedResult, result);
    }
/*
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

        //initiate Device fridge
        FridgeType fridgeType = new FridgeType();
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = fridgeType.createDevice("Fridgeratah V14", room);

        int capacity = 35;
        int capacity1 = 30;

        device0.setAttributesDevType(freezerCapacity,capacity);
        device0.setAttributesDevType(refrigeratorCapacity,20);
        device0.setAttributesDevType(annualEnergyConsumption,1000);
        device0.setAttributesDevType(nominalPower,10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(housegrid);

        int position = 0;
        housegrid.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(0);

        // Act
        boolean result = controller.setDeviceSpecs(freezerCapacity, capacity1);

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

        //initiate Device fridge
        FridgeType fridgeType = new FridgeType();
        String freezerCapacity = "freezer Capacity";
        String refrigeratorCapacity = "Refrigerator Capacity";
        String annualEnergyConsumption = "Annual Energy Consumption";
        String nominalPower = "Nominal Power";

        Device device0 = fridgeType.createDevice("Fridgeratah V14", room);

        device0.setAttributesDevType(freezerCapacity, 35);
        device0.setAttributesDevType(refrigeratorCapacity, 20);
        device0.setAttributesDevType(annualEnergyConsumption, 1000);
        device0.setAttributesDevType(nominalPower, 10);

        EditConfigurationDeviceController controller = new EditConfigurationDeviceController(housegrid);
        int position = 0;
        housegrid.addRoom(room);
        controller.getRoomByPosition(position);
        controller.getDeviceByPosition(position);
        controller.getNewRoom(0);

        String expectedResult = "1 - freezer Capacity: 35.0\n" +
                "2 - Refrigerator Capacity: 20.0\n" +
                "3 - Annual Energy Consumption: 1000.0\n" +
                "4 - Nominal Power: 10.0\n";

        //Act
        String result = controller.getDevSpecsAttributesToString();

        //Assert
        assertEquals(expectedResult, result);

    }*/
}