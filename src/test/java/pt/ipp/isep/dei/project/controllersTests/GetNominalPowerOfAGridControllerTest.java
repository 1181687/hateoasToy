package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GetNominalPowerOfAGridControllerTest {

    private GetNominalPowerOfAGridController controller;
    private House house;
    @BeforeEach
    public void StartUp(){
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);


        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);

        this.controller = new GetNominalPowerOfAGridController(house);


    }

    @Test
    public void checkIfGridListIsEmptyWithEmptyHouseGridListShouldReturnTrue(){
        //Act
        boolean result = controller.isGridListEmpty();
        //Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGridListIsEmptyWhenHouseGridListIsNotEmptyShouldReturnFalse(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);
        //Act
        boolean result = controller.isGridListEmpty();
        //Assert
        assertFalse(result);
    }

    @Test
    public void listHouseGridsTestWithOneHouseGridShouldShowListWithOneGrid(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);
        String expectedResult = "1 - Name: Grid 1\n";
        //Act
        String result = controller.listHouseGrids();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridListLengthWhenHouseGridListHasTwoGridsReturnsTwo(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        house.addGrid(grid1);
        house.addGrid(grid2);

        int expectedResult = 2;
        //Act
        int result = controller.getHouseGridListSize();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridListLengthWhenHouseGridListHasOneGridShouldReturnOne(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);

        int expectedResult = 1;
        //Act
        int result = controller.getHouseGridListSize();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridListLengthWhenHouseGridHasNoGridsShouldReturnZero(){
        //Arrange
        int expectedResult = 0;
        //Act
        int result = controller.getHouseGridListSize();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridTotalNominalPower_CalculatesTotalNominalPowerOfHGWithTwoDevices_ShouldReturn15(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);

        Dimension dimension = new Dimension(2, 5, 10);
        Room room1 = new Room("Quarto", 1, dimension);
        DeviceSpecs specs = new FridgeSpecs(25, 50, 5000, 7.5);
        Device1 fridge1 = new Device1("FridgeA", room1, specs);
        Device1 fridge2 = new Device1("FridgeB", room1, specs);

        grid1.attachRoom(room1);

        controller.getHouseGridByPosition(0);

        double expectedResult = 15;
        //Act
        double result = controller.getHouseGridTotalNominalPower();

        //Assert
        assertEquals(expectedResult,result,0.00001);
    }
}
