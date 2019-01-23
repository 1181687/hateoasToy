package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateHouseGridControllerTest {

    @Test
    public void createAGridAndAddItToTheListTest() {
        // Arrange
        HouseGridList gridList = new HouseGridList();
        CreateHouseGridController ctrl = new CreateHouseGridController(gridList);
        String gridName = "Grid";
        HouseGrid grid = ctrl.createANewHouseGrid(gridName);
        ctrl.addHouseGridToTheListOfHouseGrids(grid);

        // Act
        boolean result = ctrl.getHouseGridList().getmHouseGridsList().contains(grid);

        // Assert
        assertTrue(result);
    }
}