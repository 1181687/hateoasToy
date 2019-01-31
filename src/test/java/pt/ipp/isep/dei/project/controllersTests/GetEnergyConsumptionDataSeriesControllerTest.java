package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDataSeriesController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetEnergyConsumptionDataSeriesControllerTest {

    @Test
    public void testGetHouseGridListToString() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        gridList.getmHouseGridsList().add(grid0);
        gridList.getmHouseGridsList().add(grid1);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        // Act
        String result = ctrl.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);


        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        //act
        String result = ctrl.getRoomListToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // FridgeSpecs Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // ElectricWaterHeaterSpecs Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgeratah V14", room0, fridge);
        room0.addDevice(device0);
        Device device1 = new Device("Fridgeratah V15", room0, fridge);
        room0.addDevice(device1);
        Device device2 = new Device("Fridgeratah V16", room0, fridge);
        room0.addDevice(device2);
        Device device3 = new Device("Bosh Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device3);
        Device device4 = new Device("Bosh Tronic 4000", room1, electricWaterHeater);
        room1.addDevice(device4);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        String expectedResult =
                "1 - Device: Fridgeratah V14, located in room: Kitchen\n" +
                        "2 - Device: Fridgeratah V15, located in room: Kitchen\n" +
                        "3 - Device: Fridgeratah V16, located in room: Kitchen\n" +
                        "4 - Device: Bosh Tronic 3000, located in room: Laundry\n" +
                        "5 - Device: Bosh Tronic 4000, located in room: Laundry\n";

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        // Act
        String result = ctrl.getDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthTest() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        gridList.getmHouseGridsList().add(grid0);
        gridList.getmHouseGridsList().add(grid1);
        int expectedResult = 2;

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        // Act
        int result = ctrl.getHouseGridListSize();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthEmptyListTest() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);

        int expectedResult = 0;

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        // Act
        int result = ctrl.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomListSize() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        int expectResult = 2;

        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getRoomListSizeEmptyList() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        int expectResult = 0;

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListSize() {
        //arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new FridgeSpecs(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new LampSpecs(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);

        house.addRoom(room);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);


        int expectResult = 2;
        //act
        int result = ctrl.getDeviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListSizeEmptyList() {
        //arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        int expectResult = 0;
        //act
        int result = ctrl.getDeviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void testGetDeviceDataSeriesToString() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        // Fridge Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgeratah V14", room, fridge);

        room.addDevice(device);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);
        LocalDateTime time3 = LocalDateTime.of(2019, 01, 25, 10, 00, 00);
        Readings readings3 = new Readings(10, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 25, 12, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 25, 16, 00, 00);
        Readings readings5 = new Readings(15, time5);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);
        device.addReadingsToTheList(readings3);
        device.addReadingsToTheList(readings4);
        device.addReadingsToTheList(readings5);

        Map<LocalDateTime, Double> mapToTest = new TreeMap<>();

        mapToTest.put(time0, 3.0);
        mapToTest.put(time1, 5.0);
        mapToTest.put(time2, 7.0);
        mapToTest.put(time3, 10.0);
        mapToTest.put(time4, 5.0);

        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 3.0 kWh\n" +
                "Date/hour: 2019-01-24 08:00, Energy Consumption: 5.0 kWh\n" +
                "Date/hour: 2019-01-24 16:00, Energy Consumption: 7.0 kWh\n" +
                "Date/hour: 2019-01-25 10:00, Energy Consumption: 10.0 kWh\n" +
                "Date/hour: 2019-01-25 12:00, Energy Consumption: 5.0 kWh\n";

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        ctrl.getRoomByPosition(0);
        ctrl.getDeviceByPosition(0);

        LocalDateTime timeToTest0 = LocalDateTime.of(2019, 01, 23, 10, 00);
        LocalDateTime timeToTest1 = LocalDateTime.of(2019, 01, 25, 13, 00);

        //Act
        String result = ctrl.getDeviceDataSeriesToString(timeToTest0, timeToTest1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDeviceDataSeriesToStringNoValidNumbers() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        // Fridge Instantiation
        DeviceSpecs fridge = new FridgeSpecs(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgeratah V14", room, fridge);

        room.addDevice(device);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);
        LocalDateTime time3 = LocalDateTime.of(2019, 01, 25, 10, 00, 00);
        Readings readings3 = new Readings(10, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 25, 12, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 25, 16, 00, 00);
        Readings readings5 = new Readings(15, time5);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);
        device.addReadingsToTheList(readings3);
        device.addReadingsToTheList(readings4);
        device.addReadingsToTheList(readings5);

        Map<LocalDateTime, Double> mapToTest = new TreeMap<>();

        mapToTest.put(time0, 3.0);
        mapToTest.put(time1, 5.0);
        mapToTest.put(time2, 7.0);
        mapToTest.put(time3, 10.0);
        mapToTest.put(time4, 5.0);

        String expectedResult = "No valid values found for that period.\n";

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        ctrl.getRoomByPosition(0);
        ctrl.getDeviceByPosition(0);

        LocalDateTime timeToTest0 = LocalDateTime.of(2019, 01, 26, 10, 00);
        LocalDateTime timeToTest1 = LocalDateTime.of(2019, 01, 30, 13, 00);

        //Act
        String result = ctrl.getDeviceDataSeriesToString(timeToTest0, timeToTest1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomDataSeriesToStringWithValidValues(){
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        DeviceSpecs deviceSpecs = new LampSpecs(25, 20);
        Device lamp = new Device("LampSpecs", room, deviceSpecs);

        DeviceSpecs specsFridge = new FridgeSpecs(12, 15, 25, 12);
        Device fridge = new Device("FridgeSpecs", room, specsFridge);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        int position = 0;
        ctrl.getRoomByPosition(position);
        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 6.0 kWh\n" +
                                "Date/hour: 2019-01-24 08:00, Energy Consumption: 10.0 kWh\n" +
                                "Date/hour: 2019-01-24 16:00, Energy Consumption: 14.0 kWh\n";

        //Act
        String result = ctrl.getRoomDataSeriesToString(startTime,endTime);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetRoomDataSeriesToStringWithNoValidValues(){
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        DeviceSpecs deviceSpecs = new LampSpecs(25, 20);
        Device lamp = new Device("LampSpecs", room, deviceSpecs);

        DeviceSpecs specsFridge = new FridgeSpecs(12, 15, 25, 12);
        Device fridge = new Device("FridgeSpecs", room, specsFridge);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

        LocalDateTime startTime = LocalDateTime.of(2018, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2018, 01, 25, 17, 40, 00);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        int position = 0;
        ctrl.getRoomByPosition(position);
        String expectedResult = "No valid values found for that period.\n";

        //Act
        String result = ctrl.getRoomDataSeriesToString(startTime,endTime);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetHouseGridDataSeriesToStringWithValidValues(){
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate House Grid
        HouseGrid houseGrid = new HouseGrid("Main Grid");

        house.addRoom(room);
        houseGrid.attachRoom(room);
        listHG.addHouseGrid(houseGrid);

        DeviceSpecs deviceSpecs = new LampSpecs(25, 20);
        Device lamp = new Device("LampSpecs", room, deviceSpecs);

        DeviceSpecs specsFridge = new FridgeSpecs(12, 15, 25, 12);
        Device fridge = new Device("FridgeSpecs", room, specsFridge);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        int position = 0;
        ctrl.getHouseGridByPosition(position);
        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 6.0 kWh\n" +
                                "Date/hour: 2019-01-24 08:00, Energy Consumption: 10.0 kWh\n" +
                                "Date/hour: 2019-01-24 16:00, Energy Consumption: 14.0 kWh\n";

        //Act
        String result = ctrl.getHouseGridDataSeriesToString(startTime,endTime);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetHouseGridDataSeriesToStringWithNoValidValues(){
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeographicalAreaType GAType = new GeographicalAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate House Grid
        HouseGrid houseGrid = new HouseGrid("Main Grid");

        house.addRoom(room);
        houseGrid.attachRoom(room);
        listHG.addHouseGrid(houseGrid);

        DeviceSpecs deviceSpecs = new LampSpecs(25, 20);
        Device lamp = new Device("LampSpecs", room, deviceSpecs);

        DeviceSpecs specsFridge = new FridgeSpecs(12, 15, 25, 12);
        Device fridge = new Device("FridgeSpecs", room, specsFridge);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

        LocalDateTime startTime = LocalDateTime.of(2018, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2018, 01, 25, 17, 40, 00);

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(house);

        int position = 0;
        ctrl.getHouseGridByPosition(position);
        String expectedResult = "No valid values found for that period.\n";

        //Act
        String result = ctrl.getHouseGridDataSeriesToString(startTime,endTime);

        //Assert
        assertEquals(expectedResult,result);
    }
}
