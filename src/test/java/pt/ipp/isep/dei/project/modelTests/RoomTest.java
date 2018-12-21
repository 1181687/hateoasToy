package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {

    @Test
    public void getDisplayRoomTest() {
        //arrange
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(name1,houseFloor1,dimensions1);

        String expectResult = "Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0";

        //act
        String result = room.getRoomDisplay();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void testhashCode() {
        //Arrange
        Dimensions dim = new Dimensions(3.5,3.5,3.5);
        Room room = new Room("Room",2,dim);
        int expectedResult = 1;
        // Act
        int result = room.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsTrue() {
        //Arrange
        Dimensions dim = new Dimensions(3.5,3.5,3.5);
        Room room = new Room("Room",2,dim);
        Dimensions dim2 = new Dimensions(3.5,3.5,3.5);
        Room room2 = new Room("Room",2,dim2);
        //Act
        boolean result = room.equals(room2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsFalse() {
        //Arrange
        Dimensions dim = new Dimensions(3,3.5,3.5);
        Room room = new Room("Room",2,dim);
        Dimensions dim2 = new Dimensions(3.5,3.5,3.5);
        Room room2 = new Room("Room",2,dim2);
        //Act
        boolean result = room.equals(room2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsFalseDifTypes() {
        //Arrange
        Dimensions dim = new Dimensions(3.5, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        TipoSensor tipo = new TipoSensor("humidade");

        //Act
        boolean result = room.equals(tipo);
        //Assert
        assertFalse(result);
    }

    @Test
    void testAddRoomToRoomList(){
        //Arrange
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(3,3.5,3.5);
        Room room = new Room("RoomOne",2,dim);
        //Act
        boolean result = list.addRoomToRoomList(room);
        //assert
        assertTrue(result);
    }

    @Test
    void testAddRoomToRoomListFalse(){
        //Arrange
        RoomList list = new RoomList();
        Dimensions dim = new Dimensions(3,3.5,3.5);
        Room room = new Room("RoomOne",2,dim);
        list.addRoomToRoomList(room);
        //Act
        boolean result = list.addRoomToRoomList(room);
        //assert
        assertFalse(result);
    }

    @Test
    void testAddSensorToTheListOfSensorsInTheRoom () {
        // Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Location locS1 = new Location(123, 345, 50);

        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);

        Dimensions dim = new Dimensions(3,3.5,3.5);
        Room room = new Room("Room",2,dim);

        // Act
        boolean result = room.addSensorToTheListOfSensorsInTheRoom(s1);

        // Assert
        assertTrue(result);
    }

    @Test
    void getSensorList() {
        //Arrange
        SensorList sensorList = new SensorList();

        Calendar calendario0 = new GregorianCalendar(1991, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Temperatura");
        Location locS0 = new Location(123, 345, 50);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);

        Dimensions dim = new Dimensions(3,3.5,3.5);
        Room room = new Room("Room",2,dim);

        room.addSensorToTheListOfSensorsInTheRoom(s0);
        sensorList.addSensorToTheListOfSensors(s0);
        List<Sensor> expectedResult = sensorList.getmSensorList();
        //Act
        List<Sensor> result = room.getSensorList().getmSensorList();
        //Assert
        assertEquals(result, expectedResult);
    }
}
