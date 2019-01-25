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
        boolean result = Ctrl.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyNegativeTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        gridList.addHouseGrid(grid);
        RoomList roomList = new RoomList();
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        // Act
        boolean result = Ctrl.isHouseGridListEmpty();

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
        String result = Ctrl.getHouseGridListToString();

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
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);
        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        String expectedResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        // Act
        String result = Ctrl.getRoomListContent();

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
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);
        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);
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
        HouseGrid grid = gridList.newHouseGrid(gridName);
        gridList.addHouseGrid(grid);
        RoomList roomList = new RoomList();
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        roomList.addRoom(room);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        Ctrl.setGridToBeUsed(grid);
        Ctrl.setRoomToBeAttached(room);
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
        HouseGrid grid = gridList.newHouseGrid(gridName);
        gridList.addHouseGrid(grid);
        RoomList roomList = new RoomList();
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        roomList.addRoom(room);
        AttachRoomToHouseGridController Ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        Ctrl.setGridToBeUsed(grid);
        Ctrl.setRoomToBeAttached(room);

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
        gridList.addHouseGrid(grid);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        int expectedResult = 1;

        // Act
        int result = ctrl.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimension);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        // Act
        boolean result = ctrl.isRoomListEmpty();

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
        boolean result = ctrl.isRoomListEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    void roomListLengthTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimension);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();
        roomList.addRoom(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);

        int expectedResult = 1;

        // Act
        int result = ctrl.getRoomListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTheGridWhereTheRoomIsConnectedTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimension);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);
        grid2.attachRoom(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGrid(grid0);
        gridList.addHouseGrid(grid1);
        gridList.addHouseGrid(grid2);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        ctrl.setRoomToBeAttached(room);

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
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, houseFloor, dimension);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGrid(grid0);
        gridList.addHouseGrid(grid1);
        gridList.addHouseGrid(grid2);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        ctrl.setRoomToBeAttached(room);

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
        Dimension dimension0 = new Dimension(4, 10, 12);
        Room room0 = new Room(roomName0, houseFloor0, dimension0);
        String roomName1 = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(4, 10, 12);
        Room room1 = new Room(roomName1, houseFloor1, dimension1);

        // Instantiate List of Rooms
        RoomList roomList = new RoomList();
        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // Instantiate House Grids
        String gridName0 = "Grid0";
        HouseGrid grid0 = new HouseGrid(gridName0, 20, roomList);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGrid(grid0);

        // Instantiate Controller
        AttachRoomToHouseGridController ctrl = new AttachRoomToHouseGridController(gridList, roomList);
        ctrl.setGridToBeUsed(grid0);
        ctrl.setRoomToBeAttached(room0);
        ctrl.detachRoomFromTheHouseGrid(grid0);

        // Act
        boolean result = ctrl.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertFalse(result);
    }
}