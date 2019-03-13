package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
/*
class CreateHouseGridControllerTest {
@Test
    public void createAGridAndAddItToTheListTest() {
        // Arrange
        //House Instantiation
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        House housegrid = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        CreateHouseGridController ctrl = new CreateHouseGridController(housegrid);
        String gridName = "Grid";
        housegrid grid = ctrl.createANewHouseGrid(gridName);
        ctrl.addHouseGridToTheListOfHouseGrids(grid);

        // Act
        boolean result = ctrl.getHouseGridList().contains(grid);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCreateANewHouseGrid() {
        // Arrange
        //House Instantiation
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        House housegrid = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        CreateHouseGridController ctrl = new CreateHouseGridController(housegrid);
        String gridName = "Grid";
        housegrid grid = ctrl.createANewHouseGrid(gridName);
        ctrl.addHouseGridToTheListOfHouseGrids(grid);
        List<housegrid> expectedResult = new ArrayList<>();
        expectedResult.add(grid);

        // Act
        List<housegrid> result = ctrl.getHouseGridList();

        //Assert
        assertEquals(expectedResult, result);
    }
  }
  */