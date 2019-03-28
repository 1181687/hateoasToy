package pt.ipp.isep.dei.project.controllersTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaTypeController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import static org.junit.Assert.*;

public class AddGeographicalAreaTypeControllerTest {
    private AddGeoAreaTypeController controller;
    private GeographicalAreaTypeList geographicalAreaTypeList;

    @Before
    public void StartUp() {
        // List of Geographical Area Types
        geographicalAreaTypeList = new GeographicalAreaTypeList();

        // Geographical Area Types
        GeographicalAreaType city = new GeographicalAreaType("City");
        geographicalAreaTypeList.addTypeOfGeoAreaToTheList(city);

        // Controller
        controller = new AddGeoAreaTypeController(geographicalAreaTypeList);
    }

    @Test
    public void addTypeOfGeoAreaToTheListPositiveTest() {
        // Act
        boolean result = controller.addTypeOfGeoAreaToTheList("Region");

        // Assert
        assertTrue(result);
    }

    @Test
    public void addTypeOfGeoAreaToTheListNegativeTest() {
        // Act
        boolean result = controller.addTypeOfGeoAreaToTheList("City");

        // Assert
        assertFalse(result);
    }

    @Test
    public void getListTest() {
        //Arrange
        GeographicalAreaTypeList expectedResult = geographicalAreaTypeList;

        // Act
        GeographicalAreaTypeList result = controller.getList();

        // Assert
        assertEquals(expectedResult, result);
    }

}
