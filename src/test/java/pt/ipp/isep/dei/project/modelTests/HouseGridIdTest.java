package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseGridIdTest {

    private HouseGridId houseGridId;
    private HouseGrid houseGrid;


    @Test
    public void testGetHouseGridId_grid() {
        //Arrange
        String expectedResult = "grid";
        //Act
        String result = houseGridId.getHousegridId();
        //Assert
        assertEquals(expectedResult, result);

    }

}