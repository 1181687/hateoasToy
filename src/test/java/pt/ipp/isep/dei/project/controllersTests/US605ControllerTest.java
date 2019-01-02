package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US605Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class US605ControllerTest {

    @Test
    void testarNovoTipoSensor() {
        //Arrange
        SensorTypeList list = new SensorTypeList();
        SensorType newSensorType = new SensorType("Humidity");
        //Act
        boolean result = list.adicionarTipoSensorALista(newSensorType);
        //Assert
        assertTrue(result);
    }

    @Test
    void testNewTypeSensorDuplicated() {
        //Arrange
        SensorTypeList list = new SensorTypeList();
        SensorType newSensorType = new SensorType("Humidity");
        list.adicionarTipoSensorALista(newSensorType);
        //Act
        boolean result = list.adicionarTipoSensorALista(newSensorType);
        //Assert
        assertFalse(result);
    }

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
    public void getDisplayRoomListEmptyTest() {
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






    /*
    @Test
    public void testarUltimoRegistoDeUmaListaDeTiposDeSensores() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        GeoAreaType tipo = new GeoAreaType("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local1);
        GeographicalArea ag1 = new GeographicalArea(nomeAG, tipo, local1, area);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        SensorType sensorType0 = new SensorType("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, sensorType0, locS0);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(1991, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        SensorType sensorType1 = new SensorType("Temperatura");
        Location locS1 = new Location(123, 355, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, sensorType1, locS1);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar calendario2 = new GregorianCalendar(1991, 11, 11, 15, 20, 00);
        Date dataFuncionamento2 = calendario2.getTime();
        SensorType sensorType2 = new SensorType("Humidade");
        Location locS2 = new Location(123, 345, 55);
        Sensor s2 = new Sensor("A123", dataFuncionamento2, sensorType2, locS2);
        ag1.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s2);

        //Instanciar Measurement
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
        Measurement measurement22 = new Measurement(25, dataHoraDaMedicao22);

        s2.addMeasurementToList(measurement21);
        s2.addMeasurementToList(measurement22);

        double expectedResult = 25;

        SensorType tipoResultado = new SensorType("Temperatura");
        SensorList listSens = new SensorList();
        listSens.addSensorToTheListOfSensors(s0);
        listSens.addSensorToTheListOfSensors(s1);
        listSens.addSensorToTheListOfSensors(s2);

        Dimensions dimensions = new Dimensions(300, 600, 600);
        Room room = new Room("room1", 1, dimensions);
        RoomList roomList = new RoomList();
        roomList.addRoomToRoomList(room);
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        GeoAreaType geoAreaType = new GeoAreaType("City");
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);
        Address address = new Address("0000", location);
        room.getSensorList().addSensorToTheListOfSensors(s0);
        room.getSensorList().addSensorToTheListOfSensors(s1);
        room.getSensorList().addSensorToTheListOfSensors(s2);
        House house = new House(roomList, gridList, address, insertedGeoArea);
        US605Controller ctrl = new US605Controller(house, sensorType1);

        //Act
        Measurement result = listSens.getLatestMeasurementBySensorType(tipoResultado).getmValue();

        //Assert
        assertEquals(expectedResult, result);
    }
    */
}