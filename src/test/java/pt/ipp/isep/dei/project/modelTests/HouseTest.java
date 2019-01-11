package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {

    @Test
    public void testAddRoomToHouse() {
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4, 4, 4);
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
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

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
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

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
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2,1.5,1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

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

        Address address = new Address("4250-302",local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instanciar House
        House house = new House (roomList, houseGridList, address, AG);


        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);


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
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2,1.5,1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

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

        Address address = new Address("4250-302",local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instanciar House
        House house = new House (roomList, houseGridList, address, AG);


        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

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
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2,1.5,1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

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

        Address address = new Address("4250-302",local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instantiate House
        House house = new House (roomList, houseGridList, address, AG);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurement(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testTotalDailyMeasurementInAHouseArea (){
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
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

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
        Dimensions dim = new Dimensions(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToTheListOfSensorsInTheRoom(s0);
        room.addSensorToTheListOfSensorsInTheRoom(s1);
        room.addSensorToTheListOfSensorsInTheRoom(s2);
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
        Dimensions dim = new Dimensions(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToTheListOfSensorsInTheRoom(s0);
        room.addSensorToTheListOfSensorsInTheRoom(s1);
        room.addSensorToTheListOfSensorsInTheRoom(s2);
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
        Dimensions dim = new Dimensions(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToTheListOfSensorsInTheRoom(s0);
        room.addSensorToTheListOfSensorsInTheRoom(s1);
        room.addSensorToTheListOfSensorsInTheRoom(s2);
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
        Dimensions dimensions = new Dimensions(height, length, width);
        Room room1 = new Room(name, houseFloor, dimensions);

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

        room1.getSensorList().addSensorToTheListOfSensors(s0);
        room1.getSensorList().addSensorToTheListOfSensors(s1);

        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(room1);

        LocalDate dayNeeded = LocalDate.of(1991, 12, 2);

        double expectedResult = -10.0;

        //Act
        double result = house.getMaximumTemperatureOfARoomInASpecificDay(name, sensorType0, dayNeeded);


        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testOfCreateANewDate (){
        //ARRANGE
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
        int year= 2001, month =12, day=1;
        LocalDate expectedResult = LocalDate.of(2001, 12, 1);

        //ACT
        LocalDate result = house.createANewDate(year, month, day);
        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void testCheckIfNameAlreadyExists() {
        String nameToCheck = "Room one";
        String name = "ROOM ONE";
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(5, 6, 7);
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

        boolean result = house.checkIfNameAlreadyExists(nameToCheck);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testCheckIfNameAlreadyExistsFalse() {
        String nameToCheck = "Room one";
        String name = "ROOM Two";
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(5, 6, 7);
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

        boolean result = house.checkIfNameAlreadyExists(nameToCheck);

        assertEquals(expectedResult, result);
    }
}