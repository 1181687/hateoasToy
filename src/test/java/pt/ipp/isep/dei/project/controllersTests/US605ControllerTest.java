package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US605Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US605ControllerTest {

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoomToRoomList(room1);
        rList.addRoomToRoomList(room2);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");
        US605Controller ctrl = new US605Controller(house, sensorType);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0\n2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Dimensions - Length: 1.5, Dimensions - Width: 1.3\n";

        //act
        String result = ctrl.getDisplayRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDisplayRoomListEmpty() {
        //arrange
        RoomList rList = new RoomList();

        String expectResult = "";

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");
        US605Controller ctrl = new US605Controller(house, sensorType);
        //act
        String result = ctrl.getDisplayRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testLengthOfRoomList() {
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoomToRoomList(room1);
        rList.addRoomToRoomList(room2);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType = new SensorType("Temperature");
        US605Controller ctrl = new US605Controller(house, sensorType);

        int expectResult = 2;
        //act
        int result = ctrl.lengthOfRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetLatestTemperatureRoom() {
        //arrange
        Dimensions dimensions = new Dimensions(300, 600, 600);
        Room room1 = new Room("room1", 1, dimensions);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType0, locS1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Measurement
        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Measurement measurement01 = new Measurement(23, dataHoraDaMedicao01);
        Measurement measurement02 = new Measurement(25, dataHoraDaMedicao02);

        s0.addMeasurementToList(measurement01);
        s0.addMeasurementToList(measurement02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(1991, 11, 4, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Measurement measurement11 = new Measurement(22, dataHoraDaMedicao11);
        Measurement measurement12 = new Measurement(25, dataHoraDaMedicao12);

        s1.addMeasurementToList(measurement11);
        s1.addMeasurementToList(measurement12);

        //Sensor2
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(26, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        Measurement expectedResult = measurement12;

        room1.addSensorToTheListOfSensorsInTheRoom(s0);
        room1.addSensorToTheListOfSensorsInTheRoom(s1);
        room1.addSensorToTheListOfSensorsInTheRoom(s2);

        RoomList roomList = new RoomList();
        roomList.addRoomToRoomList(room1);
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(roomList, gridList, address, insertedGeoArea);
        US605Controller ctrl = new US605Controller(house, sensorType0);

        //Act
        Measurement result = ctrl.getLatestMeasurementByRoomName("room1");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLatestTemperatureRoomNull() {
        //arrange
        Dimensions dimensions = new Dimensions(300, 600, 600);
        Room room1 = new Room("room1", 1, dimensions);

        //Sensor
        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidity");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);

        //Measurement
        Calendar calendarioDaMedicao21 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao21 = calendarioDaMedicao21.getTime();

        Calendar calendarioDaMedicao22 = new GregorianCalendar(1991, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao22 = calendarioDaMedicao22.getTime();

        Measurement measurement21 = new Measurement(20, dataHoraDaMedicao21);
        Measurement measurement22 = new Measurement(26, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        Measurement expectedResult = null;

        room1.addSensorToTheListOfSensorsInTheRoom(s2);

        SensorType sensorType0 = new SensorType("Temperature");
        RoomList roomList = new RoomList();
        roomList.addRoomToRoomList(room1);
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(roomList, gridList, address, insertedGeoArea);
        US605Controller ctrl = new US605Controller(house, sensorType0);

        //Act
        Measurement result = ctrl.getLatestMeasurementByRoomName("room1");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameOfTheChosenRoomInSpecificPos() {

        //Arrange
        RoomList rList = new RoomList();
        Dimensions dim0 = new Dimensions(3, 3.5, 3.5);
        Dimensions dim1 = new Dimensions(3, 3.5, 3.5);
        Room room0 = new Room("RoomOne", 2, dim0);
        Room room1 = new Room("RoomTwo", 2, dim1);

        rList.addRoomToRoomList(room0);
        rList.addRoomToRoomList(room1);

        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType0 = new SensorType("Temperature");
        US605Controller ctrl = new US605Controller(house, sensorType0);

        String expectedResult = "RoomTwo";
        int roomPos = 1;
        //Act
        String result = ctrl.getNameOfTheChosenRoomInSpecificPos(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        //Arrange
        RoomList rList = new RoomList();
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        House house = new House(rList, gridList, address, insertedGeoArea);
        SensorType sensorType0 = new SensorType("Temperature");
        US605Controller ctrl = new US605Controller(house, sensorType0);

        String expectedResult = null;
        int roomPos = 0;
        //Act
        String result = ctrl.getNameOfTheChosenRoomInSpecificPos(roomPos);
        //Assert
        assertEquals(expectedResult, result);
    }
}