package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(rList, gridlist, adr, insertedGeoArea);
        boolean result = house.addRoomToHouse(room);
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse() {
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10, 10, 10);
        Address adr = new Address("5000", local);
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(rList, gridlist, adr, insertedGeoArea);

        boolean result = house.addRoomToHouse(null);
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
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        Location expectedResult = local;
        // Act
        Location result = house.getLocationOfTheHouse();
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
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
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

        double expectedResult = 24.375;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurementOfHouseArea(searchType, startDate1, endDate1);

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
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
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

        Calendar startDate = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date startDate1 = startDate.getTime();
        Calendar endDate = new GregorianCalendar(2018, 11, 6, 17, 24, 00);
        Date endDate1 = endDate.getTime();

        double expectedResult = 0;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurementOfHouseArea(searchType, startDate1, endDate1);

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

        roomList.addRoomToRoomList(room1);
        roomList.addRoomToRoomList(room2);


        //Instanciar HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmList().add(grid0);
        houseGridList.getmList().add(grid1);


        //Instanciar Address
        Location local = new Location(32.1496, 7.6109, 98);

        Address address = new Address("4250-302",local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        RectangleArea area = new RectangleArea(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instanciar House
        House house = new House (roomList, houseGridList, address, AG);


        //Instantiate Sensors
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);


        //Instantiate Measurement
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

        double expectedResult = 25;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurementOfTheHouseArea(type);

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

        roomList.addRoomToRoomList(room1);
        roomList.addRoomToRoomList(room2);


        //Instanciar HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmList().add(grid0);
        houseGridList.getmList().add(grid1);


        //Instanciar Address
        Location local = new Location(32.1496, 7.6109, 98);

        Address address = new Address("4250-302",local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        RectangleArea area = new RectangleArea(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instanciar House
        House house = new House (roomList, houseGridList, address, AG);


        //Instantiate Sensors
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        AG.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurementOfTheHouseArea(type);

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

        roomList.addRoomToRoomList(room1);
        roomList.addRoomToRoomList(room2);


        //Instanciar HouseGridList
        HouseGridList houseGridList = new HouseGridList();

        String gridName0 = "Grid0";
        String gridName1 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        houseGridList.getmList().add(grid0);
        houseGridList.getmList().add(grid1);


        //Instanciar Address
        Location local = new Location(32.1496, 7.6109, 98);

        Address address = new Address("4250-302",local);


        //Instanciar AreaInserida
        String nomeAG = "Região Norte";
        GeoAreaType tipo = new GeoAreaType("Região");
        Location localAG = new Location(32.1576, 7.6199, 100);
        RectangleArea area = new RectangleArea(10, 10, localAG);

        GeographicalArea AG = new GeographicalArea(nomeAG, tipo, localAG, area);


        //Instantiate House
        House house = new House (roomList, houseGridList, address, AG);

        double expectedResult = Double.NaN;

        SensorType type = new SensorType("Temperature");

        //Act
        double result = house.getLastMeasurementOfTheHouseArea(type);

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
        RectangleArea rectangleArea = new RectangleArea(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, rectangleArea);
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


        double expectedResult = 26;

        SensorType searchType = new SensorType("Rainfall");
        //Act
        double result = house.getTotalDailyMeasurementOfHouseArea(searchType, day);

        //Assert
        assertEquals(expectedResult, result);
    }
}
