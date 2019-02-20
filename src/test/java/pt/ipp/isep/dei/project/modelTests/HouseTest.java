package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {
    private House house;
    private GeographicalArea ag;
    private Room laundry;
    private Room kitchen;
    private Device electricWaterHeater;

    @BeforeEach
    public void StartUp() {
        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(1.261, 1.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        ag = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(ag);

        // Room Instantiation
        Dimension dim = new Dimension(3, 3.5, 3.5);
        laundry = new Room("Laundry", 2, dim);
        house.addRoom(laundry);
        kitchen = new Room("Kitchen", 1, dim);

        // ElectricWaterHeaters Instantiation
        electricWaterHeater = new ElectricWaterHeater("Bosch Tronic 3000", laundry);
        house.setDeviceAttribute("Bosch Tronic 3000", "Nominal Power", 0.5);
        house.setDeviceAttribute("Bosch Tronic 3000", "Performance Ratio", 0.8);
        house.setDeviceAttribute("Bosch Tronic 3000", "Hot-Water Temperature", 70);
    }

    @Test
    public void createDeviceTrue() {
        // Act
        boolean result = house.createDevice("Fridge", "Bosch 2000", laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    public void createDeviceDeviceTypeNotExists_False() {
        // Act
        boolean result = house.createDevice("Hoven", "Bosch 1000", laundry);

        // Assert
        assertFalse(result);
    }

    @Test
    public void createDeviceNameAlreadyExists_False() {
        // Arrange
        house.createDevice("Fridge", "Bosch 2000", laundry);

        // Act
        // Act
        boolean result = house.createDevice("Lamp", "Bosch 2000", laundry);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDisplayRoomListTest() {
        // Arrange
        String expectResult = "1- Name: Laundry, House Floor: 2, Dimension - Height: 3.0, Length: 3.5, Width: 3.5\n";
        // Act
        String result = house.getRoomListContent();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        // Arrange
        house.getRoomList().getRoomList().remove(laundry);
        String expectResult = "";

        // Act
        String result = house.getRoomListContent();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        String expectedResult = "Laundry";

        // Act
        String result = house.getRoomNameByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        // Arrange
        house.getRoomList().getRoomList().remove(laundry);
        String expectedResult = null;

        // Act
        String result = house.getRoomNameByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        int expectResult = 1;

        // Act
        int result = house.getRoomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        // Arrange
        house.getRoomList().getRoomList().remove(laundry);
        int expectResult = 0;

        // Act
        int result = house.getRoomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testAddRoomToHouse() {
        // Act
        boolean result = house.addRoom(kitchen);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse() {
        // Act
        boolean result = house.addRoom(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetLocationOfTheHouse() {
        // Arrange
        Location location = new Location(41.178553, -8.608035, 111);
        Location expectedResult = location;

        // Act
        Location result = house.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testeAverageRainfallOfHouseArea() {
        //Arrange
        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(41.178553, -8.608035, 111);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(41.178553, -8.608035, 111);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);
        Readings readings13 = new Readings(20, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);
        s1.addReadingsToList(readings13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        double expectedResult = 26.5;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurementInHouseArea(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testeAverageRainfallOfHouseAreaNoMeasurements() {
        //Arrange
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
        double result = house.getAverageDailyMeasurementInHouseArea(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseArea() {
        //arrange
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

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(41.178553, -8.608035, 111);
        Sensor s0 = new Sensor("A122", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(41.178553, -8.608035, 111);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);


        //Instantiate Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        double expectedResult = 30;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurementByTypeInHouseArea(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        //arrange
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

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurementByTypeInHouseArea(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        //arrange
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

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurementByTypeInHouseArea(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testTotalDailyMeasurementInAHouseArea() {
        //Arrange
        //Instantiate Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(41.1, -8.6, 111);
        Sensor s0 = new Sensor("A122", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(41.1, -8.6, 111);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0 - Register 1
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 1, 15, 20, 00);
        Readings readings01 = new Readings(10, dataHoraDaMedicao01);
        s0.addReadingsToList(readings01);

        // Sensor0 - Register 2
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 1, 17, 24, 00);
        Readings readings02 = new Readings(11, dataHoraDaMedicao02);
        s0.addReadingsToList(readings02);

        //Sensor1 - Register 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 1, 17, 20, 00);
        Readings readings11 = new Readings(15, dataHoraDaMedicao11);
        s1.addReadingsToList(readings11);

        //Sensor1 - Register 2
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 1, 23, 24, 00);
        Readings readings12 = new Readings(20, dataHoraDaMedicao12);
        s1.addReadingsToList(readings12);

        LocalDate day = LocalDate.of(2018, 12, 1);

        double expectedResult = 20;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getTotalDailyMeasurementInHouseArea(searchType, day);

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

        //Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(24, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 24, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(30, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(27, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        SensorType sensorType = new SensorType("Temperatura");
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);

        house.addRoom(room);

        Readings expectedResult = readings22;

        //Act
        Readings result = house.getLatestMeasurementBySensorType("F5", sensorType);

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

        //Instanciar Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(25, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        SensorType tipoResultado = new SensorType("Pluviosidade");

        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);

        house.addRoom(room);

        //Act
        Readings result = house.getLatestMeasurementBySensorType("F5", tipoResultado);

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

        //Instanciar Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(25, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        //Sensor2
        LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

        Readings readings21 = new Readings(20, dataHoraDaMedicao21);
        Readings readings22 = new Readings(25, dataHoraDaMedicao22);

        s2.addReadingsToList(readings21);
        s2.addReadingsToList(readings22);

        SensorType tipoResultado = new SensorType("Pluviosidade");

        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);

        house.addRoom(room);

        //Act
        Readings result = house.getLatestMeasurementBySensorType("F6", tipoResultado);

        //Assert
        assertNull(result);
    }

    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresListaVazia() {
        //arrange
        SensorType tipoResultado = new SensorType("Temperatura");

        //Act
        Readings result = house.getLatestMeasurementBySensorType("F5", tipoResultado);

        //Assert
        assertNull(result);
    }


    @Test
    public void getMaximumTemperatureOfARoomInASpecificDay() {
        //Arrange
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

        Readings readings1 = new Readings(-20.0, dateTimeDayMeasure1);
        Readings readings2 = new Readings(-25.0, dateTimeDayMeasure2);

        s0.addReadingsToList(readings1);
        s0.addReadingsToList(readings2);

        LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
        LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 12, 2, 17, 24, 00);

        Readings readings3 = new Readings(-10.0, dateTimeDayMeasure3);
        Readings readings4 = new Readings(-15.0, dateTimeDayMeasure4);

        s1.addReadingsToList(readings3);
        s1.addReadingsToList(readings4);

        room1.getSensorList().addSensor(s0);
        room1.getSensorList().addSensor(s1);

        house.addRoom(room1);

        LocalDate dayNeeded = LocalDate.of(1991, 12, 2);

        double expectedResult = -10.0;

        //Act
        double result = house.getMaximumTemperatureOfRoomInSpecificDay(name, sensorType0, dayNeeded);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testCheckIfNameAlreadyExists() {
        //Arrange
        String nameToCheck = "Room one";
        String name = "ROOM ONE";

        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        house.addRoom(room1);

        //Act
        boolean expectedResult = true;

        boolean result = house.isNameExistant(nameToCheck);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testCheckIfNameAlreadyExistsFalse() {
        //Arrange
        String nameToCheck = "Room one";
        String name = "ROOM Two";

        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, 1, dim);
        house.addRoom(room1);
        //Act
        boolean expectedResult = false;

        boolean result = house.isNameExistant(nameToCheck);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSensorsListContentOfARoomTest() {
        // Arrange
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 300, 50);
        Sensor s1 = new Sensor("A456", dataFuncionamento1, sensorType1, locS1);
        laundry.addSensorToListOfSensorsInRoom(s0);
        laundry.addSensorToListOfSensorsInRoom(s1);
        String expectedResult =
                "1 - Name of the sensor: A123\n" +
                        "2 - Name of the sensor: A456\n";
        // Act
        String result = house.getSensorListContentOfARoom(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfSensorListIsEmptyTestTrue() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

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
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        laundry.addSensorToListOfSensorsInRoom(s0);

        // Act
        boolean result = house.isSensorListEmpty(0);

        // Assert
        assertFalse(result);
    }


   @Test
    public void getDeviceListContentOfARoomTest() {
        // Arrange
       String expectedResult = "1 - Name of the device: Bosch Tronic 3000\n";

        // Act
       String result = house.getDeviceListContentRoom(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        laundry.removeDevice(electricWaterHeater);

        // Act
        boolean result = house.isDeviceListEmpty(0);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room);

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

        FridgeType fridgeType = new FridgeType();
        Device dev1 = fridgeType.createDevice("FridgeAriston", room1);

        WashingMachineType washingMachineType = new WashingMachineType();
        Device dev2 = washingMachineType.createDevice("WashingMachineBosh", room1);

        DishWasherType dishWasherType = new DishWasherType();
        Device dev3 = dishWasherType.createDevice("DishWasherSpecs", room1);



        //Room TWO
        String name2 = "KitchenBasement";
        Room room2 = new Room(name2, -1, dim);

        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);

        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeaterSpecs", room2);

        //add to Lists
        house.addRoom(room1);
        house.addRoom(room2);

        HouseGrid grid = new HouseGrid("g1");
        grid.attachRoom(room1);
        grid.attachRoom(room2);
        house.addGrid(grid);

        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);
        expectedResult.add(dev2);
        expectedResult.add(dev3);
        expectedResult.add(dev4);
        expectedResult.add(dev5);
        expectedResult.add(dev6);

        List<Device> result = house.getAllDevicesListByGridPosition(0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Arrange
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        FridgeType fridgeType = new FridgeType();
        Device dev1 = fridgeType.createDevice("FridgeAriston", room1);

        WashingMachineType washingMachineType = new WashingMachineType();
        Device dev2 = washingMachineType.createDevice("WashingMachineBosh", room1);

        DishWasherType dishWasherType = new DishWasherType();
        Device dev3 = dishWasherType.createDevice("DishWasherSpecs", room1);



        //Room TWO
        String name2 = "KitchenBasement";
        Room room2 = new Room(name2, -1, dim);

        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);

        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeaterSpecs", room2);


        HouseGrid hg = new HouseGrid("Grid");
        house.addGrid(hg);
        hg.attachRoom(room1);
        hg.attachRoom(room2);

        //add to Lists
        house.addRoom(room1);
        house.addRoom(room2);

        String expectedResult = "Electric Water Heater\n" +
                "- Device Name: ElectricWaterHeaterSpecs, Location: KitchenBasement.\n" +
                "\n" +
                "Dishwasher\n" +
                "- Device Name: DishWasherSpecs, Location: Kitchen.\n" +
                "- Device Name: DishWasherTeka, Location: KitchenBasement.\n" +
                "\n" +
                "Washing Machine\n" +
                "- Device Name: WashingMachineBosh, Location: Kitchen.\n" +
                "\n" +
                "Fridge\n" +
                "- Device Name: FridgeAriston, Location: Kitchen.\n" +
                "- Device Name: FridgeSiemens, Location: KitchenBasement.\n" +
                "\n";

        String result = house.getDeviceListContentNameTypeLocationByHG(0);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetRoomListLength() {
        // Arrange
        double expectedResult = 1;

        // Act
        double result = house.houseRoomListSize();

        // Assert
        assertEquals(expectedResult, result);

    }

  /*  @Test
    public void getEnergyConsumptionInADayOfAllDevicesOfATypeTestWithValidValues() {
        // Arrange
        // Dimension Instantiation
        double height = 3;
        double length = 3.5;
        double width = 3.5;
        Dimension dim = new Dimension(height, length, width);

        // Room Instantiation
        Room room = new Room("Room", 2, dim);

        // ElectricWaterHeaterSpecs Instantiation
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device device0 = electricWaterHeaterType.createDevice("Electric Water Heater", room);
        device0.setAttributesDevType("Hot-Water Temperature",50);
        device0.setAttributesDevType("Volume Of Water To Heat",150);
        device0.setAttributesDevType("Performance Ratio",0.9);
        device0.setAttributesDevType("Nominal Power",100);

        house.addRoom(room);

        int coldWaterTempPosition = 5;
        int volumeOfWaterToHeatPosition = 6;
        house.setDeviceAttribute("Electric Water Heater", 0, coldWaterTempPosition, 30);
        house.setDeviceAttribute("Electric Water Heater", 0, volumeOfWaterToHeatPosition, 100);

        double expectedResult = 2.09;

        // Act
        double result = house.getDailyEnergyConsumptionOfADevice("Electric Water Heater", 0);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    */

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


        house.addGrid(grid0);
        house.addGrid(grid1);
        house.addGrid(grid2);

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

        // Act
        boolean result = house.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        house.addGrid(grid);
        // Act
        boolean result = house.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfARoomIsAlreadyInAHouseGridOfTheListWithNegativeTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        String gridName = "Grid";
        HouseGrid grid = house.newHouseGrid(gridName);
        house.addGrid(grid);

        // Act
        boolean result = house.checkIfRoomIsAlreadyInHouseGrid(grid, room);

        // Assert
        assertFalse(result);
    }


    @Test
    public void getTheGridWhereTheRoomIsConnectedTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimension);

        // Instantiate House Grids
        String gridName0 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid2";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid3";
        HouseGrid grid2 = new HouseGrid(gridName2);
        grid2.attachRoom(room);

        house.addGrid(grid0);
        house.addGrid(grid1);
        house.addGrid(grid2);

        HouseGrid expectedResult = grid2;

        // Act
        HouseGrid result = house.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getTheGridWhereTheRoomIsConnectedNullTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimension);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);


        house.addGrid(grid0);
        house.addGrid(grid1);
        house.addGrid(grid2);

        HouseGrid expectedResult = null;

        // Act
        HouseGrid result = house.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        //grid
        String gridName0 = "Grid";
        String gridName1 = "Grid2";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        house.addGrid(grid0);
        house.addGrid(grid1);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid2\n";

        // Act
        String result = house.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthTest() {
        // Arrange
        //grid
        String gridName = "Grid";
        String gridName1 = "Grid2";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName1);
        house.addGrid(grid0);
        house.addGrid(grid1);
        int expectedResult = 2;

        // Act
        int result = house.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthEmptyListTest() {
        // Arrange
        int expectedResult = 0;

        // Act
        int result = house.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAGridFromASpecificPosition() {
        // Arrange
        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        house.addGrid(grid0);
        house.addGrid(grid1);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = house.getHouseGridByPosition(0);

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

        FridgeType fridgeType = new FridgeType();
        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);

        DishWasherType dishWasherType = new DishWasherType();
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);

        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeaterSpecs", room2);

        HouseGrid grid = new HouseGrid("g1");
        grid.attachRoom(room2);
        grid.attachRoom(room2);
        house.addGrid(grid);

        // Act
        boolean result = house.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesTrue() {
        // Arrange
        HouseGrid grid = new HouseGrid("g1");
        house.addGrid(grid);
        // Act
        boolean result = house.checkIfThereAreNoDevicesHGbyPosition(0);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetNumberOfDevicesOfAType() {
        //Arrange
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, 2, dim);

        FridgeType fridgeType = new FridgeType();
        WashingMachineType washingMachineType = new WashingMachineType();
        DishWasherType dishWasherType = new DishWasherType();
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();

        Device dev1 = fridgeType.createDevice("FridgeAriston", room1);
        Device dev2 = washingMachineType.createDevice("WashingMachineBosh", room1);
        Device dev3 = dishWasherType.createDevice("DishWasher", room1);


        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeater", room2);

        int expectedResult = 2;

        house.addRoom(room1);
        house.addRoom(room2);

        int result = house.getNumberOfDevicesOfAType("Fridge");

        assertEquals(expectedResult, result);

    }

    @Test
    public void getDeviceNameOfATypeByPositionTest() {
        // Arrange
        String expectedResult = "Bosch Tronic 3000";

        // Act
        String result = house.getDeviceNameOfATypeByPosition("Electric Water Heater", 0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameFromDeviceListExceptionTest() {
        // Arrange
        laundry.removeDevice(electricWaterHeater);
        String expectedResult = "There are no devices in the device list.";

        // Act
        String result = house.getDeviceNameOfATypeByPosition("Electric Water Heater", 0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        // Arrange
        house.getRoomList().getRoomList().remove(laundry);

        // Act
        boolean result = house.roomListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        house.addRoom(room1);

        //act
        boolean result = house.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        String expectedResult = "1 - Device: Bosch Tronic 3000, located in room: Laundry\n";

        // Act
        String result = house.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAllDevicesTest() {
        // Arrange
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(electricWaterHeater);

        // Act
        List<Device> result = house.getAllDevices();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfDevicesTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        FridgeType fridgeType = new FridgeType();
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();

        // Device Instantiation
        Device device0 = fridgeType.createDevice("Fridgeratah V14", room0);

        Device Device = electricWaterHeaterType.createDevice("Bosch Tronic 3000", room1);

        house.addRoom(room0);
        house.addRoom(room1);

        int expectedResult = 2;

        // Act
        int result = house.getNumberOfDevices();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceByPositionTest() {
        // Arrange
        Device expectedResult = electricWaterHeater;

        // Act
        Device result = house.getDeviceByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {
        // Arrange
        int expectResult = 1;

        // Act
        int result = house.getDeviceSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListSizeEmptyList() {
        // Arrange
        house.getRoomList().getRoomList().remove(laundry);
        int expectResult = 0;

        // Act
        int result = house.getDeviceSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testSetInsertedGeoArea() {
        //arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //initiate House
        Location location = new Location(42.0, -8.608035, 111);
        AreaShape areaShape = new AreaShape(1.5, 2.5, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea geo = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        house.setInsertedGeoArea(geo);

        GeographicalArea expectedResult = geo;

        GeographicalArea result = house.getInsertedGeoArea();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesFalse() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        FridgeType fridgeType = new FridgeType();
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();

        // Device Instantiation
        Device device0 = fridgeType.createDevice("Fridgeratah V14", room);

        Device Device = electricWaterHeaterType.createDevice("Bosch Tronic 3000", room2);


        house.addRoom(room);
        house.addRoom(room2);

        // Act
        boolean result = house.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testCheckIfThereAreNoDevicesOnRoomsTrue() {
        // Arrange
        laundry.removeDevice(electricWaterHeater);

        // Act
        boolean result = house.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertTrue(result);
    }


    @Test
    public void testNewConstructorGrid() {
        //arrange
        //initiate House
        int meteringPeriodGrid = 15;
        int meteringPeriodDevice = 15;

        List<String> deviceTypeList = new ArrayList<>();
        deviceTypeList.add("Fridge");
        deviceTypeList.add("Lamp");
        deviceTypeList.add("DishWasher");
        deviceTypeList.add("WashingMachine");
        deviceTypeList.add("ElectricWaterHeater");

        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        int expectedResult = 15;


        //Act
        int result = house.getMeteringPeriodGrid();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testNewConstructorDevice() {
        //arrange
        //initiate House
        int meteringPeriodGrid = 15;
        int meteringPeriodDevice = 15;

        List<String> deviceTypeList = new ArrayList<>();
        deviceTypeList.add("Fridge");
        deviceTypeList.add("Lamp");
        deviceTypeList.add("DishWasher");
        deviceTypeList.add("WashingMachine");
        deviceTypeList.add("ElectricWaterHeater");

        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        int expectedResult = 15;


        //Act
        int result = house.getMeteringPeriodDevice();

        //Assert
        assertEquals(expectedResult, result);

    }


    @Test
    public void testGetDateLastTemperatureOfTheHouseArea() {
        //arrange
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

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(41.178553, -8.608035, 111);
        Sensor s0 = new Sensor("A122", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(41.178553, -8.608035, 111);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);


        //Instantiate Readings
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        LocalDateTime expectedResult = LocalDateTime.of(2018,12,4,17,24);

        SensorType type = new SensorType("Temperature");

        //Act
        LocalDateTime result = house.getDateOfLastMeasurementByType(type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDateLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        //arrange
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

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime expectedResult = null;

        SensorType type = new SensorType("Temperature");

        //Act
        LocalDateTime result = house.getDateOfLastMeasurementByType(type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDateGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        //arrange
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

        LocalDateTime expectedResult = null;

        SensorType type = new SensorType("Temperature");

        //Act
        LocalDateTime result = house.getDateOfLastMeasurementByType(type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddGridTrue(){
        //Arrange
        String gridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(gridName);
        //Act
        boolean result = this.house.addGrid(houseGrid);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testAddGridFalse(){
        //Arrange
        String gridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(gridName);
        this.house.addGrid(houseGrid);
        //Act
        boolean result = this.house.addGrid(houseGrid);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGridNameAlreadyExistsTrue(){
        //Arrange
        String gridName = "main grid";
        HouseGrid grid = new HouseGrid(gridName);
        this.house.addGrid(grid);
        //Act
        boolean result = this.house.gridNameAlreadyExists(gridName);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testGridNameAlreadyExistsFalse(){
        //Arrange
        String gridName = "main grid";
        HouseGrid grid = new HouseGrid(gridName);
        this.house.addGrid(grid);
        String gridName2 = "grid 1";
        //Act
        boolean result = this.house.gridNameAlreadyExists(gridName2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGridNameAlreadyExists_WithNoHouseGrids_ShouldReturnFalse(){
        //Arrange
        String gridName2 = "grid 1";
        //Act
        boolean result = this.house.gridNameAlreadyExists(gridName2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testNewHouseGrid_ThrowsException(){
        //Arrange
        String name = "Main Grid";
        HouseGrid grid = new HouseGrid(name);
        this.house.addGrid(grid);
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                this.house.newHouseGrid(name)
        );
        //Assert
        assertEquals("Name already exists. Please, write a new one.", exception.getMessage());
    }

    @Test
    public void testNewHouseGrid_CreatesHouseGrid(){
        //Arrange
        String name = "Main Grid";
        HouseGrid expectedResult = new HouseGrid(name);
        //Act
        HouseGrid result = this.house.newHouseGrid(name);
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceByNameExceptionTest() {
        // Arrange
        String expectedResult = "There isn't any device with that name.";

        // Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                house.getDeviceByName("Whatever")
        );

        // Assert
        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    void setAttributeTrueTest() {
        // Act
        boolean result = house.setDeviceAttribute("Bosch Tronic 3000", "Cold-Water Temperature", 40);

        // Assert
        assertTrue(result);
    }

    @Test
    void setAttributeFalseTest() {
        // Act
        boolean result = house.setDeviceAttribute("Bosch Tronic 3000", "Hot-Water Temperature", 70);

        // Assert
        assertFalse(result);
    }
}

