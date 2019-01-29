package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionOfRoomInAnIntervalController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GetEnergyConsumptionOfRoomInAnIntervalControllerTest {

    @Test
    public void testGetRoomListToString() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
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

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = ctrl.getRoomListToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        RoomList rList = new RoomList();


        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(rList, gridList, address, geo);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        //act
        boolean result = ctrl.roomListIsEmpty();
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

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(rList, gridList, address, geo);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        //act
        boolean result = ctrl.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getListSize() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
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

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        int expectResult = 2;
        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        int expectResult = 0;
        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        ctrl.getRoomByPosition(0);

        // Act
        boolean result = ctrl.isDeviceListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        house.addRoom(room);
        room.addDevice(dev1);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        ctrl.getRoomByPosition(0);
        // Act
        boolean result = ctrl.isDeviceListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        RoomList rList = new RoomList();
        Dimension dim0 = new Dimension(4, 4, 4);
        Room room0 = new Room("RoomOne", 1, dim0);
        Dimension dim1 = new Dimension(4, 4, 4);
        Room room1 = new Room("RoomTwo", 1, dim1);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        rList.addRoom(room0);
        rList.addRoom(room1);

        String expectedResult = "RoomTwo";

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        //Act
        String result = ctrl.getRoomNameByPosition(1);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String expectedResult = null;

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);

        //Act
        String result = ctrl.getRoomNameByPosition(0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithOneFullPeriod() {
        // Arrange

        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Kitchen", 1, dim);

        rList.addRoom(room);

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);
        ctrl.getRoomByPosition(0);
        // Act
        double result = ctrl.getEnergyConsumptionOfRoomInInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithTwoFullPeriods() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        rList.addRoom(room);
        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);
        ctrl.getRoomByPosition(0);
        // Act
        double result = ctrl.getEnergyConsumptionOfRoomInInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalTestWithoutFullPeriods() {
        // Arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        // Dimension Instantiation
        double height = 3;
        double length = 5;
        double width = 6;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Kitchen", 1, dim);

        rList.addRoom(room);
        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // Device Instantiation
        Device device = new Device("Fridgerator", room, fridge);

        // Measurement Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Measurement measurement0 = new Measurement(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Measurement measurement1 = new Measurement(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Measurement measurement2 = new Measurement(7, time2);

        // List<Measurement Configuration
        device.addMeasurementToTheList(measurement0);
        device.addMeasurementToTheList(measurement1);
        device.addMeasurementToTheList(measurement2);

        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        GetEnergyConsumptionOfRoomInAnIntervalController ctrl = new GetEnergyConsumptionOfRoomInAnIntervalController(house);
        ctrl.getRoomByPosition(0);
        // Act
        double result = ctrl.getEnergyConsumptionOfRoomInInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }
}