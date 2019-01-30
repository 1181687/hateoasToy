package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionOfAGridController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GetEnergyConsumptionOfAGridControllerTest {

    private GetEnergyConsumptionOfAGridController controller;
    private HouseGridList houseGridList;

    @BeforeEach
    public void StartUp(){
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geoAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geoAreaType, location, areaShape);

        //House
        RoomList roomList = new RoomList();
        this.houseGridList = new HouseGridList();
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        House houseEdificioB = new House(roomList, houseGridList, address, insertedGeoArea);

        this.controller = new GetEnergyConsumptionOfAGridController(houseEdificioB);
    }

    @Test
    public void checkIfGridListIsEmptyWithEmptyHouseGridListShouldReturnTrue(){
        //Act
        boolean result = controller.isHouseGridListEmpty();
        //Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGridListIsEmptyWhenHouseGridListIsNotEmptyShouldReturnFalse(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGrid(grid1);
        //Act
        boolean result = controller.isHouseGridListEmpty();
        //Assert
        assertFalse(result);
    }

    @Test
    public void getHouseGridListLengthWhenHouseGridListHasOneGridShouldReturnOne(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGrid(grid1);

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
    public void listHouseGridsTestWithOneHouseGridShouldShowListWithOneGrid(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGrid(grid1);
        String expectedResult = "1 - Name: Grid 1\n";
        //Act
        String result = controller.getHouseGridListToString();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridName(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGrid(grid1);
        String expectedResult = "Grid 1";
        controller.getHouseGridByPosition(0);
        //Act
        String result = controller.getHouseGridName();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getEnergyConsumptionInAInterval(){
        //Arrange
        Dimension dimension = new Dimension(25, 25, 25);
        Room room1 = new Room("Room", 2, dimension);

        DeviceSpecs deviceSpecs = new Lamp(25, 20);
        Device lamp = new Device("Lamp", room1, deviceSpecs);


        String gridName = "Grid 1";
        HouseGrid grid1 = new HouseGrid(gridName);
        grid1.attachRoom(room1);
        houseGridList.addHouseGrid(grid1);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        int position = 0;
        controller.getHouseGridByPosition(position);

        double expectedResult = 12;
        //Act
        double result = controller.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }
}
