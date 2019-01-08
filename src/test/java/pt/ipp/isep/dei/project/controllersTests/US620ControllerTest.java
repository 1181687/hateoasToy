package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US620Controller;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US620ControllerTest {

    @Test
    public void testOfCreateANewDate() {
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

        US620Controller ctrl = new US620Controller(house);

        int year = 2001, month = 12, day = 1;
        LocalDate dateLD = LocalDate.of(2001, 12, 1);

        Date expectedResult = Date.from(dateLD.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //ACT
        Date result = ctrl.createANewDate(year, month, day);
        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    public void testTotalDailyMeasurementInAHouseArea () {
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
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Sensor0 - Register 1
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();
        Measurement measurement01 = new Measurement(10, dataHoraDaMedicao01);
        s0.addMeasurementToList(measurement01);

        // Sensor0 - Register 2
        Calendar calendarioDaMedicao02 = new GregorianCalendar(2018, 11, 1, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();
        Measurement measurement02 = new Measurement(11, dataHoraDaMedicao02);
        s0.addMeasurementToList(measurement02);

        //Sensor1 - Register 1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();
        Measurement measurement11 = new Measurement(11, dataHoraDaMedicao11);
        s1.addMeasurementToList(measurement11);

        //Sensor1 - Register 2
        Calendar calendarioDaMedicao12 = new GregorianCalendar(2018, 11, 1, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();
        Measurement measurement12 = new Measurement(15, dataHoraDaMedicao12);
        s1.addMeasurementToList(measurement12);

        Calendar calendarDay = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date day = calendarDay.getTime();

        US620Controller ctrl = new US620Controller(house);

        double expectedResult = 26;

        //Act
        double result = ctrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(day);

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
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        house.getmInsertedGeoArea().getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2018, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2018, 11, 4, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2018, 11, 5, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);
        Measurement measurement13 = new Measurement(20, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);
        s1.addMeasurementToList(measurement13);

        Calendar startDate = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date startDate1 = startDate.getTime();
        Calendar endDate = new GregorianCalendar(2018, 11, 6, 17, 24, 00);
        Date endDate1 = endDate.getTime();

        US620Controller ctrl = new US620Controller(house);


        double expectedResult = 24.375;

        //Act
        double result = ctrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(startDate1, endDate1);

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
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();
        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmList().add(grid0);
        houseGridList.getmList().add(grid1);

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Instantiate Sensors
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        //Instantiate MeasurementS
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2018, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(30, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2018, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        // Controller
        US620Controller ctrl = new US620Controller(house);

        double expectedResult = 25;

        //Act
        double result = ctrl.getMostRecentAvailableMeasurementInTheHouseArea();

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
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();
        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmList().add(grid0);
        houseGridList.getmList().add(grid1);

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Instantiate Sensors
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Controller
        US620Controller ctrl = new US620Controller(house);

        double expectedResult = Double.NaN;

        //Act
        double result = ctrl.getMostRecentAvailableMeasurementInTheHouseArea();

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
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        roomList.addRoom(room1);
        roomList.addRoom(room2);

        // Instantiate HouseGridList
        HouseGridList houseGridList = new HouseGridList();
        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmList().add(grid0);
        houseGridList.getmList().add(grid1);

        // Instantiate Address
        Location local = new Location(32.1496, 7.6109, 98);
        Address address = new Address("4250-302", local);

        // Instantiate AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Controller
        US620Controller ctrl = new US620Controller(house);

        double expectedResult = Double.NaN;

        //Act
        double result = ctrl.getMostRecentAvailableMeasurementInTheHouseArea();

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
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        AreaShape area = new AreaShape(10, 10, localAG);
        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);

        // Instantiate House
        House house = new House(roomList, houseGridList, address, AG);

        // Controller
        US620Controller ctrl = new US620Controller(house);

        String expectedResult = "temperature";

        //Act
        String result = ctrl.getmTypeTemperature();

        //Assert
        assertEquals(expectedResult, result);
    }
}
