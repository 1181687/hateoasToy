package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid.HouseGrid;
import pt.ipp.isep.dei.project.model.PowerSource.PowerSourceType;
import pt.ipp.isep.dei.project.model.PowerSource.PowerSourceTypeList;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddPowerSourceToHouseGridControllerTest {
    private AddPowerSourceToHouseGridController controller;
    private HouseGrid mainGrid;
    private HouseGrid secondaryGrid;
    private PowerSourceType publicElectricGrid;
    private House house;

    @BeforeEach
    public void StartUp() {

        // House Grids
        mainGrid = new HouseGrid("Main grid");
        secondaryGrid = new HouseGrid("Secondary grid");

        // List of Power Source Types
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();

        // Power Source Type
        publicElectricGrid = new PowerSourceType("Public electric grid");
        powerSourceTypeList.addPowerSourceType(publicElectricGrid);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);


        // Controller
        controller = new AddPowerSourceToHouseGridController(house, powerSourceTypeList);
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
        house.addGrid(mainGrid);

        // Act
        boolean result = controller.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getHouseGridListToStringTest() {
        // Arrange
        house.addGrid(mainGrid);
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
        house.addGrid(mainGrid);
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
        house.addGrid(mainGrid);
        house.addGrid(secondaryGrid);
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
        house.addGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);
        controller.getPowerSourceTypeByPosition(0);
        controller.createAndAddPowerSourceToHouseGrid("Electric power source");

        // Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                controller.createAndAddPowerSourceToHouseGrid("Electric power source")
        );

        assertEquals("Name already exists. Please, write a new one.", exception.getMessage());
    }


    @Test
    public void getPowerSourceTypeListToStringTest() {
        // Arrange
        house.addGrid(mainGrid);

        String expectedResult = "1 - Power Source Type: Public electric grid\n";

        // Act
        String result = controller.getPowerSourceTypeListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listPowerSourcesConnectedToHouseGridTest() {
        // Arrange
        house.addGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);
        controller.getPowerSourceTypeByPosition(0);
        controller.createAndAddPowerSourceToHouseGrid("Non-electric power source");

        String expectedResult = "1- Non-electric power source\n";

        // Act
        String result = controller.listPowerSourcesConnectedToGrid();

        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridNameTest() {
        // Arrange
        house.addGrid(mainGrid);
        controller.getHouseGridFromListByPosition(0);

        String expectedResult = "Main grid";

        // Act
        String result = controller.getHouseGridName();

        // Assert
        assertEquals(expectedResult,result);
    }

}