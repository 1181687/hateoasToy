package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.TipoSensor;

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
}
