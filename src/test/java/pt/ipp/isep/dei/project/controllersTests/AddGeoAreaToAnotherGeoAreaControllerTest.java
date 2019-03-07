package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AddGeoAreaToAnotherGeoAreaControllerTest {
    private AddGeoAreaToAnotherGeoAreaController controller;
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea cityOfPorto;
    private GeographicalArea parishOfBonfim;
    private GeographicalArea cityOfBraga;

    @BeforeEach
    public void StartUp() {
        // Geographical Area Types
        GeographicalAreaType city = new GeographicalAreaType("City");
        GeographicalAreaType parish = new GeographicalAreaType("Parish");

        // List of Geographical Areas
        geographicalAreaList = new GeographicalAreaList();

        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        cityOfPorto = new GeographicalArea("City of Porto", city, location, areaShape);
        geographicalAreaList.addGeoArea(cityOfPorto);
        Location location1 = new Location(40.178553, -8.208035, 112);
        AreaShape areaShape1 = new AreaShape(0.161, 0.149, location1);
        parishOfBonfim = new GeographicalArea("Parish of Bonfim", parish, location1, areaShape1);
        parishOfBonfim.setInsertedIn(cityOfPorto);
        geographicalAreaList.addGeoArea(parishOfBonfim);
        Location location2 = new Location(21.178553, -7.608035, 100);
        AreaShape areaShape2 = new AreaShape(0.191, 0.249, location2);
        cityOfBraga = new GeographicalArea("City of Braga", city, location2, areaShape2);

        // Controller
        controller = new AddGeoAreaToAnotherGeoAreaController(geographicalAreaList);
    }

    @Test
    void getListToStringTest() {
        // Arrange
        String expectResult = "1 - Name: City of Porto, Type: City, Latitude: 41.178553, Longitude: -8.608035\n" +
                "2 - Name: Parish of Bonfim, Type: Parish, Latitude: 40.178553, Longitude: -8.208035, Inserted in: City City of Porto\n";

        // Act
        String result = controller.getListToString(true);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeoAreaInTheListTest() {
        // Arrange
        GeographicalArea expectedResult = parishOfBonfim;

        // Act
        GeographicalArea result = controller.getGeoAreaInTheList(1);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfGeoAreaDoesntHaveAnInsertedAreaPositiveTest() {
        // Act
        boolean result = controller.isGeoAreaInsertedinAnotherArea(cityOfPorto);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGeoAreaDoesntHaveAnInsertedAreaNegativeTest() {
        // Act
        boolean result = controller.isGeoAreaInsertedinAnotherArea(parishOfBonfim);

        // Assert
        assertFalse(result);
    }

    @Test
    public void addGeoAreaInASpecificPositionTest() {
        // Arrange
        controller.addGeoAreaInASpecificPosition(0, cityOfBraga);

        GeographicalArea expectedResult = cityOfBraga;

        // Act
        GeographicalArea result = geographicalAreaList.getGeoAreaList().get(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void removeGeoAreaTest() {
        // Arrange
        controller.removeGeoArea(cityOfPorto);

        GeographicalArea expectedResult = parishOfBonfim;

        // Act
        GeographicalArea result = geographicalAreaList.getGeoAreaList().get(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSizeList() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = controller.getListSize();

        // Assert
        assertEquals(expectedResult, result);
    }
}