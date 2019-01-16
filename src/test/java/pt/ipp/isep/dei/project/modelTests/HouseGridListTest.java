package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class HouseGridListTest {

    @Test
    public void getAGridFromASpecificPosition() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        HouseGridList list = new HouseGridList();
        list.getmHouseGridsList().add(grid0);
        list.getmHouseGridsList().add(grid1);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = list.getHouseGridByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        HouseGridList list = new HouseGridList();
        list.getmHouseGridsList().add(grid0);
        list.getmHouseGridsList().add(grid1);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        // Act
        String result = list.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void detachRoomInTheHouseGridTest() {
        // Arrange
        String roomName = "Kitchen";
        String roomName1 = "Bedroom";
        int houseFloor = 0;
        int houseFloor1 = 0;
        Dimensions dimensions = new Dimensions(2, 2, 2);
        Dimensions dimensions1 = new Dimensions(2, 4, 4);
        Room room = new Room(roomName, houseFloor, dimensions);
        Room room1 = new Room(roomName1, houseFloor1, dimensions1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);
        gridList.attachRoomInASpecificHouseGridInTheList(grid, room);
        gridList.attachRoomInASpecificHouseGridInTheList(grid, room1);
        int gridPosition = gridList.getmHouseGridsList().indexOf(grid);
        gridList.detachRoomInASpecificHouseGridInTheList(grid, room1);

        // Act
        boolean result = gridList.getHouseGridByPosition(gridPosition).checkIfRoomIsInHouseGrid(room1);

        // Assert
        assertFalse(result);
    }

    @Test
    public void attachRoomInTheHouseGridTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);
        gridList.attachRoomInASpecificHouseGridInTheList(grid, room);
        int gridPosition = gridList.getmHouseGridsList().indexOf(grid);

        // Act
        boolean result = gridList.getHouseGridByPosition(gridPosition).checkIfRoomIsInHouseGrid(room);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();

        // Act
        boolean result = gridList.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);

        // Act
        boolean result = gridList.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfARoomIsAlreadyInAHouseGridOfTheListWithNegativeTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = gridList.createAHouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);

        // Act
        boolean result = gridList.checkIfARoomIsAlreadyInAHouseGrid(grid, room);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getTheGridWhereTheRoomIsConnectedTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimensions dimensions = new Dimensions(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimensions);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);
        grid2.attachRoom(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);
        gridList.addHouseGridToTheList(grid1);
        gridList.addHouseGridToTheList(grid2);

        HouseGrid expectedResult = grid2;

        // Act
        HouseGrid result = gridList.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTheGridWhereTheRoomIsConnectedNullTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimensions dimensions = new Dimensions(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimensions);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);
        gridList.addHouseGridToTheList(grid1);
        gridList.addHouseGridToTheList(grid2);

        HouseGrid expectedResult = null;

        // Act
        HouseGrid result = gridList.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void TestGetAllDevicesListByPosition() {
        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        DeviceSpecs specWashing = new WashingMachine(100, 100);
        DeviceSpecs specDishWasher = new DishWasher(100, 100);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater(100, 100, 100, 0.9);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        //add to Lists
        RoomList roomListEmpty = new RoomList();
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGridToTheList(houseGrid);
        houseGridList1.addHouseGridToTheList(houseGridEmpty);

        DeviceList expectedResult = new DeviceList();
        expectedResult.addDevice(dev1);
        expectedResult.addDevice(dev2);
        expectedResult.addDevice(dev3);
        expectedResult.addDevice(dev4);
        expectedResult.addDevice(dev5);
        expectedResult.addDevice(dev6);

        DeviceList result = houseGridList1.getAllDevicesListByPosition(0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void TestGetAllDevicesListByPositionEmpty() {
        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        DeviceSpecs specWashing = new WashingMachine(100, 100);
        DeviceSpecs specDishWasher = new DishWasher(100, 100);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater(100, 100, 100, 0.9);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        //add to Lists
        RoomList roomListEmpty = new RoomList();
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGridToTheList(houseGrid);
        houseGridList1.addHouseGridToTheList(houseGridEmpty);

        DeviceList expectedResult = new DeviceList();

        DeviceList result = houseGridList1.getAllDevicesListByPosition(1);

        assertEquals(expectedResult, result);
    }


    @Test
    public void getEnergyConsumptionInADayOfAllDevicesOfATypeTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimensions dim = new Dimensions(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double nominalPower0 = 100;
        double performanceRatio = 0.9;
        DeviceSpecs electricWaterHeater0 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, nominalPower0, performanceRatio);
        double hotWaterTemp1 = 60;
        double maximumVolume1 = 200;
        double nominalPower1 = 110;
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeater(hotWaterTemp1, maximumVolume1, nominalPower1, performanceRatio);

        // Device Instantiation
        Device device0 = new Device("Electric Water Heater A", room, electricWaterHeater0);
        Device device1 = new Device("Electric Water Heater B", room, electricWaterHeater1);

        room.addDevice(device0);
        room.addDevice(device1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // HouseGrid Instantiation
        String houseGridName = "Main Grid";
        double maximumContractedPower = 200;
        HouseGrid houseGrid = new HouseGrid(houseGridName, maximumContractedPower, roomList);

        // HouseGridList Instantiation
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.addHouseGridToTheList(houseGrid);

        houseGridList.setColdWaterTempAndVolumeOfWaterToHeat(30, 100);

        double expectedResult = 5233.5;

        // Act
        double result = houseGridList.getEnergyConsumptionInADayOfAllDevicesOfAType("Electric Water Heater");

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getNameByHGPosition() {
        // Arrange
        // Instantiate House Grids
        String gridName0 = "Grid0";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid1";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid2";
        HouseGrid grid2 = new HouseGrid(gridName2);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);
        gridList.addHouseGridToTheList(grid1);
        gridList.addHouseGridToTheList(grid2);

        int position = 0;
        String expectedResult = "Grid0";

        // Act
        String result = gridList.getNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameByHGPositionEmpty() {
        // Arrange
        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();

        int position = 0;
        String expectedResult = null;

        // Act
        String result = gridList.getNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

}
