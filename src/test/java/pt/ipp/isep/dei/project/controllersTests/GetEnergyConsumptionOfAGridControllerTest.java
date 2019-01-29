package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetEnergyConsumptionOfAGridControllerTest {

    private GetNominalPowerOfAGridController controller;
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

        this.controller = new GetNominalPowerOfAGridController(houseEdificioB);
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
        houseGridList.addHouseGrid(grid1);
        //Act
        boolean result = controller.isGridListEmpty();
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
}
