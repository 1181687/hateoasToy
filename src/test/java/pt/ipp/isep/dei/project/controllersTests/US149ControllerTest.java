package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US149Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class US149ControllerTest {

    @Test
    void testsGetListContentMethod() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        String expectedResult = "1 - Name: Main Grid" + "\n";
        //Act
        String result = ctrl.getListOfHouseGridsAttachedToHouseGrid();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void testsGetListContentMethodMoreThanOneGrid() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        String expectedResult = "1 - Name: Main Grid" + "\n" + "2 - Name: Secondary Grid" + "\n";
        //Act
        String result = ctrl.getListOfHouseGridsAttachedToHouseGrid();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getHouseGridFromTheList() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid0);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        HouseGrid expectedResult = newGrid0;
        //Act
        HouseGrid result = ctrl.getHouseGridFromTheList(0);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getListOfRooms() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid0);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimensions - Height: 2.0, Dimensions - Length: 3.0, Dimensions - Width: 4.0\n" +
                "2- Name: Living Room, House Floor: 2, Dimensions - Height: 2.0, Dimensions - Length: 3.0, Dimensions - Width: 3.0" + "\n";
        //Act
        String result = ctrl.getListOfRooms();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getRoomFromTheListOfRoomByAPosition() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid0);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        Room expectedResult = r1;
        //Act
        Room result = ctrl.getRoomFromTheListOfRoomByAPosition(1);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void detachRoomFromGridList() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid0);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        ctrl.detachRoomFromGridList(newGrid0, r1);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimensions - Height: 2.0, Dimensions - Length: 3.0, Dimensions - Width: 4.0\n";
        //Act
        String result = ctrl.getListOfRoomsInACertainHouseGrid(0);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void detachRoomFromGridListRoomNotInTheListRemainsTheSame() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        Room r2 = new Room("Bathroom", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        listOfRooms1.addRoom(r2);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid0);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        ctrl.detachRoomFromGridList(newGrid0, r2);
        String expectedResult = "1- Name: Bedroom, House Floor: 3, Dimensions - Height: 2.0, Dimensions - Length: 3.0, Dimensions - Width: 4.0\n" +
                "2- Name: Living Room, House Floor: 2, Dimensions - Height: 2.0, Dimensions - Length: 3.0, Dimensions - Width: 3.0" + "\n";
        //Act
        String result = ctrl.getListOfRoomsInACertainHouseGrid(0);
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void getNumberOfGridLists() {
        //Arrange
        Dimensions r0Dimensions = new Dimensions(2, 3, 4);
        Dimensions r1Dimensions = new Dimensions(2, 3, 3);
        Room r0 = new Room("Bedroom", 3, r0Dimensions);
        Room r1 = new Room("Living Room", 2, r1Dimensions);
        Room r2 = new Room("Bathroom", 2, r1Dimensions);
        RoomList listOfRooms = new RoomList();
        RoomList listOfRooms1 = new RoomList();
        listOfRooms.addRoom(r0);
        listOfRooms.addRoom(r1);
        listOfRooms1.addRoom(r2);
        HouseGrid newGrid0 = new HouseGrid("Main Grid", 20, listOfRooms);
        HouseGrid newGrid1 = new HouseGrid("Secondary Grid", 20, listOfRooms1);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(newGrid0);
        houseGridList.getmList().add(newGrid1);
        US149Controller ctrl = new US149Controller(houseGridList, listOfRooms);
        int expectedResult = 2;
        //Act
        int result = ctrl.getNumberOfGridLists();
        //Assert
        assertEquals(result, expectedResult);
    }
}