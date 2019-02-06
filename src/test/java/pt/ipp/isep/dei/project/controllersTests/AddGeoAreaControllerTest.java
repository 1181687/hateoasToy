package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaController;
import pt.ipp.isep.dei.project.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddGeoAreaControllerTest {
    private AddGeoAreaController controller;
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea cityOfPorto;

    @BeforeEach
    public void StartUp() {
        // List of Geographical Area Types
        GeographicalAreaTypeList geographicalAreaTypeList = new GeographicalAreaTypeList();

        // Geographical Area Types
        GeographicalAreaType city = new GeographicalAreaType("City");
        geographicalAreaTypeList.addTypeOfGeoAreaToTheList(city);

        // List of Geographical Areas
        geographicalAreaList = new GeographicalAreaList();

        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        cityOfPorto = new GeographicalArea("City of Porto", city, location, areaShape);

        // Controller
        controller = new AddGeoAreaController(geographicalAreaList, geographicalAreaTypeList);
    }

    @Test
    public void addNewGeoAreaPositiveTest() {
        // Arrange

        // Act
        boolean result = controller.addNewGeoArea(cityOfPorto);

        // Assert
        assertTrue(result);
    }

    @Test
    public void addNewGeoAreaNegativeTest() {
        // Arrange
        controller.addNewGeoArea(cityOfPorto);

        // Act
        boolean result = controller.addNewGeoArea(cityOfPorto);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getGeographicalAreaListTest() {
        // Arrange
        controller.addNewGeoArea(cityOfPorto);

        GeographicalAreaList expectedResult = geographicalAreaList;

        // Act
        GeographicalAreaList result = controller.getGeographicalAreaList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTGAListTest() {
        // Arrange
        List<String> expectedResult = Arrays.asList("City");

        //Act
        List<String> result = controller.getTGAList();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void createNewGeoAreaTest() {
        //Arrange
        GeographicalArea expectedResult = cityOfPorto;

        //Act
        GeographicalArea result = controller.createNewGeoArea("City of Porto", "City",
                41.178553, -8.608035, 111, 0.261, 0.249);

        //Assert
        assertEquals(expectedResult,result);
    }
}
