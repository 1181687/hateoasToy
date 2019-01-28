package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {

    @Test
    public void getRoomListTest() {
        // Arrange
        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        house.addRoom(room);

        RoomList expectedResult = rList;

        // Act
        RoomList result = house.getRoomList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomListEmptyTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        RoomList expectResult = rList;

        //act
        RoomList result = house.getRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListTest() {
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


        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        //act
        String result = house.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        String expectResult = "";

        //act
        String result = house.getRoomListContent();
        //assert
        assertEquals(expectResult, result);
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
        int roomPos = 1;
        //Act
        String result = house.getRoomNameByPosition(roomPos);
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
        int roomPos = 0;
        //Act
        String result = house.getRoomNameByPosition(roomPos);
        //Assert
        assertEquals(expectedResult, result);
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

        int expectResult = 2;
        //act
        int result = house.getRoomListSize();
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

        int expectResult = 0;
        //act
        int result = house.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testAddRoomToHouse() {
        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        boolean result = house.addRoom(room);
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse() {
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        boolean result = house.addRoom(null);
        assertFalse(result);
    }

    @Test
    public void testGetLocationOfTheHouse() {
        // Arrange
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        Location expectedResult = local;
        // Act
        Location result = house.getLocation();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testeAverageRainfallOfHouseArea() {
        //Arrange
        String zipCode = "4050";
        double latitude = 42.1;
        double longitude = -8.6;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);
        Measurement measurement13 = new Measurement(20, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);
        s1.addMeasurementToList(measurement13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        double expectedResult = 24.375;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurement(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testeAverageRainfallOfHouseAreaNoMeasurements() {
        //Arrange
        String zipCode = "4050";
        double latitude = 42.1;
        double longitude = -8.6;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        double expectedResult = 0;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurement(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseArea() {
        //arrange

        //Instantiate RoomList
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);


        //Instanciar HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);


        //Instanciar Address
        Location local = new Location(32.1496, 7.6109, 98);

        Address address = new Address("4250-302", local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instanciar House
        House house = new House(roomList, houseGridList, address, AG);


        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getSensorListInTheGeographicArea().addSensor(s1);


        //Instantiate Measurement
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        double expectedResult = 25;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurement(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        //arrange

        //Instantiate RoomList
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);


        //Instanciar HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);


        //Instanciar Address
        Location local = new Location(32.1496, 7.6109, 98);

        Address address = new Address("4250-302", local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instanciar House
        House house = new House(roomList, houseGridList, address, AG);


        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getSensorListInTheGeographicArea().addSensor(s1);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurement(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        //arrange

        //Instantiate RoomList
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);


        //Instanciar HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);


        //Instanciar Address
        Location local = new Location(32.1496, 7.6109, 98);

        Address address = new Address("4250-302", local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurement(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testTotalDailyMeasurementInAHouseArea() {
        //Arrange
        //Instantiate House
        String zipCode = "4050";
        double latitude = 42.1;
        double longitude = -8.6;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        //Instantiate Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0 - Register 1
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 1, 15, 20, 00);
        Measurement measurement01 = new Measurement(10, dataHoraDaMedicao01);
        s0.addMeasurementToList(measurement01);

        // Sensor0 - Register 2
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 1, 17, 24, 00);
        Measurement measurement02 = new Measurement(11, dataHoraDaMedicao02);
        s0.addMeasurementToList(measurement02);

        //Sensor1 - Register 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 1, 15, 20, 00);
        Measurement measurement11 = new Measurement(11, dataHoraDaMedicao11);
        s1.addMeasurementToList(measurement11);

        //Sensor1 - Register 2
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 1, 17, 24, 00);
        Measurement measurement12 = new Measurement(15, dataHoraDaMedicao12);
        s1.addMeasurementToList(measurement12);

        LocalDate day = LocalDate.of(2018, 12, 1);

        double expectedResult = 26;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getTotalDailyMeasurement(searchType, day);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresIguais() {
        //arrange
        //Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 11, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Temperatura");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Measurement
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(24, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 24, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(30, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(27, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        SensorType sensorType = new SensorType("Temperatura");
        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        house.addRoom(room);

        Measurement expectedResult = measurement22;

        //Act
        Measurement result = house.getLatestMeasurementBySensorType("F5", sensorType);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresQueNaoTem() {
        //arrange
        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 11, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Measurement
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        SensorType tipoResultado = new SensorType("Pluviosidade");

        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        house.addRoom(room);

        //Act
        Measurement result = house.getLatestMeasurementBySensorType("F5", tipoResultado);

        //Assert
        assertNull(result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresQuartoNulo() {
        //arrange
        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);

        LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 11, 15, 20, 00);
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Instanciar Measurement
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        SensorType tipoResultado = new SensorType("Pluviosidade");

        RoomList rList = new RoomList();
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        house.addRoom(room);

        //Act
        Measurement result = house.getLatestMeasurementBySensorType("F6", tipoResultado);

        //Assert
        assertNull(result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresListaVazia() {
        //arrange
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        Measurement result = house.getLatestMeasurementBySensorType("F5", tipoResultado);

        //Assert
        assertNull(result);
    }


    @Test
    public void getMaximumTemperatureOfARoomInASpecificDay() {

        String zipCode = "4050";
        double latitude = 42.1;
        double longitude = -8.6;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        HouseGridList houseGridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        String name = "Master Bedroom";
        int houseFloor = 2;
        double height = 10.0;
        double length = 5.0;
        double width = 5.0;
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, houseFloor, dimension);

        house.addRoom(room1);

        LocalDateTime date0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", date0, sensorType0, locS0);

        LocalDateTime date1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(123, 345, 50);
        Sensor s1 = new Sensor("B123", date1, sensorType1, locS1);

        LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 12, 2, 20, 24, 00);

        Measurement measurement1 = new Measurement(-20.0, dateTimeDayMeasure1);
        Measurement measurement2 = new Measurement(-25.0, dateTimeDayMeasure2);

        s0.addMeasurementToList(measurement1);
        s0.addMeasurementToList(measurement2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 12, 2, 17, 24, 00);

        Measurement measurement3 = new Measurement(-10.0, dateTimeDayMeasure3);
        Measurement measurement4 = new Measurement(-15.0, dateTimeDayMeasure4);

        s1.addMeasurementToList(measurement3);
        s1.addMeasurementToList(measurement4);

        room1.getSensorList().addSensor(s0);
        room1.getSensorList().addSensor(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);

        LocalDate dayNeeded = LocalDate.of(1991, 12, 2);

        double expectedResult = -10.0;

        //Act
        double result = house.getMaximumTemperatureOfRoomInSpecificDay(name, sensorType0, dayNeeded);


        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testCheckIfNameAlreadyExists() {
        String nameToCheck = "Room one";
        String name = "ROOM ONE";
        RoomList list = new RoomList();
        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        list.addRoom(room1);
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address adress = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(list, listHG, adress, geo);

        boolean expectedResult = true;

        boolean result = house.isNameExistant(nameToCheck);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testCheckIfNameAlreadyExistsFalse() {
        String nameToCheck = "Room one";
        String name = "ROOM Two";
        RoomList list = new RoomList();
        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        list.addRoom(room1);
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address adress = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(list, listHG, adress, geo);

        boolean expectedResult = false;

        boolean result = house.isNameExistant(nameToCheck);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorsListContentOfARoomTest() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        Sensor s1 = new Sensor("A456", dataFuncionamento1, sensorType1, locS1);

        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);

        house.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";
        // Act
        String result = house.getSensorListContentOfARoom(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        int position = 0;
        // Act
        boolean result = house.isSensorListEmpty(position);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        room.addSensorToListOfSensorsInRoom(s0);
        house.addRoom(room);

        int position = 0;
        // Act
        boolean result = house.isSensorListEmpty(position);

        // Assert
        assertFalse(result);
    }


    @Test
    public void getDeviceListContentOfARoomTest() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        //initiate House
        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        //initiate Devices

        double freezerCapacity = 5.5;
        double refrigeratorCapacity = 15.5;
        double annualEnergyConsumption = 5000;
        double nominalPower = 100.5;
        DeviceSpecs deviceSpecs = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        Device dev = new Device("Fridge1", room, deviceSpecs);


        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev);
        room.addDevice(dev1);

        house.addRoom(room);

        int position = 0;
        String expectedResult =
                "1 - Name of the device: Fridge1\n" +
                        "2 - Name of the device: Lamp1\n";


        // Act
        String result = house.getDeviceListContentRoom(position);


        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        house.addRoom(room);

        int position = 0;
        // Act
        boolean result = house.isDeviceListEmpty(position);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);
        RoomList roomList = new RoomList();

        HouseGridList listHG = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, listHG, address, geo);

        double luminousFlux = 10.0;
        double nominalPower1 = 1.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower1);
        Device dev1 = new Device("Lamp1", room, deviceSpecs1);

        room.addDevice(dev1);
        house.addRoom(room);

        int position = 0;
        // Act
        boolean result = house.isDeviceListEmpty(position);

        // Assert
        assertFalse(result);
    }


    @Test
    public void TestGetAllDevicesListByGridPosition() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        ProgramList programList = new ProgramList();
        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        DeviceSpecs specWashing = new WashingMachine(10, 100, programList);
        DeviceSpecs specDishWasher = new DishWasher(100, 100, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room1.addDevice(dev3);

        //Room TWO
        String name2 = "KitchenBasement";
        Room room2 = new Room(name2, -1, dim);
        DeviceSpecs specWaterHeater = new ElectricWaterHeater(100, 100, 100, 100);
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
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);

        DeviceList expectedResult = new DeviceList();
        expectedResult.addDevice(dev1);
        expectedResult.addDevice(dev2);
        expectedResult.addDevice(dev3);
        expectedResult.addDevice(dev4);
        expectedResult.addDevice(dev5);
        expectedResult.addDevice(dev6);

        DeviceList result = house.getAllDevicesListByGridPosition(0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        ProgramList programList = new ProgramList();
        Fridge specFridge = new Fridge(100, 100, 100, 100);
        WashingMachine specWashing = new WashingMachine(100, 100, programList);
        DishWasher specDishWasher = new DishWasher(100, 100, programList);
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
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater(100, 100, 100, 100);
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
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);


        String expectedResult = "Dish Washer\n- Device Name: DishWasher, Location: Kitchen.\n- Device Name: DishWasherTeka, Location: KitchenBasement.\n\nElectric Water Heater\n- Device Name: ElectricWaterHeater, Location: KitchenBasement.\n\nWashing Machine\n- Device Name: WashingMachineBosh, Location: Kitchen.\n\nFridge\n- Device Name: FridgeAriston, Location: Kitchen.\n- Device Name: FridgeSiemens, Location: KitchenBasement.\n\n";

        String result = house.getDeviceListContentNameTypeLocationByHG(0);
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetRoomListLength() {
        String name = "Kitchen";
        String name2 = "Bedroom";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);
        Room room2 = new Room(name2, 1, dim);

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        HouseGrid houseGrid = new HouseGrid("grid1", 1000, roomList);
        HouseGridList houseGridList1 = new HouseGridList();
        houseGridList1.addHouseGrid(houseGrid);

        //house
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, houseGridList1, address, geo);

        house.addRoom(room1);
        house.addRoom(room2);

        double expectedResult = 2;

        //Act

        double result = house.houseRoomListSize();

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void getEnergyConsumptionInADayOfAllDevicesOfATypeTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeater Instantiation
        double hotWaterTemp0 = 50;
        double maximumVolume0 = 150;
        double performanceRatio = 0.9;
        double nominalPower0 = 100;

        DeviceSpecs electricWaterHeater0 = new ElectricWaterHeater(hotWaterTemp0, maximumVolume0, performanceRatio, nominalPower0);

        // Device Instantiation
        Device device0 = new Device("Electric Water Heater", room, electricWaterHeater0);
        room.addDevice(device0);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // HouseGrid Instantiation
        String houseGridName = "Main Grid";
        double maximumContractedPower = 200;
        HouseGrid houseGrid = new HouseGrid(houseGridName, maximumContractedPower, roomList);

        // HouseGridList Instantiation
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.addHouseGrid(houseGrid);

        // House Instantiation
        House house = new House(roomList, houseGridList, null, null);

        int coldWaterTempPosition = 5;
        int volumeOfWaterToHeatPosition = 6;
        house.setAttribute("Electric Water Heater", 0, coldWaterTempPosition, 30);
        house.setAttribute("Electric Water Heater", 0, volumeOfWaterToHeatPosition, 100);

        double expectedResult = 2.09;

        // Act
        double result = house.getEnergyConsumptionOfADevice("Electric Water Heater", 0);

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
        gridList.addHouseGrid(grid0);
        gridList.addHouseGrid(grid1);
        gridList.addHouseGrid(grid2);

        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        int position = 0;
        String expectedResult = "Grid0";

        // Act
        String result = house.getHGNameByHGPosition(position);

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
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        int position = 0;
        String expectedResult = "There are no Grids in the house";

        // Act
        String result = house.getHGNameByHGPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Act
        boolean result = house.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        gridList.addHouseGrid(grid);
        // Act
        boolean result = house.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        //house
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
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

        // Act
        String result = house.getHouseGridListContent();

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
        GeoAreaType GAType = new GeoAreaType("City");
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

        // Act
        int result = house.getHouseGridListSize();

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
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);

        int expectedResult = 0;

        // Act
        int result = house.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAGridFromASpecificPosition() {
        // Arrange
        //house
        HouseGridList list = new HouseGridList();
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, list, address, geo);

        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        list.getmHouseGridsList().add(grid0);
        list.getmHouseGridsList().add(grid1);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = list.getHouseGridByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfThereAreNoDevicesHGbyPosition() {
        // Arrange
        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        ProgramList pglist = new ProgramList();
        DishWasher dishWasher = new DishWasher(100, 100, pglist);
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

        HouseGridList gridList = new HouseGridList();
        HouseGrid grid = new HouseGrid("g1");
        grid.attachRoom(room2);
        grid.attachRoom(room2);
        gridList.addHouseGrid(grid);
        RoomList roomList = new RoomList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        // Act
        boolean result = house.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesTrue() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        HouseGrid grid = new HouseGrid("g1");
        gridList.addHouseGrid(grid);
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        RoomList roomList = new RoomList();
        House house = new House(roomList, gridList, address, geo);


        // Act
        boolean result = house.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetNumberOfDevicesOfAType() {

        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        ProgramList pgList = new ProgramList();
        Fridge specFridge = new Fridge(100, 100, 100, 100);
        WashingMachine specWashing = new WashingMachine(100, 100, pgList);
        DishWasher specDishWasher = new DishWasher(100, 100, pgList);
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
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater(100, 100, 100, 0.9);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);

        int expectedResult = 2;

        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);

        int result = house.getNumberOfDevicesOfAType("Fridge");

        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetDeviceName() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        ProgramList pgList = new ProgramList();
        Fridge specFridge = new Fridge(100, 100, 100, 100);
        WashingMachine specWashing = new WashingMachine(100, 100, pgList);
        DishWasher specDishWasher = new DishWasher(100, 100, pgList);
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
        ElectricWaterHeater specWaterHeater = new ElectricWaterHeater(100, 100, 100, 0.9);
        Device dev4 = new Device("FridgeSiemens", room2, specFridge);
        Device dev5 = new Device("DishWasherTeka", room2, specDishWasher);
        Device dev6 = new Device("ElectricWaterHeater", room2, specWaterHeater);

        room2.addDevice(dev4);
        room2.addDevice(dev5);
        room2.addDevice(dev6);


        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(2, 3, 4);
        Address address = new Address("4500", location);
        GeoAreaType GAType = new GeoAreaType("City");
        AreaShape areaShape = new AreaShape(2, 2, location);
        GeographicalArea geo = new GeographicalArea("Porto", GAType, location, areaShape);
        House house = new House(roomList, gridList, address, geo);


        String expectedResult = "FridgeSiemens";

        String result = house.getDeviceNameOfATypeByPosition("Fridge", 1);

        assertEquals(expectedResult, result);
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

        //act
        boolean result = rList.isEmpty();
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

        //act
        boolean result = rList.isEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // ElectricWaterHeater Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgerator V14", room0, fridge);
        room0.addDevice(device0);
        Device device1 = new Device("Bosh Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        String expectedResult =
                "1 - Name of the device: Fridgerator V14\n" + "2 - Name of the device: Bosh Tronic 3000\n";

        // Act
        String result = house.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAllDevicesTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // ElectricWaterHeater Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgerator V14", room0, fridge);
        room0.addDevice(device0);
        Device device1 = new Device("Bosh Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(device0);
        expectedResult.add(device1);

        // Act
        List<Device> result = house.getAllDevices().getDeviceList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceByPositionTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // Fridge Instantiation
        DeviceSpecs fridge = new Fridge(35, 20, 1000, 10);

        // ElectricWaterHeater Instantiation
        DeviceSpecs electricWaterHeater = new ElectricWaterHeater(50, 150,
                0.9, 100);

        // Device Instantiation
        Device device0 = new Device("Fridgerator V14", room0, fridge);
        room0.addDevice(device0);
        Device device1 = new Device("Bosh Tronic 3000", room1, electricWaterHeater);
        room1.addDevice(device1);

        // RoomList Instantiation
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // House Instantiation
        House house = new House(roomList, null, null, null);

        Device expectedResult = device1;

        // Act
        Device result = house.getDeviceByPosition(1);

        // Assert
        assertEquals(expectedResult, result);
    }
}

