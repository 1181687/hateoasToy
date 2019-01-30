package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class GetDevicesInHouseGridControllerTest {


    @Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        ProgramList wmProgramList = new ProgramList();
        ProgramList dwProgramList = new ProgramList();

        DeviceSpecs specWashing = new WashingMachine(100, 100, wmProgramList);
        DeviceSpecs specDishWasher = new DishWasher(100, 100, dwProgramList);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
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
        houseGridList1.addHouseGrid(houseGrid);
        houseGridList1.addHouseGrid(houseGridEmpty);

        //house
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        String expectedResult = "Dish Washer\n- Device Name: DishWasher, Location: Kitchen.\n- Device Name: DishWasherTeka, Location: KitchenBasement.\n\nElectric Water Heater\n- Device Name: ElectricWaterHeater, Location: KitchenBasement.\n\nWashing Machine\n- Device Name: WashingMachineBosh, Location: Kitchen.\n\nFridge\n- Device Name: FridgeAriston, Location: Kitchen.\n- Device Name: FridgeSiemens, Location: KitchenBasement.\n\n";


        String result = ctrl.getDeviceListContentNameTypeLocationByHG(0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        RoomList roomListEmpty = new RoomList();
        RoomList roomList = new RoomList();
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGrid(houseGrid);
        houseGridList1.addHouseGrid(houseGridEmpty);

        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);


        String expectedResult = "1 - Name: grid1\n2 - Name: grid2\n";


        // Act
        String result = ctrl.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        RoomList roomList = new RoomList();
        HouseGridList houseGridList1 = new HouseGridList();

        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        // Act
        boolean result = ctrl.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        RoomList roomListEmpty = new RoomList();
        RoomList roomList = new RoomList();
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGrid(houseGrid);
        houseGridList1.addHouseGrid(houseGridEmpty);

        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        // Act
        boolean result = ctrl.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetHouseGridListLength() {

        RoomList roomListEmpty = new RoomList();
        RoomList roomList = new RoomList();
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGrid(houseGrid);
        houseGridList1.addHouseGrid(houseGridEmpty);

        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        int expectedResult = 2;

        // Act
        int result = ctrl.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesHGbyPositionFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        ProgramList dwProgramList = new ProgramList();


        DishWasher dishWasher = new DishWasher(100, 100, dwProgramList);
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater(100, 100, 100, 0.9);
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device dev4 = new Device("FridgeSiemens", room2, fridge);
        Device dev5 = new Device("DishWasherTeka", room2, dishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        roomList.addRoom(room);
        roomList.addRoom(room2);

        RoomList roomListEmpty = new RoomList();
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGrid(houseGrid);
        houseGridList1.addHouseGrid(houseGridEmpty);

        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);


        // Act
        boolean result = ctrl.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesHGbyPositionTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        ProgramList dwProgramList = new ProgramList();
        DishWasher dishWasher = new DishWasher(100, 100, dwProgramList);
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater(100, 100, 100, 0.9);
        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);

        Device dev4 = new Device("FridgeSiemens", room2, fridge);
        Device dev5 = new Device("DishWasherTeka", room2, dishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        roomList.addRoom(room2);

        RoomList roomListEmpty = new RoomList();
        roomList.addRoom(room);
        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGrid houseGridEmpty = new HouseGrid("grid2", 500, roomListEmpty);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGrid(houseGrid);
        houseGridList1.addHouseGrid(houseGridEmpty);

        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);


        // Act
        boolean result = ctrl.checkIfThereAreNoDevicesHGbyPosition(1);

        // Assert
        assertTrue(result);
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
        gridList.addHouseGrid(grid0);
        gridList.addHouseGrid(grid1);
        gridList.addHouseGrid(grid2);

        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        int position = 0;
        String expectedResult = "Grid0";

        // Act
        String result = ctrl.getHGNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameByHGPositionEmpty() {
        // Arrange
        HouseGridList gridList = new HouseGridList();

        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);
        GetDevicesInHouseGridController ctrl = new GetDevicesInHouseGridController(house);

        int position = 0;
        String expectedResult = "There are no Grids in the house";

        // Act
        String result = ctrl.getHGNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }
}

