package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetTotalAndAverageRainfallAndCurrentTempHouseAreaControllerTest {

    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController controller;
    private House house;
    private GeographicalArea geoArea;
    @Mock
    private HouseService houseService;


    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        //Geographical Area
        Location location = new Location(42.1, -8.6, 100.0);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        this.geoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");


        Location houseLocation = new Location(42.1, -8.6, 100.0);
        Address address = new Address("4200-072", houseLocation, geoArea);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);
    }

    @Test
    public void testTotalDailyMeasurementInAHouseArea() {
        //Arrange
        when(houseService.getHouse()).thenReturn(house);

        //Instantiate sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.15, -8.610, 97);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S01"), "A122", dataFuncionamento0, sensorType0, locS0, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.15, -8.610, 97);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S02"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0 - Register 1
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        Reading reading01 = new Reading(10, dataHoraDaMedicao01);
        s0.addReadingsToList(reading01);

        // Sensor0 - Register 2
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 1, 17, 24, 00);
        Reading reading02 = new Reading(11, dataHoraDaMedicao02);
        s0.addReadingsToList(reading02);

        //Sensor1 - Register 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        Reading reading11 = new Reading(11, dataHoraDaMedicao11);
        s1.addReadingsToList(reading11);

        //Sensor1 - Register 2
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 1, 18, 24, 00);
        Reading reading12 = new Reading(15, dataHoraDaMedicao12);
        s1.addReadingsToList(reading12);

        LocalDateTime day = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        double expectedResult = 15;

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        //Act
        double result = this.controller.getTotalRainfallInTheHouseAreaInTheSelectedDay(day.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testeAverageRainfallOfHouseArea() {
        //Arrange
        when(houseService.getHouse()).thenReturn(house);
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S01"), "Sensor0", dataFuncionamento0, sensorType0, locS0, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S09"), "Sensor1", dataFuncionamento1, sensorType1, locS1, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 5, 17, 24, 00);
        LocalDateTime dataHoraDaMedicao13 = LocalDateTime.of(2018, 11, 5, 18, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);
        Reading reading13 = new Reading(20, dataHoraDaMedicao13);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);
        s1.addReadingsToList(reading13);

        LocalDateTime startDate1 = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        LocalDateTime endDate1 = LocalDateTime.of(2018, 11, 6, 17, 24, 00);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        double expectedResult = 22.25;

        //Act
        double result = this.controller.getAverageDailyRainfall(startDate1.toLocalDate(), endDate1.toLocalDate());

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseArea() {
        // ARRANGE
        when(houseService.getHouse()).thenReturn(house);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("temperature");
        Location locS0 = new Location(42.1, -8.6, 100.0);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S01"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s0);

        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S02"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s1);

        //Instantiate MeasurementS
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 4, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        double expectedResult = 25.0;

        //Act
        double result = this.controller.getMostRecentAvailableMeasurement();

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        // ARRANGE
        when(houseService.getHouse()).thenReturn(house);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("temperature");
        Location locS0 = new Location(-1, 30, 50);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S01"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S02"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s1);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        double expectedResult = Double.NaN;

        //Act
        double result = this.controller.getMostRecentAvailableMeasurement();

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        // ARRANGE
        when(houseService.getHouse()).thenReturn(house);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        double expectedResult = Double.NaN;

        //Act
        double result = this.controller.getMostRecentAvailableMeasurement();

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetmTypeTemperature() {
        // Arrange
        when(houseService.getHouse()).thenReturn(house);

        String expectedResult = "temperature";

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        //Act
        String result = this.controller.getTypeTemperature();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDateLastTemperatureOfTheHouseArea() {
        // ARRANGE
        when(houseService.getHouse()).thenReturn(house);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("temperature");
        Location locS0 = new Location(42.1, -8.6, 100.0);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S08"), "A123", dataFuncionamento0, sensorType0, locS0, "C");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s0);

        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S09"), "A123", dataFuncionamento0, sensorType0, locS0, "C");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s1);

        //Instantiate MeasurementS
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 4, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        LocalDateTime expectedResult = LocalDateTime.of(2018, 11, 3, 17, 24);

        //Act
        LocalDateTime result = this.controller.getDateOfLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDateLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        // ARRANGE
        when(houseService.getHouse()).thenReturn(house);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("temperature");
        Location locS0 = new Location(-1, 30, 50);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S09"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S01"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        this.geoArea.getSensorListInTheGeographicArea().addSensor(s1);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        LocalDateTime expectedResult = null;

        //Act
        LocalDateTime result = this.controller.getDateOfLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDateLastTemperatureOfTheHouseAreaWithoutSensors() {
        // ARRANGE
        when(houseService.getHouse()).thenReturn(house);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(houseService);

        LocalDateTime expectedResult = null;

        //Act
        LocalDateTime result = this.controller.getDateOfLastMeasurement();

        //Assert
        assertEquals(expectedResult, result);
    }
}