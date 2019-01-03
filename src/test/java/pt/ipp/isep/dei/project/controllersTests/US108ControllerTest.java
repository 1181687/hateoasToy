package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US108Controller;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import static org.junit.jupiter.api.Assertions.*;

class US108ControllerTest {

    @Test
    public void getDisplayRoomListTest(){
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2,1.5,1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);

        rList.addRoomToRoomList(room1);
        rList.addRoomToRoomList(room2);

        US108Controller ctrl = new US108Controller(rList);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0\n2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Dimensions - Length: 1.5, Dimensions - Width: 1.3\n";

        //act
        String result = ctrl.displayOfTheRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest(){
        //arrange
        RoomList rList = new RoomList();

        String expectResult = "";

        //act
        String result = rList.getDisplayRoomList();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue(){
        //arrange
        RoomList rList = new RoomList();

        US108Controller ctrl = new US108Controller(rList);
        //act
        boolean result = ctrl.checkIfListIsEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse(){
        //arrange
        RoomList rList = new RoomList();

        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2,2,2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);

        rList.addRoomToRoomList(room1);

        US108Controller ctrl = new US108Controller(rList);

        //act
        boolean result = ctrl.checkIfListIsEmpty();
        //assert
        assertFalse(result);
    }

}