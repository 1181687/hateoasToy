package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AddDeviceToRoomControllerTest {
    private AddDeviceToRoomController controller;
    private House house;
    private Room kitchen;
    private Room livingRoom;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // Rooms
        Dimension dimension1 = new Dimension(2, 2, 2);
        kitchen = new Room("Kitchen", "room", 0, dimension1);
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        livingRoom = new Room("Living Room", "room", 1, dimension2);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);

        // Controller
        controller = new AddDeviceToRoomController(house);
    }

    @Test
    public void getDisplayRoomListTest() {
        // Arrange
        house.addRoom(kitchen);
        house.addRoom(livingRoom);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        // Act
        String result = controller.getRoomListContent();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        // Arrange
        String expectResult = "";

        // Act
        String result = controller.getRoomListContent();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        house.addRoom(kitchen);
        house.addRoom(livingRoom);

        int expectResult = 2;

        // Act
        int result = controller.roomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getSelectedRoom() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        Room expectedResult = kitchen;

        // Act
        Room result = controller.getSelectedRoom();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        // Arrange
        int expectResult = 0;

        // Act
        int result = controller.roomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testNewFridge_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createNewFridge("Fridge", 1000,
                200, 20, 50);

        // Assert
        assertTrue(result);
    }

    @Test
    public void getDeviceTypeListToString() {
        // Arrange
        String expectedResult = "1- Fridge\n" +
                "2- Lamp\n" +
                "3- DishWasher\n" +
                "4- WashingMachine\n" +
                "5- ElectricWaterHeater\n" +
                "6- ElectricOven\n" +
                "7- Freezer\n" +
                "8- WineCooler\n" +
                "9- Television\n" +
                "10- MicrowaveOven\n" +
                "11- Fan\n" +
                "12- Stove\n" +
                "13- WallTowelHeater\n" +
                "14- Kettle\n";


        // Act
        String result = controller.getDeviceTypeListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLamp_ValidDevice_ReturnTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createNewLamp("lamp", 200, 100);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewFridgeNegative_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewFridge("Fridge", 1000, 200,
                20, 50);

        // Act
        boolean result = controller.createNewFridge("Fridge", 1000, 200,
                20, 50);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewLampNegative() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewLamp("lamp", 200, 100);

        // Act
        boolean result = controller.createNewLamp("lamp", 200, 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewWashingMachine() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createNewWashingMachine("Washing Machine", 200, 100);

        // Assert
        assertTrue(result);
    }

    @Test
    public void newElectricWaterHeater() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createNewElectricWaterHeater("Electric Water Heater", 80, 100, 1.5, 0.9);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewWashingMachineNegative() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewWashingMachine("Washing Machine", 200, 100);

        // Act
        boolean result = controller.createNewWashingMachine("Washing Machine", 200, 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void newElectricWaterHeaterNegative() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewElectricWaterHeater("ElectricWaterHeater", 50, 150,
                100, 0.9);

        // Act
        boolean result = controller.createNewElectricWaterHeater("ElectricWaterHeater", 50, 150,
                100, 0.9);
        // Assert
        assertFalse(result);
    }

    @Test
    public void newDishWasherNegative() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewDishWasher("DW1", 12, 50);

        // Act
        boolean result = controller.createNewDishWasher("DW1", 12, 50);
        // Assert
        assertFalse(result);
    }


    @Test
    public void newDishWasher() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        //Act
        boolean result = controller.createNewDishWasher("Dishwasher", 12, 50);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testNewKettle_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createNewKettle("Kettle", 1.5, 2, 13, 2, 0.7);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewKettle_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewKettle("Kettle", 1.5, 2, 13, 2, 0.7);

        // Act
        boolean result = controller.createNewKettle("Kettle", 1.5, 2, 13, 2, 0.7);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewElectricOven_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createNewElectricOven("Oven", 1.5, 20);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewElectricOven_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewElectricOven("Oven", 1.5, 20);

        // Act
        boolean result = controller.createNewElectricOven("Oven", 1.5, 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewFan_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createFan("Fan", 1.5);

        // Act
        boolean result = controller.createFan("Fan", 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewFan_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createFan("Fan", 1.5);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewFreezer_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createFreezer("Freezer", 1.5, 20, 300);

        // Act
        boolean result = controller.createFreezer("Freezer", 1.5, 20, 300);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewFreezer_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createFreezer("Freezer", 1.5, 20, 300);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewMicroWaveOven_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createMicroWaveOven("Microwave", 1.5);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewMicroWaveOven_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createMicroWaveOven("Microwave", 1.5);

        // Act
        boolean result = controller.createMicroWaveOven("Microwave", 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewWallTowelHeater_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createWallTowelHeater("Wall Towel Heater", 1.5, 20);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewTelevision_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createTelevision("Television", 1.5, 0.01);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewTelevision_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createTelevision("Television", 1.5, 0.01);

        // Act
        boolean result = controller.createTelevision("Television", 1.5, 0.01);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testNewWineCooler_ValidDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        // Act
        boolean result = controller.createWineCooler("Wine coolah", 1.5, 20, 350);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testNewWallTowelHeater_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createWallTowelHeater("Wall Towel Heater", 1.5, 20);

        // Act
        boolean result = controller.createWallTowelHeater("Wall Towel Heater", 1.5, 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetDeviceListContentOfARoomTest() {
        // Arrange
        house.addRoom(kitchen);
        house.createDevice("Fridge", "Fridgeratah V14", kitchen);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 5.5);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 15.5);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 5000);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.5);

        house.createDevice("Lamp", "Lamp Bizkit 5000", kitchen);
        kitchen.getDeviceByPosition(1).setAttributesDevType("Nominal Power", 1.0);
        kitchen.getDeviceByPosition(1).setAttributesDevType("Luminous Flux", 10.0);

        String expectedResult = "1 - Name of the device: Fridgeratah V14\n" +
                "2 - Name of the device: Lamp Bizkit 5000\n";

        // Act
        String result = controller.getDeviceListContentOfARoom(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewWineCooler_SameDeviceAlreadyExists_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createWineCooler("Wine coolah", 1.5, 20, 350);

        // Act
        boolean result = controller.createWineCooler("Wine coolah", 1.5, 20, 350);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetNumberOfDeviceTypes() {
        // Arrange
        int expectedResult = 14;

        // Act
        int result = controller.getNumberOfDeviceTypes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgrammableDevice_ReturnsTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewWashingMachine("Washimashine", 100.5, 8);
        controller.isProgrammable();

        controller.createNewProgram("Program1");

        // Act
        boolean result = controller.addProgram();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsDeviceProgrammable_ProgrammableDevice_ReturnsTrue() {
        //Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewWashingMachine("Washimashine", 100.5, 8);

        //Act
        boolean result = controller.isProgrammable();

        //Assert
        assertTrue(result);
    }

    @Test
    public void testIsDeviceProgrammable_NotAProgrammableDevice_ReturnsFalse() {
        //Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewLamp("Lamp", 5, 8);

        //Act
        boolean result = controller.isProgrammable();

        //Assert
        assertFalse(result);
    }

    @Test
    public void testSetProgramAttributes_ValidAttribute_ReturnTrue() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewWashingMachine("Washimashine", 5, 8);
        controller.isProgrammable();
        controller.createNewProgram("Program xpto");

        // Act
        boolean result = controller.setProgramAttributes("duration", 10);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetProgramAttributes_InvalidAttribute_ReturnFalse() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewWashingMachine("Washimashine", 5, 8);
        controller.isProgrammable();
        controller.createNewProgram("Program xpto");

        // Act
        boolean result = controller.setProgramAttributes("wrong attribute", 10);
        // Assert
        assertFalse(result);
    }

    @Configuration
    static class Config {
    }

}