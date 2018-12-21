package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US147Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class US147ControllerTest {

    @Test
    public void checkIfHouseGridListIsEmptyPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        US147Controller Ctrl = new US147Controller(gridList, roomList);

        // Act
        boolean result = Ctrl.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyNegativeTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);
        RoomList roomList = new RoomList();
        US147Controller Ctrl = new US147Controller(gridList, roomList);

        // Act
        boolean result = Ctrl.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void listAllTheHouseGridsInTheListTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        gridList.getmList().add(grid0);
        gridList.getmList().add(grid1);
        RoomList roomList = new RoomList();
        US147Controller Ctrl = new US147Controller(gridList, roomList);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        // Act
        String result = Ctrl.listAllTheHouseGridsInTheList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridFromTheListTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        HouseGridList listOfGrids = new HouseGridList();
        listOfGrids.getmList().add(grid0);
        listOfGrids.getmList().add(grid1);
        RoomList listOfRooms = new RoomList();
        US147Controller Ctrl = new US147Controller(listOfGrids, listOfRooms);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = Ctrl.getHouseGridFromTheList(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listAllTheRoomsInTheListTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);
        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);
        roomList.addRoomToRoomList(room1);
        roomList.addRoomToRoomList(room2);
        US147Controller Ctrl = new US147Controller(gridList, roomList);
        String expectedResult = "1- Name: Kitchen, House Floor: 0, Dimensions - Height: 2.0, Dimensions - Length: 2.0, Dimensions - Width: 2.0\n2- Name: Living Room, House Floor: 1, Dimensions - Height: 2.0, Dimensions - Length: 1.5, Dimensions - Width: 1.3\n";

        // Act
        String result = Ctrl.listAllTheRoomsInTheList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomFromTheListTest() {
        // Arrange
        HouseGridList listOfGrids = new HouseGridList();
        RoomList roomList = new RoomList();
        String name1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimensions1);
        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimensions dimensions2 = new Dimensions(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimensions2);
        roomList.addRoomToRoomList(room1);
        roomList.addRoomToRoomList(room2);
        US147Controller Ctrl = new US147Controller(listOfGrids, roomList);
        Room expectedResult = room1;

        // Act
        Room result = Ctrl.getRoomFromTheList(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfTheChosenRoomIsAlreadyInTheChosenGridPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = gridList.createAHouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);
        RoomList roomList = new RoomList();
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        roomList.addRoomToRoomList(room);
        US147Controller Ctrl = new US147Controller(gridList, roomList);
        Ctrl.setmGridToBeUsed(grid);
        Ctrl.setmRoomToBeAttached(room);
        Ctrl.attachRoomInTheHouseGrid();

        // Act
        boolean result = Ctrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfTheChosenRoomIsAlreadyInTheChosenGridNegativeTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = gridList.createAHouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);
        RoomList roomList = new RoomList();
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        roomList.addRoomToRoomList(room);
        US147Controller Ctrl = new US147Controller(gridList, roomList);
        Ctrl.setmGridToBeUsed(grid);
        Ctrl.setmRoomToBeAttached(room);

        // Act
        boolean result = Ctrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertFalse(result);
    }
}