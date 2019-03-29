package pt.ipp.isep.dei.project.controllersTests;

import org.junit.Test;
import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CreateHouseGridControllerTest {

    @Test
    public void createAGridAndAddItToTheListTest() {
        // Arrange
        //House Instantiation
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        CreateHouseGridController ctrl = new CreateHouseGridController(house);
        HouseGridDTO grid = HouseGridMapper.newHouseGridDTO();
        String gridName = "Grid";
        grid.setName(gridName);
        ctrl.createANewHouseGrid(grid);

        // Act
        boolean result = ctrl.getHouseGridList().contains(HouseGridMapper.mapToEntity(grid));

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
        HouseGridDTO grid = HouseGridMapper.newHouseGridDTO();
        grid.setName(gridName);

        // Act
        boolean result = ctrl.createANewHouseGrid(grid);

        //Assert
        assertTrue(result);
    }

}