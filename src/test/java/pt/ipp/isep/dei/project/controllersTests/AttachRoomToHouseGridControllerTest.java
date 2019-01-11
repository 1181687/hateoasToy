package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AttachRoomToHouseGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AttachRoomToHouseGridControllerTest {

    @Test
    public void checkIfHouseGridListIsEmptyPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);

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
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);

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
        gridList.getmHouseGridsList().add(grid0);
        gridList.getmHouseGridsList().add(grid1);
        RoomList roomList = new RoomList();
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
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
        listOfGrids.getmHouseGridsList().add(grid0);
        listOfGrids.getmHouseGridsList().add(grid1);
        RoomList listOfRooms = new RoomList();
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(listOfGrids, listOfRooms);
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
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
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
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(listOfGrids, roomList);
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
        roomList.addRoom(room);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
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
        roomList.addRoom(room);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        Ctrl.setmGridToBeUsed(grid);
        Ctrl.setmRoomToBeAttached(room);

        // Act
        boolean result = Ctrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertFalse(result);
    }

    @Test
    void houseGridListLengthTest() {
        // Arrange
        // Instantiate House Grids
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        int expectedResult = 1;

        // Act
        int result = ctrl.houseGridListLength();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimensions dimensions = new Dimensions(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimensions);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        // Act
        boolean result = ctrl.checkIfRoomListIsEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        // Arrange
        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        // Act
        boolean result = ctrl.checkIfRoomListIsEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    void roomListLengthTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimensions dimensions = new Dimensions(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimensions);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        int expectedResult = 1;

        // Act
        int result = ctrl.roomListLength();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTheGridWhereTheRoomIsConnectedTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimensions dimensions = new Dimensions(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimensions);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);
        grid2.attachRoomToTheRoomList(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);
        gridList.addHouseGridToTheList(grid1);
        gridList.addHouseGridToTheList(grid2);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        ctrl.setmRoomToBeAttached(room);

        HouseGrid expectedResult = grid2;

        // Act
        HouseGrid result = ctrl.getTheGridWhereTheRoomIsConnected();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTheGridWhereTheRoomIsConnectedNullTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimensions dimensions = new Dimensions(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimensions);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);
        gridList.addHouseGridToTheList(grid1);
        gridList.addHouseGridToTheList(grid2);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        ctrl.setmRoomToBeAttached(room);

        HouseGrid expectedResult = null;

        // Act
        HouseGrid result = ctrl.getTheGridWhereTheRoomIsConnected();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void detachRoomFromGridListTest() {
        //Arrange
        // Instantiate Rooms
        String roomName0 = "Kitchen";
        int houseFloor0 = 0;
        Dimensions dimensions0 = new Dimensions(4, 10, 12);
        Room room0 = new Room(roomName0, houseFloor0, dimensions0);
        String roomName1 = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(4, 10, 12);
        Room room1 = new Room(roomName1, houseFloor1, dimensions1);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // Instantiate House Grids
        String gridName0 = "Grid0";
        HouseGrid grid0 = new HouseGrid(gridName0, 20, roomList);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        ctrl.setmGridToBeUsed(grid0);
        ctrl.setmRoomToBeAttached(room0);
        ctrl.detachRoomFromTheHouseGrid(grid0);

        // Act
        boolean result = ctrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertFalse(result);
    }
}