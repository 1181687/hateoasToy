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
        SensorList list = new SensorList();
        Room room = new Room("F5", 1, dim, list);
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

}
