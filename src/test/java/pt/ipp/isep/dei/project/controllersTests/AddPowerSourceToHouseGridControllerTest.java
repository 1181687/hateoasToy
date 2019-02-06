package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSourceType;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;

import static org.junit.jupiter.api.Assertions.*;

class AddPowerSourceToHouseGridControllerTest {
    private AddPowerSourceToHouseGridController controller;
    private HouseGridList houseGridList;
    private HouseGrid mainGrid;
    private HouseGrid secondaryGrid;
    private PowerSourceType publicElectricGrid;

    @BeforeEach
    public void StartUp() {
        // List of House Grids
        houseGridList = new HouseGridList();

        // House Grids
        mainGrid = new HouseGrid("Main grid");
        secondaryGrid = new HouseGrid("Secondary grid");

        // List of Power Source Types
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();

        // Power Source Type
        publicElectricGrid = new PowerSourceType("Public electric grid");
        powerSourceTypeList.addPowerSourceType(publicElectricGrid);

        // Controller
        controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
    }

    @Test
    public void isHouseGridListEmptyPositiveTest() {
        // Act
        boolean result = controller.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void isHouseGridListEmptyNegativeTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);

        // Act
        boolean result = controller.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getHouseGridListToStringTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);
        String expectedResult = "1 - Name: Main grid\n";

        // Act
        String result = controller.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListSizeTest() {
        // Arrange
        int expectedResult = 0;

        // Act
        int result = controller.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getPowerSourceTypeListSizeTest() {
        // Arrange
        int expectedResult = 1;

        // Act
        int result = controller.getPowerSourceTypeListSize();

        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void createAndAddPowerSourceToHouseGridPositiveTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);
        controller.getPowerSourceTypeByPosition(0);

        // Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid("Electric power source");

        // Assert
        assertTrue(result);
    }

    @Test
    public void createAndAddPowerSourceToHouseGridTestWithVariousGrid() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);
        houseGridList.addHouseGrid(secondaryGrid);
        controller.getHouseGridFromListByPosition(1);
        controller.getPowerSourceTypeByPosition(0);

        // Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid("Electric power source");

        // Assert
        assertTrue(result);
    }

    @Test
    public void createAndAddPowerSourceToHouseGridNegativeTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);
        controller.getPowerSourceTypeByPosition(0);
        controller.createAndAddPowerSourceToHouseGrid("Electric power source");

        // Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid("Electric power source");

        //Assert
        assertFalse(result);
    }

    @Test
    public void getPowerSourceTypeListToStringTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);

        String expectedResult = "1 - Power Source Type: Public electric grid\n";

        // Act
        String result = controller.getPowerSourceTypeListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listPowerSourcesConnectedToHouseGridTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);
        controller.getPowerSourceTypeByPosition(0);
        controller.createAndAddPowerSourceToHouseGrid("Non-electric power source");

        String expectedResult = "1- Non-electric power source\n";

        // Act
        String result = controller.listPowerSourcesConnectedToHouseGrid();

        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridNameTest() {
        // Arrange
        houseGridList.addHouseGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);

        String expectedResult = "Main grid";

        // Act
        String result = controller.getHouseGridName();

        // Assert
        assertEquals(expectedResult,result);
    }

}