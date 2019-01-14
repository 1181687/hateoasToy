package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseGridTest {

    @Test
    void TestDisplayRoomsAttachedToHouseGrid () {

        // Arrange
        Dimensions dimensionsRoom1 = new Dimensions(5.2, 3.7, 8.5);
        Room room1 = new Room("Kid's room", 1, dimensionsRoom1);
        Dimensions dimensionsRoom2 = new Dimensions(5.2, 3.7, 8.5);
        Room room2 = new Room("Bathroom", 1, dimensionsRoom2);

        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);

        houseGrid1.getmRoomsConnectedToHouseGrid().addRoom(room1);
        houseGrid1.getmRoomsConnectedToHouseGrid().addRoom(room2);

        String expectedResult =
                "1- Name: Kid's room, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5\n" +
                "2- Name: Bathroom, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5" +
                "\n";

        // Act
        String result = houseGrid1.getRoomsAttached();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void attachRoomInTheHouseGridRoomListTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        grid.attachRoomToTheRoomList(room);

        // Act
        boolean result = grid.getmRoomsConnectedToHouseGrid().getmRoomList().contains(room);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testListPowerSources(){
        //Arrange
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name,type1);
        PowerSource powerSource2 = new PowerSource(name2,type1);
        HouseGrid houseGrid = new HouseGrid("House Grid1");
        houseGrid.addPowerSource(powerSource1);
        houseGrid.addPowerSource(powerSource2);
        String expectedResult="1- Power Source 1\n" +
                "2- Power Source 2\n";
        //Act
        String result=houseGrid.listPowerSources();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetAllDevicesList() {
        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge();
        DeviceSpecs specWashing = new WashingMachine();
        DeviceSpecs specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room1, specFridge, 300);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing, 300);
        Device dev3 = new Device("DishWasher", room1, specDishWasher, 400);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater();
        Device dev4 = new Device("FridgeSiemens", room2, specFridge, 300);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher, 400);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater, 25);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        DeviceList expectedResult = new DeviceList();
        expectedResult.addDevice(dev1);
        expectedResult.addDevice(dev2);
        expectedResult.addDevice(dev3);
        expectedResult.addDevice(dev4);
        expectedResult.addDevice(dev5);
        expectedResult.addDevice(dev6);

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        HouseGrid housegrid = new HouseGrid("grid1", 1000, roomList);

        DeviceList result = housegrid.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPower(){
        //Assert

        //Room ONE
        String name = "Kitchen";
        Dimensions dim = new Dimensions(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge();
        DeviceSpecs specWashing = new WashingMachine();
        DeviceSpecs specDishWasher = new DishWasher();
        Device dev1 = new Device("FridgeAriston", room1, specFridge, 300);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing, 300);
        Device dev3 = new Device("DishWasher", room1, specDishWasher, 400);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimensions dim2 = new Dimensions(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater();
        Device dev4 = new Device("FridgeSiemens", room2, specFridge, 300);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher, 400);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater, 25);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        HouseGrid grid1 = new HouseGrid("Grid 1");
        grid1.attachRoomToTheRoomList(room1);
        grid1.attachRoomToTheRoomList(room2);

        double expectedResult = 1725;

        //Act
        double result = grid1.getNominalPower();

        //Assert
        assertEquals(expectedResult,result,0.001);
    }
}
