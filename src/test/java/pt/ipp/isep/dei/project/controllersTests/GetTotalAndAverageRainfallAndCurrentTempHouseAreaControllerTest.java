package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTotalAndAverageRainfallAndCurrentTempHouseAreaControllerTest {

    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController controller;
    private House house;

    @BeforeEach
    public void StartUp() {

        //Geographical Area
        Location location = new Location(42.1, -8.6, 100.0);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(42.1, -8.6, 100.0);
        Address address = new Address("4200-072", houseLocation);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(insertedGeoArea);

        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);
    }

    @Test
    public void testTotalDailyMeasurementInAHouseArea () {
        //Arrange
        //Instantiate Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.15, -8.610, 97);
        Sensor s0 = new Sensor("A122", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.15, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0 - Register 1
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        Readings readings01 = new Readings(10, dataHoraDaMedicao01);
        s0.addReadingsToList(readings01);

        // Sensor0 - Register 2
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 1, 17, 24, 00);
        Readings readings02 = new Readings(11, dataHoraDaMedicao02);
        s0.addReadingsToList(readings02);

        //Sensor1 - Register 1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        Readings readings11 = new Readings(11, dataHoraDaMedicao11);
        s1.addReadingsToList(readings11);

        //Sensor1 - Register 2
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 1, 18, 24, 00);
        Readings readings12 = new Readings(15, dataHoraDaMedicao12);
        s1.addReadingsToList(readings12);

        LocalDateTime day = LocalDateTime.of(2018, 11, 1, 15, 20, 00);

        double expectedResult = 15;

        //Act
        double result = this.controller.getTotalRainfallInTheHouseAreaInTheSelectedDay(day.toLocalDate());

        //Assert
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
        AreaShape rectangleArea = new AreaShape(20, 20, local);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geographicalAreaType, local, rectangleArea);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        //Instanciar Sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("Sensor0", dataFuncionamento0, sensorType0, locS0);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("Sensor1", dataFuncionamento1, sensorType1, locS1);
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 3, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 4, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 5, 17, 24, 00);

        LocalDateTime dataHoraDaMedicao13 = LocalDateTime.of(2018, 11, 5, 18, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);
        Readings readings13 = new Readings(20, dataHoraDaMedicao13);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);
        s1.addReadingsToList(readings13);

        LocalDateTime startDate1 = LocalDateTime.of(2018, 11, 1, 15, 20, 00);
        LocalDateTime endDate1 = LocalDateTime.of(2018, 11, 6, 17, 24, 00);

        GetTotalAndAverageRainfallAndCurrentTempHouseAreaController ctrl = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);


        double expectedResult = 22.25;

        //Act
        double result = ctrl.getAverageDailyRainfall(startDate1.toLocalDate(), endDate1.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseArea() {
        // ARRANGE
        // Instantiate RoomList
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

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();
        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate AreaInserida
        String nomeAG = "Região Norte";
        GeographicalAreaType tipo = new GeographicalAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getSensorListInTheGeographicArea().addSensor(s1);

        //Instantiate MeasurementS
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 11, 4, 17, 24, 00);

        Readings readings01 = new Readings(23, dataHoraDaMedicao01);
        Readings readings02 = new Readings(30, dataHoraDaMedicao02);

        s0.addReadingsToList(readings01);
        s0.addReadingsToList(readings02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 11, 2, 15, 20, 00);

        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 11, 3, 17, 24, 00);

        Readings readings11 = new Readings(22, dataHoraDaMedicao11);
        Readings readings12 = new Readings(25, dataHoraDaMedicao12);

        s1.addReadingsToList(readings11);
        s1.addReadingsToList(readings12);

        // Controller
        GetTotalAndAverageRainfallAndCurrentTempHouseAreaController ctrl = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);

        double expectedResult = 25;

        //Act
        double result = ctrl.getMostRecentAvailableMeasurement();

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        // ARRANGE
        // Instantiate RoomList
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

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();
        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate AreaInserida
        String nomeAG = "Região Norte";
        GeographicalAreaType tipo = new GeographicalAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType0 = new SensorType("temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 11, 2, 15, 20, 00);
        SensorType sensorType1 = new SensorType("temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getSensorListInTheGeographicArea().addSensor(s1);

        // Controller
        GetTotalAndAverageRainfallAndCurrentTempHouseAreaController ctrl = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);

        double expectedResult = Double.NaN;

        //Act
        double result = ctrl.getMostRecentAvailableMeasurement();

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        // ARRANGE
        // Instantiate RoomList
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

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();
        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate AreaInserida
        String nomeAG = "Região Norte";
        GeographicalAreaType tipo = new GeographicalAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Controller
        GetTotalAndAverageRainfallAndCurrentTempHouseAreaController ctrl = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);

        double expectedResult = Double.NaN;

        //Act
        double result = ctrl.getMostRecentAvailableMeasurement();

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetmTypeTemperature() {
        // ARRANGE
        // Instantiate RoomList
        RoomList roomList = new RoomList();

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate Inserted Area
        String nomeAG = "Região Norte";
        GeographicalAreaType tipo = new GeographicalAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Controller
        GetTotalAndAverageRainfallAndCurrentTempHouseAreaController ctrl = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);

        String expectedResult = "temperature";

        //Act
        String result = ctrl.getTypeTemperature();

        //Assert
        assertEquals(expectedResult, result);
    }
}
