package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionOfAGridController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetEnergyConsumptionOfAGridControllerTest {

    private GetEnergyConsumptionOfAGridController controller;
    private House house;

    @BeforeEach
    public void StartUp() {

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        this.controller = new GetEnergyConsumptionOfAGridController(house);
    }

    @Test
    public void checkIfGridListIsEmpty_WithEmptyHouseGridList_ShouldReturnTrue() {
        //Act
        boolean result = controller.isHouseGridListEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGridListIsEmpty_WhenHouseGridListIsNotEmpty_ShouldReturnFalse() {
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);

        //Act
        boolean result = controller.isHouseGridListEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void getHouseGridListLength_WhenHouseGridListHasOneGrid_ShouldReturnOne() {
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);

        int expectedResult = 1;

        //Act
        int result = controller.getHouseGridListSize();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLength_WhenHouseGridHasNoGrids_ShouldReturnZero() {
        //Arrange
        int expectedResult = 0;

        //Act
        int result = controller.getHouseGridListSize();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void listHouseGridsTest_WithOneHouseGrid_ShouldShowListWithOneGrid() {
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);
        String expectedResult = "1 - Name: Grid 1\n";

        //Act
        String result = controller.getHouseGridListToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridName() {
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        house.addGrid(grid1);
        String expectedResult = "Grid 1";
        controller.getHouseGridByPosition(0);

        //Act
        String result = controller.getHouseGridName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInAInterval() {
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Room", 2, dimension);
        String name = "Lamp IKEA";
        DeviceType lampType = house.getDeviceType("Lamp");
        Device lamp = lampType.createDevice(name, room1);
        lamp.setAttributesDevType("Nominal Power", 20);

        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);
        house.addGrid(grid1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        int position = 0;
        controller.getHouseGridByPosition(position);

        double expectedResult = 12;

        //Act
        double result = controller.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }
}
