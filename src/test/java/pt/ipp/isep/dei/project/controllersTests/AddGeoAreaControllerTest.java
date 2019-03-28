package pt.ipp.isep.dei.project.controllersTests;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.GeoAreaService;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
public class AddGeoAreaControllerTest {
    private AddGeoAreaController controller;
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea cityOfPorto;

    @Autowired
    private GeoAreaRepository geoAreaRepository;

    @Before
    public void StartUp() {
        GeoAreaService.getInstance().setGeoAreaRepository(geoAreaRepository);
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
        cityOfPorto = new GeographicalArea("Porto", "City of Porto", city, location, areaShape);

        // Controller
        controller = new AddGeoAreaController(geographicalAreaList, geographicalAreaTypeList);
    }

    @Test
    public void addNewGeoAreaPositiveTest() {
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
        List<String> result = controller.getGeoAreaList();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void createNewGeoAreaTest() {
        //Arrange
        GeographicalArea expectedResult = cityOfPorto;

        //Act
        Location location = new Location(41.178553, -8.608035, 111);
        GeographicalArea result = controller.createNewGeoArea("Porto", "City of Porto", "City",
                location, 0.261, 0.249);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void createNewLocationTest_ValidLocation() {
        //Arrange
        Location testLocation = new Location(45.234, 23.453, 250);

        Location expectedResult = testLocation;

        //Act
        Location result = controller.createLocation(45.234, 23.453, 250);

        //Assert
        assertEquals(expectedResult, result);
    }
}
