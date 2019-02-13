package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfARoomController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GetNominalPowerOfARoomControllerTest {

    private GetNominalPowerOfARoomController ctrl;
    private House house;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(insertedGeoArea);

        this.ctrl = new GetNominalPowerOfARoomController(house);
    }

    @Test
    void testGetListOfRooms() {
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("r1", 1, dim1);

        Dimension dim2 = new Dimension(4, 4, 4);
        Room room2 = new Room("r2", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        String expectedResult = "1- Name: r1, House Floor: 1, Dimension - Height: 4.0, Length: 4.0, Width: 4.0\n" +
                "2- Name: r2, House Floor: 1, Dimension - Height: 4.0, Length: 4.0, Width: 4.0\n";

        //Act
        String result = this.ctrl.getListOfRooms();

        //Assert
        assertEquals(result, expectedResult);
    }


    @Test
    void testGetListOfRoomsEmpty() {
        String expectedResult = "";
        //Act

        String result = this.ctrl.getListOfRooms();

        //Assert
        assertEquals(result, expectedResult);
    }

   /* @Test
    void getNominalPower() {

        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("F5", 1, dim1);

        Dimension dim2 = new Dimension(4, 4, 4);
        Room room2 = new Room("F5", 1, dim2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        this.ctrl.getRoom(0);

        FridgeSpecs fridgeSpecs1 = new FridgeSpecs(25, 50, 5000, 110);
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs1 = new DishWasherSpecs(400, 110, programList);

        Device d1 = new Device("Fridge1", room1, fridgeSpecs1);
        Device d2 = new Device("Dish Washer1", room1, dishWasherSpecs1);

        room1.addDevice(d1);
        room1.addDevice(d2);

        double expectedResult = 220;

        //Act
        double result = ctrl.getNominalPower();

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getNominalPowerNoDevices() {

        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("F5", 1, dim1);

        Dimension dim2 = new Dimension(4, 4, 4);
        Room room2 = new Room("F5", 1, dim2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        this.ctrl.getRoom(0);

        FridgeSpecs fridgeSpecs1 = new FridgeSpecs(20, 20, 50, 400);
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs1 = new DishWasherSpecs(50, 250, programList);

        Device d1 = new Device("Fridge1", room1, fridgeSpecs1);
        Device d2 = new Device("Dish Washer1", room1, dishWasherSpecs1);

        room2.addDevice(d1);
        room2.addDevice(d2);

        double expectedResult = 0;

        //Act
        double result = ctrl.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetRoomListLength() {
        //Arrange
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("Kitchen", 1, dim1);

        Dimension dim2 = new Dimension(4, 4, 4);
        Room room2 = new Room("Bedroom", 1, dim2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        int expectedResult = 2;

        //Act
        int result = this.ctrl.getRoomListSize();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIfDeviceListIsEmpty() {

        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("Kitchen", 1, dim1);

        Dimension dim2 = new Dimension(4, 4, 4);
        Room room2 = new Room("Bedroom", 1, dim2);

        house.addRoom(room1);
        house.addRoom(room2);

        //Act
        boolean result = ctrl.isDeviceListEmpty(1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testifDeviceListIsEmptyWithDevices() {

        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("Kitchen", 1, dim1);

        Dimension dim2 = new Dimension(4, 4, 4);
        Room room2 = new Room("Bedroom", 1, dim2);

        this.house.addRoom(room1);
        this.house.addRoom(room2);

        FridgeSpecs fridgeSpecs1 = new FridgeSpecs(20, 20, 50, 400);
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs1 = new DishWasherSpecs(20, 450, programList);

        Device d1 = new Device("FridgeSpecs", room1, fridgeSpecs1);

        Device d2 = new Device("Dish Washer", room1, dishWasherSpecs1);

        room2.getDeviceList().add(d1);

        room2.getDeviceList().add(d2);

        //Act
        boolean result = this.ctrl.isDeviceListEmpty(1);

        //Assert
        assertFalse(result);
    }*/
}