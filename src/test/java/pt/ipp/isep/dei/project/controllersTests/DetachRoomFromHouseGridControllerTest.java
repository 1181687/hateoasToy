package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.DetachRoomFromHouseGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class DetachRoomFromHouseGridControllerTest {

    @Test
    void testsGetListContentMethod() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        String expectedResult = "1 - Name: Main Grid" + "\n";
        //Act
        String result = ctrl.getListOfHouseGridsAttachedToHouseGrid();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void testsGetListContentMethodMoreThanOneGrid() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        String expectedResult = "1 - Name: Main Grid" + "\n" + "2 - Name: Secondary Grid" + "\n";
        //Act
        String result = ctrl.getListOfHouseGridsAttachedToHouseGrid();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getHouseGridFromTheList() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        HouseGrid expectedResult = newGrid0;
        //Act
        HouseGrid result = ctrl.getHouseGridFromTheList(0);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getListOfRooms() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimension - Height: 2.0, Length: 3.0, Width: 4.0\n" +
                "2- Name: Living Room, House Floor: 2, Dimension - Height: 2.0, Length: 3.0, Width: 3.0\n";
        //Act
        String result = ctrl.getListOfRooms();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomFromTheListOfRoomByAPosition() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        Room expectedResult = r1;
        //Act
        Room result = ctrl.getRoomFromTheListOfRoomByAPosition(1);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void detachRoomFromGridList() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        ctrl.detachRoomFromGridList(newGrid0, r1);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimension - Height: 2.0, Length: 3.0, Width: 4.0\n";

        String result = ctrl.getListOfRoomsInACertainHouseGrid(0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void detachRoomFromGridListRoomNotInTheListRemainsTheSame() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        Room r2 = new Room("Bathroom", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        listOfRooms1.addRoom(r2);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        ctrl.detachRoomFromGridList(newGrid0, r2);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimension - Height: 2.0, Length: 3.0, Width: 4.0\n" +
                "2- Name: Living Room, House Floor: 2, Dimension - Height: 2.0, Length: 3.0, Width: 3.0\n";

        //Act
        String result = ctrl.getListOfRoomsInACertainHouseGrid(0);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void detachRoomFromGridListRoomNotInTheListRemainsTheSameBooleanMethod() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        Room r2 = new Room("Bathroom", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        listOfRooms1.addRoom(r2);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);

        //Act
        boolean result = ctrl.detachRoomFromGridList(newGrid0, r2);
        //Assert
        assertFalse(result);
    }

    @Test
    void detachRoomFromGridListBooleanMethod() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);

        //Act
        boolean result = ctrl.detachRoomFromGridList(newGrid0, r1);
        //Assert
        assertTrue(result);
    }

    @Test
    void getNumberOfGridLists() {
        //Arrange
        Dimension r0Dimension = new Dimension(2, 3, 4);
        Dimension r1Dimension = new Dimension(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimension);
        Room r1 = new Room("Living Room", 2, r1Dimension);
        Room r2 = new Room("Bathroom", 2, r1Dimension);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        listOfRooms1.addRoom(r2);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(newGrid0);
        houseGridList.getmHouseGridsList().add(newGrid1);
        DetachRoomFromHouseGridController ctrl = new DetachRoomFromHouseGridController(houseGridList, listOfRooms);
        int expectedResult = 2;
        //Act
        int result = ctrl.getNumberOfGridLists();
        //Assert
        assertEquals(result, expectedResult);
    }
}