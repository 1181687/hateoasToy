package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AttachRoomToHouseGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AttachRoomToHouseGridControllerTest {

    private AttachRoomToHouseGridController controller;

    private HouseGridList houseGridList;
    private RoomList roomList;

    @BeforeEach
    public void StartUp() {
        this.houseGridList = new HouseGridList();
        this.roomList = new RoomList();

        this.controller = new AttachRoomToHouseGridController(houseGridList, roomList);
    }


    @Test
    public void checkIfHouseGridListIsEmptyPositiveTest() {
        // Arrange


        // Act
        boolean result = controller.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyNegativeTest() {
        // Arrange

        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        houseGridList.addHouseGrid(grid);

        // Act
        boolean result = controller.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void listAllTheHouseGridsInTheListTest() {
        // Arrange

        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);

        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        // Act
        String result = controller.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridFromTheListTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        houseGridList.getmHouseGridsList().add(grid0);
        houseGridList.getmHouseGridsList().add(grid1);

        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = controller.getHouseGridFromTheList(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listAllTheRoomsInTheListTest() {
        // Arrange

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

        String expectedResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";
        // Act
        String result = controller.getRoomListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomFromTheListTest() {
        // Arrange

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
        Room expectedResult = room1;

        // Act
        Room result = controller.getRoomFromTheList(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfTheChosenRoomIsAlreadyInTheChosenGridPositiveTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid = houseGridList.newHouseGrid(gridName);
        houseGridList.addHouseGrid(grid);
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        roomList.addRoom(room);

        controller.setGridToBeUsed(grid);
        controller.setRoomToBeAttached(room);
        controller.attachRoomInTheHouseGrid();

        // Act
        boolean result = controller.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertTrue(result);
    }

    @Test
    void checkIfTheChosenRoomIsAlreadyInTheChosenGridNegativeTest() {
        // Arrange

        String gridName = "Grid";
        HouseGrid grid = houseGridList.newHouseGrid(gridName);
        houseGridList.addHouseGrid(grid);

        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        roomList.addRoom(room);

        controller.setGridToBeUsed(grid);
        controller.setRoomToBeAttached(room);

        // Act
        boolean result = controller.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertFalse(result);
    }

    @Test
    void houseGridListLengthTest() {
        // Arrange
        // Instantiate House Grids
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);

        houseGridList.addHouseGrid(grid);

        int expectedResult = 1;

        // Act
        int result = controller.getHouseGridListSize();

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

        roomList.addRoom(room);

        // Act
        boolean result = controller.isRoomListEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        // Act
        boolean result = controller.isRoomListEmpty();

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
        roomList.addRoom(room);

        int expectedResult = 1;

        // Act
        int result = controller.getRoomListSize();

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
        houseGridList.addHouseGrid(grid0);
        houseGridList.addHouseGrid(grid1);
        houseGridList.addHouseGrid(grid2);

        controller.setRoomToBeAttached(room);

        HouseGrid expectedResult = grid2;

        // Act
        HouseGrid result = controller.getTheGridWhereTheRoomIsConnected();

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

        houseGridList.addHouseGrid(grid0);
        houseGridList.addHouseGrid(grid1);
        houseGridList.addHouseGrid(grid2);

        controller.setRoomToBeAttached(room);

        HouseGrid expectedResult = null;

        // Act
        HouseGrid result = controller.getTheGridWhereTheRoomIsConnected();

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

        roomList.addRoom(room0);
        roomList.addRoom(room1);

        // Instantiate House Grids
        String gridName0 = "Grid0";
        HouseGrid grid0 = new HouseGrid(gridName0);

        houseGridList.addHouseGrid(grid0);

        // Instantiate Controller
        controller.setGridToBeUsed(grid0);
        controller.setRoomToBeAttached(room0);
        controller.detachRoomFromTheHouseGrid(grid0);

        // Act
        boolean result = controller.checkIfTheChosenRoomIsAlreadyInTheChosenGrid();

        // Assert
        assertFalse(result);
    }
}