package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class HouseGridTest {

    @Test
    void TestDisplayRoomsAttachedToHouseGrid() {

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
        boolean result = grid.getRoomList().getRoomList().contains(room);

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

   /* @Test
    public void testGetAllDevicesList() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
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
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater(100, 100, 100, 100);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

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
    } */

    /*@Test
    public void getNominalPower(){
        //Assert

        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        DeviceSpecs specFridge = new Fridge(500,25,125,25);
        DeviceSpecs specWashing = new WashingMachine(500,50);
        DeviceSpecs specDishWasher = new DishWasher(500,25);
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
        DeviceSpecs specWaterHeater = new ElectricWaterHeater(50, 50, 0.9, 35);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        HouseGrid grid1 = new HouseGrid("Grid 1");
        grid1.attachRoom(room1);
        grid1.attachRoom(room2);

        double expectedResult = 185;

        //Act
        double result = grid1.getNominalPower();

        //Assert
        assertEquals(expectedResult,result,0.001);
    }*/

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

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 3000.0;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 0.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);


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

        double luminousFlux1 = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux1, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        double luminousFlux2 = 15.0;
        double nominalPower2 = 2.0;

        DeviceSpecs deviceSpecs2 = new Lamp(luminousFlux2, nominalPower2);
        Device dev2 = new Device("Lamp2", room, deviceSpecs2);

        room.addDevice(dev1);
        room.addDevice(dev2);

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
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

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
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);

        Device expectedResult = dev1;

        // Act
        Device result = houseGrid.getDeviceFromPositionInList(0, 0);

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
        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);
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
        double maximumContractedPower = 24.1;
        RoomList roomList = new RoomList();

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name, maximumContractedPower, roomList)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullNameShouldThrowException() {
        //Arrange
        String name = null;
        double maximumContractedPower = 24.1;
        RoomList roomList = new RoomList();

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name, maximumContractedPower, roomList)
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
    public void testSetMeteringConfig() {
        String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        int expectedResult = 10;

        int result = houseGrid.setGridMeteringPeriod();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithThreeValidMeasurements() {
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Quarto", 2, dimension);

        DeviceSpecs deviceSpecs = new Lamp(25, 20);
        Device lamp = new Device("Lamp", room1, deviceSpecs);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addMeasurementToTheList(readings0);
        lamp.addMeasurementToTheList(readings1);
        lamp.addMeasurementToTheList(readings2);

        double expectedResult = 12;
        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithOneValidMeasurement() {
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Quarto", 2, dimension);

        DeviceSpecs deviceSpecs = new Lamp(25, 20);
        Device lamp = new Device("Lamp", room1, deviceSpecs);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 24, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        lamp.addMeasurementToTheList(measurement0);
        lamp.addMeasurementToTheList(measurement1);
        lamp.addMeasurementToTheList(measurement2);

        double expectedResult = 0;
        //Act
        double result = grid1.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithNoValidMeasurements() {

        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Quarto", 2, dimension);

        DeviceSpecs deviceSpecs = new Lamp(25, 20);
        Device lamp = new Device("Lamp", room1, deviceSpecs);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 25, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        lamp.addMeasurementToTheList(measurement0);
        lamp.addMeasurementToTheList(measurement1);
        lamp.addMeasurementToTheList(measurement2);

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

        DeviceSpecs deviceSpecs = new Lamp(25, 20);
        Device lamp = new Device("Lamp", room1, deviceSpecs);

        DeviceSpecs specsFridge = new Fridge(12, 15, 25, 12);
        Device fridge = new Device("Fridge", room2, specsFridge);

        DeviceSpecs specsElectricWaterHeater = new ElectricWaterHeater(45, 20, 12, 12);
        Device electricWaterHeater = new Device("EWH200", room2, specsElectricWaterHeater);

        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);
        grid1.attachRoom(room2);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        lamp.addMeasurementToTheList(measurement0);
        lamp.addMeasurementToTheList(measurement1);
        lamp.addMeasurementToTheList(measurement2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement3 = new Measurement(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement4 = new Measurement(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement5 = new Measurement(7, time5);

        fridge.addMeasurementToTheList(measurement3);
        fridge.addMeasurementToTheList(measurement4);
        fridge.addMeasurementToTheList(measurement5);

        LocalDateTime time6 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement6 = new Measurement(3, time6);
        LocalDateTime time7 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement7 = new Measurement(5, time7);

        electricWaterHeater.addMeasurementToTheList(measurement6);
        electricWaterHeater.addMeasurementToTheList(measurement7);

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

        String expectedResult = "There are no rooms connected to this house grid.";

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                grid1.getEnergyConsumptionInAnInterval(startTime, endTime)
        );

        //Assert
        assertEquals(expectedResult, exception.getMessage());

    }

}
