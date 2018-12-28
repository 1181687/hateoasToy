package pt.ipp.isep.dei.project.controllers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class US253ControllerTest {

    @Test
    void testDisplayRoomsInTheHouse() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room (name1,houseFloor1,dimensions1, sensorList);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2,1.5,1.3);
        Room room2 = new Room (name2,houseFloor2,dimensions2, sensorList);

        roomList.addRoomToRoomList(room1);
        roomList.addRoomToRoomList(room2);

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(roomList, gridlist, adr);

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0\n2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Dimensions - Length: 1.5, Dimensions - Width: 1.3\n";

        //act
        String result = us253Controller.displayRoomsInTheHouse();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void displayListOfSensorsType() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room (name1,houseFloor1,dimensions1, sensorList);

        roomList.addRoomToRoomList(room1);

        // Type of sensor
        TipoSensor tipoSensor = new TipoSensor("Temperatura");

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(roomList, gridlist, adr);

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();
        listSensorsType.adicionarTipoSensorALista(tipoSensor);

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        String expectedResult = "1 - Sensor Type: Temperatura\n";

        // Act
        String result = us253Controller.displayListOfSensorsType();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void createAndAddSensorToTheList() {
        // Arrange

        // Sensor
        TipoSensor sensorType = new TipoSensor("Temperatura");
        String sensorName = "A123";

        // Sensors List
        SensorList sensorList = new SensorList();

        // Room
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room (name1,houseFloor1,dimensions1, sensorList);

        // Room list
        RoomList roomList = new RoomList();
        roomList.addRoomToRoomList(room1);

        // House
        HouseGridList houseGridList = new HouseGridList();
        Location location = new Location(10,10,10);
        Address address = new Address("5000", location);
        House house = new House(roomList, houseGridList, address);

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();
        listSensorsType.adicionarTipoSensorALista(sensorType);

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        us253Controller.getRoomByIndex(0);
        us253Controller.getSensorTypeByIndex(0);
        us253Controller.getLocationOfTheHouse();

        // Act
        boolean result = us253Controller.createAndAddSensorToTheList(sensorName);

        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfRoomListIsEmptyPositive() {
        // Arrange
        RoomList roomList = new RoomList();

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(roomList, gridlist, adr);

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        // Act
        boolean result = us253Controller.checkIfRoomListIsEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfRoomListIsEmptyNegative() {
        // Arrange
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList();

        // Room
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room (name1,houseFloor1,dimensions1, sensorList);

        roomList.addRoomToRoomList(room1);

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(roomList, gridlist, adr);

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        // Act
        boolean result = us253Controller.checkIfRoomListIsEmpty();
        // Assert
        assertFalse(result);
    }

    @Test
    void checkIfTheListOfSensorTypeIsEmptyPositive() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room (name1,houseFloor1,dimensions1, sensorList);

        roomList.addRoomToRoomList(room1);

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(roomList, gridlist, adr);

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        // Act
        boolean result = us253Controller.checkIfTheListOfSensorTypeIsEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfTheListOfSensorTypeIsEmptyNegative() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room (name1,houseFloor1,dimensions1, sensorList);

        roomList.addRoomToRoomList(room1);

        // House
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(roomList, gridlist, adr);

        // Tipo de sensor
        TipoSensor sensorType = new TipoSensor("Temperatura");

        // Sensors Type List
        ListaTiposSensores listSensorsType = new ListaTiposSensores();
        listSensorsType.adicionarTipoSensorALista(sensorType);

        US253Controller us253Controller = new US253Controller(listSensorsType, roomList, house);

        // Act
        boolean result = us253Controller.checkIfTheListOfSensorTypeIsEmpty();
        // Assert
        assertFalse(result);
    }
}