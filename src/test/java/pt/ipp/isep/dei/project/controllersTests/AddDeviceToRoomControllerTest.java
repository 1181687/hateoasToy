package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeviceToRoomControllerTest {
    private AddDeviceToRoomController controller;
    private House house;
    private Room kitchen;
    private Room livingRoom;

    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dimension1 = new Dimension(2, 2, 2);
        kitchen = new Room("Kitchen", 0, dimension1);
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        livingRoom = new Room("Living Room", 1, dimension2);


        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
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
    void getSelectedRoom() {
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
    public void getListSizeEmptyList() {
        // Arrange
        int expectResult = 0;

        // Act
        int result = controller.roomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    void getDeviceTypeListToString() {
        // Arrange
        String expectedResult = "1- Fridge\n" +
                "2- Lamp\n" +
                "3- DishWasher\n" +
                "4- WashingMachine\n" +
                "5- ElectricWaterHeater\n" +
                "6- ElectricOven\n" +
                "7- Freezer\n" +
                "8- WineCooler\n" +
                "9- Television\n";

        // Act
        String result = controller.getDeviceTypeListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewFridge() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        Device d2 = controller.createNewFridge("Fridge", 1000,
                200, 20, 50);
        Device expectedResult = d2;

        // Act
        Device result = controller.getDevice(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    /*
    @Test
    public void testNewFridgeNegative() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewFridge("Fridge", 1000, 200,
                20, 50);

        // Act
        Throwable exception =
                assertThrows(RuntimeException.class, () -> controller.createNewFridge("Fridge",
                        1000, 200, 20, 50));

        // Assert
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void testNewLamp() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        Device device = controller.createNewLamp("lamp", 200, 100);

        Device expectedResult = device;

        // Act
        Device result = controller.getDevice(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewLampNegative() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewLamp("lamp", 200, 100);

        // Act
        Throwable exception =
                assertThrows(RuntimeException.class, () -> controller.createNewLamp("lamp",
                        200, 100));

        // Assert
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void testNewWashingMachine() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        Device device = controller.createNewWashingMachine("Washing Machine", 200, 100);

        Device expectedResult = device;

        // Act
        Device result = controller.getDevice(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewWashingMachineNegative() throws RuntimeException {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewWashingMachine("Washing Machine", 200, 100);

        // Act
        Throwable exception =
                assertThrows(RuntimeException.class, () ->
                        controller.createNewWashingMachine("Washing Machine", 200, 100));

        // Assert
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void newElectricWaterHeater() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        Device device = controller.createNewElectricWaterHeater("Electric Water Heater", 50,
                150, 100, 0.9);

        Device expectedResult = device;

        // Act
        Device result = controller.getDevice(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void newElectricWaterHeaterNegative() throws RuntimeException {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        controller.createNewElectricWaterHeater("Electric Water Heater", 50, 150,
                100, 0.9);

        // Act
        Throwable exception =
                assertThrows(RuntimeException.class, () ->
                        controller.createNewElectricWaterHeater("Electric Water Heater", 50,
                                150, 100, 0.9));

        // Assert
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void newDishWasher() {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);

        Device device = controller.createNewDishWasher("Dishwasher", 12, 50);

        Device expectedResult = device;

        //Act
        Device result = controller.getDevice(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void newDishWasherNegative() throws RuntimeException {
        // Arrange
        house.addRoom(kitchen);
        controller.getRoom(0);
        controller.createNewDishWasher("DW1", 12, 50);

        // Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createNewDishWasher("DW1", 12, 50));

        // Assert
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void testGetDeviceListContentOfARoomTest() {
        // Arrange
        house.addRoom(kitchen);
        //house.getDeviceType("fridge").createDevice("Fridgeratah V14", kitchen);
        house.createDevice("Fridge", "Fridgeratah V14", kitchen);
        kitchen.getDeviceByPosition(0).setAttributesDevType("freezer Capacity", 5.5);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 15.5);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 5000);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.5);

        //house.getDeviceType("lamp").createDevice("lamp Bizkit 5000", kitchen);
        house.createDevice("lamp", "lamp Bizkit 5000", kitchen);
        kitchen.getDeviceByPosition(1).setAttributesDevType("Nominal Power", 1.0);
        kitchen.getDeviceByPosition(1).setAttributesDevType("Luminous Flux", 10.0);

        String expectedResult = "1 - Name of the device: Fridgeratah V14\n" +
                "2 - Name of the device: lamp Bizkit 5000\n";

        // Act
        String result = controller.getDeviceListContentOfARoom(0);

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testCreateNewProgram() {
        // Arrange
        Device device = house.createDevice("Electric Water Heater", "Electric Water Heater", kitchen);
        //house.getDeviceType("Electric Water Heater").createDevice("Electric Water Heater", kitchen);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 5.5);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 15.5);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 5000);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.5);

        //DeviceSpecs eWHSpecs = new ElectricWaterHeaterSpecs(50, 150, 100, 0.9);

        //Device electricWaterHeater = new Device("Electric Water Heater", kitchen, eWHSpecs);
        //kitchen.addDevice(electricWaterHeater);
        house.addRoom(kitchen);

        // Act
        boolean result = controller.createAndAddProgram("Program1", 10.2, 50);

        // Assert
        assertTrue(result);
    }

    @Test
    public void addProgramToListFalse() {
        //Arrange
        Program program = null;
        boolean expectedResult = false;

        //Act
        boolean result = controller.createAndAddProgram(null, Double.NaN, Double.NaN);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void addProgramToListTrue() {
        // Arrange
        //boolean program = controller.createAndAddProgram("Program1", 10.2, 50);

        // Act
        boolean result = controller.createAndAddProgram("Program1", 10.2, 50);

        // Assert
        assertTrue(result);
    }
*/
    @Test
    public void testGetNumberOfDeviceTypes() {
        // Arrange
        int expectedResult = 9;

        // Act
        int result = controller.getNumberOfDeviceTypes();

        // Assert
        assertEquals(expectedResult, result);
    }
}