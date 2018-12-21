package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US147Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.RoomList;

import static org.junit.jupiter.api.Assertions.*;

class US147ControllerTest {

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        RoomList roomList = new RoomList();
        US147Controller ctrl = new US147Controller(gridList, roomList);

        // Act
        boolean result = ctrl.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        gridList.addHouseGridToTheList(grid);
        RoomList roomList = new RoomList();
        US147Controller ctrl = new US147Controller(gridList, roomList);

        // Act
        boolean result = ctrl.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void listAllOfTheContentOfTheHouseGrids() {
        //
        HouseGridList gridList = new HouseGridList();
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        gridList.getmList().add(grid0);
        gridList.getmList().add(grid1);
        RoomList roomList = new RoomList();
        US147Controller ctrl = new US147Controller(gridList, roomList);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid\n";

        // Act
        String result = ctrl.listAllTheHouseGridsInTheList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridFromTheList() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        HouseGridList listOfGrids = new HouseGridList();
        listOfGrids.getmList().add(grid0);
        listOfGrids.getmList().add(grid1);
        RoomList listOfRooms = new RoomList();
        US147Controller ctrl = new US147Controller(listOfGrids, listOfRooms);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = ctrl.getHouseGridFromTheList(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void listAllTheRoomsInTheList() {
    }

    @Test
    void getRoomFromTheList() {
    }

    @Test
    void checkIfTheChosenRoomIsntAlreadyInTheChosenGrid() {
    }

    @Test
    void attachRoomInTheHouseGrid() {
    }
}