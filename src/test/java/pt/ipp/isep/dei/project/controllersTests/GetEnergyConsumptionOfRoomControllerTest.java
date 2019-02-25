package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionOfRoomInAnIntervalController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GetEnergyConsumptionOfRoomControllerTest {

    private GetEnergyConsumptionOfRoomInAnIntervalController ctrl;
    private House house;
    private Room kitchen;
    private Room livingRoom;
    private Device fridge;

    @BeforeEach
    public void StartUp() {
        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        //Rooms
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        kitchen = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        livingRoom = new Room(name2, houseFloor2, dimension2);

        // Device Instantiation
        FridgeType fridgeType = new FridgeType();
        fridge = fridgeType.createDevice("Fridgerator", kitchen);

        //Set Specs
        fridge.setAttributesDevType("Freezer Capacity", 35);
        fridge.setAttributesDevType("Refrigerator Capacity", 20);
        fridge.setAttributesDevType("Annual Energy Consumption", 1000);
        fridge.setAttributesDevType("Nominal Power", 1000);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        // List<Readings Configuration
        fridge.addReadingsToTheList(readings0);
        fridge.addReadingsToTheList(readings1);
        fridge.addReadingsToTheList(readings2);


        this.ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);
    }

    @Test
    public void testGetRoomListToString() {
        //arrange

        this.house.addRoom(kitchen);
        this.house.addRoom(livingRoom);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = this.ctrl.getRoomListToString();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //act
        boolean result = this.ctrl.roomListIsEmpty();

        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        this.house.addRoom(kitchen);

        //act
        boolean result = this.ctrl.roomListIsEmpty();

        //assert
        assertFalse(result);
    }

    @Test
    public void getRoomListSize() {
        //arrange
        this.house.addRoom(kitchen);
        this.house.addRoom(livingRoom);

        int expectResult = 2;

        //act
        int result = this.ctrl.getRoomListSize();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getRoomListSizeEmptyList() {
        //arrange
        int expectResult = 0;

        //act
        int result = this.ctrl.getRoomListSize();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        this.house.addRoom(livingRoom);

        this.ctrl.getRoomByPosition(0);

        // Act
        boolean result = this.ctrl.isDeviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        house.addRoom(kitchen);

        ctrl.getRoomByPosition(0);
        // Act
        boolean result = ctrl.isDeviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        this.house.addRoom(kitchen);
        this.house.addRoom(livingRoom);

        String expectedResult = "Living Room";

        this.ctrl.getRoomByPosition(1);
        //Act
        String result = this.ctrl.getRoomName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneFullPeriod() {
        // Arrange
        this.house.addRoom(kitchen);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        ctrl.getRoomByPosition(0);
        // Act
        double result = ctrl.getEnergyConsumptionOfRoomInInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoFullPeriods() {
        // Arrange
        house.addRoom(kitchen);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        ctrl.getRoomByPosition(0);
        // Act
        double result = ctrl.getEnergyConsumptionOfRoomInInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithoutFullPeriods() {
        // Arrange
        house.addRoom(kitchen);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        ctrl.getRoomByPosition(0);
        // Act
        double result = ctrl.getEnergyConsumptionOfRoomInInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}