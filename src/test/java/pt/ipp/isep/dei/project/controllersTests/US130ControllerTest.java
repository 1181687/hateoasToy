package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US130Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class US130ControllerTest {

    @Test
    public void createAGridAndAddItToTheListTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        US130Controller ctrl = new US130Controller(gridList);
        String gridName = "Grid";
        HouseGrid grid = ctrl.createANewHouseGrid(gridName);
        ctrl.addHouseGridToTheListOfHouseGrids(grid);

        // Act
        boolean result = ctrl.getmHouseGridList().getmHouseGridsList().contains(grid);

        // Assert
        assertTrue(result);
    }
}