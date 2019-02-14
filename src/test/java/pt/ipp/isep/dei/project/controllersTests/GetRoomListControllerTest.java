package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetRoomListController;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import static org.junit.jupiter.api.Assertions.*;

class GetRoomListControllerTest {

    @Test
    public void getDisplayRoomListTest(){
        //arrange
        RoomList rList = new RoomList();
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

        rList.addRoom(room1);
        rList.addRoom(room2);

        GetRoomListController ctrl = new GetRoomListController(rList);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = ctrl.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest(){
        //arrange
        RoomList rList = new RoomList();
        String expectResult = "";

        //act
        String result = rList.getRoomListContent();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue(){
        //arrange
        RoomList rList = new RoomList();
        GetRoomListController ctrl = new GetRoomListController(rList);

        //act
        boolean result = ctrl.isEmpty();

        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse(){
        //arrange
        RoomList rList = new RoomList();
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        rList.addRoom(room1);

        GetRoomListController ctrl = new GetRoomListController(rList);

        //act
        boolean result = ctrl.isEmpty();

        //assert
        assertFalse(result);
    }

}