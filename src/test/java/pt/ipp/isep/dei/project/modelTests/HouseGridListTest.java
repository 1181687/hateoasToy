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
        String result = list.displayOfTheContentOfTheHouseGridsInTheList();

        // Assert
        assertEquals(expectedResult, result);
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
        boolean result = gridList.checkIfARoomIsAlreadyInAHouseGridOfTheList(grid, room);

        // Assert
        assertFalse(result);
    }
}
