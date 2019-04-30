package pt.ipp.isep.dei.project.controllersTests;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.repositories.GeoAreaRepository;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;


public class AddNewGeographicalAreaControllerTest {
    private AddNewGeographicalAreaController controller;
    @InjectMocks
    private GeographicalAreaService geographicalAreaService;
    private GeographicalArea cityOfPorto;

    @Mock
    private GeoAreaRepository geoAreaRepository;
}

   /* @BeforeEach
    public void StartUp() {
        // List of Geographical Area Types
        GeographicalAreaTypeList geographicalAreaTypeList = new GeographicalAreaTypeList();

        // Geographical Area Types
        GeographicalAreaType city = new GeographicalAreaType("City");
        geographicalAreaTypeList.addTypeOfGeoAreaToTheList(city);

        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        cityOfPorto = new GeographicalArea("Porto", "City of Porto", city, location, areaShape);

        // Controller
        controller = new AddGeoAreaController(geographicalAreaService, geographicalAreaTypeList);
    }

    @Configuration
    static class Config {
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

        GeographicalAreaService expectedResult = geographicalAreaService;

        // Act
        GeographicalAreaService result = controller.getGeographicalAreaService();

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

 */