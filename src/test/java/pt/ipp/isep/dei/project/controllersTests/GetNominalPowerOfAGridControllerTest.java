package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;


public class GetNominalPowerOfAGridControllerTest {

    private GetNominalPowerOfAGridController controller;
    private HouseGridList houseGridList;
    @BeforeEach
    public void StartUp(){
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeoAreaType geoAreaType = new GeoAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geoAreaType, location, areaShape);

        //House
        RoomList roomList = new RoomList();
        this.houseGridList = new HouseGridList();
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        House houseEdificioB = new House(roomList, houseGridList, address, insertedGeoArea);

        this.controller = new GetNominalPowerOfAGridController(houseEdificioB);
    }

    @Test
    public void checkIfGridListIsEmptyWithEmptyHouseGridListShouldReturnTrue(){
        //Act
        boolean result = controller.checkIfGridListIsEmpty();
        //Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGridListIsEmptyWhenHouseGridListIsNotEmptyShouldReturnFalse(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGridToTheList(grid1);
        //Act
        boolean result = controller.checkIfGridListIsEmpty();
        //Assert
        assertFalse(result);
    }

    @Test
    public void listHouseGridsTestWithOneHouseGridShouldShowListWithOneGrid(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGridToTheList(grid1);
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
        houseGridList.addHouseGridToTheList(grid1);
        houseGridList.addHouseGridToTheList(grid2);

        int expectedResult = 2;
        //Act
        int result = controller.getHouseGridListLength();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridListLengthWhenHouseGridListHasOneGridShouldReturnOne(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGridToTheList(grid1);

        int expectedResult = 1;
        //Act
        int result = controller.getHouseGridListLength();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridListLengthWhenHouseGridHasNoGridsShouldReturnZero(){
        //Arrange
        int expectedResult = 0;
        //Act
        int result = controller.getHouseGridListLength();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getHouseGridTotalNominalPowerCalculatesTotalNominalPowerOfHGWithTwoDevicesShouldReturn15(){
        //Arrange
        HouseGrid grid1 = new HouseGrid("Grid 1");
        houseGridList.addHouseGridToTheList(grid1);

        Dimensions dimensions = new Dimensions(2,5,10);
        Room room1 = new Room("Quarto",1,dimensions);
        DeviceSpecs specs = new Fridge(25, 50, 5000, 7.5);
        Device fridge1 = new Device("FridgeA", room1, specs);
        Device fridge2 = new Device("FridgeB", room1, specs);

        room1.addDevice(fridge1);
        room1.addDevice(fridge2);
        grid1.attachRoom(room1);

        controller.getHouseGridByPosition(0);

        double expectedResult = 15;
        //Act
        double result = controller.getHouseGridTotalNominalPower();

        //Assert
        assertEquals(expectedResult,result,0.00001);
    }
}
