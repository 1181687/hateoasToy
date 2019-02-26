package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DishWasher.DishWasherType;
import pt.ipp.isep.dei.project.model.Devices.ElectricWaterHeater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.Devices.Fridge.FridgeType;
import pt.ipp.isep.dei.project.model.Devices.Lamp.LampType;
import pt.ipp.isep.dei.project.model.Devices.WashingMachine.WashingMachineType;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HouseGridTest {

    @Test
    public void testHashCode(){
        //Arrange
        String name = "Main Grid";
        HouseGrid grid = new HouseGrid(name);
        int expectedResult = Objects.hash(name);

        //Act
        int result = grid.hashCode();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testHashCodeWithDifferentName(){
        //Arrange
        String name1 = "Main Grid";
        String name2 = "Secondary Grid";

        HouseGrid grid = new HouseGrid(name1);
        int expectedResult = Objects.hash(name2);

        //Act
        int result = grid.hashCode();

        //Assert
        assertNotEquals(expectedResult,result);
    }

    @Test
    public void testEqualsWithDifferentObjects() {
        //Arrange
        HouseGrid grid = new HouseGrid("Main Grid");
        Dimension dimension = new Dimension(2, 2, 2);
        //Act
        boolean result = grid.equals(dimension);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testDisplayRoomsAttachedToHouseGrid() {

        // Arrange
        Dimension dimensionRoom1 = new Dimension(5.2, 3.7, 8.5);
        Room room1 = new Room("Kid's room", 1, dimensionRoom1);
        Dimension dimensionRoom2 = new Dimension(5.2, 3.7, 8.5);
        Room room2 = new Room("Bathroom", 1, dimensionRoom2);

        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);

        houseGrid1.getRoomList().addRoom(room1);
        houseGrid1.getRoomList().addRoom(room2);

        String expectedResult =
                "1- Name: Kid's room, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n" +
                        "2- Name: Bathroom, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n";
        // Act
        String result = houseGrid1.getRoomListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void attachRoomInTheHouseGridRoomListTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        grid.attachRoom(room);

        // Act
        boolean result = grid.getRoomList().getListOfRooms().contains(room);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testListPowerSources() {
        //Arrange
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name, type1);
        PowerSource powerSource2 = new PowerSource(name2, type1);
        HouseGrid houseGrid = new HouseGrid("House Grid1");
        houseGrid.addPowerSource(powerSource1);
        houseGrid.addPowerSource(powerSource2);
        String expectedResult = "1- Power Source 1\n" +
                "2- Power Source 2\n";
        //Act
        String result = houseGrid.getPowerSourceListContent();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDevicesList() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        //Fridge - dev1
        FridgeType fridgeType = new FridgeType();
        Device dev1 = fridgeType.createDevice("FridgeAriston", room1);
        dev1.setAttributesDevType("Freezer Capacity", 100);
        dev1.setAttributesDevType("Refrigerator Capacity", 100);
        dev1.setAttributesDevType("Annual Energy Consumption", 100);
        dev1.setAttributesDevType("Nominal Power", 100);

        //WashingMachine - dev2
        WashingMachineType washingMachineType = new WashingMachineType();
        Device dev2 = washingMachineType.createDevice("WashingMachineBosh", room1);
        dev2.setAttributesDevType("Capacity", 100);
        dev2.setAttributesDevType("Nominal Power", 100);

        //DishWasher - dev3
        DishWasherType dishWasherType = new DishWasherType();
        Device dev3 = dishWasherType.createDevice("DishWasherSpecs", room1);
        dev3.setAttributesDevType("Capacity", 100);
        dev3.setAttributesDevType("Nominal Power", 100);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);

        //EWH - dev6
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeaterSpecs", room2);
        dev6.setAttributesDevType("Hot-Water Temperature", 100);
        dev6.setAttributesDevType("Volume Of Water To Heat", 100);
        dev6.setAttributesDevType("Performance Ratio", 100);
        dev6.setAttributesDevType("Hot-Water Temperature", 100);

        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);
        expectedResult.add(dev2);
        expectedResult.add(dev3);
        expectedResult.add(dev4);
        expectedResult.add(dev5);
        expectedResult.add(dev6);

        HouseGrid housegrid = new HouseGrid("grid1");
        housegrid.attachRoom(room1);
        housegrid.attachRoom(room2);

        List<Device> result = housegrid.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDevicesListEmptylist() {
        HouseGrid housegrid = new HouseGrid("grid1");

        List<Device> expectedResult = new ArrayList<>();

        List<Device> result = housegrid.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPower() {
        //Assert

        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        //Fridge - dev1
        FridgeType fridgeType = new FridgeType();
        Device dev1 = fridgeType.createDevice("FridgeAriston", room1);
        dev1.setAttributesDevType("Freezer Capacity", 100);
        dev1.setAttributesDevType("Refrigerator Capacity", 100);
        dev1.setAttributesDevType("Annual Energy Consumption", 100);
        dev1.setAttributesDevType("Nominal Power", 100);

        //WashingMachine - dev2
        WashingMachineType washingMachineType = new WashingMachineType();
        Device dev2 = washingMachineType.createDevice("WashingMachineBosh", room1);
        dev2.setAttributesDevType("Capacity", 100);
        dev2.setAttributesDevType("Nominal Power", 100);

        //DishWasher - dev3
        DishWasherType dishWasherType = new DishWasherType();
        Device dev3 = dishWasherType.createDevice("DishWasherSpecs", room1);
        dev3.setAttributesDevType("Capacity", 100);
        dev3.setAttributesDevType("Nominal Power", 100);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);
        dev4.setAttributesDevType("Nominal Power", 100);
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);
        dev5.setAttributesDevType("Nominal Power", 100);


        //EWH - dev6
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeaterSpecs", room2);
        dev6.setAttributesDevType("Hot-Water Temperature", 100);
        dev6.setAttributesDevType("Volume Of Water To Heat", 100);
        dev6.setAttributesDevType("Performance Ratio", 100);
        dev6.setAttributesDevType("Hot-Water Temperature", 100);
        dev6.setAttributesDevType("Nominal Power", 100);

        HouseGrid grid1 = new HouseGrid("Grid 1");
        grid1.attachRoom(room1);
        grid1.attachRoom(room2);

        double expectedResult = 600;

        //Act
        double result = grid1.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getRoomFromAPosition() {
        //arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        houseGrid.attachRoom(room1);
        houseGrid.attachRoom(room2);

        Room expectResult = room1;

        //act
        Room result = houseGrid.getRoomByPosition(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        houseGrid.attachRoom(room);

        //initiate Devices
        FridgeType fridgeType = new FridgeType();
        Device dev = fridgeType.createDevice("Fridge1", room);


        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = houseGrid.getDeviceListContent(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);


        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);
        Device dev2 = lampType.createDevice("Lamp2", room);

        int expectResult = 2;
        //act
        int result = houseGrid.getDeviceListSizeByRoomPosition(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //act
        boolean result = houseGrid.isRoomListEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        rList.addRoom(room1);
        //act
        boolean result = rList.isEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        // Act
        boolean result = room.isDeviceListEmpty();

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

        // Act
        boolean result = room.isDeviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceFromPositionInList() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

        Device expectedResult = dev1;

        // Act
        Device result = houseGrid.getDeviceByRoomAndDevicePosition(0, 0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        houseGrid.attachRoom(room1);
        houseGrid.attachRoom(room2);


        int expectResult = 2;
        //act
        int result = houseGrid.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }


    @Test
    public void getListSizeEmptyList() {
        //arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        int expectResult = 0;
        //act
        int result = houseGrid.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testCheckThereAreNoDevices() {
        //arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);
        //Act

        boolean result = houseGrid.isDeviceListOfAllRoomsEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void testCheckThereAreNoDevicesWithDevices() {
        //arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room

        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room(name, 2, dim);

        houseGrid.attachRoom(room);

        //initiate Device
        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

        //Act

        boolean result = houseGrid.isDeviceListOfAllRoomsEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void testValidateNameWithEmptyNameShouldThrowException() {
        //Arrange
        String name = " ";

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );
        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateNameWithNullNameShouldThrowException() {
        //Arrange
        String name = null;

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );
        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmptyNameShouldThrowException() {
        //Arrange
        String name = "";

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullNameShouldThrowException() {
        //Arrange
        String name = null;

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testGetNameToString() {
        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        String expectResult = "HouseGrid: hgname1\n";
        //act
        String result = houseGrid.getNameToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithThreeValidReadingss() {
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Quarto", 2, dimension);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room1);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        double expectedResult = 12;
        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithOneValidReadings() {
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Quarto", 2, dimension);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room1);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 24, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        double expectedResult = 0;
        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithNoValidReadingss() {

        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Quarto", 2, dimension);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room1);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 25, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        double expectedResult = 0;

        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithTwoRooms() {
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Room", 2, dimension);
        Room room2 = new Room("Kitchen", 1, dimension);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room1);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeSpecs", room2);

        ElectricWaterHeaterType electricWaterHeaterType =  new ElectricWaterHeaterType();
        Device electricWaterHeater = electricWaterHeaterType.createDevice("EWH200", room2);

        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);
        grid1.attachRoom(room2);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime time6 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading6 = new Reading(3, time6);
        LocalDateTime time7 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading7 = new Reading(5, time7);

        electricWaterHeater.addReadingsToTheList(reading6);
        electricWaterHeater.addReadingsToTheList(reading7);

        double expectedResult = 29;

        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithNoRoomsConnected() {

        //Arrange
        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        double expectedResult = 0;

        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDataSeries() {

        //Arrange
        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);

        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Room", 2, dimension);
        Room room2 = new Room("Kitchen", 1, dimension);

        grid1.attachRoom(room1);
        grid1.attachRoom(room2);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room1);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeSpecs", room2);

        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device electricWaterHeater = electricWaterHeaterType.createDevice("EWH200", room2);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime time6 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading6 = new Reading(3, time6);
        LocalDateTime time7 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading7 = new Reading(5, time7);

        electricWaterHeater.addReadingsToTheList(reading6);
        electricWaterHeater.addReadingsToTheList(reading7);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        Map<LocalDateTime, Double> expectedResult = new TreeMap<>();
        expectedResult.put(time0, 9.0);
        expectedResult.put(time1, 15.0);
        expectedResult.put(time2, 14.0);

        //Act
        Map<LocalDateTime, Double> result = grid1.getDataSeries(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }
}
