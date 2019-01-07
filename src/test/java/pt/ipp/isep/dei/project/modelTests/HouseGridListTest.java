package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;

import static org.junit.jupiter.api.Assertions.*;

public class HouseGridListTest {

    @Test
    public void getAGridFromASpecificPosition() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        HouseGridList list = new HouseGridList();
        list.getmList().add(grid0);
        list.getmList().add(grid1);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = list.getHouseGridFromASpecificPositionInTheList(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        HouseGridList list = new HouseGridList();
        list.getmList().add(grid0);
        list.getmList().add(grid1);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        // Act
        String result = list.getContentOfTheHouseGridsInTheList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void detachRoomInTheHouseGridTest() {
        // Arrange
        String roomName = "Kitchen";
        String roomName1 = "Bedroom";
        int houseFloor = 0;
        int houseFloor1 = 0;
        Dimensions dimensions = new Dimensions(2, 2, 2);
        Dimensions dimensions1 = new Dimensions(2, 4, 4);
        Room room = new Room(roomName, houseFloor, dimensions);
        Room room1 = new Room(roomName1, houseFloor1, dimensions1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);
        gridList.attachRoomInASpecificHouseGridInTheList(grid, room);
        gridList.attachRoomInASpecificHouseGridInTheList(grid, room1);
        int gridPosition = gridList.getmList().indexOf(grid);
        gridList.detachRoomInASpecificHouseGridInTheList(grid, room1);

        // Act
        boolean result = gridList.getHouseGridFromASpecificPositionInTheList(gridPosition).checkIfARoomIsAlreadyInTheGrid(room1);

        // Assert
        assertFalse(result);
    }

    @Test
    public void attachRoomInTheHouseGridTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);
        gridList.attachRoomInASpecificHouseGridInTheList(grid, room);
        int gridPosition = gridList.getmList().indexOf(grid);

        // Act
        boolean result = gridList.getHouseGridFromASpecificPositionInTheList(gridPosition).checkIfARoomIsAlreadyInTheGrid(room);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();

        // Act
        boolean result = gridList.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid);

        // Act
        boolean result = gridList.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfARoomIsAlreadyInAHouseGridOfTheListWithNegativeTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = gridList.createAHouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);

        // Act
        boolean result = gridList.checkIfARoomIsAlreadyInAHouseGrid(grid, room);

        // Assert
        assertFalse(result);
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
        grid2.attachRoomInTheHouseGridRoomList(room);

        // Instantiate List of House Grids
        HouseGridList gridList = new HouseGridList();
        gridList.addHouseGridToTheList(grid0);
        gridList.addHouseGridToTheList(grid1);
        gridList.addHouseGridToTheList(grid2);

        HouseGrid expectedResult = grid2;

        // Act
        HouseGrid result = gridList.getTheGridWhereTheRoomIsConnected(room);

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

        HouseGrid expectedResult = null;

        // Act
        HouseGrid result = gridList.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }
}
